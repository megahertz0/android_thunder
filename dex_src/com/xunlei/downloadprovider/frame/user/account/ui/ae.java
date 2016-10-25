package com.xunlei.downloadprovider.frame.user.account.ui;

import android.view.View;
import com.umeng.message.MsgConstant;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLThirdUserInfo;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.downloadprovider.frame.user.account.l;
import com.xunlei.downloadprovider.member.login.LoginHelper.i;

// compiled from: UserAccountSecurityActivity.java
final class ae implements i {
    final /* synthetic */ View a;
    final /* synthetic */ UserAccountSecurityActivity b;

    ae(UserAccountSecurityActivity userAccountSecurityActivity, View view) {
        this.b = userAccountSecurityActivity;
        this.a = view;
    }

    public final void a(int i, XLThirdUserInfo xLThirdUserInfo) {
        UserAccountSecurityActivity.a(this.b, i, 0);
        UserAccountSecurityActivity.a(this.b, this.a, 0);
        String stringValue = xLThirdUserInfo.getStringValue("nickname");
        UserAccountSecurityActivity.a(this.b, i, stringValue);
        l.a().b(i, stringValue);
        k.a(i, MsgConstant.KEY_SUCCESS, 0);
    }

    public final void a(int i, int i2) {
        switch (i2) {
            case XLErrorCode.AUTH_USER_CANCLE:
                break;
            case XLErrorCode.BIND_DUPLICATE_ERROR:
                XLToast.b(2131232960.a, XLToastType.XLTOAST_TYPE_ALARM, 2131232960.a.getString(2131232960));
                break;
            default:
                XLToast.b(2131232961.a, XLToastType.XLTOAST_TYPE_ALARM, 2131232961.a.getString(2131232961));
                break;
        }
        if (i2 != 16781283) {
            k.a(i, MsgConstant.KEY_FAIL, i2);
        }
    }
}
