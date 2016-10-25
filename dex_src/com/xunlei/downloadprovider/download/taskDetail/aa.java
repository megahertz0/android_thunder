package com.xunlei.downloadprovider.download.taskDetail;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.report.a;
import com.xunlei.downloadprovider.download.util.n;

// compiled from: DownloadCenterDetailFragment.java
final class aa implements OnClickListener {
    final /* synthetic */ DownloadCenterDetailFragment a;

    aa(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        this.a = downloadCenterDetailFragment;
    }

    public final void onClick(View view) {
        DownloadCenterDetailFragment.h(this.a);
        a.a("share", n.e(DownloadCenterDetailFragment.f(this.a)), n.c(DownloadCenterDetailFragment.f(this.a)) ? 1 : 0);
    }
}
