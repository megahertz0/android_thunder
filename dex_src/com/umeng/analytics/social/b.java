package com.umeng.analytics.social;

// compiled from: UMLog.java
public class b {
    public static void a(String str, String str2) {
        boolean z = e.v;
    }

    public static void a(String str, String str2, Exception exception) {
        if (e.v) {
            new StringBuilder().append(exception.toString()).append(":  [").append(str2).append("]");
        }
    }

    public static void b(String str, String str2) {
        boolean z = e.v;
    }

    public static void b(String str, String str2, Exception exception) {
        if (e.v) {
            new StringBuilder().append(exception.toString()).append(":  [").append(str2).append("]");
            for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                new StringBuilder("        at\t ").append(stackTraceElement.toString());
            }
        }
    }

    public static void c(String str, String str2) {
        boolean z = e.v;
    }

    public static void c(String str, String str2, Exception exception) {
        if (e.v) {
            new StringBuilder().append(exception.toString()).append(":  [").append(str2).append("]");
        }
    }

    public static void d(String str, String str2) {
        boolean z = e.v;
    }

    public static void d(String str, String str2, Exception exception) {
        if (e.v) {
            new StringBuilder().append(exception.toString()).append(":  [").append(str2).append("]");
        }
    }

    public static void e(String str, String str2) {
        boolean z = e.v;
    }

    public static void e(String str, String str2, Exception exception) {
        if (e.v) {
            new StringBuilder().append(exception.toString()).append(":  [").append(str2).append("]");
            for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                new StringBuilder("        at\t ").append(stackTraceElement.toString());
            }
        }
    }
}
