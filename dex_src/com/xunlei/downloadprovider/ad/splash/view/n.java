package com.xunlei.downloadprovider.ad.splash.view;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xllib.a.b;

// compiled from: SplashWrapAdView.java
final class n implements OnClickListener {
    final /* synthetic */ l a;

    n(l lVar) {
        this.a = lVar;
    }

    public final void onClick(View view) {
        if (this.a.b == null) {
            return;
        }
        if (this.a.j && b.g(this.a.getContext()) && l.a(this.a) != null && l.a(this.a).g()) {
            this.a.a(new o(this, view), new p(this));
            this.a.f();
            return;
        }
        this.a.b.onClick(view);
    }
}
