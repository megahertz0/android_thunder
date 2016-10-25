package com.xunlei.downloadprovider.personal.redenvelope.redenvelopedetail.a;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: RedDetailManager.java
public final class c implements a {
    final /* synthetic */ a.a a;
    final /* synthetic */ a b;

    public c(a aVar, a.a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder("volleyError == ").append(wVar.toString());
    }
}
