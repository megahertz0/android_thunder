package com.baidu.mobad.chuilei;

public enum BaiduChuileiErrorCode {
    UNKNOWN,
    LOAD_AD_FAILED,
    INTERNAL_ERROR,
    CONFIG_ERROR;

    static {
        UNKNOWN = new BaiduChuileiErrorCode("UNKNOWN", 0);
        LOAD_AD_FAILED = new BaiduChuileiErrorCode("LOAD_AD_FAILED", 1);
        INTERNAL_ERROR = new BaiduChuileiErrorCode("INTERNAL_ERROR", 2);
        CONFIG_ERROR = new BaiduChuileiErrorCode("CONFIG_ERROR", 3);
        a = new BaiduChuileiErrorCode[]{UNKNOWN, LOAD_AD_FAILED, INTERNAL_ERROR, CONFIG_ERROR};
    }
}
