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

// compiled from: ChannelFeedVideoItemView.java
final class c implements a {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final void onShareTargetClicked(SHARE_MEDIA share_media, ShareBean shareBean) {
        String str = a.c(this.a).a;
        String str2 = BuildConfig.VERSION_NAME;
        OperationType operationType = shareBean.m;
        if (operationType == OperationType.None) {
            str2 = VideoFeedReporter.a(share_media);
        } else if (operationType == OperationType.CopyUrl) {
            str2 = "copy";
        } else if (operationType == OperationType.SystemShare) {
            str2 = "system";
        }
        com.xunlei.downloadprovider.homepage.recommend.a.b(str, "foot", str2);
        if (OperationType.Accuse == shareBean.m) {
            a.i(this.a);
            com.xunlei.downloadprovider.homepage.recommend.a.a();
        }
        if (OperationType.Upload == shareBean.m) {
            bm.a(this.a.getContext(), "channelflow");
        }
    }

    public final void onShareComplete(int i, SHARE_MEDIA share_media, ShareBean shareBean) {
        com.xunlei.downloadprovider.homepage.recommend.a.a(a.c(this.a).a, com.xunlei.downloadprovider.homepage.recommend.a.a(share_media), d.a(i), i, "foot");
        if (i == 0) {
            com.xunlei.downloadprovider.homepage.recommend.b.a.a(BrothersApplication.a).a(a.c(this.a).a, a.c(this.a).q, b.d(), "share_success");
        }
    }
}
