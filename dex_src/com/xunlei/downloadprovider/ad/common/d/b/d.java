package com.xunlei.downloadprovider.ad.common.d.b;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: SSPCfgLoader.java
final class d implements a {
    final /* synthetic */ b.a a;
    final /* synthetic */ b b;

    d(b bVar, b.a aVar) {
        this.b = bVar;
        this.a = aVar;
    }

    public final void onErrorResponse(w wVar) {
        if (this.a != null) {
            this.a.a();
        }
    }
}
