package com.xunlei.downloadprovider.member.payment.ui;

import android.text.Editable;
import android.text.TextWatcher;
import com.umeng.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ActivationActivity.java
final class f implements TextWatcher {
    final /* synthetic */ ActivationActivity a;

    f(ActivationActivity activationActivity) {
        this.a = activationActivity;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (a.d.equals(this.a.b.getText().toString().trim())) {
            this.a.f.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        } else {
            this.a.f.setVisibility(0);
        }
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
    }
}
