package com.xunlei.tdlive;

import com.xunlei.common.member.XLUserInfo;
import com.xunlei.tdlive.user.DefaultXLOnUserListener;
import com.xunlei.tdlive.util.XLog;
import org.android.spdy.SpdyProtocol;

// compiled from: LoginGuideActivity.java
class dk extends DefaultXLOnUserListener {
    final /* synthetic */ LoginGuideActivity a;

    dk(LoginGuideActivity loginGuideActivity) {
        this.a = loginGuideActivity;
    }

    public boolean onUserThirdLogin(int i, XLUserInfo xLUserInfo, int i2, int i3, Object obj, String str, int i4) {
        XLog.d("LoginGuideActivity", new StringBuilder("onUserThirdLogin() errCode = ").append(i).toString());
        this.a.hideLoadingDialog();
        if (i == 0) {
            this.a.a();
        } else {
            this.a.showToast("\u767b\u5f55\u5931\u8d25", 0, R.layout.xllive_common_toast, R.id.text, SpdyProtocol.CUSTOM);
        }
        return false;
    }
}
