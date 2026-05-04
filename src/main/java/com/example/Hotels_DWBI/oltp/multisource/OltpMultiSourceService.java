package com.example.Hotels_DWBI.oltp.multisource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OltpMultiSourceService {

    /** Path/query entity name → Oracle table name (same schema assumed for all OLTP DBs). */
    private static final Map<String, String> ENTITY_TABLE = Map.ofEntries(
            Map.entry("guests", "GUESTS"),
            Map.entry("countries", "COUNTRIES"),
            Map.entry("cities", "CITIES"),
            Map.entry("hotels", "HOTELS"),
            Map.entry("rooms", "ROOMS"),
            Map.entry("room-types", "ROOM_TYPES"),
            Map.entry("reservations", "RESERVATIONS"),
            Map.entry("payments", "PAYMENTS"),
            Map.entry("services", "SERVICES"),
            Map.entry("reviews", "REVIEWS"),
            Map.entry("reservation-rooms", "RESERVATION_ROOMS"),
            Map.entry("reservation-services", "RESERVATION_SERVICES")
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
                "oltp-user", "oltp_user",
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
                        buildLabel(key)
                ))
                .collect(Collectors.toList());
    }

    /** Available entity keys for dropdowns (e.g. guests, room-types). */
    public Map<String, String> listEntityAliases() {
        return Collections.unmodifiableMap(ENTITY_TABLE);
    }

    public List<Map<String, Object>> fetchRows(String sourceKey, String entity, int limit) {
        String table = ENTITY_TABLE.get(entity.toLowerCase(Locale.ROOT));
        if (table == null) {
            throw new IllegalArgumentException("Unknown entity: " + entity);
        }
        JdbcTemplate jdbc = jdbcBySource.get(sourceKey);
        if (jdbc == null) {
            throw new IllegalArgumentException("Unknown source: " + sourceKey);
        }
        int safeLimit = Math.max(1, Math.min(limit, 500));
        String sql = "SELECT * FROM " + table + " FETCH FIRST " + safeLimit + " ROWS ONLY";
        return jdbc.queryForList(sql);
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
                return "OLTP orclpdb — user oltp_user";
            case "oltp-s3":
                return "OLTP orclpdb — user s3";
            case "oltp-ue-s1":
                return "OLTP-UE mops_bd_ue — user s1";
            case "oltp-ue-s2":
                return "OLTP-UE mops_bd_ue — user s2";
            default:
                return key;
        }
    }
}
