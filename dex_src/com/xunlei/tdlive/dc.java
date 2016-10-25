package com.xunlei.tdlive;

import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.tdlive.user.DefaultXLOnUserListener;
import com.xunlei.tdlive.util.XLog;

// compiled from: LoginActivity.java
class dc extends DefaultXLOnUserListener {
    final /* synthetic */ LoginActivity a;

    dc(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public boolean onUserLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        XLog.d("LoginActivity", new StringBuilder("onUserLogin() errorCode = ").append(i).append(", userInfo = ").append(xLUserInfo).append(", errorDesc = ").append(str).toString());
        if (i == 0) {
            this.a.e();
            this.a.setResult(-1);
            this.a.finish();
        } else {
            if (i == 6) {
                XLUserUtil.getInstance().userGetVerifyCode(this, "get verify code.");
            } else {
                this.a.a(this.a.b(i));
            }
            this.a.o.setText(R.string.login);
            this.a.o.setEnabled(true);
        }
        return false;
    }

    public boolean onUserPreVerifyedCode(int i, Object obj, String str, int i2) {
        if (i == 0) {
            this.a.a(false);
        } else {
            XLUserUtil.getInstance().userGetVerifyCode(this, "get verify code.");
        }
        return false;
    }

    public boolean onUserVerifyCodeUpdated(int i, String str, int i2, byte[] bArr, Object obj, int i3) {
        if (i == 0) {
            this.a.a(str, bArr);
        }
        return false;
    }

    public boolean onUserVerifyedCode(int i, Object obj, String str, int i2) {
        return false;
    }
}
