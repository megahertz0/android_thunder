package com.tencent.stat.common;

public class StatConstants {
    public static final String MTA_COOPERATION_TAG = "";
    public static final String MTA_SERVER = "pingma.qq.com:80";
    public static final String MTA_STAT_URL = "/mstat/report";
    public static final int SDK_ONLINE_CONFIG_TYPE = 1;
    public static final int STAT_DB_VERSION = 3;
    public static final int USER_ONLINE_CONFIG_TYPE = 2;
    public static final String VERSION = "1.6.2";
    static String a;

    static {
        a = "tencent_analysis.db";
    }
}
