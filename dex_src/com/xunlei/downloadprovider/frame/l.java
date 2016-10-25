package com.xunlei.downloadprovider.frame;

import com.xunlei.downloadprovider.member.login.LoginHelper.g;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: MainTabActivity.java
final class l implements g {
    final /* synthetic */ MainTabActivity a;

    l(MainTabActivity mainTabActivity) {
        this.a = mainTabActivity;
    }

    public final void a() {
        MainTabActivity.f(this.a).obtainMessage(0).sendToTarget();
        MainTabActivity.f(this.a).obtainMessage(XZBDevice.DOWNLOAD_LIST_FAILED).sendToTarget();
        MainTabActivity.f(this.a).obtainMessage(XZBDevice.DOWNLOAD_LIST_ALL).sendToTarget();
    }
}
