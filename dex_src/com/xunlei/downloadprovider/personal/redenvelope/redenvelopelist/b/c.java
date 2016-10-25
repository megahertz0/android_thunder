package com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.b;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: RedEnvelopesManager.java
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
