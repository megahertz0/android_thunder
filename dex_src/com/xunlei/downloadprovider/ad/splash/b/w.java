package com.xunlei.downloadprovider.ad.splash.b;

import com.android.volley.r.b;

// compiled from: SplashInMobiAd.java
final class w implements b<String> {
    final /* synthetic */ String a;
    final /* synthetic */ s b;

    w(s sVar, String str) {
        this.b = sVar;
        this.a = str;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        new StringBuilder("report done: ").append(this.a);
    }
}
