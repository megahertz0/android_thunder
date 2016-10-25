package com.umeng.common;

public class UmLog {
    public static boolean LOG;

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
        if (LOG) {
            new StringBuilder().append(exception.toString()).append(":  [").append(str2).append("]");
            for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                new StringBuilder("        at\t ").append(stackTraceElement.toString());
            }
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
}
