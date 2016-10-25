package com.xunlei.downloadprovider.download.tasklist.list.a;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: CommendGDTShowReporter.java
final class j implements a {
    final /* synthetic */ h a;

    j(h hVar) {
        this.a = hVar;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder("exposure ssp show fail: ").append(wVar.getLocalizedMessage());
    }
}
