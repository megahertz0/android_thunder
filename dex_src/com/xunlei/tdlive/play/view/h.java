package com.xunlei.tdlive.play.view;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: ConnectMicView.java
class h implements AnimationListener {
    final /* synthetic */ ConnectMicView a;

    h(ConnectMicView connectMicView) {
        this.a = connectMicView;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.b();
    }
}
