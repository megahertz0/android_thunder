package com.xunlei.downloadprovider.ad.splash.b;

import android.graphics.Bitmap;
import com.xunlei.downloadprovider.ad.recommend.a.b;
import com.xunlei.downloadprovider.ad.splash.b.a.a;

// compiled from: SplashInMobiAd.java
final class u implements a {
    final /* synthetic */ com.xunlei.downloadprovider.ad.splash.a.a a;
    final /* synthetic */ t b;

    u(t tVar, com.xunlei.downloadprovider.ad.splash.a.a aVar) {
        this.b = tVar;
        this.a = aVar;
    }

    public final void a(Bitmap bitmap) {
        new StringBuilder("setImageBitmap success (bitmap == null)").append(bitmap == null);
        this.b.a.a(this.a);
    }

    public final void a(b.a aVar) {
        this.b.a.a(null);
        com.xunlei.downloadprovider.ad.splash.c.a.a("adv_launch_inmobi_fail", "load_image_fail");
    }
}
