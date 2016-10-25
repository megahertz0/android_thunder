package com.xunlei.downloadprovider.download.center.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import org.android.spdy.SpdyProtocol;

// compiled from: DownloadCenterSelectFileTitleView.java
final class k implements AnimationListener {
    final /* synthetic */ DownloadCenterSelectFileTitleView a;

    k(DownloadCenterSelectFileTitleView downloadCenterSelectFileTitleView) {
        this.a = downloadCenterSelectFileTitleView;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        this.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.setAnimation(null);
    }
}
