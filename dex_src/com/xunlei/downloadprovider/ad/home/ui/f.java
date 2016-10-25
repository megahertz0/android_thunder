package com.xunlei.downloadprovider.ad.home.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: ADImageItemview.java
final class f implements OnClickListener {
    final /* synthetic */ ADImageItemview a;

    f(ADImageItemview aDImageItemview) {
        this.a = aDImageItemview;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.a();
        if (this.a.d != null) {
            this.a.d.dismiss();
        }
    }
}
