package com.xunlei.downloadprovider.app;

import com.tencent.open.utils.SystemUtils;
import com.xunlei.downloadprovidercommon.a.a;
import com.xunlei.downloadprovidercommon.a.c;
import com.xunlei.downloadprovidercommon.a.d;

// compiled from: GuideLoginReporter.java
public class n {
    private static final String a;

    static {
        a = n.class.getSimpleName();
    }

    public static void a(String str) {
        c a = a.a("android_installCloud", "installCloud_show");
        a.a(SystemUtils.IS_LOGIN, str);
        a(a);
    }

    public static void a(String str, String str2, String str3) {
        c a = a.a("android_installCloud", "installCloud_click");
        a.a(SystemUtils.IS_LOGIN, str);
        a.a("clickid", str2);
        a.a("is_tick", str3);
        a(a);
    }

    private static void a(c cVar) {
        new StringBuilder("[NEW_STAT_EVENT]").append(cVar);
        d.a(cVar);
    }
}
