package com.xunlei.downloadprovider.frame.user.account.ui;

import com.xunlei.common.member.XLBindedOtherAccountItem;
import com.xunlei.downloadprovider.member.login.LoginHelper.j;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: UserAccountPortraitSettingActivity.java
final class y implements j {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ UserAccountPortraitSettingActivity d;

    y(UserAccountPortraitSettingActivity userAccountPortraitSettingActivity, String str, String str2, String str3) {
        this.d = userAccountPortraitSettingActivity;
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final void a(XLBindedOtherAccountItem[] xLBindedOtherAccountItemArr) {
        for (XLBindedOtherAccountItem xLBindedOtherAccountItem : xLBindedOtherAccountItemArr) {
            switch (xLBindedOtherAccountItem.mThirdTypeId) {
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    this.d.o = xLBindedOtherAccountItem.mThirdRelationship;
                    UserAccountPortraitSettingActivity.a(this.d, this.c, this.d.o, this.d.h, this.d.k);
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    this.d.m = xLBindedOtherAccountItem.mThirdRelationship;
                    UserAccountPortraitSettingActivity.a(this.d, this.a, this.d.m, this.d.f, this.d.i);
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    this.d.n = xLBindedOtherAccountItem.mThirdRelationship;
                    UserAccountPortraitSettingActivity.a(this.d, this.b, this.d.n, this.d.g, this.d.j);
                    break;
                default:
                    break;
            }
        }
    }
}
