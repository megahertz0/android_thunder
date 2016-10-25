package com.xunlei.downloadprovider.ad.splash.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

// compiled from: SplashAdView.java
final class d implements AnimatorListener {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public final void onAnimationStart(Animator animator) {
    }

    public final void onAnimationEnd(Animator animator) {
        this.a.j();
    }

    public final void onAnimationCancel(Animator animator) {
    }

    public final void onAnimationRepeat(Animator animator) {
    }
}
