package com.umeng.socialize.utils;

public class Log {
    public static final boolean ENCRYPT_LOG = true;
    public static boolean LOG = false;
    public static final String TAG = "umengsocial";

    static {
        LOG = true;
    }

    public static void i(String str, String str2) {
    }

    public static void i(String str, String str2, Exception exception) {
        if (LOG) {
            new StringBuilder().append(exception.toString()).append(":  [").append(str2).append("]");
        }
    }

    public static void e(String str, String str2) {
    }

    public static void e(String str, String str2, Exception exception) {
        new StringBuilder().append(exception.toString()).append(":  [").append(str2).append("]");
        for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
            new StringBuilder("        at\t ").append(stackTraceElement.toString());
        }
    }

    public static void d(String str, String str2) {
    }

    public static void d(String str, String str2, Exception exception) {
        if (LOG) {
            new StringBuilder().append(exception.toString()).append(":  [").append(str2).append("]");
        }
    }

    public static void v(String str, String str2) {
    }

    public static void v(String str, String str2, Exception exception) {
        if (LOG) {
            new StringBuilder().append(exception.toString()).append(":  [").append(str2).append("]");
        }
    }

    public static void w(String str, String str2) {
    }

    public static void w(String str, String str2, Exception exception) {
        if (LOG) {
            new StringBuilder().append(exception.toString()).append(":  [").append(str2).append("]");
            for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                new StringBuilder("        at\t ").append(stackTraceElement.toString());
            }
        }
    }

    public static void i(String str) {
    }

    public static void e(String str) {
    }

    public static void d(String str) {
    }

    public static void v(String str) {
    }

    public static void w(String str) {
    }
}
