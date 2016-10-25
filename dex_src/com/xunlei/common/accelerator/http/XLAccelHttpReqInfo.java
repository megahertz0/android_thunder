package com.xunlei.common.accelerator.http;

public class XLAccelHttpReqInfo {
    public static final String GET_ACCELINFO_INTERFACE = "/v2/bandwidth";
    public static final String GET_TRY_ACCELINFO_INTERFACE = "/v2/query_try_info";
    public static int HTTP_CLIENT_SOCKET_TIMEOUT = 0;
    public static final String KEEP_ALIVE = "/v2/keepalive";
    public static final String START_ACCEL = "/v2/upgrade";
    public static final String STOP_ACCEL = "/v2/recover";
    public static String XLA_BASE_PREFIX = null;
    private static final int XLA_KEEP_ALIVE_TIME_DEBUG = 60000;
    private static final int XLA_KEEP_ALIVE_TIME_RELEASE = 3600000;
    private static final String XLA_PORTAL_DEBUG = "http://10.10.200.16:81/v2/queryportal";
    private static final String XLA_PORTAL_RELEASE = "http://api.portal.swjsq.vip.xunlei.com:81/v2/queryportal";
    private static final String XLA_PORTAL_RELEASE_SECOND = "http://api2.portal.swjsq.vip.xunlei.com:81/v2/queryportal";
    private static final long XLA_RECOVER_QUERY_COUNT_TIME_DEBUG = 120000;
    private static final long XLA_RECOVER_QUERY_COUNT_TIME_RELEASE = 900000;
    private static final int XLA_TRY_INIT_STATUS_TIME_DEBUG = 120000;
    private static final int XLA_TRY_INIT_STATUS_TIME_RELEASE = 1800000;
    private static final int XLA_USER_TRY_TIME_DEBUG = 1;
    private static final int XLA_USER_TRY_TIME_RELEASE = 10;
    private static boolean debug;

    static {
        debug = false;
        XLA_BASE_PREFIX = "http://";
        HTTP_CLIENT_SOCKET_TIMEOUT = 10000;
    }

    public static int getXLKeepAliveTime() {
        return debug ? XLA_KEEP_ALIVE_TIME_DEBUG : XLA_KEEP_ALIVE_TIME_RELEASE;
    }

    public static int getUserTryTime() {
        return debug ? XLA_USER_TRY_TIME_DEBUG : XLA_USER_TRY_TIME_RELEASE;
    }

    public static String getPortalAdress() {
        return debug ? XLA_PORTAL_DEBUG : XLA_PORTAL_RELEASE;
    }

    public static String getPortalAdressSecond() {
        return XLA_PORTAL_RELEASE_SECOND;
    }

    public static long getRecoverQueryCountTime() {
        return debug ? XLA_RECOVER_QUERY_COUNT_TIME_DEBUG : XLA_RECOVER_QUERY_COUNT_TIME_RELEASE;
    }

    public static int getInitTryStatusTime() {
        return debug ? XLA_TRY_INIT_STATUS_TIME_DEBUG : XLA_TRY_INIT_STATUS_TIME_RELEASE;
    }
}
