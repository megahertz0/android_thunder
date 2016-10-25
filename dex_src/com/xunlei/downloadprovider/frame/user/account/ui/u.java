package com.xunlei.downloadprovider.frame.user.account.ui;

import com.umeng.message.MsgConstant;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.user.account.h;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.q;
import com.xunlei.xllib.a.b;

// compiled from: UserAccountPortraitSettingActivity.java
final class u implements q {
    final /* synthetic */ UserAccountPortraitSettingActivity a;

    u(UserAccountPortraitSettingActivity userAccountPortraitSettingActivity) {
        this.a = userAccountPortraitSettingActivity;
    }

    public final void a(int i, String str) {
        if (i == 0) {
            UserAccountPortraitSettingActivity.k(this.a);
            if (this.a.l) {
                this.a.e;
                h.a();
                this.a.l = false;
            }
            LoginHelper.a().s();
            XLToast.b(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_SUC, this.a.getString(2131232971));
            k.a("account_center", str, MsgConstant.KEY_SUCCESS);
        } else if (i == 16781285) {
            XLToast.b(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_SUC, this.a.getString(2131232972));
        } else {
            if (b.a(this.a.getApplicationContext())) {
                XLToast.b(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, this.a.getString(2131232970));
            } else {
                XLToast.b(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
            }
            k.a("account_center", str, MsgConstant.KEY_FAIL);
        }
    }
}
