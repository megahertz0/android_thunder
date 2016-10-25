package com.xunlei.downloadprovider.ad.splash.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

// compiled from: SplashAdView.java
final class c implements AnimatorUpdateListener {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.g.setProgress(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }
}
