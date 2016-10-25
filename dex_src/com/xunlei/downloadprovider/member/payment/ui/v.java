package com.xunlei.downloadprovider.member.payment.ui;

import android.support.v4.view.ViewPager.OnPageChangeListener;

// compiled from: BasePayPagerActivity.java
final class v implements OnPageChangeListener {
    final /* synthetic */ BasePayPagerActivity a;

    v(BasePayPagerActivity basePayPagerActivity) {
        this.a = basePayPagerActivity;
    }

    public final void onPageScrolled(int i, float f, int i2) {
    }

    public final void onPageSelected(int i) {
        if (this.a.g != null) {
            this.a.g.setCurrentTab(i);
        }
        new StringBuilder("onPageSelected--position=").append(i).append("|this=").append(this.a);
    }

    public final void onPageScrollStateChanged(int i) {
    }
}
