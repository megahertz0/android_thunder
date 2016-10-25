package com.xunlei.downloadprovider.ad.splash.b;

import android.graphics.Bitmap;
import com.xunlei.downloadprovider.ad.recommend.a.b;
import com.xunlei.downloadprovider.ad.splash.b.a.a;

// compiled from: SplashGDTAd.java
final class r implements a {
    final /* synthetic */ com.xunlei.downloadprovider.ad.splash.a.a a;
    final /* synthetic */ q b;

    r(q qVar, com.xunlei.downloadprovider.ad.splash.a.a aVar) {
        this.b = qVar;
        this.a = aVar;
    }

    public final void a(Bitmap bitmap) {
        new StringBuilder("setImageBitmap success (bitmap == null): ").append(bitmap == null);
        this.b.a.a(this.a);
    }

    public final void a(b.a aVar) {
        com.xunlei.downloadprovider.ad.splash.c.a.a("adv_launch_tx_fail", "load_image_fail");
        this.b.a.a(null);
    }
}
