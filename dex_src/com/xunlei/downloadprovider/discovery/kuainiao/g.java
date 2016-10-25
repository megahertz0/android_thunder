package com.xunlei.downloadprovider.discovery.kuainiao;

import android.app.Activity;
import com.xunlei.downloadprovider.member.login.LoginHelper.c;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;

// compiled from: KuaiNiaoUtil.java
public final class g implements c {
    final /* synthetic */ Activity a;
    final /* synthetic */ PayFrom b;

    public g(Activity activity, PayFrom payFrom) {
        this.a = activity;
        this.b = payFrom;
    }

    public final void a(int i) {
        if (i == 0) {
            f.a(this.a, this.b);
        }
    }
}
