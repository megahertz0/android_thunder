package com.xunlei.downloadprovider.discovery.kuainiao;

import com.xunlei.downloadprovider.frame.user.UserCenterFragment;
import com.xunlei.downloadprovider.member.login.LoginHelper.d;

// compiled from: KuaiNiaoFragment.java
final class b implements d {
    final /* synthetic */ KuaiNiaoFragment a;

    b(KuaiNiaoFragment kuaiNiaoFragment) {
        this.a = kuaiNiaoFragment;
    }

    public final void a(int i, int i2, boolean z, Object obj) {
        new StringBuilder("OnLoginCompleted , event = ").append(i).append(" , errCode = ").append(i2);
        if (i == 0 && i2 == 0) {
            UserCenterFragment.a = true;
        }
    }
}
