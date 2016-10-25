package com.xunlei.downloadprovider.homepage.recommend;

import com.xunlei.downloadprovider.homepage.recommend.feed.ag;

// compiled from: SummaryMoviesFeedView.java
final class k implements ag {
    final /* synthetic */ e a;

    k(e eVar) {
        this.a = eVar;
    }

    public final void a(int i) {
        this.a.l.postDelayed(new l(this, i), 1000);
    }

    public final void a() {
        this.a.l.postDelayed(new m(this), 1000);
    }
}
