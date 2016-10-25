package com.xunlei.downloadprovider.util.sniff;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: SniffConfigure.java
public final class c implements a {
    final /* synthetic */ String a;
    final /* synthetic */ SniffConfigure b;

    public c(SniffConfigure sniffConfigure, String str) {
        this.b = sniffConfigure;
        this.a = str;
    }

    public final void onErrorResponse(w wVar) {
        f.a(false);
        SniffConfigure.h();
        new StringBuilder("downloadOnlineConfigure - ").append(this.a).append(" error: ").append(wVar.toString());
        if (!this.b.a) {
            SniffConfigure.b(this.b);
        }
    }
}
