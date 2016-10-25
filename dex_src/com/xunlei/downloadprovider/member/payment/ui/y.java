package com.xunlei.downloadprovider.member.payment.ui;

import com.xunlei.downloadprovider.member.payment.a.k.a;

// compiled from: PayActivity.java
final class y implements a {
    final /* synthetic */ PayActivity a;

    y(PayActivity payActivity) {
        this.a = payActivity;
    }

    public final void a(boolean z) {
        if (this.a.g()) {
            int count = this.a.e.getCount();
            for (int i = 0; i < count; i++) {
                BasePayPageFragment d = this.a.d(i);
                if (d != null) {
                    d.b(z);
                }
            }
        }
    }
}
