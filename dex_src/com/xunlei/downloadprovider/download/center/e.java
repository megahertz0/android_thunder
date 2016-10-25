package com.xunlei.downloadprovider.download.center;

// compiled from: DownloadCenterActivityFragment.java
final class e implements Runnable {
    final /* synthetic */ DownloadCenterActivityFragment a;

    e(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void run() {
        try {
            if (DownloadCenterActivityFragment.a(this.a) != null) {
                DownloadCenterActivityFragment.a(this.a).removeCallbacks(this);
                DownloadCenterActivityFragment.a(this.a).postDelayed(this, 1000);
            }
            DownloadCenterActivityFragment.b(this.a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
