package com.xunlei.downloadprovider.homepage.recommend.b;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: CounterNetWorkHelper.java
final class d implements a {
    final /* synthetic */ a a;
    final /* synthetic */ b b;

    d(b bVar, a aVar) {
        this.b = bVar;
        this.a = aVar;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder("sendPraise onErrorResponse ").append(wVar);
        if (this.a != null) {
            this.a.onErrorResponse(wVar);
        }
    }
}
