package com.xunlei.downloadprovider.frame.user.account.ui;

import android.widget.ImageView;
import com.xunlei.common.member.XLThirdUserInfo;
import com.xunlei.downloadprovider.member.login.LoginHelper.k;

// compiled from: UserAccountPortraitSettingActivity.java
final class w implements k {
    final /* synthetic */ ImageView a;
    final /* synthetic */ ImageView b;
    final /* synthetic */ int c;
    final /* synthetic */ UserAccountPortraitSettingActivity d;

    w(UserAccountPortraitSettingActivity userAccountPortraitSettingActivity, ImageView imageView, ImageView imageView2, int i) {
        this.d = userAccountPortraitSettingActivity;
        this.a = imageView;
        this.b = imageView2;
        this.c = i;
    }

    public final void a(XLThirdUserInfo xLThirdUserInfo) {
        UserAccountPortraitSettingActivity.a(this.d, xLThirdUserInfo, this.a, this.b, this.c);
    }
}
