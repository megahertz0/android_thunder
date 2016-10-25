package com.xunlei.downloadprovider.web.base.a;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import org.android.spdy.SpdyProtocol;

// compiled from: BaseInfoViewHolder.java
final class e implements AnimationListener {
    final /* synthetic */ a a;

    e(a aVar) {
        this.a = aVar;
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
