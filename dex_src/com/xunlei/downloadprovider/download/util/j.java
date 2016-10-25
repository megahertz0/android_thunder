package com.xunlei.downloadprovider.download.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.commonview.dialog.d;

// compiled from: FreeTrialHelper.java
public final class j implements OnClickListener {
    final /* synthetic */ d a;
    final /* synthetic */ g b;

    public j(g gVar, d dVar) {
        this.b = gVar;
        this.a = dVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.a != null) {
            this.a.dismiss();
        }
    }
}
