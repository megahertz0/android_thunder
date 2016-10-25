package com.xunlei.downloadprovider.download.center;

import com.xunlei.downloadprovider.member.login.LoginHelper;

// compiled from: DownloadCenterActivityFragment.java
final class aa implements Runnable {
    final /* synthetic */ DownloadCenterActivityFragment a;

    aa(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void run() {
        if (this.a.isVisible()) {
            LoginHelper.a();
            boolean z = LoginHelper.c() && LoginHelper.a().f();
            DownloadCenterActivityFragment.r(this.a).a(true, z);
            DownloadCenterActivityFragment.r(this.a).a();
            DownloadCenterActivityFragment.s(this.a);
            DownloadCenterActivityFragment.h(this.a);
        }
    }
}
