package com.xunlei.downloadprovider.download.center;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.download.center.widget.t;
import com.xunlei.downloadprovider.download.tasklist.a.a;
import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import java.util.ArrayList;
import java.util.List;

// compiled from: DownloadCenterActivityFragment.java
final class k implements OnClickListener {
    final /* synthetic */ List a;
    final /* synthetic */ t b;
    final /* synthetic */ i c;

    k(i iVar, List list, t tVar) {
        this.c = iVar;
        this.a = list;
        this.b = tVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        List<e> b = DownloadCenterActivityFragment.c(this.c.a).b();
        List arrayList = new ArrayList();
        for (e eVar : b) {
            a b2 = eVar.b();
            if (b2 != null) {
                arrayList.add(b2);
            }
        }
        if (this.a.size() > 0) {
            com.xunlei.downloadprovider.download.report.a.a("sure", this.b.a.isChecked());
        } else {
            com.xunlei.downloadprovider.download.report.a.l("sure");
        }
        com.xunlei.downloadprovider.download.a.a aVar = this.c.a.d;
        com.xunlei.downloadprovider.download.a.a.a(arrayList, !this.b.a.isChecked());
        this.c.a.b();
        this.b.dismiss();
    }
}
