package com.xunlei.downloadprovider.member.payment.ui;

import android.content.Intent;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;

// compiled from: ActivationActivity.java
final class b implements p {
    final /* synthetic */ ActivationActivity a;

    b(ActivationActivity activationActivity) {
        this.a = activationActivity;
    }

    public final void OnRefreshUserInfoCompleted(int i, boolean z) {
        LoginHelper.a().b(this.a.q);
        ActivationActivity.k(this.a);
        this.a.startActivity(new Intent(this.a, ActivationPaySuccessActivity.class));
        this.a.finish();
    }
}
