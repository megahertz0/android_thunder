package com.xunlei.downloadprovider.download.taskDetail;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.download.a.a;
import com.xunlei.downloadprovider.download.center.widget.t;
import java.util.ArrayList;
import java.util.List;

// compiled from: DownloadCenterDetailFragment.java
final class h implements OnClickListener {
    final /* synthetic */ t a;
    final /* synthetic */ f b;

    h(f fVar, t tVar) {
        this.b = fVar;
        this.a = tVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        List arrayList = new ArrayList();
        arrayList.add(DownloadCenterDetailFragment.f(this.b.a));
        DownloadCenterDetailFragment.m(this.b.a);
        a.a(arrayList, !this.a.a.isChecked());
        if (DownloadCenterDetailFragment.f(this.b.a) == null || DownloadCenterDetailFragment.f(this.b.a).mTaskStatus == 8) {
            com.xunlei.downloadprovider.download.report.a.a("sure", this.a.a.isChecked());
        } else {
            com.xunlei.downloadprovider.download.report.a.l("sure");
        }
        this.b.a.b(true);
        this.a.dismiss();
    }
}
