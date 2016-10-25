package com.xunlei.downloadprovider.frame;

import com.xunlei.downloadprovider.member.login.LoginHelper.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: MainTabActivity.java
final class j implements d {
    final /* synthetic */ MainTabActivity a;

    j(MainTabActivity mainTabActivity) {
        this.a = mainTabActivity;
    }

    public final void a(int i, int i2, boolean z, Object obj) {
        MainTabActivity.f(this.a).obtainMessage(0).sendToTarget();
        if (i2 == 0) {
            MainTabActivity.f(this.a).obtainMessage(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED).sendToTarget();
        }
        if (i == 0 && i2 == 0) {
            MainTabActivity.f(this.a).obtainMessage(XZBDevice.DOWNLOAD_LIST_RECYCLE).sendToTarget();
        }
        String str = MainTabActivity.a;
    }
}
