package com.tencent.bugly.crashreport;

import com.tencent.bugly.b;
import com.tencent.bugly.proguard.x;
import com.umeng.a;

// compiled from: BUGLY
public class BuglyLog {
    public static void v(String str, String str2) {
        if (str == null) {
            str = a.d;
        }
        if (str2 == null) {
            str2 = "null";
        }
        boolean z = b.b;
        x.a("V", str, str2);
    }

    public static void d(String str, String str2) {
        if (str == null) {
            str = a.d;
        }
        if (str2 == null) {
            str2 = "null";
        }
        boolean z = b.b;
        x.a("D", str, str2);
    }

    public static void i(String str, String str2) {
        if (str == null) {
            str = a.d;
        }
        if (str2 == null) {
            str2 = "null";
        }
        boolean z = b.b;
        x.a("I", str, str2);
    }

    public static void w(String str, String str2) {
        if (str == null) {
            str = a.d;
        }
        if (str2 == null) {
            str2 = "null";
        }
        boolean z = b.b;
        x.a("W", str, str2);
    }

    public static void e(String str, String str2) {
        if (str == null) {
            str = a.d;
        }
        if (str2 == null) {
            str2 = "null";
        }
        boolean z = b.b;
        x.a("E", str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        if (str == null) {
            str = a.d;
        }
        boolean z = b.b;
        x.a("E", str, th);
    }

    public static void setCache(int i) {
        x.a(i);
    }
}
