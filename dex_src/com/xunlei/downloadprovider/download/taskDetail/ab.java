package com.xunlei.downloadprovider.download.taskDetail;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: DownloadCenterDetailFragment.java
final class ab implements OnClickListener {
    final /* synthetic */ DownloadCenterDetailFragment a;

    ab(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        this.a = downloadCenterDetailFragment;
    }

    public final void onClick(View view) {
        if (!DownloadCenterDetailFragment.i(this.a)) {
            this.a.b(true);
        }
    }
}
