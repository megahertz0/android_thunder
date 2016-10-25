package com.xunlei.downloadprovider.member.payment.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.a;

// compiled from: ActivationActivity.java
final class c implements OnClickListener {
    final /* synthetic */ ActivationActivity a;

    c(ActivationActivity activationActivity) {
        this.a = activationActivity;
    }

    public final void onClick(View view) {
        this.a.b.setText(a.d);
        this.a.b.requestFocus();
    }
}
