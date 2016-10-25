package com.xunlei.downloadprovider.ad.splash.b;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.ad.splash.a.a;

// compiled from: SplashAd.java
final class b implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ a b;

    b(a aVar, a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    public final void onClick(View view) {
        this.a.onClick(this.b.a, this.b.c);
        this.b.d(this.a);
        this.b.h();
        com.xunlei.downloadprovider.ad.splash.c.a.a(this.a.p(), this.a.d(), this.b.e, this.a.n(), this.b.l.getAdStyle());
        this.b.b(this.a.m());
    }
}
