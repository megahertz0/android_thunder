package com.xunlei.downloadprovider.member.login.ui;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: LoginActivity.java
final class k implements OnClickListener {
    final /* synthetic */ Dialog a;
    final /* synthetic */ LoginActivity b;

    k(LoginActivity loginActivity, Dialog dialog) {
        this.b = loginActivity;
        this.a = dialog;
    }

    public final void onClick(View view) {
        this.a.dismiss();
    }
}
