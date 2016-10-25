package com.baidu.mobad.feeds;

public enum NativeErrorCode {
    UNKNOWN,
    LOAD_AD_FAILED,
    INTERNAL_ERROR,
    CONFIG_ERROR;

    static {
        UNKNOWN = new NativeErrorCode("UNKNOWN", 0);
        LOAD_AD_FAILED = new NativeErrorCode("LOAD_AD_FAILED", 1);
        INTERNAL_ERROR = new NativeErrorCode("INTERNAL_ERROR", 2);
        CONFIG_ERROR = new NativeErrorCode("CONFIG_ERROR", 3);
        a = new NativeErrorCode[]{UNKNOWN, LOAD_AD_FAILED, INTERNAL_ERROR, CONFIG_ERROR};
    }
}
