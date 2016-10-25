package com.xunlei.downloadprovider.frame.user;

import android.graphics.drawable.AnimationDrawable;
import org.android.spdy.SpdyProtocol;

// compiled from: UserCenterFragment.java
final class bg implements Runnable {
    final /* synthetic */ AnimationDrawable a;
    final /* synthetic */ UserCenterFragment b;

    bg(UserCenterFragment userCenterFragment, AnimationDrawable animationDrawable) {
        this.b = userCenterFragment;
        this.a = animationDrawable;
    }

    public final void run() {
        UserCenterFragment.k(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.stop();
        UserCenterFragment.l(this.b);
    }
}
