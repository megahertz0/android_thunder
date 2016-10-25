package com.xunlei.downloadprovider.download.c;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: SpeedLimitManager.java
final class e implements a {
    final /* synthetic */ c a;

    e(c cVar) {
        this.a = cVar;
    }

    public final void onErrorResponse(w wVar) {
        wVar.printStackTrace();
        new StringBuilder("volleyError == ").append(wVar.toString());
    }
}
