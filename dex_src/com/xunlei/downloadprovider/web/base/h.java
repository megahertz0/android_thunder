package com.xunlei.downloadprovider.web.base;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: KandanDataLoader.java
final class h implements a {
    final /* synthetic */ f a;

    h(f fVar) {
        this.a = fVar;
    }

    public final void onErrorResponse(w wVar) {
        f.c;
        this.a.a.a(true, null, null);
    }
}
