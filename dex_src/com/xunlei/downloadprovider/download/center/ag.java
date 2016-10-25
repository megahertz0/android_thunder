package com.xunlei.downloadprovider.download.center;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.report.a;

// compiled from: DownloadCenterActivityFragment.java
final class ag implements OnClickListener {
    final /* synthetic */ DownloadCenterActivityFragment a;

    ag(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void onClick(View view) {
        a.f("top_manual");
        DownloadCenterActivityFragment.e(this.a);
    }
}
