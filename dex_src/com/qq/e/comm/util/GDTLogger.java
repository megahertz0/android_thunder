package com.qq.e.comm.util;

import com.qq.e.comm.services.a;

public class GDTLogger {
    public static final boolean DEBUG_ENABLE = false;

    public static void d(String str) {
    }

    public static void e(String str) {
    }

    public static void e(String str, Throwable th) {
        if (th != null) {
        }
    }

    public static void i(String str) {
    }

    public static void report(String str) {
        report(str, null);
    }

    public static void report(String str, Throwable th) {
        e(str, th);
        a.a();
        a.a(str, th);
    }

    public static void w(String str) {
    }

    public static void w(String str, Throwable th) {
        if (th != null) {
        }
    }
}
