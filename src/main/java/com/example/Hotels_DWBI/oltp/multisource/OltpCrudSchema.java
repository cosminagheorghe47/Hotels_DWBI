package com.example.Hotels_DWBI.oltp.multisource;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Coloane CRUD pentru explorator; trebuie aliniat la {@link OltpMultiSourceService} (alias → tabel).
 */
public final class OltpCrudSchema {

    public record EntitySpec(
            String table,
            String pkColumn,
            List<String> dataColumns,
            boolean identityGeneratedPk
    ) {
        public EntitySpec(String table, String pkColumn, List<String> dataColumns) {
            this(table, pkColumn, dataColumns, true);
        }
    }

    private static final Map<String, EntitySpec> BY_ALIAS = build();

    private OltpCrudSchema() {
    }

    private static Map<String, EntitySpec> build() {
        Map<String, EntitySpec> m = new LinkedHashMap<>();
        m.put("guests", new EntitySpec("GUESTS_DATA", "GUEST_ID", List.of(
                "FIRST_NAME", "LAST_NAME", "EMAIL", "PHONE")));
        m.put("countries", new EntitySpec("COUNTRIES", "COUNTRY_ID", List.of(
                "COUNTRY_NAME", "REGION")));
        m.put("cities", new EntitySpec("CITIES", "CITY_ID", List.of(
                "CITY_NAME", "COUNTRY_ID")));
        m.put("hotels", new EntitySpec("HOTELS", "HOTEL_ID", List.of(
                "NAME", "STARS", "CITY_ID", "ADDRESS", "PHONE", "EMAIL", "CREATED_AT")));
        m.put("rooms", new EntitySpec("ROOMS", "ROOM_ID", List.of(
                "HOTEL_ID", "ROOM_TYPE_ID", "ROOM_NUMBER", "FLOOR_NO", "STATUS")));
        m.put("room-types", new EntitySpec("ROOM_TYPES", "ROOM_TYPE_ID", List.of(
                "NAME", "MAX_ADULTS", "MAX_CHILDREN", "BASE_PRICE_PER_NIGHT", "CURRENCY")));
        m.put("reservations", new EntitySpec("RESERVATIONS", "RESERVATION_ID", List.of(
                "GUEST_ID", "HOTEL_ID", "CHECK_IN_DATE", "CHECK_OUT_DATE",
                "ADULTS_COUNT", "CHILDREN_COUNT", "BOOKING_CHANNEL", "STATUS",
                "CREATED_AT", "CANCELLED_AT", "NOTES")));
        m.put("payments", new EntitySpec("PAYMENTS", "PAYMENT_ID", List.of(
                "RESERVATION_ID", "PAYMENT_DATE", "AMOUNT", "CURRENCY",
                "METHOD", "STATUS", "TRANSACTION_REF")));
        m.put("reviews", new EntitySpec("REVIEWS", "REVIEW_ID", List.of(
                "RESERVATION_ID", "RATING", "COMMENT_REVIEW", "CREATED_AT")));
        m.put("reservation-rooms", new EntitySpec("RESERVATION_ROOMS", "RESERVATION_ROOM_ID", List.of(
                "RESERVATION_ID", "ROOM_ID", "PRICE_PER_NIGHT", "DISCOUNT_AMOUNT", "FINAL_PRICE_PER_NIGHT")));
        m.put("reservation-services", new EntitySpec("RESERVATION_SERVICES", "RESERVATION_SERVICE_ID", List.of(
                "RESERVATION_ID", "SERVICE_ID", "QUANTITY", "UNIT_PRICE_AT_BOOKING", "LINE_TOTAL")));
        return Collections.unmodifiableMap(m);
    }

    public static EntitySpec resolve(String entityAlias) {
        if (entityAlias == null) {
            return null;
        }
        return BY_ALIAS.get(entityAlias.toLowerCase(Locale.ROOT));
    }

    public static Map<String, EntitySpec> all() {
        return BY_ALIAS;
    }
}
