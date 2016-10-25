package com.xunlei.downloadprovider.homepage.choiceness.ui;

import com.xunlei.downloadprovider.homepage.a.a;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter.RefreshType;
import com.xunlei.downloadprovider.homepage.choiceness.a.m;

// compiled from: HomeChoicenessFragment.java
final class r implements a {
    final /* synthetic */ HomeChoicenessFragment a;

    r(HomeChoicenessFragment homeChoicenessFragment) {
        this.a = homeChoicenessFragment;
    }

    public final void a() {
        this.a.j = true;
        this.a.n = RefreshType.auto_pull;
        this.a.e();
    }

    public final long b() {
        return m.a().a.getLong("ChoicenessLastRefreshTime", 0);
    }

    public final void c() {
        m.a().b(System.currentTimeMillis());
    }
}
