package com.xunlei.downloadprovider.ad.splash.view;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: SplashThirdAdView.java
final class k implements OnClickListener {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public final void onClick(View view) {
        if (this.a.c != null) {
            this.a.c.onClick(view);
        }
        this.a.i();
        this.a.c();
    }
}
