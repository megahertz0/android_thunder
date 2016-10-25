package com.xunlei.downloadprovider.frame;

import com.xunlei.downloadprovider.member.login.LoginHelper.p;

// compiled from: MainTabActivity.java
final class k implements p {
    final /* synthetic */ MainTabActivity a;

    k(MainTabActivity mainTabActivity) {
        this.a = mainTabActivity;
    }

    public final void OnRefreshUserInfoCompleted(int i, boolean z) {
        MainTabActivity.f(this.a).obtainMessage(0).sendToTarget();
    }
}
