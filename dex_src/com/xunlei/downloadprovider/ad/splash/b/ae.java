package com.xunlei.downloadprovider.ad.splash.b;

import android.graphics.Bitmap;
import com.xunlei.downloadprovider.ad.recommend.a.b;
import com.xunlei.downloadprovider.ad.splash.b.a.a;

// compiled from: SplashSSPAd.java
final class ae implements a {
    final /* synthetic */ com.xunlei.downloadprovider.ad.splash.a.a a;
    final /* synthetic */ ad b;

    ae(ad adVar, com.xunlei.downloadprovider.ad.splash.a.a aVar) {
        this.b = adVar;
        this.a = aVar;
    }

    public final void a(Bitmap bitmap) {
        new StringBuilder("setImageBitmap success (bitmap == null)").append(bitmap == null);
        this.b.a.a.a(this.a);
    }

    public final void a(b.a aVar) {
        this.b.a.a.a(aVar);
        com.xunlei.downloadprovider.ad.splash.c.a.a("adv_launch_ssp_fail", "load_image_fail");
    }
}
