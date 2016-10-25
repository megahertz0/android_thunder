package com.xunlei.downloadprovider.member.login.ui;

import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.downloadprovider.util.aa;

// compiled from: UserAccountInfoActivity.java
final class ab implements p {
    final /* synthetic */ UserAccountInfoActivity a;

    ab(UserAccountInfoActivity userAccountInfoActivity) {
        this.a = userAccountInfoActivity;
    }

    public final void OnRefreshUserInfoCompleted(int i, boolean z) {
        aa.a(this.a, "account_button_text", this.a.a());
        LoginHelper.a().b((p) this);
    }
}
