package com.xunlei.tdlive.control;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListAdapter;
import android.widget.Scroller;
import java.util.LinkedList;
import java.util.Queue;

public class HorizontialListView extends AdapterView<ListAdapter> {
    protected ListAdapter a;
    protected int b;
    protected int c;
    protected Scroller d;
    private int e;
    private int f;
    private int g;
    private int h;
    private GestureDetector i;
    private Queue<View> j;
    private OnItemSelectedListener k;
    private OnItemClickListener l;
    private boolean m;
    public boolean mAlwaysOverrideTouch;
    private DataSetObserver n;
    private OnGestureListener o;

    public HorizontialListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAlwaysOverrideTouch = true;
        this.e = -1;
        this.f = 0;
        this.g = Integer.MAX_VALUE;
        this.h = 0;
        this.j = new LinkedList();
        this.m = false;
        this.n = new j(this);
        this.o = new l(this);
        a();
    }

    private synchronized void a() {
        this.e = -1;
        this.f = 0;
        this.h = 0;
        this.b = 0;
        this.c = 0;
        this.g = Integer.MAX_VALUE;
        this.d = new Scroller(getContext());
        this.i = new GestureDetector(getContext(), this.o);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.k = onItemSelectedListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.l = onItemClickListener;
    }

    public ListAdapter getAdapter() {
        return this.a;
    }

    public View getSelectedView() {
        return null;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.a != null) {
            this.a.unregisterDataSetObserver(this.n);
        }
        this.a = listAdapter;
        this.a.registerDataSetObserver(this.n);
        b();
    }

    private synchronized void b() {
        a();
        removeAllViewsInLayout();
        requestLayout();
    }

    public void setSelection(int i) {
    }

    private void a(View view, int i) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -1);
        }
        addViewInLayout(view, i, layoutParams, true);
        view.measure(MeasureSpec.makeMeasureSpec(getWidth(), Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(getHeight(), Integer.MIN_VALUE));
    }

    protected synchronized void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.a != null) {
            int i5;
            if (this.m) {
                i5 = this.b;
                a();
                removeAllViewsInLayout();
                this.c = i5;
                this.m = false;
            }
            if (this.d.computeScrollOffset()) {
                this.c = this.d.getCurrX();
            }
            if (this.c < 0) {
                this.c = 0;
                this.d.forceFinished(true);
            }
            if (this.c > this.g) {
                this.c = this.g;
                this.d.forceFinished(true);
            }
            i5 = this.b - this.c;
            b(i5);
            a(i5);
            c(i5);
            this.b = this.c;
            if (!this.d.isFinished()) {
                post(new k(this));
            }
        }
    }

    private void a(int i) {
        int right;
        int i2 = 0;
        View childAt = getChildAt(getChildCount() - 1);
        if (childAt != null) {
            right = childAt.getRight();
        } else {
            right = 0;
        }
        a(right, i);
        childAt = getChildAt(0);
        if (childAt != null) {
            i2 = childAt.getLeft();
        }
        b(i2, i);
    }

    private void a(int i, int i2) {
        while (i + i2 < getWidth() && this.f < this.a.getCount()) {
            View view = this.a.getView(this.f, (View) this.j.poll(), this);
            a(view, -1);
            i += view.getMeasuredWidth();
            if (this.f == this.a.getCount() - 1) {
                this.g = (this.b + i) - getWidth();
            }
            this.f++;
        }
    }

    private void b(int i, int i2) {
        while (i + i2 > 0 && this.e >= 0) {
            View view = this.a.getView(this.e, (View) this.j.poll(), this);
            a(view, 0);
            i -= view.getMeasuredWidth();
            this.e--;
            this.h -= view.getMeasuredWidth();
        }
    }

    private void b(int i) {
        View childAt = getChildAt(0);
        while (childAt != null && childAt.getRight() + i <= 0) {
            this.h += childAt.getMeasuredWidth();
            this.j.offer(childAt);
            removeViewInLayout(childAt);
            this.e++;
            childAt = getChildAt(0);
        }
        while (true) {
            childAt = getChildAt(getChildCount() - 1);
            if (childAt != null && childAt.getLeft() + i >= getWidth()) {
                this.j.offer(childAt);
                removeViewInLayout(childAt);
                this.f--;
            } else {
                return;
            }
        }
    }

    private void c(int i) {
        if (getChildCount() > 0) {
            this.h += i;
            int i2 = this.h;
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                int measuredWidth = childAt.getMeasuredWidth();
                childAt.layout(i2, 0, i2 + measuredWidth, childAt.getMeasuredHeight());
                i2 += measuredWidth;
            }
        }
    }

    public synchronized void scrollTo(int i) {
        this.d.startScroll(this.c, 0, i - this.c, 0);
        requestLayout();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.i.onTouchEvent(motionEvent);
    }

    protected boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        synchronized (this) {
            this.d.fling(this.c, 0, (int) (-f), 0, 0, this.g, 0, 0);
        }
        requestLayout();
        return true;
    }

    protected boolean a(MotionEvent motionEvent) {
        this.d.forceFinished(true);
        return true;
    }
}
