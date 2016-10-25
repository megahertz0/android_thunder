package com.xunlei.downloadprovider.ad.splash.a;

import android.graphics.Bitmap;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovider.util.r.a;

// compiled from: SplashGDTAdInfo.java
public final class c extends com.xunlei.downloadprovider.ad.splash.a.a.c implements a {
    protected Bitmap a;

    public c(NativeADDataRef nativeADDataRef) {
        super(nativeADDataRef);
        this.a = null;
    }

    public final Bitmap a() {
        return this.a;
    }

    public final void a(Bitmap bitmap) {
        this.a = bitmap;
    }

    public final long b() {
        a aVar = r.c().e;
        return aVar != null ? Math.max(3000, (long) (aVar.a().a * 1000)) : 3000;
    }

    public final String c() {
        return "background";
    }
}
