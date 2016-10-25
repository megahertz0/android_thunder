package com.xunlei.downloadprovider.ad.common.c;

import com.inmobi.ads.InMobiNative;
import com.inmobi.sdk.InMobiSdk;
import com.inmobi.sdk.InMobiSdk.LogLevel;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.ad.common.c;
import com.xunlei.downloadprovider.app.BrothersApplication;
import java.util.HashMap;
import java.util.Map;

// compiled from: InmobiNavLoader.java
public final class a implements c {
    private static boolean a;
    private long b;

    public a(long j) {
        this.b = j;
        if (!a) {
            InMobiSdk.init(BrothersApplication.a(), "93465937c16c447baaf38ba2f7ec166b");
            InMobiSdk.setLogLevel(LogLevel.DEBUG);
            a = true;
        }
    }

    public final void a(com.xunlei.downloadprovider.ad.common.c.a aVar, String str) {
        InMobiNative inMobiNative = new InMobiNative(this.b, new b(this, str, aVar));
        Map hashMap = new HashMap(1);
        new StringBuilder("AndroidConfig.getIMEI(): ").append(b.f());
        hashMap.put("iem", b.f());
        inMobiNative.setExtras(hashMap);
        inMobiNative.load();
    }
}
