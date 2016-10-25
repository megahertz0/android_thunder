package com.xunlei.downloadprovider.download.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.commonview.dialog.d;

// compiled from: FreeTrialHelper.java
public final class i implements OnClickListener {
    final /* synthetic */ d a;
    final /* synthetic */ OnClickListener b;
    final /* synthetic */ g c;

    public i(g gVar, d dVar, OnClickListener onClickListener) {
        this.c = gVar;
        this.a = dVar;
        this.b = onClickListener;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.a != null) {
            this.a.dismiss();
        }
        this.b.onClick(dialogInterface, i);
    }
}
