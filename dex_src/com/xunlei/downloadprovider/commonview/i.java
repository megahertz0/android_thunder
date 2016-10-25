package com.xunlei.downloadprovider.commonview;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: WebpageProgressBar.java
final class i implements AnimationListener {
    final /* synthetic */ WebpageProgressBar a;

    i(WebpageProgressBar webpageProgressBar) {
        this.a = webpageProgressBar;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        WebpageProgressBar.a(this.a);
        WebpageProgressBar.b(this.a);
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
