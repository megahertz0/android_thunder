package com.xunlei.downloadprovider.download.taskDetail;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.download.center.widget.t;
import com.xunlei.downloadprovider.download.report.a;

// compiled from: DownloadCenterDetailFragment.java
final class g implements OnClickListener {
    final /* synthetic */ t a;
    final /* synthetic */ f b;

    g(f fVar, t tVar) {
        this.b = fVar;
        this.a = tVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        a.l("cancel");
        this.a.dismiss();
    }
}
