package com.xunlei.downloadprovider.personal.playrecord;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;

// compiled from: PlayRecordFragment.java
final class n implements AnimationListener {
    final /* synthetic */ PlayRecordFragment a;

    n(PlayRecordFragment playRecordFragment) {
        this.a = playRecordFragment;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        PlayRecordFragment.d(this.a).setListViewMode(Mode.PULL_FROM_START);
    }
}
