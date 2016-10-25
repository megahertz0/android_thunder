package com.xunlei.downloadprovider.homepage.choiceness.ui;

import com.xunlei.downloadprovider.member.b.b;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;

// compiled from: HomeChoicenessFragment.java
final class t implements p {
    final /* synthetic */ HomeChoicenessFragment a;

    t(HomeChoicenessFragment homeChoicenessFragment) {
        this.a = homeChoicenessFragment;
    }

    public final void OnRefreshUserInfoCompleted(int i, boolean z) {
        if (i == 0) {
            if (this.a.o == null) {
                this.a.o = b.a(null);
            }
            this.a.o.a();
            HomeChoicenessFragment.n(this.a);
        }
    }
}
