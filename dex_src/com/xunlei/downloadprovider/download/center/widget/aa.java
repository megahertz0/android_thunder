package com.xunlei.downloadprovider.download.center.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.download.report.a;

// compiled from: DownloadTaskMoreOperationDialog.java
final class aa implements OnClickListener {
    final /* synthetic */ z a;

    aa(z zVar) {
        this.a = zVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        a.l("cancel");
        dialogInterface.dismiss();
        this.a.a.dismiss();
    }
}
