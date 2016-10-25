package com.xunlei.analytics.dbstore;

public class AnalyticsConstant {
    public static String API_PRE_URL = null;
    public static String API_RELEASE_URL = null;
    public static String API_TEST_URL = null;
    public static final String DB_NAME = "analytics.db";
    public static final int DB_VERSION = 1;
    public static final int SERVER_PRE_MODE = 2;
    public static final int SERVER_RELEASE_MODE = 3;
    public static final int SERVER_TEST_MODE = 1;
    public static final String STORAGE_DB_TABLE_NAME = "analytics";

    static {
        API_PRE_URL = "http://pre.api.tw06.xlmc.sandai.net/api/stat/rt";
        API_TEST_URL = "https://test.api.xlmc.sandai.net/api/stat/rt";
        API_RELEASE_URL = "http://etl.xlmc.sandai.net/api/stat/rt";
    }
}
