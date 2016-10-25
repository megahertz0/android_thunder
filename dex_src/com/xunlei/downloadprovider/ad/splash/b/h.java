package com.xunlei.downloadprovider.ad.splash.b;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: SplashAd.java
final class h implements a {
    final /* synthetic */ a a;

    h(a aVar) {
        this.a = aVar;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder("exposure ssp/show click fail: ").append(wVar.getLocalizedMessage());
    }
}
