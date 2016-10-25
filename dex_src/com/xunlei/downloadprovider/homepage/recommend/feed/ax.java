package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.xunlei.downloadprovider.homepage.recommend.ShortTimeVideoListActivity;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter;
import com.xunlei.downloadprovider.homepage.recommend.c.c;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;
import com.xunlei.downloadprovidershare.d;
import com.xunlei.xllib.a.b;

// compiled from: FeedVideoItemView.java
final class ax implements FeedVideoItemBottomView$a {
    final /* synthetic */ Context a;
    final /* synthetic */ ap b;

    ax(ap apVar, Context context) {
        this.b = apVar;
        this.a = context;
    }

    public final void a() {
        if (this.b.a != null) {
            ShortTimeVideoListActivity.a(this.a, this.b.a.g, this.b.a.l, this.b.a.n, "feed_flow");
        }
    }

    public final void a(View view) {
        if (b.a(this.a)) {
            ap.a(this.b, view, this.a);
            if (this.b.a != null) {
                ap.d(this.b);
            }
        } else if (this.b.a != null) {
            c.a().a(this.b.a.b, this.b.a.c, this.b.a.a, this.b.a.q);
            new StringBuilder("\u6dfb\u52a0\u70b9\u8d5e\u5230\u6570\u636e\u5e93 title, name, movieId == ").append(this.b.a.b).append(",").append(this.b.a.c).append(",").append(this.b.a.a);
            ap.a(this.b, view, this.a);
            ap.d(this.b);
        }
    }

    public final void b() {
        ap.a(this.b, From.FEED_FLOW);
        if (this.b.a != null) {
            VideoFeedReporter.b(this.b.a.a);
        }
    }

    public final void c() {
        if (this.b.a != null) {
            d.b().a((Activity) this.a, this.b.getShareBean(), this.b.t);
            VideoFeedReporter.a(this.b.a.a, "foot");
        }
    }
}
