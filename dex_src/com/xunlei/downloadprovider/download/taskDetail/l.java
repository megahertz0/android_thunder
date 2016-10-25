package com.xunlei.downloadprovider.download.taskDetail;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: DownloadCenterDetailFragment.java
final class l implements AnimationListener {
    final /* synthetic */ DownloadCenterDetailFragment a;

    l(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        this.a = downloadCenterDetailFragment;
    }

    public final void onAnimationStart(Animation animation) {
        DownloadCenterDetailFragment.o(this.a).setClickable(false);
    }

    public final void onAnimationEnd(Animation animation) {
        DownloadCenterDetailFragment.o(this.a).setAnimation(null);
        DownloadCenterDetailFragment.o(this.a).setClickable(true);
        if (DownloadCenterDetailFragment.a(this.a) != null) {
            DownloadCenterDetailFragment.a(this.a).g();
        }
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
