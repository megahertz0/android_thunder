package com.xunlei.downloadprovider.frame.user.account.ui;

import com.umeng.a;
import com.xunlei.common.member.XLBindedOtherAccountItem;
import com.xunlei.downloadprovider.frame.user.account.l;
import com.xunlei.downloadprovider.member.login.LoginHelper.j;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: UserAccountSecurityActivity.java
final class z implements j {
    final /* synthetic */ UserAccountSecurityActivity a;

    z(UserAccountSecurityActivity userAccountSecurityActivity) {
        this.a = userAccountSecurityActivity;
    }

    public final void a(XLBindedOtherAccountItem[] xLBindedOtherAccountItemArr) {
        int length = xLBindedOtherAccountItemArr.length;
        for (int i = 0; i < length; i++) {
            XLBindedOtherAccountItem xLBindedOtherAccountItem = xLBindedOtherAccountItemArr[i];
            switch (xLBindedOtherAccountItem.mThirdTypeId) {
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    this.a.n = xLBindedOtherAccountItem.mThirdRelationship;
                    UserAccountSecurityActivity.a(this.a, this.a.i, this.a.n);
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    this.a.l = xLBindedOtherAccountItem.mThirdRelationship;
                    UserAccountSecurityActivity.a(this.a, this.a.g, this.a.l);
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    this.a.m = xLBindedOtherAccountItem.mThirdRelationship;
                    UserAccountSecurityActivity.a(this.a, this.a.h, this.a.m);
                    break;
            }
            if (xLBindedOtherAccountItem.mThirdRelationship != -1) {
                String string;
                l a = l.a();
                int i2 = xLBindedOtherAccountItem.mThirdTypeId;
                if (a.a != null) {
                    string = a.a.getString(l.a(i2, "nickname"), a.d);
                } else {
                    string = null;
                }
                UserAccountSecurityActivity.a(this.a, xLBindedOtherAccountItem.mThirdTypeId, string);
            }
        }
    }
}
