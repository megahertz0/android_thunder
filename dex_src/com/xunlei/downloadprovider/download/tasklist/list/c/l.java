package com.xunlei.downloadprovider.download.tasklist.list.c;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.xunlei.downloadprovider.download.center.widget.v;
import com.xunlei.downloadprovider.download.center.widget.v.a;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.e;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter.SaveToXZBEntry;

// compiled from: TaskDownloadCardViewHolder.java
final class l implements a {
    final /* synthetic */ v a;
    final /* synthetic */ g b;

    l(g gVar, v vVar) {
        this.b = gVar;
        this.a = vVar;
    }

    public final void onClick(View view) {
        if (g.e(this.b) != null) {
            com.xunlei.downloadprovider.download.tasklist.list.xzb.a aVar = new com.xunlei.downloadprovider.download.tasklist.list.xzb.a();
            aVar.a = g.e(this.b).getTaskDownloadUrl();
            aVar.b = g.e(this.b).mFileName;
            e.a().a(this.b.c(), aVar, SaveToXZBEntry.task);
            new Handler(Looper.getMainLooper()).postDelayed(new m(this), 1000);
        }
        this.a.dismiss();
    }
}
