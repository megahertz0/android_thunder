package com.xunlei.downloadprovider.member.register;

import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovidercommon.a.c;
import com.xunlei.downloadprovidercommon.a.d;

// compiled from: LoginThirdReporter.java
public final class a {
    public static void a(String str, String str2) {
        String str3 = "login_third";
        g a = g.a("android_login_third", str3, str3);
        a.a("from", str2);
        a.a("login_account", str);
        a(a);
    }

    public static void a(String str) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_login_third", "login_third_bind_back");
        a.a("clickid", str);
        a(a);
    }

    public static void a(String str, int i) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_login_third", "login_third_bind_code_result");
        a.a("result", str);
        a.a("errorcode", i);
        a(a);
    }

    public static void b(String str, int i) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_login_third", "login_third_bind_result");
        a.a("result", str);
        a.a("errorcode", i);
        a(a);
    }

    public static void a(g gVar) {
        new StringBuilder("[STAT_EVENT]").append(gVar);
        ThunderReporter.a(gVar, true);
    }

    public static void a(c cVar) {
        new StringBuilder("[NEW_STAT_EVENT]").append(cVar);
        d.a(cVar);
    }
}
