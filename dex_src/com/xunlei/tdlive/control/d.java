package com.xunlei.tdlive.control;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: CountDownAnim.java
class d implements AnimationListener {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.c();
    }
}
