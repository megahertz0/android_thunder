package com.xunlei.downloadprovider.ad.splash.b;

import com.android.volley.r.a;
import com.android.volley.w;
import com.xunlei.downloadprovider.ad.recommend.a.b;
import com.xunlei.downloadprovider.frame.advertisement.b.d;

// compiled from: SplashSSPAd.java
final class af implements a {
    final /* synthetic */ ab a;

    af(ab abVar) {
        this.a = abVar;
    }

    public final void onErrorResponse(w wVar) {
        if (!this.a.g.e) {
            int a = d.a(wVar);
            this.a.a(new b.a(a, d.b(wVar)));
            com.xunlei.downloadprovider.ad.splash.c.a.a("adv_launch_ssp_fail", String.valueOf(a));
        }
    }
}
