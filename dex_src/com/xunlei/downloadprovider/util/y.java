package com.xunlei.downloadprovider.util;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: RedPointOnlineConfigure.java
public final class y implements a {
    final /* synthetic */ String a;
    final /* synthetic */ v b;

    public y(v vVar, String str) {
        this.b = vVar;
        this.a = str;
    }

    public final void onErrorResponse(w wVar) {
        String str = v.a;
        new StringBuilder("downloadOnlineConfigure - ").append(this.a).append(" error: ").append(wVar.toString());
        v.a(this.b, v.a(this.b.d()));
        if (v.b(this.b) != null && v.b(this.b).size() > 0) {
            v.a(this.b);
        }
        this.b.e();
    }
}
