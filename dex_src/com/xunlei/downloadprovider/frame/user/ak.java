package com.xunlei.downloadprovider.frame.user;

import com.tencent.open.utils.SystemUtils;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovidercommon.a.a;
import com.xunlei.downloadprovidercommon.a.c;
import com.xunlei.downloadprovidercommon.a.d;

// compiled from: PersonalSettingReport.java
public final class ak {
    public static String a;
    public static String b;
    private static String c;
    private static String d;
    private static String e;
    private static String f;

    static {
        c = "android_config";
        d = "check_version";
        e = "per_cl_set";
        f = "per_home_autoplay_set";
        a = "open";
        b = "close";
    }

    public static void a() {
        a(a.a(c, d));
    }

    public static void a(int i, int i2) {
        c a = a.a(c, e);
        a.a("is_vip", i);
        a.a(SystemUtils.IS_LOGIN, i2);
        a(a);
    }

    public static void a(String str) {
        c a = a.a(c, f);
        a.a(Impl.COLUMN_STATUS, str);
        a(a);
    }

    private static void a(c cVar) {
        new StringBuilder("[STAT_EVENT]").append(cVar);
        d.a(cVar);
    }
}
