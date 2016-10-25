package com.xunlei.common.register;

public interface XLRegisterListener {
    boolean onCheckBind(int i, String str, int i2, int i3);

    boolean onCheckNeedVerifyCode(int i, String str, int i2, int i3, String str2);

    boolean onCheckPassWordStrength(int i, String str, int i2, int i3);

    boolean onEmailRegister(int i, String str, int i2, int i3, String str2);

    boolean onGetVerifyCode(int i, String str, int i2, byte[] bArr, String str2, String str3, String str4, String str5);

    boolean onMobileVerifyCodeAccept(String str, int i);

    boolean onOldUserNameRegister(int i, String str, int i2, int i3, String str2);

    boolean onPhoneRegAndLogin(int i, String str, int i2, int i3, String str2);

    boolean onPhoneRegister(int i, String str, int i2, int i3, String str2);

    boolean onSendMessage(int i, String str, int i2, int i3, String str2);
}
