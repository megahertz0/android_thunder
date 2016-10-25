package com.xunlei.downloadprovider.homepage.recommend;

import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.e;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter.RefreshType;
import com.xunlei.downloadprovider.homepage.recommend.feed.ae;
import com.xunlei.downloadprovider.homepage.recommend.feed.ag;
import com.xunlei.downloadprovider.homepage.recommend.feed.ah;
import com.xunlei.downloadprovider.homepage.recommend.feed.o;
import com.xunlei.downloadprovider.homepage.recommend.feed.p;
import com.xunlei.downloadprovider.homepage.recommend.feed.r;
import com.xunlei.downloadprovider.homepage.recommend.feed.s;
import com.xunlei.downloadprovider.homepage.recommend.feed.t;
import com.xunlei.downloadprovider.homepage.recommend.feed.u;
import com.xunlei.downloadprovider.homepage.recommend.feed.v;
import com.xunlei.downloadprovidercommon.b.a.a;

// compiled from: SummaryMoviesFeedView.java
final class n implements e<ListView> {
    final /* synthetic */ e a;

    n(e eVar) {
        this.a = eVar;
    }

    public final void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
        int i;
        o a = o.a();
        RefreshType c = this.a.n;
        ag d = this.a.j;
        VideoFeedReporter.a(c);
        ae pVar = new p(a, d, c);
        StringBuilder append = new StringBuilder().append("http://api-shoulei-ssl.xunlei.com/ivideo/feed_list_refresh?peerid=%peerid&size=8&t1=%t1&t2=%t2&ts=%ts".replace("%t1", a.g).replace("%t2", a.h).replace("%peerid", b.d()).replace("%ts", a.f)).append("&isFirstLoad=");
        if (a.m) {
            i = 1;
        } else {
            i = 0;
        }
        a.a.a(new a(append.append(i).toString(), new u(a, pVar), new v(a, pVar), (byte) 0));
        if (this.a.m != null) {
            this.a.m.onPullDownToRefresh(pullToRefreshBase);
        }
    }

    public final void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
        o a = o.a();
        RefreshType c = this.a.n;
        ag f = this.a.k;
        VideoFeedReporter.a(c);
        ah rVar = new r(a, f, c);
        a.a.a(new a("http://api-shoulei-ssl.xunlei.com/ivideo/feed_list?peerid=%peerid&size=8&t1=%t1&t2=%t2".replace("%t1", a.k).replace("%t2", a.l).replace("%peerid", b.d()), new s(a, rVar), new t(a, rVar), (byte) 0));
        if (this.a.m != null) {
            this.a.m.onPullUpToRefresh(pullToRefreshBase);
        }
    }
}
