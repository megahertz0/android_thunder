package com.xunlei.downloadprovider.ad.home.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: ADShortVideoItemView.java
final class o implements OnClickListener {
    final /* synthetic */ ADShortVideoItemView a;

    o(ADShortVideoItemView aDShortVideoItemView) {
        this.a = aDShortVideoItemView;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.a();
        if (this.a.d != null) {
            this.a.d.dismiss();
        }
    }
}
