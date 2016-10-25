package com.xunlei.downloadprovider.discovery;

import com.xunlei.downloadprovider.discovery.kuainiao.e;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;

// compiled from: DiscoveryFragment.java
final class g implements p {
    final /* synthetic */ DiscoveryFragment a;

    g(DiscoveryFragment discoveryFragment) {
        this.a = discoveryFragment;
    }

    public final void OnRefreshUserInfoCompleted(int i, boolean z) {
        if (i == 0) {
            e.a().b();
        }
    }
}
