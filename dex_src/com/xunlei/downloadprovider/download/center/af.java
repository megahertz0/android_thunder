package com.xunlei.downloadprovider.download.center;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.report.a;

// compiled from: DownloadCenterActivityFragment.java
final class af implements OnClickListener {
    final /* synthetic */ DownloadCenterActivityFragment a;

    af(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void onClick(View view) {
        a.f("back");
        DownloadCenterActivityFragment.d(this.a);
    }
}
