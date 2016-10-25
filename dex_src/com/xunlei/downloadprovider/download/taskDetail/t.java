package com.xunlei.downloadprovider.download.taskDetail;

import android.graphics.Bitmap;
import com.xunlei.downloadprovider.download.center.widget.af;

// compiled from: DownloadCenterDetailFragment.java
final class t implements Runnable {
    final /* synthetic */ Bitmap a;
    final /* synthetic */ DownloadCenterDetailFragment b;

    t(DownloadCenterDetailFragment downloadCenterDetailFragment, Bitmap bitmap) {
        this.b = downloadCenterDetailFragment;
        this.a = bitmap;
    }

    public final void run() {
        if (DownloadCenterDetailFragment.w(this.b) == null) {
            DownloadCenterDetailFragment.a(this.b, new af(this.b.getActivity()));
            DownloadCenterDetailFragment.w(this.b).setOnDismissListener(new u(this));
        }
        DownloadCenterDetailFragment.w(this.b).a(this.a, DownloadCenterDetailFragment.f(this.b));
    }
}
