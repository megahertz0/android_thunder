package com.xunlei.downloadprovider.ad.home.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: ADImageItemview.java
final class h implements OnClickListener {
    final /* synthetic */ ADImageItemview a;

    h(ADImageItemview aDImageItemview) {
        this.a = aDImageItemview;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.a.d != null) {
            this.a.d.dismiss();
        }
    }
}
