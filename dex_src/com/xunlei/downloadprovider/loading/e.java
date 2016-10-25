package com.xunlei.downloadprovider.loading;

import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: LoadingActivity.java
final class e implements Runnable {
    final /* synthetic */ LoadingActivity a;

    e(LoadingActivity loadingActivity) {
        this.a = loadingActivity;
    }

    public final void run() {
        LoadingActivity.i(this.a).setVisibility(XZBDevice.Wait);
        LoadingActivity.j(this.a).setVisibility(XZBDevice.Wait);
        LoadingActivity.k(this.a).setVisibility(0);
        LoadingActivity.l(this.a).setVisibility(0);
    }
}
