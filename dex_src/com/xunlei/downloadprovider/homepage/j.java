package com.xunlei.downloadprovider.homepage;

import com.xunlei.downloadprovider.util.v;
import com.xunlei.downloadprovidercommon.a.a;
import com.xunlei.downloadprovidercommon.a.c;
import com.xunlei.downloadprovidercommon.a.d;

// compiled from: HomeTabReporter.java
public final class j {
    public static String a;
    public static String b;
    private static String c;
    private static String d;
    private static String e;

    static {
        a = "android_hometab";
        c = "home_collect_show";
        d = "home_fun_show";
        e = "hotvideo_show";
        b = "home_point_status";
    }

    public static void a(c cVar) {
        b(cVar);
    }

    public static void a() {
        c a = a.a(a, e);
        if (v.a().b("short_movie")) {
            a.a("status", "point");
        } else {
            a.a("status", "0");
        }
        b(a);
    }

    public static void b() {
        c a = a.a(a, d);
        if (v.a().b("fun_pic")) {
            a.a("status", "point");
        } else {
            a.a("status", "0");
        }
        b(a);
    }

    public static void a(String str) {
        b(a.a(a, "home_collect_show").a("status", str));
    }

    private static void b(c cVar) {
        new StringBuilder("[STAT_EVENT]").append(cVar);
        d.a(cVar);
    }
}
