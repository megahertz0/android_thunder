package com.xunlei.downloadprovider.download.center;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: DownloadCenterActivityFragment.java
final class p implements OnClickListener {
    final /* synthetic */ DownloadCenterActivityFragment a;

    p(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void onClick(View view) {
        DownloadCenterActivityFragment.i(this.a).dismiss();
    }
}
