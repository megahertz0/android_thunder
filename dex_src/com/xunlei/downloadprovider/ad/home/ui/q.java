package com.xunlei.downloadprovider.ad.home.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: ADShortVideoItemView.java
final class q implements OnClickListener {
    final /* synthetic */ ADShortVideoItemView a;

    q(ADShortVideoItemView aDShortVideoItemView) {
        this.a = aDShortVideoItemView;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.a.d != null) {
            this.a.d.dismiss();
        }
    }
}
