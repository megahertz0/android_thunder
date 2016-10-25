package com.xunlei.downloadprovider.pushmessage.d;

import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.downloadprovidercommon.a.a;
import com.xunlei.downloadprovidercommon.a.c;
import com.xunlei.downloadprovidercommon.a.d;

// compiled from: PushReport.java
public final class g {
    public static void a(String str, String str2) {
        c a = a.a("android_push", "push_click");
        a.a(JsInterface.FUNPLAY_AD_TRPE, str);
        a.a("messageid", str2);
        a(a);
    }

    public static void a(c cVar) {
        new StringBuilder("[STAT_EVENT]").append(cVar);
        d.a(cVar);
    }
}
