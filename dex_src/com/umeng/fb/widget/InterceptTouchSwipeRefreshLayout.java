package com.umeng.fb.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;

public class InterceptTouchSwipeRefreshLayout extends SwipeRefreshLayout {
    private OnTouchListener a;

    public InterceptTouchSwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.a != null) {
            this.a.onTouch(this, motionEvent);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setInterceptTouch(OnTouchListener onTouchListener) {
        this.a = onTouchListener;
    }
}
