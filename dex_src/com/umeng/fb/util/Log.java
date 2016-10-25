package com.umeng.fb.util;

public class Log {
    public static boolean LOG;

    static {
        LOG = false;
    }

    public static void a(String str, String str2) {
    }

    public static void a(String str, String str2, Exception exception) {
        if (LOG) {
            new StringBuilder().append(exception.toString()).append(":  [").append(str2).append("]");
        }
    }

    public static void b(String str, String str2) {
    }

    public static void b(String str, String str2, Exception exception) {
        if (LOG) {
            new StringBuilder().append(exception.toString()).append(":  [").append(str2).append("]");
            for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                new StringBuilder("        at\t ").append(stackTraceElement.toString());
            }
        }
    }

    public static void c(String str, String str2) {
    }

    public static void c(String str, String str2, Exception exception) {
        if (LOG) {
            new StringBuilder().append(exception.toString()).append(":  [").append(str2).append("]");
        }
    }

    public static void d(String str, String str2) {
    }

    public static void d(String str, String str2, Exception exception) {
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
}
