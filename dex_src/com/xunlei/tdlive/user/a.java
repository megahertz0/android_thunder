package com.xunlei.tdlive.user;

import com.xunlei.common.register.XLRegisterListener;

// compiled from: DefaultRegListener.java
public class a implements XLRegisterListener {
    public boolean onCheckBind(int i, String str, int i2, int i3) {
        return true;
    }

    public boolean onCheckNeedVerifyCode(int i, String str, int i2, int i3, String str2) {
        new StringBuilder("onCheckNeedVerifyCode() result = ").append(i).append(", msg = ").append(str).append(", cookie = ").append(i2).append(", need = ").append(i3).append(", verifyType = ").append(str2);
        return true;
    }

    public boolean onCheckPassWordStrength(int i, String str, int i2, int i3) {
        return true;
    }

    public boolean onEmailRegister(int i, String str, int i2, int i3, String str2) {
        return true;
    }

    public boolean onGetVerifyCode(int i, String str, int i2, byte[] bArr, String str2, String str3, String str4, String str5) {
        new StringBuilder("onGetVerifyCode() result = ").append(i).append(", msg = ").append(str).append(", cookie = ").append(i2).append(", verifyKey = ").append(str4).append(", verifyType = ").append(str5);
        return true;
    }

    public boolean onMobileVerifyCodeAccept(String str, int i) {
        return true;
    }

    public boolean onOldUserNameRegister(int i, String str, int i2, int i3, String str2) {
        return true;
    }

    public boolean onPhoneRegAndLogin(int i, String str, int i2, int i3, String str2) {
        new StringBuilder("onPhoneRegAndLogin() result = ").append(i).append(", msg = ").append(str).append(", cookie = ").append(i2).append(", uid = ").append(i3);
        return true;
    }

    public boolean onPhoneRegister(int i, String str, int i2, int i3, String str2) {
        new StringBuilder("onPhoneRegister() result = ").append(i).append(", msg = ").append(str).append(", cookie = ").append(i2).append(", uid = ").append(i3).append(", sid = ").append(str2);
        return true;
    }

    public boolean onSendMessage(int i, String str, int i2, int i3, String str2) {
        new StringBuilder("onSendMessage() result = ").append(i).append(", msg = ").append(str).append(", cookie = ").append(i2).append(", msgType = ").append(i3).append(", verifyType = ").append(str2);
        return true;
    }
}
