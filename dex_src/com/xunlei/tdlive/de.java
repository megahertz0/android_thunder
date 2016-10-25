package com.xunlei.tdlive;

import android.text.Editable;
import android.text.TextWatcher;
import com.xunlei.common.member.XLUserUtil;

// compiled from: LoginActivity.java
class de implements TextWatcher {
    final /* synthetic */ LoginActivity a;

    de(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.toString().length() == 4) {
            String c = this.a.d;
            String toString = this.a.f.getText().toString();
            this.a.g = 1;
            XLUserUtil.getInstance().userPreVerifyCode(c, toString, this.a.q, "verify code");
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
    }
}
