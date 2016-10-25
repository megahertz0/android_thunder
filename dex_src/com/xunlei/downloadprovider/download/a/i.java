package com.xunlei.downloadprovider.download.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: DownloadCenterControl.java
final class i implements OnClickListener {
    final /* synthetic */ OnClickListener a;
    final /* synthetic */ a b;

    i(a aVar, OnClickListener onClickListener) {
        this.b = aVar;
        this.a = onClickListener;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.b.c != null) {
            this.b.c.dismiss();
            this.b.c = null;
        }
        this.a.onClick(dialogInterface, i);
    }
}
