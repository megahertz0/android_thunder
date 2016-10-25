package com.xunlei.downloadprovider.frame.user;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import org.android.spdy.SpdyProtocol;

// compiled from: HistoryCommentItemViewHolder.java
final class ad implements AnimationListener {
    final /* synthetic */ v a;

    ad(v vVar) {
        this.a = vVar;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        if (v.i(this.a).getVisibility() == 0) {
            v.i(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
