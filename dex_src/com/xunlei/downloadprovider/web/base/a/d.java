package com.xunlei.downloadprovider.web.base.a;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: BaseInfoViewHolder.java
final class d implements AnimationListener {
    final /* synthetic */ Animation a;
    final /* synthetic */ a b;

    d(a aVar, Animation animation) {
        this.b = aVar;
        this.a = animation;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        this.b.j.setEnabled(false);
        this.b.i.setEnabled(false);
        this.b.i.startAnimation(this.a);
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
