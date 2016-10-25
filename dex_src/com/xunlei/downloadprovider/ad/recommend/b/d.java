package com.xunlei.downloadprovider.ad.recommend.b;

import com.xunlei.downloadprovider.ad.common.c.a;
import java.util.List;

// compiled from: RecommendAdPresenter.java
final class d implements a {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public final void a(List<com.xunlei.downloadprovider.ad.common.a> list) {
        b.b(this.a).a((List) list);
        b.b(this.a, list);
        b.b(this.a).g();
        b.b(this.a).a(true);
    }

    public final void a(int i, String str) {
        b.b(this.a).g();
        b.b(this.a).a(true);
        com.xunlei.downloadprovidercommon.a.d.a(com.xunlei.downloadprovidercommon.a.a.a("android_advertise", "adv_downloadin_change_fail").b("tabid", com.xunlei.downloadprovider.ad.recommend.c.a.a(b.b(this.a).b())));
    }
}
