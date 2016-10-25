package com.xunlei.downloadprovider.frame.user.account.ui;

import com.xunlei.tdlive.R;

// compiled from: UserAccountPortraitSettingActivity.java
final class p implements com.xunlei.downloadprovider.member.login.LoginHelper.p {
    final /* synthetic */ UserAccountPortraitSettingActivity a;

    p(UserAccountPortraitSettingActivity userAccountPortraitSettingActivity) {
        this.a = userAccountPortraitSettingActivity;
    }

    public final void OnRefreshUserInfoCompleted(int i, boolean z) {
        if (i == 0) {
            this.a.d.sendEmptyMessage(R.styleable.AppCompatTheme_ratingBarStyleIndicator);
        }
    }
}
