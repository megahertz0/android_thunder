package com.xunlei.downloadprovider.homepage.recommend.feed;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter;
import com.xunlei.downloadprovider.web.base.bm;
import com.xunlei.downloadprovidershare.d;
import com.xunlei.downloadprovidershare.d.a;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.downloadprovidershare.data.ShareBean.OperationType;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: FeedVideoItemView.java
final class ar implements a {
    final /* synthetic */ ap a;

    ar(ap apVar) {
        this.a = apVar;
    }

    public final void onShareTargetClicked(SHARE_MEDIA share_media, ShareBean shareBean) {
        String str = this.a.a.a;
        String str2 = BuildConfig.VERSION_NAME;
        OperationType operationType = shareBean.m;
        if (operationType == OperationType.None) {
            str2 = VideoFeedReporter.a(share_media);
        } else if (operationType == OperationType.CopyUrl) {
            str2 = "copy";
        } else if (operationType == OperationType.SystemShare) {
            str2 = "system";
        }
        VideoFeedReporter.a(str, "foot", str2);
        if (OperationType.Accuse == shareBean.m) {
            ap.i(this.a);
            VideoFeedReporter.a();
        }
        if (OperationType.Upload == shareBean.m) {
            bm.a(this.a.getContext(), "feedflow");
        }
    }

    public final void onShareComplete(int i, SHARE_MEDIA share_media, ShareBean shareBean) {
        VideoFeedReporter.a(this.a.a.a, VideoFeedReporter.a(share_media), d.a(i), i, "foot");
        if (i == 0) {
            com.xunlei.downloadprovider.homepage.recommend.b.a.a(BrothersApplication.a).a(this.a.a.a, this.a.a.q, b.d(), "share_success");
        }
    }
}
