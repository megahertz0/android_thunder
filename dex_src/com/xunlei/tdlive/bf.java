package com.xunlei.tdlive;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

// compiled from: LivePlayerDialog.java
class bf implements AnimatorListener {
    final /* synthetic */ au a;

    bf(au auVar) {
        this.a = auVar;
    }

    public void onAnimationStart(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
        au.g(this.a, true);
        au.h(this.a, false);
    }

    public void onAnimationCancel(Animator animator) {
    }
}
