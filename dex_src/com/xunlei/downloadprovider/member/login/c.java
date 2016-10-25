package com.xunlei.downloadprovider.member.login;

import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.downloadprovider.member.login.j.a;

// compiled from: LoginHelper.java
final class c extends a {
    final /* synthetic */ LoginHelper a;

    c(LoginHelper loginHelper) {
        this.a = loginHelper;
    }

    public final boolean onUserLogout(int i, XLUserInfo xLUserInfo, Object obj, int i2) {
        new StringBuilder("initSDKLogoutListener() onUserLogout() errorCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i));
        LoginHelper.a(this.a, i);
        com.xunlei.downloadprovider.pushmessage.a.a(false);
        return false;
    }
}
