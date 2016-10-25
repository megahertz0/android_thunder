package com.xunlei.downloadprovider.member.login;

import com.xunlei.downloadprovider.member.login.LoginHelper.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: LoginHelper.java
final class e implements Runnable {
    final /* synthetic */ LoginHelper a;

    e(LoginHelper loginHelper) {
        this.a = loginHelper;
    }

    public final void run() {
        if (LoginHelper.c() && !LoginHelper.a(this.a)) {
            if (LoginHelper.b(this.a) != null) {
                for (int i = 0; i < LoginHelper.b(this.a).size(); i++) {
                    ((d) LoginHelper.b(this.a).get(i)).a(XZBDevice.DOWNLOAD_LIST_RECYCLE, 0, LoginHelper.c(this.a), null);
                }
            }
            this.a.v();
            this.a.w();
        }
    }
}
