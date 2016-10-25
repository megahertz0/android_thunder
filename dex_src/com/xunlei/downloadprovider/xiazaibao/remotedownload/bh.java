package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import org.android.spdy.SpdyProtocol;

// compiled from: XZBTaskInfoDetailFragment.java
final class bh implements AnimationListener {
    final /* synthetic */ XZBTaskInfoDetailFragment a;

    bh(XZBTaskInfoDetailFragment xZBTaskInfoDetailFragment) {
        this.a = xZBTaskInfoDetailFragment;
    }

    public final void onAnimationStart(Animation animation) {
        XZBTaskInfoDetailFragment.a(this.a, 0);
        XZBTaskInfoDetailFragment.g(this.a).setClickable(false);
    }

    public final void onAnimationEnd(Animation animation) {
        XZBTaskInfoDetailFragment.g(this.a).setAnimation(null);
        this.a.getView().setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        XZBTaskInfoDetailFragment.a(this.a, 0);
        XZBTaskInfoDetailFragment.g(this.a).setClickable(true);
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
