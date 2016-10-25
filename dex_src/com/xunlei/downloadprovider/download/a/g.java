package com.xunlei.downloadprovider.download.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: DownloadCenterControl.java
final class g implements OnClickListener {
    final /* synthetic */ OnClickListener a;
    final /* synthetic */ a b;

    g(a aVar, OnClickListener onClickListener) {
        this.b = aVar;
        this.a = onClickListener;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.b.d != null) {
            this.b.d.dismiss();
            this.b.d = null;
        }
        this.a.onClick(dialogInterface, i);
    }
}
