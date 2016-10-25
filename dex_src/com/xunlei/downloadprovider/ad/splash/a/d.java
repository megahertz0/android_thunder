package com.xunlei.downloadprovider.ad.splash.a;

import android.graphics.Bitmap;
import com.xunlei.downloadprovider.loading.a.b;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovider.util.r.a;

// compiled from: SplashInMobiAdInfo.java
public final class d extends com.xunlei.downloadprovider.ad.splash.a.a.d implements a {
    protected Bitmap a;

    public d(b bVar) {
        super(bVar);
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
        return "fullscreen";
    }
}
