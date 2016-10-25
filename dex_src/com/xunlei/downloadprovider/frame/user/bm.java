package com.xunlei.downloadprovider.frame.user;

import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.xllib.R;

// compiled from: UserCenterFragment.java
final class bm implements p {
    final /* synthetic */ UserCenterFragment a;

    bm(UserCenterFragment userCenterFragment) {
        this.a = userCenterFragment;
    }

    public final void OnRefreshUserInfoCompleted(int i, boolean z) {
        if (UserCenterFragment.r(this.a) && i == 0) {
            UserCenterFragment.x(this.a).sendEmptyMessage(R.styleable.AppCompatTheme_buttonStyle);
        }
    }
}
