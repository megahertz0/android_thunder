package com.xunlei.downloadprovider.ad.common.d.a;

import com.android.volley.Request;
import com.xunlei.downloadprovider.ad.common.c;
import com.xunlei.downloadprovider.ad.common.c.a;
import java.util.ArrayList;
import java.util.List;

// compiled from: SSPADLoader.java
public final class b implements c {
    int a;

    public b(int i) {
        this.a = i;
    }

    public final void a(a aVar, String str) {
        List arrayList = new ArrayList();
        arrayList.add(str);
        Request cVar = new com.xunlei.downloadprovider.ad.common.d.c(new com.xunlei.downloadprovider.ad.common.d.a.c(arrayList), new c(this, aVar), new d(this, aVar));
        cVar.setShouldCache(false);
        com.xunlei.downloadprovider.j.a.a().e().a(cVar);
    }
}
