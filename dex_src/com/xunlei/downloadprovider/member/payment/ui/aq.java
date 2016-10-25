package com.xunlei.downloadprovider.member.payment.ui;

import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;

// compiled from: PaymentSuccessActivity.java
final class aq implements p {
    final /* synthetic */ PaymentSuccessActivity a;

    aq(PaymentSuccessActivity paymentSuccessActivity) {
        this.a = paymentSuccessActivity;
    }

    public final void OnRefreshUserInfoCompleted(int i, boolean z) {
        LoginHelper.a().b((p) this);
    }
}
