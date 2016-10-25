package com.xunlei.downloadprovider.web.base;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ShortMovieDetailActivity.java
final class ak implements AnimatorListener {
    final /* synthetic */ ShortMovieDetailActivity a;

    ak(ShortMovieDetailActivity shortMovieDetailActivity) {
        this.a = shortMovieDetailActivity;
    }

    public final void onAnimationStart(Animator animator) {
    }

    public final void onAnimationEnd(Animator animator) {
        ShortMovieDetailActivity.h(this.a).setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
    }

    public final void onAnimationCancel(Animator animator) {
    }

    public final void onAnimationRepeat(Animator animator) {
    }
}
