package com.xunlei.tdlive;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: SDKLiveListFragment.java
class eh implements AnimationListener {
    final /* synthetic */ ea a;

    eh(ea eaVar) {
        this.a = eaVar;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        ea.h(this.a).setTag(null);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
