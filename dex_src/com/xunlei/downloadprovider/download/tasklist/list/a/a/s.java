package com.xunlei.downloadprovider.download.tasklist.list.a.a;

import com.android.volley.r.a;
import com.android.volley.w;
import com.xunlei.downloadprovider.frame.advertisement.b.d;

// compiled from: ThunderLoader.java
final class s implements a {
    final /* synthetic */ a a;
    final /* synthetic */ q b;

    s(q qVar, a aVar) {
        this.b = qVar;
        this.a = aVar;
    }

    public final void onErrorResponse(w wVar) {
        int a = d.a(wVar);
        if (this.a != null) {
            this.a.a(a);
        }
    }
}
