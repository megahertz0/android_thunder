package com.xunlei.downloadprovider.frame.user;

import com.xunlei.downloadprovider.member.login.LoginHelper.d;

// compiled from: UserCenterFragment.java
final class bj implements d {
    final /* synthetic */ UserCenterFragment a;

    bj(UserCenterFragment userCenterFragment) {
        this.a = userCenterFragment;
    }

    public final void a(int i, int i2, boolean z, Object obj) {
        if (UserCenterFragment.p(this.a) != null) {
            UserCenterFragment.q(this.a).runOnUiThread(new bk(this, i, i2));
        }
        if (i2 == 0 && UserCenterFragment.r(this.a)) {
            this.a.b();
            this.a.a();
            UserCenterFragment.s(this.a);
        }
    }
}
