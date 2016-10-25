package com.xunlei.downloadprovider.vod;

import android.view.animation.AnimationUtils;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: VodPlayerView.java
final class bb implements Runnable {
    final /* synthetic */ VodPlayerView a;

    bb(VodPlayerView vodPlayerView) {
        this.a = vodPlayerView;
    }

    public final void run() {
        if (this.a.mLockBtn.getVisibility() == 0) {
            this.a.mLockBtn.startAnimation(AnimationUtils.loadAnimation(this.a.getContext(), 2131034229));
            this.a.mLockBtn.setVisibility(XZBDevice.Wait);
        }
    }
}
