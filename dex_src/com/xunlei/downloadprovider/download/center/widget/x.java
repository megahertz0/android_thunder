package com.xunlei.downloadprovider.download.center.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.download.report.a;
import com.xunlei.downloadprovider.download.taskDetail.au;
import com.xunlei.downloadprovidershare.d;

// compiled from: DownloadTaskMoreOperationDialog.java
final class x implements OnClickListener {
    final /* synthetic */ v a;

    x(v vVar) {
        this.a = vVar;
    }

    public final void onClick(View view) {
        a.c("share", a.a(v.a(this.a)), v.b(this.a));
        this.a.dismiss();
        if (v.c(this.a) != null) {
            com.xunlei.downloadprovider.download.a.a c = v.c(this.a);
            com.xunlei.downloadprovider.download.tasklist.a.a a = v.a(this.a);
            if (c.a != null && a != null && (c.a instanceof DownloadCenterActivity)) {
                DownloadCenterActivity downloadCenterActivity = (DownloadCenterActivity) c.a;
                a = a;
                downloadCenterActivity.c = a;
                d.b().a(downloadCenterActivity, au.a(a, "button_press"), downloadCenterActivity);
            }
        }
    }
}
