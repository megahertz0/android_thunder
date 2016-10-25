package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: XZBTaskInfoDetailFragment.java
final class bi implements AnimationListener {
    final /* synthetic */ XZBTaskInfoDetailFragment a;

    bi(XZBTaskInfoDetailFragment xZBTaskInfoDetailFragment) {
        this.a = xZBTaskInfoDetailFragment;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        XZBTaskInfoDetailFragment.h(this.a).setAnimation(null);
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
