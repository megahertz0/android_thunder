package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: ChannelFeedVideoItemView.java
final class d implements AnimationListener {
    final /* synthetic */ Animation a;
    final /* synthetic */ a b;

    d(a aVar, Animation animation) {
        this.b = aVar;
        this.a = animation;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        a.j(this.b).getClickNiceImageView().startAnimation(this.a);
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
