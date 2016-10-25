package com.xunlei.downloadprovider.frame.user.account.ui;

import android.widget.ImageView;
import com.umeng.message.MsgConstant;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLThirdUserInfo;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.downloadprovider.member.login.LoginHelper.i;

// compiled from: UserAccountPortraitSettingActivity.java
final class v implements i {
    final /* synthetic */ ImageView a;
    final /* synthetic */ ImageView b;
    final /* synthetic */ int c;
    final /* synthetic */ UserAccountPortraitSettingActivity d;

    v(UserAccountPortraitSettingActivity userAccountPortraitSettingActivity, ImageView imageView, ImageView imageView2, int i) {
        this.d = userAccountPortraitSettingActivity;
        this.a = imageView;
        this.b = imageView2;
        this.c = i;
    }

    public final void a(int i, XLThirdUserInfo xLThirdUserInfo) {
        UserAccountPortraitSettingActivity.a(this.d, i);
        UserAccountPortraitSettingActivity.a(this.d, xLThirdUserInfo, this.a, this.b, this.c);
    }

    public final void a(int i, int i2) {
        switch (i2) {
            case XLErrorCode.AUTH_USER_CANCLE:
                if (this.d.e != null) {
                    k.a("account_center", this.d.e.c, "cancel");
                }
                break;
            case XLErrorCode.BIND_DUPLICATE_ERROR:
                XLToast.b(this.d, XLToastType.XLTOAST_TYPE_ALARM, this.d.getString(2131232960));
                if (this.d.e != null) {
                    k.a("account_center", this.d.e.c, MsgConstant.KEY_FAIL);
                }
                break;
            default:
                XLToast.b(this.d, XLToastType.XLTOAST_TYPE_ALARM, 2131232961.d.getString(2131232961));
                if (this.d.e != null) {
                    k.a("account_center", this.d.e.c, MsgConstant.KEY_FAIL);
                }
                break;
        }
        if (i2 != 16781283) {
            k.a(i, MsgConstant.KEY_FAIL, i2);
        }
    }
}
