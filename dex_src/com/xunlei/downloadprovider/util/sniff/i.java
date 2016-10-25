package com.xunlei.downloadprovider.util.sniff;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: SnifferRules.java
final class i implements a {
    final /* synthetic */ String a;
    final /* synthetic */ g b;

    i(g gVar, String str) {
        this.b = gVar;
        this.a = str;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder("[athunder_sniff_rules]downloadOnlineConfigure - ").append(this.a).append(" error: ").append(wVar.toString());
        if (!this.b.a) {
            this.b.c();
        }
    }
}
