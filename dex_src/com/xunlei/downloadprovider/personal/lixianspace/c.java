package com.xunlei.downloadprovider.personal.lixianspace;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;

// compiled from: LixianSpaceFragment.java
final class c implements AnimationListener {
    final /* synthetic */ LixianSpaceFragment a;

    c(LixianSpaceFragment lixianSpaceFragment) {
        this.a = lixianSpaceFragment;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        LixianSpaceFragment.m(this.a).setListViewMode(Mode.PULL_FROM_START);
    }
}
