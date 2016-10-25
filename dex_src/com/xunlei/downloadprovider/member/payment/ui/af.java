package com.xunlei.downloadprovider.member.payment.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.android.spdy.SpdyProtocol;

// compiled from: PayOpenFragment.java
final class af extends AnimatorListenerAdapter {
    final /* synthetic */ PayOpenFragment a;

    af(PayOpenFragment payOpenFragment) {
        this.a = payOpenFragment;
    }

    public final void onAnimationEnd(Animator animator) {
        this.a.z = false;
        this.a.u.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }
}
