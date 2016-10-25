package com.xunlei.downloadprovider.personal.lixianspace;

import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;

// compiled from: LixianSpaceFragment.java
final class l implements p {
    final /* synthetic */ LixianSpaceFragment a;

    l(LixianSpaceFragment lixianSpaceFragment) {
        this.a = lixianSpaceFragment;
    }

    public final void OnRefreshUserInfoCompleted(int i, boolean z) {
        if (LixianSpaceFragment.r(this.a) != null && LixianSpaceFragment.l(this.a) != LixianSpaceFragment$b.d) {
            LixianSpaceFragment.r(this.a).post(new m(this));
            LoginHelper.a().b(LixianSpaceFragment.w(this.a));
        }
    }
}
