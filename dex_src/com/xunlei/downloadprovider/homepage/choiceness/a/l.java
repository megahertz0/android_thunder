package com.xunlei.downloadprovider.homepage.choiceness.a;

import com.android.volley.r.a;
import com.android.volley.w;
import com.xunlei.downloadprovider.search.b.b;

// compiled from: ChoicenessNetworkHelper.java
public final class l implements a {
    final /* synthetic */ b a;
    final /* synthetic */ h b;

    public l(h hVar, b bVar) {
        this.b = hVar;
        this.a = bVar;
    }

    public final void onErrorResponse(w wVar) {
        com.xunlei.downloadprovider.homepage.choiceness.a.a.b bVar = new com.xunlei.downloadprovider.homepage.choiceness.a.a.b();
        bVar.g = true;
        bVar.f = wVar;
        this.a.a(bVar);
    }
}
