package com.xunlei.downloadprovider.download.center;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.download.center.widget.t;
import com.xunlei.downloadprovider.download.report.a;

// compiled from: DownloadCenterActivityFragment.java
final class j implements OnClickListener {
    final /* synthetic */ t a;
    final /* synthetic */ i b;

    j(i iVar, t tVar) {
        this.b = iVar;
        this.a = tVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        a.l("cancel");
        this.a.dismiss();
    }
}
