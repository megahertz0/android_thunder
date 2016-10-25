package com.xunlei.downloadprovider.ad.splash.b;

import com.android.volley.r.a;
import com.android.volley.r.b;
import com.android.volley.toolbox.t;
import com.umeng.message.util.HttpRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// compiled from: SplashInMobiAd.java
final class y extends t {
    final /* synthetic */ String a;
    final /* synthetic */ s b;

    y(s sVar, String str, b bVar, a aVar, String str2) {
        this.b = sVar;
        this.a = str2;
        super(str, bVar, aVar);
    }

    public final Map<String, String> getHeaders() throws com.android.volley.a {
        Map<String, String> headers = super.getHeaders();
        if (headers == null || headers.equals(Collections.emptyMap())) {
            headers = new HashMap();
        }
        headers.put(HttpRequest.v, this.a);
        return headers;
    }
}
