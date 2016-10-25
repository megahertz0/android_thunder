package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: FeedVideoItemView.java
final class az implements AnimationListener {
    final /* synthetic */ ap a;

    az(ap apVar) {
        this.a = apVar;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        this.a.z.dismiss();
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
