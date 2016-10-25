package com.xunlei.downloadprovider.member.register.ui;

import android.text.Editable;
import android.text.TextWatcher;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: MobileSetupActivity.java
final class r implements TextWatcher {
    final /* synthetic */ MobileSetupActivity a;

    r(MobileSetupActivity mobileSetupActivity) {
        this.a = mobileSetupActivity;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence == null || charSequence.length() <= 0) {
            this.a.l.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        } else {
            this.a.l.setVisibility(0);
        }
        this.a.k();
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
    }
}
