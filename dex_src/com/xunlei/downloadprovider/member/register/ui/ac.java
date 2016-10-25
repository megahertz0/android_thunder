package com.xunlei.downloadprovider.member.register.ui;

import android.content.Context;
import com.umeng.message.MsgConstant;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.frame.user.account.h;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.q;
import com.xunlei.downloadprovider.member.register.view.a;

// compiled from: RegisterSuccessActivity.java
final class ac implements q {
    final /* synthetic */ RegisterSuccessActivity a;

    ac(RegisterSuccessActivity registerSuccessActivity) {
        this.a = registerSuccessActivity;
    }

    public final void a(int i, String str) {
        new StringBuilder("setOnUserAvatarListener errorCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i));
        if (i == 0) {
            if (this.a.k) {
                this.a.l;
                h.a();
                this.a.k = false;
            }
            LoginHelper.a().s();
            a.a((Context) this.a, 2131232971);
            k.a("phone_register_login", str, MsgConstant.KEY_SUCCESS);
        } else if (i == 16781285) {
            a.a((Context) this.a, 2131232972);
        } else {
            a.b(this.a, 2131232970);
            k.a("phone_register_login", str, MsgConstant.KEY_FAIL);
        }
    }
}
