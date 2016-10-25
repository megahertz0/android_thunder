package com.xunlei.downloadprovider.member.payment.ui;

import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: PayProblemActivity.java
final class ak implements Runnable {
    final /* synthetic */ PayProblemActivity a;

    ak(PayProblemActivity payProblemActivity) {
        this.a = payProblemActivity;
    }

    public final void run() {
        this.a.d.setVisibility(XZBDevice.Wait);
    }
}
