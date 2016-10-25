package com.xunlei.downloadprovider.member.payment.ui;

import android.support.v4.app.FragmentTransaction;
import com.umeng.socialize.PlatformConfig.Alipay;
import com.xunlei.common.pay.XLContractResp;
import com.xunlei.common.pay.XLPayUtil;
import com.xunlei.downloadprovider.member.payment.b;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.member.payment.external.f;

// compiled from: PayProblemActivity.java
final class aj extends f {
    final /* synthetic */ PayProblemActivity a;

    aj(PayProblemActivity payProblemActivity) {
        this.a = payProblemActivity;
    }

    public final void onContractOperate(int i, String str, Object obj, int i2, XLContractResp xLContractResp) {
        if (obj.equals(Alipay.Name)) {
            if (i != 0) {
                XLPayUtil.getInstance().userGetXLContractor(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).userDisContract(PayUtil.c(1, this.a.f, 0), "wxpay");
            } else {
                PayProblemActivity.b(this.a);
                this.a.d.setText(this.a.getResources().getString(2131231857));
                b.a(this.a.e.g(), this.a.e.d(), 1);
            }
        }
        if (!obj.equals("wxpay")) {
            return;
        }
        if (i == 1919) {
            PayProblemActivity.b(this.a);
            this.a.d.setText(this.a.getResources().getString(2131231855));
            b.a(this.a.e.g(), this.a.e.d(), -1);
        } else if (i == 0) {
            PayProblemActivity.b(this.a);
            this.a.d.setText(this.a.getResources().getString(2131231857));
            b.a(this.a.e.g(), this.a.e.d(), 1);
        } else {
            PayProblemActivity.b(this.a);
            this.a.d.setText(this.a.getResources().getString(2131231856));
            b.a(this.a.e.g(), this.a.e.d(), 0);
        }
    }
}
