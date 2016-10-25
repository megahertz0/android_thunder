package com.xunlei.downloadprovider.player;

import android.content.Context;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.homepage.recommend.WifiPopWindowReporter;
import com.xunlei.downloadprovider.homepage.recommend.WifiPopWindowReporter.PageFrom;
import com.xunlei.downloadprovider.player.u.a;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity;
import com.xunlei.xllib.a.b;

// compiled from: MediaPlayerManager.java
final class s implements a {
    final /* synthetic */ a a;
    final /* synthetic */ Context b;
    final /* synthetic */ String c;
    final /* synthetic */ q d;

    s(q qVar, a aVar, Context context, String str) {
        this.d = qVar;
        this.a = aVar;
        this.b = context;
        this.c = str;
    }

    public final void a() {
        q.a(this.d, false);
        this.a.a();
        if (this.b instanceof ShortMovieDetailActivity) {
            WifiPopWindowReporter.a(PageFrom.VIDEO_DEITAIL, b.d(BrothersApplication.a), this.c, 0);
        } else if (this.b instanceof MainTabActivity) {
            WifiPopWindowReporter.a(PageFrom.FEED_FLOW, b.d(BrothersApplication.a), this.c, 0);
        }
    }

    public final void b() {
        if (b.e(this.b)) {
            q.a(this.d, true);
        }
        this.a.b();
        if (this.b instanceof ShortMovieDetailActivity) {
            WifiPopWindowReporter.a(PageFrom.VIDEO_DEITAIL, b.d(BrothersApplication.a), this.c, 1);
        } else if (this.b instanceof MainTabActivity) {
            WifiPopWindowReporter.a(PageFrom.FEED_FLOW, b.d(BrothersApplication.a), this.c, 1);
        }
    }
}
