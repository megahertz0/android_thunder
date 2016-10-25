package com.xunlei.downloadprovider.frame;

import com.xunlei.downloadprovider.download.tasklist.list.xzb.e;
import com.xunlei.downloadprovider.frame.view.XLTabView;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;

// compiled from: MainTabActivity.java
final class aa implements Runnable {
    final /* synthetic */ MainTabActivity a;

    aa(MainTabActivity mainTabActivity) {
        this.a = mainTabActivity;
    }

    public final void run() {
        XLTabView a = MainTabActivity.b(this.a).a((int) XZBDevice.DOWNLOAD_LIST_FAILED);
        if (a != MainTabActivity.b(this.a).getCurrentTabView()) {
            e.a();
            if (e.f() && XZBShouleiUtil.getInstance().getDefaultDevice() != null) {
                a.setPointVisible(0);
            }
        }
    }
}
