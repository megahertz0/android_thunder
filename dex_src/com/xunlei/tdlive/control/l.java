package com.xunlei.tdlive.control;

import android.graphics.Rect;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

// compiled from: HorizontialListView.java
class l extends SimpleOnGestureListener {
    final /* synthetic */ HorizontialListView a;

    l(HorizontialListView horizontialListView) {
        this.a = horizontialListView;
    }

    public boolean onDown(MotionEvent motionEvent) {
        return this.a.a(motionEvent);
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return this.a.a(motionEvent, motionEvent2, f, f2);
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        synchronized (this.a) {
            HorizontialListView horizontialListView = this.a;
            horizontialListView.c += (int) f;
        }
        this.a.requestLayout();
        return true;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        Rect rect = new Rect();
        for (int i = 0; i < this.a.getChildCount(); i++) {
            View childAt = this.a.getChildAt(i);
            rect.set(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
            if (rect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                if (this.a.l != null) {
                    this.a.l.onItemClick(this.a, childAt, (this.a.e + 1) + i, this.a.a.getItemId((this.a.e + 1) + i));
                }
                if (this.a.k != null) {
                    this.a.k.onItemSelected(this.a, childAt, (this.a.e + 1) + i, this.a.a.getItemId((this.a.e + 1) + i));
                }
                return true;
            }
        }
        return true;
    }
}
