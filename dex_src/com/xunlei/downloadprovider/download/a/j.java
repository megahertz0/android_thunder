package com.xunlei.downloadprovider.download.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: DownloadCenterControl.java
final class j implements OnClickListener {
    final /* synthetic */ a a;

    j(a aVar) {
        this.a = aVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.a.c != null) {
            this.a.c.dismiss();
            this.a.c = null;
        }
    }
}
