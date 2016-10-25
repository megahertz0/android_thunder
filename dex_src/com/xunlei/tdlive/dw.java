package com.xunlei.tdlive;

import com.xunlei.common.member.XLUserUtil;
import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.tdlive.base.c;
import com.xunlei.tdlive.user.a;
import com.xunlei.tdlive.util.XLog;

// compiled from: RegisterActivity.java
class dw extends a {
    final /* synthetic */ RegisterActivity a;

    dw(RegisterActivity registerActivity) {
        this.a = registerActivity;
    }

    public boolean onPhoneRegister(int i, String str, int i2, int i3, String str2) {
        super.onPhoneRegister(i, str, i2, i3, str2);
        return false;
    }

    public boolean onPhoneRegAndLogin(int i, String str, int i2, int i3, String str2) {
        super.onPhoneRegAndLogin(i, str, i2, i3, str2);
        this.a.a(i == 200, i);
        if (i == 200) {
            XLUserUtil.getInstance().userLoginWithSessionid(i3, str2, XLUserUtil.getInstance().getBusinessType(), 0, new dx(this), null);
            this.a.showToast("\u6ce8\u518c\u6210\u529f\uff0c\u767b\u5f55\u4e2d...");
            this.a.finish();
        } else if (i == 703) {
            c cVar = new c(this.a, "\u6ce8\u518c", "\u8be5\u624b\u673a\u53f7\u7801\u5df2\u88ab\u6ce8\u518c\n\u8bf7\u524d\u5f80\u767b\u5f55", "\u53d6\u6d88", new String[]{"\u53bb\u767b\u5f55"});
            cVar.a(new dy(this));
            cVar.show();
        } else {
            this.a.a(XLRegErrorCode.getErrorDesc(i));
        }
        return false;
    }

    public boolean onSendMessage(int i, String str, int i2, int i3, String str2) {
        super.onSendMessage(i, str, i2, i3, str2);
        if (i == 200) {
            this.a.g();
        } else if (i == 409) {
            this.a.f(str2);
        } else {
            this.a.a(XLRegErrorCode.getErrorDesc(i));
        }
        return false;
    }

    public boolean onCheckNeedVerifyCode(int i, String str, int i2, int i3, String str2) {
        super.onCheckNeedVerifyCode(i, str, i2, i3, str2);
        if (i3 == 1) {
            this.a.f(str2);
        }
        return false;
    }

    public boolean onGetVerifyCode(int i, String str, int i2, byte[] bArr, String str2, String str3, String str4, String str5) {
        super.onGetVerifyCode(i, str, i2, bArr, str2, str3, str4, str5);
        if (i == 200 && this.a.p == i2) {
            this.a.a(str4, str5, bArr);
        } else {
            XLog.d("RegisterActivity", "get verify code failed ok cookie does not match.");
        }
        return false;
    }

    public boolean onMobileVerifyCodeAccept(String str, int i) {
        super.onMobileVerifyCodeAccept(str, i);
        this.a.b(str);
        return false;
    }
}
