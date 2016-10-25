package com.xunlei.tdlive;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: LiveListFragment.java
class v implements AnimationListener {
    final /* synthetic */ q a;

    v(q qVar) {
        this.a = qVar;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        q.e(this.a).setTag(null);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
