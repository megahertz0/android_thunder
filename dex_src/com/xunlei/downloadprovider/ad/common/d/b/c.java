package com.xunlei.downloadprovider.ad.common.d.b;

import com.android.volley.r.b;
import com.xunlei.downloadprovider.ad.common.d.b.b.a;

// compiled from: SSPCfgLoader.java
final class c implements b<String> {
    final /* synthetic */ a a;
    final /* synthetic */ b b;

    c(b bVar, a aVar) {
        this.b = bVar;
        this.a = aVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        String str = (String) obj;
        if (this.a != null) {
            a.a a = a.a(str);
            if (a.a == 0) {
                this.a.a(a.b, str);
            } else {
                this.a.a();
            }
        }
    }
}
