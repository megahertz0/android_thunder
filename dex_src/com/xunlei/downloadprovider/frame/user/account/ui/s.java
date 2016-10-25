package com.xunlei.downloadprovider.frame.user.account.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: UserAccountPortraitSettingActivity.java
final class s implements OnClickListener {
    final /* synthetic */ UserAccountPortraitSettingActivity a;

    s(UserAccountPortraitSettingActivity userAccountPortraitSettingActivity) {
        this.a = userAccountPortraitSettingActivity;
    }

    public final void onClick(View view) {
        this.a.e.c = "qq";
        k.a("account_center", "qq");
        UserAccountPortraitSettingActivity.a(this.a, UserAccountPortraitSettingActivity.b(this.a.n), (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, this.a.g, this.a.j);
    }
}
