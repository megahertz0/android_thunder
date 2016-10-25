package com.xunlei.downloadprovider.app.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: SystemBarTintManager.java
public final class d implements AnimationListener {
    final /* synthetic */ boolean a;
    final /* synthetic */ c b;

    public d(c cVar, boolean z) {
        this.b = cVar;
        this.a = z;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        if (!this.a) {
            this.b.d.setVisibility(XZBDevice.Wait);
            this.b.c.setVisibility(0);
        }
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
