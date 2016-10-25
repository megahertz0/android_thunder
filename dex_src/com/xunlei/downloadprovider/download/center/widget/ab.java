package com.xunlei.downloadprovider.download.center.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.download.a.n;
import com.xunlei.downloadprovider.download.tasklist.a.a;
import com.xunlei.downloadprovider.download.util.g;

// compiled from: DownloadTaskMoreOperationDialog.java
final class ab implements OnClickListener {
    final /* synthetic */ z a;

    ab(z zVar) {
        this.a = zVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        boolean z = true;
        boolean z2 = v.d(this.a.a) != null ? !v.d(this.a.a).a.isChecked() : false;
        if (!(v.c(this.a.a) == null || v.a(this.a.a) == null)) {
            a a = v.a(this.a.a);
            n.a().c(a, z2);
            g.a().a(a);
        }
        if (v.a(this.a.a) == null || v.a(this.a.a).mTaskStatus == 8) {
            String str = "sure";
            if (z2) {
                z = false;
            }
            com.xunlei.downloadprovider.download.report.a.a(str, z);
        } else {
            com.xunlei.downloadprovider.download.report.a.l("sure");
        }
        dialogInterface.dismiss();
        this.a.a.dismiss();
    }
}
