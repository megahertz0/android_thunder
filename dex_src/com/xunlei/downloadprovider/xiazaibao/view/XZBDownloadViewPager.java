package com.xunlei.downloadprovider.xiazaibao.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class XZBDownloadViewPager extends ViewPager {
    private static final String a;
    private boolean b;

    static {
        a = XZBDownloadViewPager.class.getSimpleName();
    }

    public XZBDownloadViewPager(Context context) {
        super(context);
        this.b = true;
    }

    public XZBDownloadViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = true;
    }

    public void setCanScroll(boolean z) {
        this.b = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.b ? false : super.onTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return !this.b ? false : super.onInterceptTouchEvent(motionEvent);
    }
}
