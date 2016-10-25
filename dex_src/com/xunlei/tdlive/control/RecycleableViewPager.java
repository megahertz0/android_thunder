package com.xunlei.tdlive.control;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashSet;

public class RecycleableViewPager extends ViewPager {
    private boolean a;
    private PagerAdapter b;
    private HashSet<OnPageChangeListener> c;
    private OnPageChangeListener d;

    static class a extends PagerAdapter {
        private PagerAdapter a;
        private DataSetObserver b;

        public a(PagerAdapter pagerAdapter) {
            this.b = new q(this);
            this.a = pagerAdapter;
            this.a.registerDataSetObserver(this.b);
        }

        public int getCount() {
            int count = this.a.getCount();
            return count < 2 ? count : count * 5000;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return this.a.isViewFromObject(view, obj);
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            return this.a.instantiateItem(viewGroup, RecycleableViewPager.a(i, this.a.getCount()));
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            this.a.destroyItem(viewGroup, RecycleableViewPager.a(i, this.a.getCount()), obj);
        }

        public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
            this.a.setPrimaryItem(viewGroup, RecycleableViewPager.a(i, this.a.getCount()), obj);
        }

        public int getItemPosition(Object obj) {
            return this.a.getItemPosition(obj);
        }

        public CharSequence getPageTitle(int i) {
            return this.a.getPageTitle(RecycleableViewPager.a(i, this.a.getCount()));
        }

        public float getPageWidth(int i) {
            return this.a.getPageWidth(RecycleableViewPager.a(i, this.a.getCount()));
        }
    }

    public RecycleableViewPager(Context context) {
        super(context);
        this.a = true;
        this.c = new HashSet();
        this.d = new p(this);
    }

    public RecycleableViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = true;
        this.c = new HashSet();
        this.d = new p(this);
    }

    public void setRecycle(boolean z) {
        this.a = z;
        setAdapter(this.b);
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        if (this.a) {
            this.b = pagerAdapter;
            super.setAdapter(new a(pagerAdapter));
            return;
        }
        this.b = pagerAdapter;
        super.setAdapter(pagerAdapter);
    }

    public PagerAdapter getAdapter() {
        return this.b;
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        super.removeOnPageChangeListener(this.d);
        super.addOnPageChangeListener(this.d);
        addOnPageChangeListener(onPageChangeListener);
    }

    public void addOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        super.removeOnPageChangeListener(this.d);
        super.addOnPageChangeListener(this.d);
        this.c.add(onPageChangeListener);
    }

    public void removeOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.c.remove(onPageChangeListener);
        if (this.c.isEmpty()) {
            super.removeOnPageChangeListener(this.d);
        }
    }

    public void step(boolean z) {
        int currentItem = super.getCurrentItem() + 1;
        if (this.a) {
            currentItem %= 5000;
        }
        super.setCurrentItem(currentItem, z);
    }

    public int getCurrentItem() {
        return (!this.a || this.b == null) ? super.getCurrentItem() : a(super.getCurrentItem(), this.b.getCount());
    }

    static int a(int i, int i2) {
        return i2 == 0 ? 0 : i % i2;
    }
}
