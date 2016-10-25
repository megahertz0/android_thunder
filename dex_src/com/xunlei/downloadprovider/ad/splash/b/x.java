package com.xunlei.downloadprovider.ad.splash.b;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: SplashInMobiAd.java
final class x implements a {
    final /* synthetic */ String a;
    final /* synthetic */ s b;

    x(s sVar, String str) {
        this.b = sVar;
        this.a = str;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder("report fail: ").append(this.a);
    }
}
