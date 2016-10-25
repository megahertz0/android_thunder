package com.xunlei.downloadprovider.download.taskDetail;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: DownloadCenterDetailFragment.java
final class p implements AnimationListener {
    final /* synthetic */ DownloadCenterDetailFragment a;

    p(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        this.a = downloadCenterDetailFragment;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        DownloadCenterDetailFragment.c(this.a).setAnimation(null);
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
