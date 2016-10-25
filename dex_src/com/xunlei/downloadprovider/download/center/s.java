package com.xunlei.downloadprovider.download.center;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.a.n;
import com.xunlei.downloadprovider.download.report.a;
import com.xunlei.downloadprovider.service.downloads.task.d;

// compiled from: DownloadCenterActivityFragment.java
final class s implements OnClickListener {
    final /* synthetic */ DownloadCenterActivityFragment a;

    s(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void onClick(View view) {
        a.f("top_act_pause");
        com.xunlei.downloadprovider.download.a.a aVar = this.a.d;
        n.a();
        d.a();
        d.a(null, true);
        DownloadCenterActivityFragment.i(this.a).dismiss();
    }
}
