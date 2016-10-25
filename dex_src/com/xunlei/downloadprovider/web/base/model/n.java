package com.xunlei.downloadprovider.web.base.model;

import com.android.volley.r.a;
import com.android.volley.w;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: ShortMovieDetailDataLoader.java
final class n implements a {
    final /* synthetic */ d a;

    n(d dVar) {
        this.a = dVar;
    }

    public final void onErrorResponse(w wVar) {
        this.a.e.a(SimpleLog.LOG_LEVEL_DEBUG, wVar.getMessage());
    }
}
