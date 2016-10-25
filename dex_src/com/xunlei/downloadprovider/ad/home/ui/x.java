package com.xunlei.downloadprovider.ad.home.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import org.android.spdy.SpdyProtocol;

// compiled from: VideoInfoViewHolder.java
final class x implements AnimationListener {
    final /* synthetic */ s a;

    x(s sVar) {
        this.a = sVar;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        if (this.a.k.getVisibility() == 0) {
            this.a.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
