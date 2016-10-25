package com.xunlei.downloadprovider.homepage.interest.a;

import com.android.volley.r.a;
import com.android.volley.w;
import com.xunlei.downloadprovider.search.b.b;

// compiled from: InterestNetworkHelper.java
final class e implements a {
    final /* synthetic */ b a;
    final /* synthetic */ a b;

    e(a aVar, b bVar) {
        this.b = aVar;
        this.a = bVar;
    }

    public final void onErrorResponse(w wVar) {
        this.a.a(Boolean.valueOf(false));
    }
}
