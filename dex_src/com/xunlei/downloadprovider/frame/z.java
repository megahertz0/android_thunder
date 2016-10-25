package com.xunlei.downloadprovider.frame;

import com.xunlei.downloadprovider.frame.view.XLTabView;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: MainTabActivity.java
final class z implements Runnable {
    final /* synthetic */ MainTabActivity a;

    z(MainTabActivity mainTabActivity) {
        this.a = mainTabActivity;
    }

    public final void run() {
        XLTabView currentTabView = this.a.h.getCurrentTabView();
        for (int i = 0; i < 4; i++) {
            XLTabView a = this.a.h.a(i);
            if (a == currentTabView) {
                a.setPointVisible(XZBDevice.Wait);
            } else {
                MainTabActivity.b(i, a);
            }
        }
    }
}
