package com.xunlei.downloadprovider.frame.user.account.ui;

import com.umeng.message.MsgConstant;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.r;

// compiled from: UserAccountNicknameActivity.java
final class m implements r {
    final /* synthetic */ UserAccountNicknameActivity a;

    m(UserAccountNicknameActivity userAccountNicknameActivity) {
        this.a = userAccountNicknameActivity;
    }

    public final void a(int i) {
        if (i == 0) {
            LoginHelper.a().s();
            k.b("account_center", MsgConstant.KEY_SUCCESS);
            this.a.finish();
        } else if (i == 16781287) {
            XLToast.b(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, this.a.getString(2131232974));
        } else {
            XLToast.b(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, this.a.getString(2131232973));
            k.b("account_center", MsgConstant.KEY_FAIL);
            this.a.finish();
        }
    }
}
