package com.xunlei.downloadprovider.frame.user;

import com.sina.weibo.sdk.utils.NetworkHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.o;
import com.xunlei.downloadprovider.util.aa;

// compiled from: UserCenterFragment.java
final class bc implements o {
    final /* synthetic */ UserCenterFragment a;

    bc(UserCenterFragment userCenterFragment) {
        this.a = userCenterFragment;
    }

    public final void a(int i, long j, long j2) {
        new StringBuilder("UserCenterFragment() =====>> status=").append(i).append(", capacity  >>").append(j).append(", remain >>").append(j2);
        if ((i == 2 || i == 0) && NetworkHelper.isNetworkAvailable(UserCenterFragment.B(this.a))) {
            aa.a(UserCenterFragment.C(this.a), "flowtotal", j);
            aa.a(UserCenterFragment.D(this.a), "flowused", j - j2);
        }
    }
}
