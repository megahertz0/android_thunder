package com.xunlei.tdlive.control;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.SpdyProtocol;

// compiled from: CountDownAnim.java
class f implements AnimationListener {
    final /* synthetic */ c a;

    f(c cVar) {
        this.a = cVar;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.a.setText(BuildConfig.VERSION_NAME);
        this.a.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        if (this.a.d != null) {
            this.a.d.a();
        }
    }
}
