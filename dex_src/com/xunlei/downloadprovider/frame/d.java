package com.xunlei.downloadprovider.frame;

import android.widget.TabHost.OnTabChangeListener;

// compiled from: BaseViewPagerFragment.java
final class d implements OnTabChangeListener {
    final /* synthetic */ BaseViewPagerFragment a;

    d(BaseViewPagerFragment baseViewPagerFragment) {
        this.a = baseViewPagerFragment;
    }

    public final void onTabChanged(String str) {
        int currentTab = BaseViewPagerFragment.e(this.a).getCurrentTab();
        if (BaseViewPagerFragment.a(this.a) != null) {
            BaseViewPagerFragment.a(this.a).setCurrentItem(currentTab);
        }
        BaseViewPagerFragment.f(this.a);
        BaseViewPagerFragment.b(this.a, currentTab);
    }
}
