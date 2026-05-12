package com.example.Hotels_DWBI.oltp.multisource;

public class OltpSourceInfo {

    private final String key;
    private final String username;
    private final String jdbcUrl;
    private final String label;
    private final String scopeCode;
    private final String scopeDescription;

    public OltpSourceInfo(
            String key,
            String username,
            String jdbcUrl,
            String label,
            String scopeCode,
            String scopeDescription
    ) {
        this.key = key;
        this.username = username;
        this.jdbcUrl = jdbcUrl;
        this.label = label;
        this.scopeCode = scopeCode;
        this.scopeDescription = scopeDescription;
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

    public String getScopeCode() {
        return scopeCode;
    }

    public String getScopeDescription() {
        return scopeDescription;
    }
}
