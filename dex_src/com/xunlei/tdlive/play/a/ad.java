package com.xunlei.tdlive.play.a;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

// compiled from: ReplayDialogPresenter.java
class ad implements AnimatorListener {
    final /* synthetic */ aa a;

    ad(aa aaVar) {
        this.a = aaVar;
    }

    public void onAnimationStart(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
        aa.c(this.a, false);
        aa.d(this.a, false);
    }

    public void onAnimationCancel(Animator animator) {
    }
}
