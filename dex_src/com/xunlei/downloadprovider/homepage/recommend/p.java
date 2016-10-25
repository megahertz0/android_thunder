package com.xunlei.downloadprovider.homepage.recommend;

import com.xunlei.downloadprovider.homepage.a.a;
import com.xunlei.downloadprovider.homepage.choiceness.a.m;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter.RefreshType;

// compiled from: SummaryMoviesFeedView.java
final class p implements a {
    final /* synthetic */ e a;

    p(e eVar) {
        this.a = eVar;
    }

    public final void a() {
        this.a.n = RefreshType.auto_pull;
        this.a.a();
    }

    public final long b() {
        return m.a().b();
    }

    public final void c() {
        m.a().c(System.currentTimeMillis());
    }
}
