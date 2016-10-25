package com.xunlei.downloadprovider.member.login.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: LoginActivity.java
final class w implements TextWatcher {
    final /* synthetic */ LoginActivity a;

    w(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.a.c();
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
        int i;
        int i2 = XZBDevice.Wait;
        View g = this.a.g;
        if (this.a.f.length() > 0) {
            i = 0;
        } else {
            i = 8;
        }
        g.setVisibility(i);
        TextView h = this.a.i;
        if (this.a.f.length() <= 0) {
            i2 = 0;
        }
        h.setVisibility(i2);
    }
}
