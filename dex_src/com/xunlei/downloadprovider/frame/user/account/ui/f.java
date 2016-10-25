package com.xunlei.downloadprovider.frame.user.account.ui;

import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.tdlive.R;

// compiled from: UserAccountInfoActivityNew.java
final class f implements p {
    final /* synthetic */ UserAccountInfoActivityNew a;

    f(UserAccountInfoActivityNew userAccountInfoActivityNew) {
        this.a = userAccountInfoActivityNew;
    }

    public final void OnRefreshUserInfoCompleted(int i, boolean z) {
        if (i == 0) {
            this.a.k.sendEmptyMessage(R.styleable.AppCompatTheme_radioButtonStyle);
            new StringBuilder("mHandler==111====").append(this.a.k);
        }
    }
}
