package com.xunlei.XLStat.XLStatLog;

public class XLStatLog {
    private static boolean LOG_CALL = false;
    private static boolean LOG_DEBUG = false;
    private static boolean LOG_ERROR = false;
    private static boolean LOG_GUID = false;
    private static boolean LOG_WARRING = false;
    private static final String TAG = "[XLStatLog]";
    public static boolean closeLog;

    static {
        closeLog = false;
        LOG_DEBUG = true;
        LOG_ERROR = true;
        LOG_WARRING = true;
        LOG_CALL = false;
        LOG_GUID = false;
    }

    public static void d(String str, String str2, String str3) {
        if (!closeLog) {
            if (LOG_DEBUG) {
                new StringBuilder("[XLStatLog][").append(str).append("]");
                new StringBuilder("[").append(str2).append("]").append(str3);
            }
            if (LOG_CALL) {
                new StringBuilder("[").append(str).append("][").append(str2).append("]");
            }
            if (LOG_GUID) {
                new StringBuilder("[XLStatLog][").append(str).append("]");
                new StringBuilder("[").append(str2).append("] msg").append(str3);
            }
        }
    }

    public static void e(String str, String str2, String str3) {
        if (!closeLog) {
            if (LOG_ERROR) {
                new StringBuilder("[XLStatLog][").append(str).append("]");
                new StringBuilder("[").append(str2).append("]").append(str3);
            }
            if (LOG_CALL) {
                new StringBuilder("[XLStatLog][").append(str).append("]");
                new StringBuilder("[").append(str2).append("]");
            }
        }
    }

    public static void w(String str, String str2, String str3) {
        if (!closeLog) {
            if (LOG_WARRING) {
                new StringBuilder("[XLStatLog][").append(str).append("]");
                new StringBuilder("[").append(str2).append("]").append(str3);
            }
            if (LOG_CALL) {
                new StringBuilder("[XLStatLog][").append(str).append("]");
                new StringBuilder("[").append(str2).append("]");
            }
        }
    }

    public static void v(String str, String str2, String str3) {
        if (!closeLog) {
            new StringBuilder("[XLStatLog][").append(str).append("]");
            new StringBuilder("[").append(str2).append("]").append(str3);
        }
    }

    public static void i(String str, String str2, String str3) {
        if (!closeLog) {
            new StringBuilder("[XLStatLog][").append(str).append("]");
            new StringBuilder("[").append(str2).append("]").append(str3);
        }
    }

    public static void logThreadID(String str, String str2) {
        if (!closeLog) {
            new StringBuilder("[XLStatLog][").append(str).append("]");
            new StringBuilder("[").append(str2).append("] current thread ID: ").append(Thread.currentThread().getId());
        }
    }
}
