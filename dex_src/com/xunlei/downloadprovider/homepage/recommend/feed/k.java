package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: ChannelFeedVideoItemView.java
final class k implements AnimationListener {
    final /* synthetic */ a a;

    k(a aVar) {
        this.a = aVar;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        a.f(this.a).dismiss();
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
