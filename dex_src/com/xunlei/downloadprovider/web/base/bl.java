package com.xunlei.downloadprovider.web.base;

import com.xunlei.downloadprovidercommon.a.a;
import com.xunlei.downloadprovidercommon.a.c;
import com.xunlei.downloadprovidercommon.a.d;

// compiled from: ShortVideoUploadReporter.java
public final class bl {
    public static void a(String str, long j) {
        c a = a.a("android_shortvideo_upload", "shortvideo_upload_notice_click");
        a.a("clickid", str);
        a.a("userid", j);
        a(a);
    }

    static void a(c cVar) {
        new StringBuilder("[STAT_EVENT]").append(cVar);
        d.a(cVar);
    }
}
