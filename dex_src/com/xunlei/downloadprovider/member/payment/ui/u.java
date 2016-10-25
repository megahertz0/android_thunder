package com.xunlei.downloadprovider.member.payment.ui;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: BasePayPagerActivity.java
final class u implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ BasePayPagerActivity b;

    u(BasePayPagerActivity basePayPagerActivity, int i) {
        this.b = basePayPagerActivity;
        this.a = i;
    }

    public final void onClick(View view) {
        this.b.g.setCurrentTab(this.a);
        if (((BasePayPageFragment) this.b.e()) != null) {
        }
    }
}
