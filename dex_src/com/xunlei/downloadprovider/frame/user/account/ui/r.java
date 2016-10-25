package com.xunlei.downloadprovider.frame.user.account.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.mm.sdk.constants.ConstantsAPI.Token;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: UserAccountPortraitSettingActivity.java
final class r implements OnClickListener {
    final /* synthetic */ UserAccountPortraitSettingActivity a;

    r(UserAccountPortraitSettingActivity userAccountPortraitSettingActivity) {
        this.a = userAccountPortraitSettingActivity;
    }

    public final void onClick(View view) {
        this.a.e.c = Token.WX_TOKEN_PLATFORMID_VALUE;
        k.a("account_center", Token.WX_TOKEN_PLATFORMID_VALUE);
        UserAccountPortraitSettingActivity.a(this.a, UserAccountPortraitSettingActivity.b(this.a.m), (int) XZBDevice.DOWNLOAD_LIST_FAILED, this.a.f, this.a.i);
    }
}
