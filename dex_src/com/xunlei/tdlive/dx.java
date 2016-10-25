package com.xunlei.tdlive;

import com.xunlei.common.member.XLUserInfo;
import com.xunlei.tdlive.user.DefaultXLOnUserListener;
import com.xunlei.tdlive.usercenter.ah;

// compiled from: RegisterActivity.java
class dx extends DefaultXLOnUserListener {
    final /* synthetic */ dw a;

    dx(dw dwVar) {
        this.a = dwVar;
    }

    public boolean onUserSessionidLogin(int i, String str, XLUserInfo xLUserInfo, Object obj, int i2) {
        if (i == 0) {
            ah.a(this.a.a);
        }
        return true;
    }
}
