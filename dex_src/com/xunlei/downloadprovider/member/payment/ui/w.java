package com.xunlei.downloadprovider.member.payment.ui;

import android.widget.TabHost.OnTabChangeListener;
import com.xunlei.downloadprovider.member.payment.a.j;

// compiled from: BasePayPagerActivity.java
final class w implements OnTabChangeListener {
    final /* synthetic */ BasePayPagerActivity a;

    w(BasePayPagerActivity basePayPagerActivity) {
        this.a = basePayPagerActivity;
    }

    public final void onTabChanged(String str) {
        if (this.a.d != null) {
            this.a.d.setCurrentItem(this.a.g.getCurrentTab());
        }
        this.a.g.getCurrentTabView().setSelected(true);
        this.a.i();
        j.a();
        this.a.d();
    }
}
