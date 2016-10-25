package com.xunlei.tdlive.control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.FrameLayout;

@SuppressLint({"ClickableViewAccessibility"})
public class GestureDetectorFrameLayout extends FrameLayout implements OnGestureListener {
    private GestureDetectorCompat a;
    private OnGestureListener b;

    public GestureDetectorFrameLayout(Context context) {
        super(context);
    }

    public GestureDetectorFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GestureDetectorFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnGestureListener(OnGestureListener onGestureListener) {
        this.b = onGestureListener;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.a == null) {
            this.a = new GestureDetectorCompat(getContext(), this);
        }
        return this.a.onTouchEvent(motionEvent);
    }

    public boolean onDown(MotionEvent motionEvent) {
        return this.b != null ? this.b.onDown(motionEvent) : false;
    }

    public void onShowPress(MotionEvent motionEvent) {
        if (this.b != null) {
            this.b.onShowPress(motionEvent);
        }
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return this.b != null ? this.b.onSingleTapUp(motionEvent) : false;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return this.b != null ? this.b.onScroll(motionEvent, motionEvent2, f, f2) : false;
    }

    public void onLongPress(MotionEvent motionEvent) {
        if (this.b != null) {
            this.b.onLongPress(motionEvent);
        }
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return this.b != null ? this.b.onFling(motionEvent, motionEvent2, f, f2) : false;
    }
}
