package com.xunlei.downloadprovider.frame.advertisement.b;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: ThunderADManager.java
final class g implements a {
    final /* synthetic */ String a;
    final /* synthetic */ d.a b;
    final /* synthetic */ d c;

    g(d dVar, String str, d.a aVar) {
        this.c = dVar;
        this.a = str;
        this.b = aVar;
    }

    public final void onErrorResponse(w wVar) {
        String str = d.a;
        new StringBuilder("onErrorResponse pageFrom: ").append(this.a);
        if (this.b != null) {
            this.b.d = true;
        }
        if (this.b != null && this.b.c) {
            str = d.a;
            new StringBuilder("onErrorResponse pageFrom: ").append(this.a).append(" timeout ");
        } else if (this.b != null) {
            this.b.a(d.a(wVar), d.b(wVar));
        }
    }
}
