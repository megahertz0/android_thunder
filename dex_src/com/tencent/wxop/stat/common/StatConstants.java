package com.tencent.wxop.stat.common;

import java.io.File;

public class StatConstants {
    public static String DATABASE_NAME = null;
    public static final String LOG_TAG = "MtaSDK";
    public static final String MTA_COOPERATION_TAG = "wxop_";
    public static final String MTA_REPORT_FULL_URL = "http://pingma.qq.com:80/mstat/report";
    public static final String MTA_SERVER = "pingma.qq.com:80";
    public static final String MTA_SERVER_HOST = "pingma.qq.com";
    public static final int MTA_SERVER_PORT = 80;
    public static final String MTA_STAT_URL = "/mstat/report";
    public static final String MTA_STORAGE_PRE_TAG;
    public static final int SDK_ONLINE_CONFIG_TYPE = 1;
    public static final int STAT_DB_VERSION = 3;
    public static final int USER_ONLINE_CONFIG_TYPE = 2;
    public static final String VERSION = "2.0.3";
    public static final int XG_PRO_VERSION = 1;

    static {
        MTA_STORAGE_PRE_TAG = new StringBuilder("tencent.mta").append(File.separator).append("datawxop_").toString();
        DATABASE_NAME = "wxop_tencent_analysis.db";
    }
}
