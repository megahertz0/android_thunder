package com.xunlei.downloadprovider.ad.splash.b;

import com.android.volley.r.b;

// compiled from: SplashSSPAd.java
final class ac implements b<String> {
    final /* synthetic */ ab a;

    ac(ab abVar) {
        this.a = abVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        String str = (String) obj;
        if (!this.a.g.e) {
            new StringBuilder("loadData.onResponse.response: ").append(str == null ? "null" : str);
            com.xunlei.downloadprovider.ad.common.d.b.a(str, new ad(this));
        }
    }
}
