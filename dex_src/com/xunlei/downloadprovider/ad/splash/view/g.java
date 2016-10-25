package com.xunlei.downloadprovider.ad.splash.view;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: SplashFullAdView.java
final class g implements OnClickListener {
    final /* synthetic */ e a;

    g(e eVar) {
        this.a = eVar;
    }

    public final void onClick(View view) {
        if (this.a.b == null) {
            return;
        }
        if (this.a.j && e.a(this.a) != null && e.a(this.a).g()) {
            this.a.a(new h(this, view), new i(this));
            this.a.f();
            return;
        }
        this.a.b.onClick(view);
    }
}
