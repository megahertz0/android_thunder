package com.xunlei.tdlive;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: SDKLiveListFragment.java
class eg implements AnimationListener {
    final /* synthetic */ ea a;

    eg(ea eaVar) {
        this.a = eaVar;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        ea.g(this.a).setTag(null);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
