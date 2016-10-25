package com.xunlei.tdlive.control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class TouchDetectorFrameLayout extends FrameLayout {
    private a a;

    public static interface a {
        boolean a(MotionEvent motionEvent);
    }

    public void setITouchEventIntercept(a aVar) {
        this.a = aVar;
    }

    public TouchDetectorFrameLayout(Context context) {
        super(context);
    }

    public TouchDetectorFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TouchDetectorFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.a != null) {
            this.a.a(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }
}
