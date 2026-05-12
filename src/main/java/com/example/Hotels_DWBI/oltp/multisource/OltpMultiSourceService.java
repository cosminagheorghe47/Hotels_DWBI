package com.example.Hotels_DWBI.oltp.multisource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OltpMultiSourceService {

    private static final String READ_ONLY_GLOBAL_SOURCE = "oltp-user";

    private static final Map<String, String> ENTITY_TABLE = Map.ofEntries(
            Map.entry("guests", "GUESTS"),
            Map.entry("countries", "COUNTRIES"),
            Map.entry("cities", "CITIES"),
            Map.entry("hotels", "HOTELS"),
            Map.entry("rooms", "ROOMS"),
            Map.entry("room-types", "ROOM_TYPES"),
            Map.entry("reservations", "RESERVATIONS"),
            Map.entry("payments", "PAYMENTS"),
            Map.entry("reviews", "REVIEWS"),
            Map.entry("reservation-rooms", "RESERVATION_ROOMS"),
            Map.entry("reservation-services", "RESERVATION_SERVICES")
    );

    private static final Map<String, String> SG_READ_VIEW = Map.ofEntries(
            Map.entry("guests", "V_GUESTS_GLOBAL"),
            Map.entry("countries", "V_COUNTRIES_GLOBAL"),
            Map.entry("cities", "V_CITIES_GLOBAL"),
            Map.entry("hotels", "V_HOTELS_GLOBAL"),
            Map.entry("rooms", "V_ROOMS_GLOBAL"),
            Map.entry("room-types", "V_ROOM_TYPES_REMOTE"),
            Map.entry("reservations", "V_RESERVATIONS_GLOBAL"),
            Map.entry("payments", "V_PAYMENTS_GLOBAL"),
            Map.entry("reviews", "V_REVIEWS_GLOBAL"),
            Map.entry("reservation-rooms", "V_RESERVATION_ROOMS_GLOBAL"),
            Map.entry("reservation-services", "V_RESERVATION_SERVICES_GLOBAL")
    );

    private final Map<String, JdbcTemplate> jdbcBySource;
    private final Map<String, DataSource> dataSourceBySource;
    private final Map<String, String> usernameBySource;

    public OltpMultiSourceService(
            JdbcTemplate oltpUserJdbcTemplate,
            JdbcTemplate oltpS3JdbcTemplate,
            JdbcTemplate oltpUeS1JdbcTemplate,
            JdbcTemplate oltpUeS2JdbcTemplate,
            @Qualifier("oltpUserDataSource") DataSource oltpUserDataSource,
            @Qualifier("oltpS3DataSource") DataSource oltpS3DataSource,
            @Qualifier("oltpUeS1DataSource") DataSource oltpUeS1DataSource,
            @Qualifier("oltpUeS2DataSource") DataSource oltpUeS2DataSource
    ) {
        this.jdbcBySource = Map.of(
                "oltp-user", oltpUserJdbcTemplate,
                "oltp-s3", oltpS3JdbcTemplate,
                "oltp-ue-s1", oltpUeS1JdbcTemplate,
                "oltp-ue-s2", oltpUeS2JdbcTemplate
        );
        this.dataSourceBySource = Map.of(
                "oltp-user", oltpUserDataSource,
                "oltp-s3", oltpS3DataSource,
                "oltp-ue-s1", oltpUeS1DataSource,
                "oltp-ue-s2", oltpUeS2DataSource
        );
        this.usernameBySource = Map.of(
                "oltp-user", "sg",
                "oltp-s3", "s3",
                "oltp-ue-s1", "s1",
                "oltp-ue-s2", "s2"
        );
    }

    public List<OltpSourceInfo> listSources() {
        return jdbcBySource.keySet().stream()
                .sorted()
                .map(key -> new OltpSourceInfo(
                        key,
                        usernameBySource.getOrDefault(key, key),
                        extractJdbcUrl(dataSourceBySource.get(key)),
                        buildLabel(key),
                        scopeCode(key),
                        scopeDescription(key)
                ))
                .collect(Collectors.toList());
    }

    public Map<String, Object> getCrudSchema() {
        Map<String, Object> out = new LinkedHashMap<>();
        for (Map.Entry<String, OltpCrudSchema.EntitySpec> e : OltpCrudSchema.all().entrySet()) {
            OltpCrudSchema.EntitySpec spec = e.getValue();
            Map<String, Object> one = new LinkedHashMap<>();
            one.put("table", spec.table());
            one.put("pkColumn", spec.pkColumn());
            one.put("dataColumns", spec.dataColumns());
            one.put("identityGeneratedPk", spec.identityGeneratedPk());
            out.put(e.getKey(), one);
        }
        return out;
    }

    public Map<String, String> listEntityAliases() {
        return Collections.unmodifiableMap(ENTITY_TABLE);
    }

    public List<Map<String, Object>> fetchRows(String sourceKey, String entity, int limit) {
        String table = resolveReadTable(sourceKey, entity);
        if (table == null) {
            throw new IllegalArgumentException("Unknown entity: " + entity);
        }
        JdbcTemplate jdbc = jdbcBySource.get(sourceKey.toLowerCase(Locale.ROOT));
        if (jdbc == null) {
            throw new IllegalArgumentException("Unknown source: " + sourceKey);
        }
        int safeLimit = Math.max(1, Math.min(limit, 500));
        String sql = "SELECT * FROM " + table + " FETCH FIRST " + safeLimit + " ROWS ONLY";
        return jdbc.queryForList(sql);
    }

    public int createRow(String sourceKey, String entity, Map<String, Object> body) {
        JdbcTemplate jdbc = resolveMutableJdbc(sourceKey);
        OltpCrudSchema.EntitySpec spec = resolveCrudSpec(entity);
        Map<String, String> columnTypes = loadColumnTypes(jdbc, spec.table());
        PreparedValues prepared = prepareValues(spec, body, true, columnTypes);
        addGeneratedPrimaryKeyIfNeeded(jdbc, spec, columnTypes, prepared);

        String columns = String.join(", ", prepared.columns);
        String placeholders = prepared.columns.stream()
                .map(column -> "?")
                .collect(Collectors.joining(", "));
        String sql = "INSERT INTO " + spec.table() + " (" + columns + ") VALUES (" + placeholders + ")";
        return jdbc.update(sql, prepared.values.toArray());
    }

    public int updateRow(String sourceKey, String entity, String id, Map<String, Object> body) {
        JdbcTemplate jdbc = resolveMutableJdbc(sourceKey);
        OltpCrudSchema.EntitySpec spec = resolveCrudSpec(entity);
        Map<String, String> columnTypes = loadColumnTypes(jdbc, spec.table());
        PreparedValues prepared = prepareValues(spec, body, false, columnTypes);

        String assignments = prepared.columns.stream()
                .map(column -> column + " = ?")
                .collect(Collectors.joining(", "));
        List<Object> args = new ArrayList<>(prepared.values);
        args.add(convertValue(spec.pkColumn(), id, columnTypes));

        String sql = "UPDATE " + spec.table() + " SET " + assignments + " WHERE " + spec.pkColumn() + " = ?";
        return jdbc.update(sql, args.toArray());
    }

    public int deleteRow(String sourceKey, String entity, String id) {
        JdbcTemplate jdbc = resolveMutableJdbc(sourceKey);
        OltpCrudSchema.EntitySpec spec = resolveCrudSpec(entity);
        Map<String, String> columnTypes = loadColumnTypes(jdbc, spec.table());

        String sql = "DELETE FROM " + spec.table() + " WHERE " + spec.pkColumn() + " = ?";
        return jdbc.update(sql, convertValue(spec.pkColumn(), id, columnTypes));
    }

    private JdbcTemplate resolveMutableJdbc(String sourceKey) {
        if (sourceKey == null) {
            throw new IllegalArgumentException("Unknown source: null");
        }
        String normalizedSource = sourceKey.toLowerCase(Locale.ROOT);
        if (READ_ONLY_GLOBAL_SOURCE.equals(normalizedSource)
                || "sg".equals(normalizedSource)
                || "oltp_user".equals(normalizedSource)) {
            throw new IllegalArgumentException("SG (oltp_user) este read-only în Multi DB Explorer.");
        }
        JdbcTemplate jdbc = jdbcBySource.get(normalizedSource);
        if (jdbc == null) {
            throw new IllegalArgumentException("Unknown source: " + sourceKey);
        }
        return jdbc;
    }

    private OltpCrudSchema.EntitySpec resolveCrudSpec(String entity) {
        OltpCrudSchema.EntitySpec spec = OltpCrudSchema.resolve(entity);
        if (spec == null) {
            throw new IllegalArgumentException("Unknown entity: " + entity);
        }
        return spec;
    }

    private String resolveReadTable(String sourceKey, String entity) {
        if (entity == null) {
            return null;
        }
        String normalizedEntity = entity.toLowerCase(Locale.ROOT);
        String normalizedSource = sourceKey == null ? "" : sourceKey.toLowerCase(Locale.ROOT);
        if (READ_ONLY_GLOBAL_SOURCE.equals(normalizedSource)
                || "sg".equals(normalizedSource)
                || "oltp_user".equals(normalizedSource)) {
            return SG_READ_VIEW.getOrDefault(normalizedEntity, ENTITY_TABLE.get(normalizedEntity));
        }
        if ("guests".equals(normalizedEntity)) {
            return "GUESTS_DATA";
        }
        return ENTITY_TABLE.get(normalizedEntity);
    }

    private Map<String, String> loadColumnTypes(JdbcTemplate jdbc, String table) {
        return jdbc.query(
                "SELECT COLUMN_NAME, DATA_TYPE FROM USER_TAB_COLUMNS WHERE TABLE_NAME = ?",
                rs -> {
                    Map<String, String> out = new HashMap<>();
                    while (rs.next()) {
                        out.put(
                                rs.getString("COLUMN_NAME").toUpperCase(Locale.ROOT),
                                rs.getString("DATA_TYPE").toUpperCase(Locale.ROOT)
                        );
                    }
                    return out;
                },
                table.toUpperCase(Locale.ROOT)
        );
    }

    private void addGeneratedPrimaryKeyIfNeeded(
            JdbcTemplate jdbc,
            OltpCrudSchema.EntitySpec spec,
            Map<String, String> columnTypes,
            PreparedValues prepared
    ) {
        if (containsColumn(prepared.columns, spec.pkColumn())) {
            return;
        }
        if (!isNumericColumn(spec.pkColumn(), columnTypes)) {
            throw new IllegalArgumentException(
                    "Cheia primara " + spec.pkColumn() + " trebuie trimisa pentru " + spec.table()
            );
        }

        BigDecimal nextId = nextNumericPrimaryKey(jdbc, spec.table(), spec.pkColumn());
        prepared.columns.add(0, spec.pkColumn());
        prepared.values.add(0, nextId);
    }

    private BigDecimal nextNumericPrimaryKey(JdbcTemplate jdbc, String table, String pkColumn) {
        BigDecimal nextId = jdbc.queryForObject(
                "SELECT COALESCE(MAX(" + pkColumn + "), 0) + 1 FROM " + table,
                BigDecimal.class
        );
        if (nextId == null) {
            throw new IllegalArgumentException("Nu s-a putut genera cheia primara pentru " + table + ".");
        }
        return nextId;
    }

    private boolean isNumericColumn(String column, Map<String, String> columnTypes) {
        String columnType = columnTypes.getOrDefault(column.toUpperCase(Locale.ROOT), "");
        return columnType.contains("NUMBER") || columnType.contains("FLOAT") || columnType.contains("DOUBLE");
    }

    private boolean containsColumn(List<String> columns, String wanted) {
        String normalizedWanted = wanted.toUpperCase(Locale.ROOT);
        return columns.stream().anyMatch(column -> column.toUpperCase(Locale.ROOT).equals(normalizedWanted));
    }

    private PreparedValues prepareValues(
            OltpCrudSchema.EntitySpec spec,
            Map<String, Object> body,
            boolean insert,
            Map<String, String> columnTypes
    ) {
        if (body == null || body.isEmpty()) {
            throw new IllegalArgumentException("Nu exista valori pentru salvare");
        }

        Map<String, String> allowedColumns = new HashMap<>();
        for (String column : spec.dataColumns()) {
            allowedColumns.put(column.toUpperCase(Locale.ROOT), column);
        }
        if (insert) {
            allowedColumns.put(spec.pkColumn().toUpperCase(Locale.ROOT), spec.pkColumn());
        }

        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        String pkUpper = spec.pkColumn().toUpperCase(Locale.ROOT);

        for (Map.Entry<String, Object> entry : body.entrySet()) {
            String incomingColumn = entry.getKey();
            String normalizedColumn = incomingColumn.toUpperCase(Locale.ROOT);
            String allowedColumn = allowedColumns.get(normalizedColumn);

            if (allowedColumn == null) {
                if (!insert && pkUpper.equals(normalizedColumn)) {
                    continue;
                }
                throw new IllegalArgumentException("Column is not editable for " + spec.table() + ": " + incomingColumn);
            }

            Object value = entry.getValue();
            if (isBlank(value)) {
                continue;
            }

            columns.add(allowedColumn);
            values.add(convertValue(allowedColumn, value, columnTypes));
        }

        if (columns.isEmpty()) {
            throw new IllegalArgumentException("Nu exista valori valide pentru salvare");
        }
        return new PreparedValues(columns, values);
    }

    private static boolean isBlank(Object value) {
        return value == null || (value instanceof String text && text.trim().isEmpty());
    }

    private Object convertValue(String column, Object value, Map<String, String> columnTypes) {
        String columnType = columnTypes.getOrDefault(column.toUpperCase(Locale.ROOT), "");
        if (value == null) {
            return null;
        }
        if (!(value instanceof String)) {
            return value;
        }

        String text = ((String) value).trim();
        if (text.isEmpty()) {
            return null;
        }

        try {
            if (columnType.contains("NUMBER") || columnType.contains("FLOAT") || columnType.contains("DOUBLE")) {
                return new BigDecimal(text);
            }
            if (columnType.equals("DATE")) {
                return parseDateOrTimestamp(text);
            }
            if (columnType.startsWith("TIMESTAMP")) {
                return Timestamp.valueOf(parseLocalDateTime(text));
            }
            return text;
        } catch (NumberFormatException | DateTimeParseException ex) {
            throw new IllegalArgumentException("Valoare invalida pentru coloana " + column + ": " + text);
        }
    }

    private Object parseDateOrTimestamp(String text) {
        if (text.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return Date.valueOf(LocalDate.parse(text));
        }
        return Timestamp.valueOf(parseLocalDateTime(text));
    }

    private LocalDateTime parseLocalDateTime(String text) {
        String normalized = text.replace(' ', 'T');
        try {
            return LocalDateTime.parse(normalized);
        } catch (DateTimeParseException ex) {
            return OffsetDateTime.parse(normalized).toLocalDateTime();
        }
    }

    private static String extractJdbcUrl(DataSource ds) {
        if (ds instanceof HikariDataSource hk) {
            return hk.getJdbcUrl() != null ? hk.getJdbcUrl() : "";
        }
        return "";
    }

    private static String buildLabel(String key) {
        switch (key) {
            case "oltp-user":
                return "OLTP orclpdb — user SG";
            case "oltp-s3":
                return "OLTP orclpdb — user S3";
            case "oltp-ue-s1":
                return "OLTP-UE mops_bd_ue — user S1";
            case "oltp-ue-s2":
                return "OLTP-UE mops_bd_ue — user S2";
            default:
                return key;
        }
    }

    private static String scopeCode(String key) {
        return switch (key) {
            case "oltp-user" -> "SG";
            case "oltp-s3" -> "S3";
            case "oltp-ue-s1" -> "S1";
            case "oltp-ue-s2" -> "S2";
            default -> "?";
        };
    }

    private static String scopeDescription(String key) {
        return switch (key) {
            case "oltp-ue-s1" -> "S1 — hoteluri din România (country = Romania).";
            case "oltp-ue-s2" -> "S2 — Europa: Germania, Franta, Italia, Spania, Austria (REGION = EU).";
            case "oltp-s3" -> "S3 — internațional, în afara UE.";
            case "oltp-user" -> "SG — global: toate datele.";
            default -> "";
        };
    }

    private static final class PreparedValues {
        private final List<String> columns;
        private final List<Object> values;

        private PreparedValues(List<String> columns, List<Object> values) {
            this.columns = columns;
            this.values = values;
        }
    }
}
