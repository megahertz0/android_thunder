package com.xunlei.downloadprovider.frame.user;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import org.android.spdy.SpdyProtocol;

// compiled from: UserCenterFragment.java
final class bi implements AnimationListener {
    final /* synthetic */ UserCenterFragment a;

    bi(UserCenterFragment userCenterFragment) {
        this.a = userCenterFragment;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        UserCenterFragment.o(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
