package com.xunlei.downloadprovider.download.center.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: DownloadCenterSelectFileTitleView.java
final class j implements AnimationListener {
    final /* synthetic */ DownloadCenterSelectFileTitleView a;

    j(DownloadCenterSelectFileTitleView downloadCenterSelectFileTitleView) {
        this.a = downloadCenterSelectFileTitleView;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        this.a.setAnimation(null);
        if (DownloadCenterSelectFileTitleView.d(this.a) != null) {
            DownloadCenterSelectFileTitleView.d(this.a).a();
        }
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
