package com.xunlei.downloadprovider.c;

import com.android.volley.r.a;
import com.android.volley.w;
import com.xunlei.downloadprovider.c.a.b;

// compiled from: CommentManager.java
final class j implements a {
    final /* synthetic */ a a;
    final /* synthetic */ a b;

    j(a aVar, a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    public final void onErrorResponse(w wVar) {
        wVar.printStackTrace();
        a.a;
        new StringBuilder("error response=>").append(wVar.getStackTrace()[0].toString());
        b bVar = new b();
        bVar.a = -1001;
        bVar.b = wVar.getMessage();
        this.a.a(bVar);
    }
}
