package com.xunlei.downloadprovider.web.base.a;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: CommentItemViewHolder.java
final class u implements AnimationListener {
    final /* synthetic */ Animation a;
    final /* synthetic */ j b;

    u(j jVar, Animation animation) {
        this.b = jVar;
        this.a = animation;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        this.b.n.setEnabled(false);
        this.b.o.setEnabled(false);
        this.b.o.startAnimation(this.a);
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
