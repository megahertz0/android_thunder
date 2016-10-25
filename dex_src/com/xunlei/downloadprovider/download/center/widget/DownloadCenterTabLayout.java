package com.xunlei.downloadprovider.download.center.widget;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class DownloadCenterTabLayout extends TabLayout {
    private boolean a;

    public DownloadCenterTabLayout(Context context) {
        super(context);
        this.a = true;
    }

    public DownloadCenterTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = true;
    }

    public DownloadCenterTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = true;
    }

    public void setTabLayoutEnable(boolean z) {
        this.a = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.a ? super.onTouchEvent(motionEvent) : false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.a ? super.onInterceptTouchEvent(motionEvent) : true;
    }
}
