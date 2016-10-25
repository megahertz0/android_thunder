package com.xunlei.downloadprovider.ad.recommend.a;

import com.xunlei.downloadprovider.ad.common.c.a;
import java.util.ArrayList;
import java.util.List;

// compiled from: RecommendAdModelProxy.java
public final class l implements a {
    final /* synthetic */ int a;
    final /* synthetic */ k b;

    public l(k kVar, int i) {
        this.b = kVar;
        this.a = i;
    }

    public final void a(List<com.xunlei.downloadprovider.ad.common.a> list) {
        k.a(this.b, this.a, list);
        k.a(this.b, this.a);
        this.b.a(this.a, list);
    }

    public final void a(int i, String str) {
        k.a(this.b, this.a, new ArrayList(0));
        k.a(this.b, this.a);
        this.b.a(this.a, i, str);
    }
}
