package com.xunlei.downloadprovider.commonview;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: DownloadEntranceView.java
final class b implements AnimationListener {
    final /* synthetic */ Animation a;
    final /* synthetic */ DownloadEntranceView b;

    b(DownloadEntranceView downloadEntranceView, Animation animation) {
        this.b = downloadEntranceView;
        this.a = animation;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        DownloadEntranceView.b(this.b).b.startAnimation(this.a);
    }
}
