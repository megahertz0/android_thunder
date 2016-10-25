package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter;
import com.xunlei.downloadprovider.homepage.recommend.a;
import com.xunlei.downloadprovidershare.d;

// compiled from: FeedItemShareLayout.java
final class ab implements OnClickListener {
    final /* synthetic */ FeedItemShareLayout a;

    ab(FeedItemShareLayout feedItemShareLayout) {
        this.a = feedItemShareLayout;
    }

    public final void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_weixin:
                d.b().a(this.a.getActivity(), this.a.getShareBean(), SHARE_MEDIA.WEIXIN, this.a.a);
                if (this.a.b == null) {
                    return;
                }
                if (this.a.i) {
                    VideoFeedReporter.a(this.a.b.a, "screen", "wechart");
                } else {
                    a.b(this.a.b.a, "screen", "wechart");
                }
            case R.id.iv_wxfriend:
                if (this.a.b != null) {
                    d.b().a(this.a.getActivity(), this.a.getShareBean(), SHARE_MEDIA.WEIXIN_CIRCLE, this.a.a);
                    if (this.a.i) {
                        VideoFeedReporter.a(this.a.b.a, "screen", "pengyouquan");
                    } else {
                        a.b(this.a.b.a, "screen", "pengyouquan");
                    }
                }
            case R.id.iv_qq:
                if (this.a.b != null) {
                    d.b().a(this.a.getActivity(), this.a.getShareBean(), SHARE_MEDIA.QQ, this.a.a);
                    if (this.a.i) {
                        VideoFeedReporter.a(this.a.b.a, "screen", "qq");
                    } else {
                        a.b(this.a.b.a, "screen", "qq");
                    }
                }
            case R.id.iv_sina:
                if (this.a.b != null) {
                    d.b().a(this.a.getActivity(), this.a.getShareBean(), SHARE_MEDIA.SINA, this.a.a);
                    if (this.a.i) {
                        VideoFeedReporter.a(this.a.b.a, "screen", "weibo");
                    } else {
                        a.b(this.a.b.a, "screen", "weibo");
                    }
                }
            case R.id.replay_btn:
                if (this.a.c != null) {
                    this.a.c.onClick(view);
                }
            case R.id.iv_qzone:
                if (this.a.b != null) {
                    d.b().a(this.a.getActivity(), this.a.getShareBean(), SHARE_MEDIA.QZONE, this.a.a);
                    if (this.a.i) {
                        VideoFeedReporter.a(this.a.b.a, "screen", "qzone");
                    } else {
                        a.b(this.a.b.a, "screen", "qzone");
                    }
                }
            default:
                break;
        }
    }
}
