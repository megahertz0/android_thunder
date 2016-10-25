package com.xunlei.downloadprovider.homepage.recommend.feed;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter;
import com.xunlei.downloadprovidershare.d;
import com.xunlei.downloadprovidershare.d.a;
import com.xunlei.downloadprovidershare.data.ShareBean;

// compiled from: FeedItemShareLayout.java
final class ac implements a {
    final /* synthetic */ FeedItemShareLayout a;

    ac(FeedItemShareLayout feedItemShareLayout) {
        this.a = feedItemShareLayout;
    }

    public final void onShareTargetClicked(SHARE_MEDIA share_media, ShareBean shareBean) {
        ao d = this.a.b;
        if (d == null) {
            return;
        }
        if (this.a.i) {
            VideoFeedReporter.a(d.a, "screen", VideoFeedReporter.a(share_media));
        } else {
            com.xunlei.downloadprovider.homepage.recommend.a.b(d.a, "screen", com.xunlei.downloadprovider.homepage.recommend.a.a(share_media));
        }
    }

    public final void onShareComplete(int i, SHARE_MEDIA share_media, ShareBean shareBean) {
        ao d = this.a.b;
        if (d != null) {
            if (this.a.i) {
                VideoFeedReporter.a(d.a, VideoFeedReporter.a(share_media), d.a(i), i, "screen");
            } else {
                com.xunlei.downloadprovider.homepage.recommend.a.a(d.a, com.xunlei.downloadprovider.homepage.recommend.a.a(share_media), d.a(i), i, "screen");
            }
            if (i == 0) {
                com.xunlei.downloadprovider.homepage.recommend.b.a.a(BrothersApplication.a).a(d.a, d.q, b.d(), "share_success");
            }
        }
    }
}
