package com.xunlei.downloadprovider.member.payment.ui;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.umeng.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ActivationActivity.java
final class g implements OnFocusChangeListener {
    final /* synthetic */ ActivationActivity a;

    g(ActivationActivity activationActivity) {
        this.a = activationActivity;
    }

    public final void onFocusChange(View view, boolean z) {
        if (z) {
            if (a.d.equals(this.a.c.getText().toString().trim())) {
                this.a.g.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            } else {
                this.a.g.setVisibility(0);
            }
            this.a.f.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        }
    }
}
