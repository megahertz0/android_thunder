package com.xunlei.downloadprovider.web.base.a;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import org.android.spdy.SpdyProtocol;

// compiled from: CommentItemViewHolder.java
final class l implements AnimationListener {
    final /* synthetic */ j a;

    l(j jVar) {
        this.a = jVar;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        if (j.o(this.a).getVisibility() == 0) {
            j.o(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
