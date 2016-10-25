package com.xunlei.downloadprovider.ad.splash.view;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;

// compiled from: SplashWrapAdView.java
final class o implements OnClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ n b;

    o(n nVar, View view) {
        this.b = nVar;
        this.a = view;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.b.a.b.onClick(this.a);
        this.b.a.g();
    }
}
