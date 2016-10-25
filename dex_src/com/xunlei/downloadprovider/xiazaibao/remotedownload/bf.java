package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: XZBTaskInfoDetailFragment.java
final class bf implements AnimationListener {
    final /* synthetic */ XZBTaskInfoDetailFragment a;

    bf(XZBTaskInfoDetailFragment xZBTaskInfoDetailFragment) {
        this.a = xZBTaskInfoDetailFragment;
    }

    public final void onAnimationStart(Animation animation) {
        XZBTaskInfoDetailFragment.g(this.a).setClickable(false);
    }

    public final void onAnimationEnd(Animation animation) {
        XZBTaskInfoDetailFragment.g(this.a).setAnimation(null);
        XZBTaskInfoDetailFragment.g(this.a).setClickable(true);
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
