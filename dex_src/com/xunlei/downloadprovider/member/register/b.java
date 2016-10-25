package com.xunlei.downloadprovider.member.register;

import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: RegisterReporter.java
public final class b {
    public static void a(String str) {
        String str2 = "phone_login";
        g a = g.a("android_phone_register", str2, str2);
        a.a("from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a(a);
    }

    public static void a(String str, String str2) {
        String str3 = "phone_reg_passcode_result";
        g a = g.a("android_phone_register", str3, str3);
        a.a("result", str);
        a.a("errcode", str2);
        a(a);
    }

    public static void b(String str, String str2) {
        String str3 = "phone_login_passcode_result";
        g a = g.a("android_phone_register", str3, str3);
        a.a("result", str);
        a.a("errcode", str2);
        a(a);
    }

    public static void b(String str) {
        String str2 = "phone_register_success";
        g a = g.a("android_phone_register", str2, str2);
        a.a("from_src", str);
        a(a);
    }

    public static void a(int i) {
        String str = "phone_login_fail";
        g a = g.a("android_phone_register", str, str);
        a.a("failtype", (long) i);
        a(a);
    }

    public static void a(g gVar) {
        new StringBuilder("[STAT_EVENT]").append(gVar);
        ThunderReporter.a(gVar, true);
    }
}
