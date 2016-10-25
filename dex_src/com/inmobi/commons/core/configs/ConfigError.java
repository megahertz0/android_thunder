package com.inmobi.commons.core.configs;

class ConfigError {
    private ErrorCode a;
    private String b;

    public enum ErrorCode {
        NETWORK_ERROR,
        CONFIG_SERVER_INTERNAL_ERROR,
        PARSING_ERROR
    }

    public ConfigError(ErrorCode errorCode, String str) {
        this.a = errorCode;
        this.b = str;
    }

    public ErrorCode a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }
}
