package com.xunlei.downloadprovider.commonview;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: DownloadEntranceView.java
final class a implements AnimationListener {
    final /* synthetic */ DownloadEntranceView a;

    a(DownloadEntranceView downloadEntranceView) {
        this.a = downloadEntranceView;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        DownloadEntranceView.a(this.a);
    }
}
