package com.xunlei.downloadprovider.web.base.model;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: ShortMovieDetailDataLoader.java
final class l implements a {
    final /* synthetic */ d a;

    l(d dVar) {
        this.a = dVar;
    }

    public final void onErrorResponse(w wVar) {
        this.a.e.a(1, wVar.getMessage());
    }
}
