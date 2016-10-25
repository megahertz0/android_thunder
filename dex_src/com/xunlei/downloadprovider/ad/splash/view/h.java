package com.xunlei.downloadprovider.ad.splash.view;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;

// compiled from: SplashFullAdView.java
final class h implements OnClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ g b;

    h(g gVar, View view) {
        this.b = gVar;
        this.a = view;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.b.a.b.onClick(this.a);
        this.b.a.g();
    }
}
