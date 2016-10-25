package com.xunlei.downloadprovider.frame.user;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: HistoryCommentItemViewHolder.java
final class ac implements AnimationListener {
    final /* synthetic */ Animation a;
    final /* synthetic */ v b;

    ac(v vVar, Animation animation) {
        this.b = vVar;
        this.a = animation;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        v.g(this.b).setEnabled(false);
        v.h(this.b).setEnabled(false);
        v.h(this.b).startAnimation(this.a);
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
