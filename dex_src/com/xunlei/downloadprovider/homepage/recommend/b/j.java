package com.xunlei.downloadprovider.homepage.recommend.b;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: CounterNetWorkHelper.java
public final class j implements a {
    final /* synthetic */ a a;
    final /* synthetic */ b b;

    public j(b bVar, a aVar) {
        this.b = bVar;
        this.a = aVar;
    }

    public final void onErrorResponse(w wVar) {
        if (this.a != null) {
            this.a.onErrorResponse(wVar);
        }
    }
}
