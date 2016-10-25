package com.xunlei.downloadprovider.app.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.a.d;
import org.android.spdy.SpdyAgent;

public class ScrollLayout extends RelativeLayout {
    public static final String a;
    public int b;
    public c c;
    boolean d;
    private int e;
    private Scroller f;
    private int g;
    private int h;
    private float i;
    private float j;
    private float k;
    private boolean l;
    private int m;
    private b n;
    private a o;

    public static interface c {
        void a(int i);
    }

    public static interface a {
    }

    public static interface b {
    }

    static {
        a = ScrollLayout.class.getSimpleName();
    }

    public void setScrollEnable(boolean z) {
        this.l = z;
    }

    public void setOnScrollPageChangeListener(c cVar) {
        this.c = cVar;
    }

    public ScrollLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 0;
        this.l = true;
        this.m = 0;
        this.d = true;
        this.f = new Scroller(context);
        this.b = 0;
        this.h = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.g = d.a(context) / 2;
        setChildrenDrawingCacheEnabled(true);
        setChildrenDrawnWithCacheEnabled(true);
    }

    public ScrollLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrollLayout(Context context) {
        super(context);
        this.e = 0;
        this.l = true;
        this.m = 0;
        this.d = true;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = MeasureSpec.getSize(i);
        int childCount = getChildCount();
        if (1073741824 == MeasureSpec.getMode(i)) {
            for (int i3 = 0; i3 < childCount; i3++) {
                getChildAt(i3).measure(i, i2);
            }
            scrollTo(this.b * (size + this.m), 0);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                childAt.layout(i5, 0, i5 + measuredWidth, childAt.getMeasuredHeight());
                i5 += measuredWidth;
                if (i6 < childCount - 1) {
                    i5 += this.m;
                }
            }
        }
    }

    public void computeScroll() {
        if (this.f.computeScrollOffset()) {
            scrollTo(this.f.getCurrX(), this.f.getCurrY());
            postInvalidate();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.l) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (action) {
            case SpdyAgent.ACCS_TEST_SERVER:
                this.k = x;
                this.i = x;
                this.j = y;
                this.e = this.f.isFinished() ? 0 : 1;
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.e = 0;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                if (((int) Math.abs(x - this.i)) > this.h && Math.abs(y - this.j) < Math.abs(x - this.i)) {
                    this.e = 1;
                }
                break;
        }
        return this.e != 0;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        new StringBuilder().append(getClass()).append("---onTouchEvent---").append(Thread.currentThread().getId());
        if (!this.l) {
            return super.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        switch (action) {
            case SpdyAgent.ACCS_TEST_SERVER:
                if (!this.f.isFinished()) {
                    this.f.abortAnimation();
                }
                this.i = x;
                return true;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.d = true;
                if (x - this.k > ((float) (this.h * 4)) && this.b > 0) {
                    a(this.b - 1);
                } else if (this.k - x > ((float) (this.h * 4)) && this.b < getChildCount() - 1) {
                    a(this.b + 1);
                } else if (getChildCount() > 0) {
                    action = getWidth();
                    a((getScrollX() + (action / 2)) / action);
                }
                this.e = 0;
                return true;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                action = (int) (this.i - x);
                if (this.d) {
                    this.d = false;
                }
                this.i = x;
                if (action < 0) {
                    if (getScrollX() <= 0) {
                        return true;
                    }
                    scrollBy(Math.max(-getScrollX(), action), 0);
                    return true;
                } else if (action <= 0 || getChildCount() <= 0) {
                    return true;
                } else {
                    int right = (getChildAt(getChildCount() - 1).getRight() - getScrollX()) - getWidth();
                    if (right <= 0) {
                        return true;
                    }
                    scrollBy(Math.min(right, action), 0);
                    return true;
                }
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.e = 0;
                return true;
            default:
                return true;
        }
    }

    public int getCurScreen() {
        return this.b;
    }

    public int getMaxScreenSize() {
        return getChildCount();
    }

    public void setPageMarginWidth(int i) {
        this.m = i;
    }

    public void setPageMarginDrawable(int i) {
        setBackgroundResource(i);
    }

    public final boolean a(int i) {
        boolean z = false;
        new StringBuilder().append(getClass()).append("---snapToScreen---whichScreen---").append(i).append("---").append(Thread.currentThread().getId());
        int max = Math.max(0, Math.min(i, getChildCount() - 1));
        if (getScrollX() != getWidth() * max) {
            int width = ((getWidth() + this.m) * max) - getScrollX();
            this.f.startScroll(getScrollX(), 0, width, 0, Math.abs(width) > this.g ? this.g : Math.abs(width));
            this.b = max;
            new StringBuilder("curScreen: ").append(this.b);
            z = true;
            invalidate();
        }
        if (this.c != null) {
            this.c.a(max);
        }
        return z;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void setBeginScrollListener(b bVar) {
        this.n = bVar;
    }

    public void setOnAttachedToWindowListener(a aVar) {
        this.o = aVar;
    }
}
