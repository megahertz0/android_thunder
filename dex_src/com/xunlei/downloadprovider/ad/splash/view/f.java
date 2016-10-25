package com.xunlei.downloadprovider.ad.splash.view;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: SplashFullAdView.java
final class f implements OnClickListener {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public final void onClick(View view) {
        if (this.a.c != null) {
            this.a.c.onClick(view);
        }
        this.a.i();
        this.a.c();
    }
}
