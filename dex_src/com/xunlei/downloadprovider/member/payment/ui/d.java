package com.xunlei.downloadprovider.member.payment.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.a;

// compiled from: ActivationActivity.java
final class d implements OnClickListener {
    final /* synthetic */ ActivationActivity a;

    d(ActivationActivity activationActivity) {
        this.a = activationActivity;
    }

    public final void onClick(View view) {
        this.a.c.setText(a.d);
        this.a.c.requestFocus();
    }
}
