package com.xunlei.downloadprovider.ad.home.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: VideoInfoViewHolder.java
final class w implements AnimationListener {
    final /* synthetic */ Animation a;
    final /* synthetic */ s b;

    w(s sVar, Animation animation) {
        this.b = sVar;
        this.a = animation;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        this.b.j.setEnabled(false);
        this.b.i.setEnabled(false);
        this.b.i.startAnimation(this.a);
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
