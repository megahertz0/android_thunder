package com.xunlei.tdlive;

import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.tdlive.base.n;
import com.xunlei.tdlive.user.DefaultXLOnUserListener;

// compiled from: SettingActivity.java
class ei extends DefaultXLOnUserListener {
    final /* synthetic */ SettingActivity a;
    private String b;

    ei(SettingActivity settingActivity) {
        this.a = settingActivity;
    }

    public boolean onUserLogout(int i, XLUserInfo xLUserInfo, Object obj, int i2) {
        new StringBuilder("onUserLogout() errorCode = ").append(i).append(", userInfo = ").append(xLUserInfo);
        if (i == 0) {
            this.a.setResult(1);
            this.a.finish();
        } else {
            n.a(this.a, XLErrorCode.getErrorDesc(i));
        }
        return false;
    }
}
