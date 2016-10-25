package com.xunlei.downloadprovider.member.register.ui;

import com.umeng.message.MsgConstant;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.r;
import com.xunlei.downloadprovider.member.register.view.a;

// compiled from: RegisterSuccessActivity.java
final class ad implements r {
    final /* synthetic */ RegisterSuccessActivity a;

    ad(RegisterSuccessActivity registerSuccessActivity) {
        this.a = registerSuccessActivity;
    }

    public final void a(int i) {
        new StringBuilder("setOnUserInfoListener() errorCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i));
        if (i == 0) {
            k.b("phone_register_login", MsgConstant.KEY_SUCCESS);
            LoginHelper.a().s();
            this.a.finish();
        } else if (i == 16781287) {
            a.b(this.a, 2131232974);
        } else {
            k.b("phone_register_login", MsgConstant.KEY_FAIL);
            a.b(this.a, 2131232973);
        }
    }
}
