package com.android.volley.toolbox;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: ImageLoader.java
final class k implements a {
    final /* synthetic */ String a;
    final /* synthetic */ i b;

    k(i iVar, String str) {
        this.b = iVar;
        this.a = str;
    }

    public final void onErrorResponse(w wVar) {
        i iVar = this.b;
        String str = this.a;
        a aVar = (a) iVar.c.remove(str);
        if (aVar != null) {
            aVar.b = wVar;
            iVar.a(str, aVar);
        }
    }
}
