package com.example.Hotels_DWBI.oltp.multisource;

/** Metadata for one OLTP connection (user / JDBC URL). */
public class OltpSourceInfo {

    private final String key;
    private final String username;
    private final String jdbcUrl;
    private final String label;

    public OltpSourceInfo(String key, String username, String jdbcUrl, String label) {
        this.key = key;
        this.username = username;
        this.jdbcUrl = jdbcUrl;
        this.label = label;
    }

    public String getKey() {
        return key;
    }

    public String getUsername() {
        return username;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getLabel() {
        return label;
    }
}
