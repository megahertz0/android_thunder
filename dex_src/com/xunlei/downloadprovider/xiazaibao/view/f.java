package com.xunlei.downloadprovider.xiazaibao.view;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.a;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.e;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter.SaveToXZBEntry;

// compiled from: XZBDetailDeleteDialog.java
final class f implements OnClickListener {
    final /* synthetic */ a a;

    f(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        if (this.a.h != null) {
            a aVar = new a();
            aVar.a = this.a.h.getTaskDownloadUrl();
            aVar.b = this.a.h.mFileName;
            e.a().a(this.a.getContext(), aVar, SaveToXZBEntry.task_detail);
        }
        this.a.dismiss();
    }
}
