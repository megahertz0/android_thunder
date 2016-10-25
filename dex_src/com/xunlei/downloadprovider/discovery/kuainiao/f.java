package com.xunlei.downloadprovider.discovery.kuainiao;

import android.app.Activity;
import com.xunlei.downloadprovider.member.payment.external.PayEntryParam;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.downloadprovider.member.payment.external.PaymentEntryActivity;

// compiled from: KuaiNiaoUtil.java
public final class f {
    public static void a(Activity activity, PayFrom payFrom) {
        activity.startActivity(PaymentEntryActivity.b(activity, new PayEntryParam(payFrom)));
    }
}
