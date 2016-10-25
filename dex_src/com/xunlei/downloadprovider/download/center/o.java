package com.xunlei.downloadprovider.download.center;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: DownloadCenterActivityFragment.java
final class o implements OnClickListener {
    final /* synthetic */ DownloadCenterActivityFragment a;

    o(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void onClick(View view) {
        DownloadCenterActivityFragment.i(this.a).dismiss();
        DownloadCenterActivityFragment.j(this.a);
    }
}
