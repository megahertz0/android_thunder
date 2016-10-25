package com.xunlei.downloadprovider.homepage.recommend.feed;

import com.xunlei.downloadprovider.c.a.b;
import com.xunlei.downloadprovider.c.a.f;
import java.util.List;

// compiled from: FeedDataManager.java
final class q implements a<f> {
    final /* synthetic */ ao a;
    final /* synthetic */ int b;
    final /* synthetic */ List c;
    final /* synthetic */ ag d;
    final /* synthetic */ o e;

    q(o oVar, ao aoVar, int i, List list, ag agVar) {
        this.e = oVar;
        this.a = aoVar;
        this.b = i;
        this.c = list;
        this.d = agVar;
    }

    public final /* synthetic */ void a(Object obj) {
        this.a.a((f) obj);
        if (this.b == this.c.size() - 1) {
            this.d.a(this.c.size());
        }
    }

    public final void a(b bVar) {
        if (this.b == this.c.size() - 1) {
            this.d.a(this.c.size());
        }
    }
}
