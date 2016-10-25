package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: FeedVideoItemView.java
final class as implements AnimationListener {
    final /* synthetic */ Animation a;
    final /* synthetic */ ap b;

    as(ap apVar, Animation animation) {
        this.b = apVar;
        this.a = animation;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        this.b.n.getClickNiceImageView().startAnimation(this.a);
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
