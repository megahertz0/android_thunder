package com.xunlei.downloadprovider.download.taskDetail;

// compiled from: DownloadCenterDetailFragment.java
final class d implements TaskDetailDragView$a {
    final /* synthetic */ DownloadCenterDetailFragment a;

    d(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        this.a = downloadCenterDetailFragment;
    }

    public final void a(float f) {
        if (DownloadCenterDetailFragment.i(this.a)) {
            DownloadCenterDetailFragment.a(this.a, DownloadCenterDetailFragment.b);
        } else if (((double) f) == -1.0d) {
            DownloadCenterDetailFragment.a(this.a, DownloadCenterDetailFragment.c);
            DownloadCenterDetailFragment.j(this.a);
        } else {
            DownloadCenterDetailFragment.k(this.a);
            if (DownloadCenterDetailFragment.l(this.a) != DownloadCenterDetailFragment.d) {
                DownloadCenterDetailFragment.a(this.a, DownloadCenterDetailFragment.d);
            }
        }
    }

    public final void a(int i) {
        if (i == 10) {
            this.a.b(true);
        }
    }
}
