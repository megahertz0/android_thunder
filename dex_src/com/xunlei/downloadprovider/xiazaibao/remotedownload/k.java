package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.support.v4.view.ViewPager.OnPageChangeListener;

// compiled from: RemoteDownloadContainerFragment.java
final class k implements OnPageChangeListener {
    final /* synthetic */ RemoteDownloadContainerFragment a;

    k(RemoteDownloadContainerFragment remoteDownloadContainerFragment) {
        this.a = remoteDownloadContainerFragment;
    }

    public final void onPageScrolled(int i, float f, int i2) {
    }

    public final void onPageSelected(int i) {
        this.a.g.a = i;
        this.a.g.a(true);
    }

    public final void onPageScrollStateChanged(int i) {
    }
}
