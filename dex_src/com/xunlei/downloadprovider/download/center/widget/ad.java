package com.xunlei.downloadprovider.download.center.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: DownloadTitleBarView.java
final class ad implements AnimationListener {
    final /* synthetic */ DownloadTitleBarView a;

    ad(DownloadTitleBarView downloadTitleBarView) {
        this.a = downloadTitleBarView;
    }

    public final void onAnimationStart(Animation animation) {
        DownloadTitleBarView.a(this.a, 1);
    }

    public final void onAnimationEnd(Animation animation) {
        DownloadTitleBarView.a(this.a, 0);
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
