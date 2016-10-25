package com.xunlei.downloadprovider.download.center.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class DownloadCenterViewPager extends ViewPager {
    private boolean a;

    public DownloadCenterViewPager(Context context) {
        super(context);
        this.a = true;
    }

    public DownloadCenterViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = true;
    }

    public void setCanScroll(boolean z) {
        this.a = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.a ? super.onTouchEvent(motionEvent) : false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.a ? super.onInterceptTouchEvent(motionEvent) : false;
    }
}
