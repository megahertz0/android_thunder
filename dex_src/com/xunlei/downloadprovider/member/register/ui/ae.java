package com.xunlei.downloadprovider.member.register.ui;

import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.tdlive.R;

// compiled from: RegisterSuccessActivity.java
final class ae implements p {
    final /* synthetic */ RegisterSuccessActivity a;

    ae(RegisterSuccessActivity registerSuccessActivity) {
        this.a = registerSuccessActivity;
    }

    public final void OnRefreshUserInfoCompleted(int i, boolean z) {
        new StringBuilder("OnRefreshUserInfoCompleted errorCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i));
        if (i == 0) {
            this.a.a.sendEmptyMessage(R.styleable.AppCompatTheme_seekBarStyle);
        }
    }
}
