package com.xunlei.tdlive.play.view;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import org.android.spdy.SpdyProtocol;

// compiled from: LevelUpgradeBar.java
class m implements AnimationListener {
    final /* synthetic */ LevelUpgradeBar a;

    m(LevelUpgradeBar levelUpgradeBar) {
        this.a = levelUpgradeBar;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.e = false;
        this.a.b();
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
