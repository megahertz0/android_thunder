package com.xunlei.downloadprovider.discovery;

// compiled from: DiscoveryFragment.java
final class d implements com.xunlei.downloadprovider.member.login.LoginHelper.d {
    final /* synthetic */ DiscoveryFragment a;

    d(DiscoveryFragment discoveryFragment) {
        this.a = discoveryFragment;
    }

    public final void a(int i, int i2, boolean z, Object obj) {
        DiscoveryFragment.a(this.a).runOnUiThread(new e(this, i, i2));
    }
}
