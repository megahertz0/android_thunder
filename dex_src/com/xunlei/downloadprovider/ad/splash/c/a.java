package com.xunlei.downloadprovider.ad.splash.c;

import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;

// compiled from: SplashADReporter.java
public final class a {
    public static void a(g gVar) {
        new StringBuilder("[STAT_EVENT]").append(gVar);
        ThunderReporter.a(gVar, true);
    }

    public static void a(int i) {
        String str = "adv_launch_pv";
        a(g.a("android_advertise", str, str).a("ad_from", b(i)).a("net_type", com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a.a()));
    }

    public static void a(String str) {
        a(g.a("android_advertise", str, str));
    }

    public static void a(String str, String str2) {
        new StringBuilder("splashReport loadFail attribute1: ").append(str).append(" errorcode: ").append(str2).append(" net_type: \u6b63\u5728\u83b7\u53d6");
        a(g.a("android_advertise", str, str).a("errorcode", str2).a("net_type", com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a.a()));
    }

    public static void a(String str, String str2, int i, String str3, String str4) {
        new StringBuilder("splashReport click adv_id: ").append(str).append(" ad_type: ").append(str2).append(" ad_from: ").append(i).append(" material: ").append(str3).append(" style: ").append(str4);
        String str5 = "adv_launch_click";
        g a = g.a("android_advertise", str5, str5);
        str5 = "adv_id";
        if (str == null) {
            str = com.umeng.a.d;
        }
        a = a.a(str5, str);
        str5 = "ad_type";
        if (str2 == null) {
            str2 = com.umeng.a.d;
        }
        a(a.a(str5, str2).a("ad_from", b(i)).a("material", str3).a("style", str4));
    }

    public static void a(String str, String str2, int i, String str3, String str4, String str5) {
        new StringBuilder("splashReport show advid: ").append(str).append(" adType: ").append(str2).append(" spalshOrigin: ").append(i).append(" loadTime: ").append(str3).append(" material: ").append(str4).append(" style: ").append(str5);
        String str6 = "adv_launch_show";
        g a = g.a("android_advertise", str6, str6);
        str6 = "ad_id";
        if (str == null) {
            str = com.umeng.a.d;
        }
        a = a.a(str6, str);
        str6 = "ad_type";
        if (str2 == null) {
            str2 = com.umeng.a.d;
        }
        a(a.a(str6, str2).a("ad_from", b(i)).a("load_time", str3).a("material", str4).a("style", str5));
    }

    public static String b(int i) {
        return i == 0 ? "launch" : "forground";
    }
}
