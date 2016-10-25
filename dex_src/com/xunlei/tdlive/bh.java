package com.xunlei.tdlive;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

// compiled from: LivePlayerDialog.java
class bh implements AnimatorListener {
    final /* synthetic */ au a;

    bh(au auVar) {
        this.a = auVar;
    }

    public void onAnimationStart(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
        au.g(this.a, false);
        au.h(this.a, false);
    }

    public void onAnimationCancel(Animator animator) {
    }
}
