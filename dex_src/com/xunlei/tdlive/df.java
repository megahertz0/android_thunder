package com.xunlei.tdlive;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.common.member.XLUserUtil;

// compiled from: LoginActivity.java
class df implements OnClickListener {
    final /* synthetic */ LoginActivity a;

    df(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public void onClick(View view) {
        XLUserUtil.getInstance().userGetVerifyCode(this.a.q, "get verify code.");
    }
}
