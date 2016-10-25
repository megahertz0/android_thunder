package com.xunlei.tdlive.usercenter;

import com.xunlei.tdlive.user.f.b;
import com.xunlei.tdlive.util.XLog;

// compiled from: UserCenterActivity.java
class o implements b {
    final /* synthetic */ UserCenterActivity a;

    o(UserCenterActivity userCenterActivity) {
        this.a = userCenterActivity;
    }

    public void a(boolean z) {
        if (z) {
            XLog.d(UserCenterActivity.a(), "on login state changed, follow liver");
            UserCenterActivity.c(this.a);
        }
    }
}
