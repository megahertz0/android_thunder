package com.xunlei.downloadprovider.frame;

import android.support.v4.view.ViewPager.OnPageChangeListener;

// compiled from: BaseViewPagerFragment.java
final class c implements OnPageChangeListener {
    final /* synthetic */ BaseViewPagerFragment a;
    private int b;

    c(BaseViewPagerFragment baseViewPagerFragment) {
        this.a = baseViewPagerFragment;
        this.b = 0;
    }

    public final void onPageScrolled(int i, float f, int i2) {
        int b = BaseViewPagerFragment.b(this.a);
        BaseViewPagerFragment.d(this.a).setTranslationX((float) (b + ((int) (((float) (BaseViewPagerFragment.c(this.a) - b)) * (((float) i) + f)))));
    }

    public final void onPageSelected(int i) {
        BaseViewPagerFragment.a(this.a, i);
        if (BaseViewPagerFragment.e(this.a) != null) {
            BaseViewPagerFragment.e(this.a).setCurrentTab(i);
        }
        if (this.b != i) {
            BasePageFragment c = this.a.c(this.b);
            if (c != null) {
                c.onPageOff();
            }
            this.b = i;
        }
        this.a.b(i);
    }

    public final void onPageScrollStateChanged(int i) {
    }
}
