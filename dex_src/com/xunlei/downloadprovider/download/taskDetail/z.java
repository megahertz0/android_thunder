package com.xunlei.downloadprovider.download.taskDetail;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.report.a;
import com.xunlei.downloadprovider.download.util.n;

// compiled from: DownloadCenterDetailFragment.java
final class z implements OnClickListener {
    final /* synthetic */ DownloadCenterDetailFragment a;

    z(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        this.a = downloadCenterDetailFragment;
    }

    public final void onClick(View view) {
        DownloadCenterDetailFragment.g(this.a);
        a.a("dl_more", n.e(DownloadCenterDetailFragment.f(this.a)), n.c(DownloadCenterDetailFragment.f(this.a)) ? 1 : 0);
    }
}
