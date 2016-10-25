package com.xunlei.downloadprovider.ad.recommend.a;

import com.xunlei.downloadprovider.ad.common.c.a;
import com.xunlei.downloadprovider.ad.common.d;
import java.util.List;

// compiled from: RecommendAdModelProxy.java
final class n implements a {
    final /* synthetic */ d a;
    final /* synthetic */ a b;
    final /* synthetic */ k c;

    n(k kVar, d dVar, a aVar) {
        this.c = kVar;
        this.a = dVar;
        this.b = aVar;
    }

    public final void a(List<com.xunlei.downloadprovider.ad.common.a> list) {
        if (this.a.e) {
            k.c();
            return;
        }
        this.a.d = true;
        if (this.b != null) {
            this.b.a(list);
        }
    }

    public final void a(int i, String str) {
        if (this.a.e) {
            k.c();
            return;
        }
        this.a.d = true;
        if (this.b != null) {
            this.b.a(i, str);
        }
    }
}
