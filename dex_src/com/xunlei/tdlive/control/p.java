package com.xunlei.tdlive.control;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import java.util.Iterator;

// compiled from: RecycleableViewPager.java
class p implements OnPageChangeListener {
    final /* synthetic */ RecycleableViewPager a;

    p(RecycleableViewPager recycleableViewPager) {
        this.a = recycleableViewPager;
    }

    public void onPageScrolled(int i, float f, int i2) {
        Iterator it = this.a.c.iterator();
        while (it.hasNext()) {
            ((OnPageChangeListener) it.next()).onPageScrolled(RecycleableViewPager.a(i, this.a.b.getCount()), f, i2);
        }
    }

    public void onPageSelected(int i) {
        Iterator it = this.a.c.iterator();
        while (it.hasNext()) {
            ((OnPageChangeListener) it.next()).onPageSelected(RecycleableViewPager.a(i, this.a.b.getCount()));
        }
    }

    public void onPageScrollStateChanged(int i) {
        Iterator it = this.a.c.iterator();
        while (it.hasNext()) {
            ((OnPageChangeListener) it.next()).onPageScrollStateChanged(i);
        }
    }
}
