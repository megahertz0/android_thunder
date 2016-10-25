package com.xunlei.downloadprovider.member.login.ui;

import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.tdlive.R;

// compiled from: UserAccountInfoActivity.java
final class z implements p {
    final /* synthetic */ UserAccountInfoActivity a;

    z(UserAccountInfoActivity userAccountInfoActivity) {
        this.a = userAccountInfoActivity;
    }

    public final void OnRefreshUserInfoCompleted(int i, boolean z) {
        if (!this.a.K) {
            this.a.H.obtainMessage(R.styleable.AppCompatTheme_buttonStyle, i, -1).sendToTarget();
        }
    }
}
