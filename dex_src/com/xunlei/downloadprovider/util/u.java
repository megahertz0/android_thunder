package com.xunlei.downloadprovider.util;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: OnlineConfigure.java
final class u implements a {
    final /* synthetic */ String a;
    final /* synthetic */ r b;

    u(r rVar, String str) {
        this.b = rVar;
        this.a = str;
    }

    public final void onErrorResponse(w wVar) {
        String str = r.a;
        new StringBuilder("downloadOnlineConfigure - ").append(this.a).append(" error: ").append(wVar.toString());
        if (!this.b.d) {
            this.b.d();
        }
    }
}
