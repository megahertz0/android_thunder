package com.xunlei.tdlive;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: LiveListFragment.java
class w implements AnimationListener {
    final /* synthetic */ q a;

    w(q qVar) {
        this.a = qVar;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        q.f(this.a).setTag(null);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
