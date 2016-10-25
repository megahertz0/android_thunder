package com.xunlei.tdlive;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: LivePublishDialog.java
class cs implements AnimationListener {
    final /* synthetic */ co a;

    cs(co coVar) {
        this.a = coVar;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        co.a(this.a, false);
    }
}
