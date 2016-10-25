package com.xunlei.downloadprovider.ad.splash.a;

import android.graphics.Bitmap;
import com.xunlei.downloadprovider.ad.a.a;
import com.xunlei.downloadprovider.util.r;

// compiled from: SplashSSPAdInfo.java
public final class e extends com.xunlei.downloadprovider.ad.splash.a.a.e implements a {
    protected Bitmap a;

    public e(a aVar) {
        super(aVar);
        this.a = null;
    }

    public final Bitmap a() {
        return this.a;
    }

    public final void a(Bitmap bitmap) {
        this.a = bitmap;
    }

    public final long b() {
        r.a aVar = r.c().e;
        return aVar != null ? Math.max(3000, (long) (aVar.a().a * 1000)) : 3000;
    }

    public final String c() {
        return ((a) this.b).j == 2 ? "background" : "fullscreen";
    }

    public final String d() {
        return "shangwu";
    }
}
