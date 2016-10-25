package com.xunlei.downloadprovider.member.register.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: RegisterSuccessActivity.java
final class y implements TextWatcher {
    final /* synthetic */ RegisterSuccessActivity a;

    y(RegisterSuccessActivity registerSuccessActivity) {
        this.a = registerSuccessActivity;
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
        boolean z = false;
        this.a.g.setVisibility(editable.length() > 0 ? 0 : XZBDevice.Wait);
        Button c = this.a.h;
        if (editable.toString().trim().length() > 0) {
            z = true;
        }
        c.setEnabled(z);
    }
}
