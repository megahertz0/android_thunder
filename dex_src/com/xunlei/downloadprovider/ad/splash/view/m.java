package com.xunlei.downloadprovider.ad.splash.view;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: SplashWrapAdView.java
final class m implements OnClickListener {
    final /* synthetic */ l a;

    m(l lVar) {
        this.a = lVar;
    }

    public final void onClick(View view) {
        if (this.a.c != null) {
            this.a.c.onClick(view);
        }
        this.a.i();
        this.a.c();
    }
}
