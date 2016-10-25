package com.xunlei.downloadprovider.frame.user.account.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: UserAccountPortraitSettingActivity.java
final class t implements OnClickListener {
    final /* synthetic */ UserAccountPortraitSettingActivity a;

    t(UserAccountPortraitSettingActivity userAccountPortraitSettingActivity) {
        this.a = userAccountPortraitSettingActivity;
    }

    public final void onClick(View view) {
        this.a.e.c = "weibo";
        k.a("account_center", "weibo");
        UserAccountPortraitSettingActivity.a(this.a, UserAccountPortraitSettingActivity.b(this.a.o), (int) XZBDevice.DOWNLOAD_LIST_RECYCLE, this.a.h, this.a.k);
    }
}
