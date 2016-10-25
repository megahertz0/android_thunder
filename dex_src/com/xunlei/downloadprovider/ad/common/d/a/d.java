package com.xunlei.downloadprovider.ad.common.d.a;

import com.android.volley.r.a;
import com.android.volley.w;
import com.xunlei.downloadprovider.ad.common.b;
import com.xunlei.downloadprovider.ad.common.c;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: SSPADLoader.java
final class d implements a {
    final /* synthetic */ c.a a;
    final /* synthetic */ b b;

    d(b bVar, c.a aVar) {
        this.b = bVar;
        this.a = aVar;
    }

    public final void onErrorResponse(w wVar) {
        if (this.a != null) {
            this.a.a(b.a(wVar), wVar != null ? wVar.getClass().getSimpleName() : BuildConfig.VERSION_NAME);
        }
    }
}
