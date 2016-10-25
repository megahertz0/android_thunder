package com.xunlei.downloadprovider.download.taskDetail;

import android.view.View;
import com.xunlei.downloadprovider.download.a.n;
import com.xunlei.downloadprovider.download.taskDetail.ao.a;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter.SaveToXZBEntry;

// compiled from: DownloadCenterDetailFragment.java
final class e implements a {
    final /* synthetic */ DownloadCenterDetailFragment a;

    e(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        this.a = downloadCenterDetailFragment;
    }

    public final void onClick(View view) {
        if (DownloadCenterDetailFragment.f(this.a) != null) {
            com.xunlei.downloadprovider.download.tasklist.list.xzb.a aVar = new com.xunlei.downloadprovider.download.tasklist.list.xzb.a();
            aVar.a = DownloadCenterDetailFragment.f(this.a).getTaskDownloadUrl();
            aVar.b = DownloadCenterDetailFragment.f(this.a).mFileName;
            com.xunlei.downloadprovider.download.tasklist.list.xzb.e.a().a(this.a.getActivity(), aVar, SaveToXZBEntry.task_detail);
            DownloadCenterDetailFragment.m(this.a);
            n.a().a.a();
        }
        DownloadCenterDetailFragment.n(this.a).dismiss();
    }
}
