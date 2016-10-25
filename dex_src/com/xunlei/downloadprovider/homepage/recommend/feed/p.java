package com.xunlei.downloadprovider.homepage.recommend.feed;

import com.android.volley.w;
import com.xunlei.downloadprovider.ad.common.b;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter.RefreshType;
import java.util.Collection;

// compiled from: FeedDataManager.java
public final class p implements ae {
    final /* synthetic */ ag a;
    final /* synthetic */ RefreshType b;
    final /* synthetic */ o c;

    public p(o oVar, ag agVar, RefreshType refreshType) {
        this.c = oVar;
        this.a = agVar;
        this.b = refreshType;
    }

    public final void a(af afVar) {
        if (afVar.a.size() != 0) {
            this.c.f = afVar.f;
            this.c.g = afVar.b;
            this.c.h = afVar.c;
            boolean z = afVar.g;
            if (z) {
                this.c.k = afVar.d;
                this.c.l = afVar.e;
                this.c.d.clear();
            }
            Collection collection = afVar.a;
            this.c.d.addAll(0, collection);
            o.a(this.c, collection, this.a);
            if (collection.size() >= 8 || z) {
                this.c.e.clear();
                this.c.e.addAll(collection);
                this.c.i = afVar.d;
                this.c.j = afVar.e;
            } else {
                this.c.e.addAll(0, collection);
            }
            VideoFeedReporter.a(this.b, VideoFeedReporter.a(afVar.a));
        }
    }

    public final void a(w wVar) {
        this.a.a();
        VideoFeedReporter.a(this.b, b.a(wVar));
    }
}
