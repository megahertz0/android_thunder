package com.xunlei.downloadprovider.download.taskDetail;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.AutoScrollHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ListView;
import android.widget.Scroller;
import com.xunlei.downloadprovider.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class TaskDetailDragView extends ViewGroup {
    Scroller a;
    int b;
    boolean c;
    int d;
    int e;
    int f;
    int g;
    float h;
    float i;
    private final float j;
    private final int k;
    private final int l;
    private final float m;
    private final int n;
    private boolean o;
    private int p;
    private int q;
    private int r;
    private VelocityTracker s;
    private int t;
    private boolean u;
    private a v;
    private int w;
    private int x;
    private boolean y;
    private int z;

    public TaskDetailDragView(Context context) {
        super(context);
        this.j = (float) ViewConfiguration.getTouchSlop();
        this.k = -10;
        this.l = 800;
        this.m = 2.5f;
        this.n = 2;
        this.o = false;
        this.c = false;
        this.u = true;
        this.x = -1;
        this.h = 0.0f;
        this.i = 0.0f;
    }

    public TaskDetailDragView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = (float) ViewConfiguration.getTouchSlop();
        this.k = -10;
        this.l = 800;
        this.m = 2.5f;
        this.n = 2;
        this.o = false;
        this.c = false;
        this.u = true;
        this.x = -1;
        this.h = 0.0f;
        this.i = 0.0f;
        this.a = new Scroller(context, new LinearInterpolator());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BLContainer);
        this.d = obtainStyledAttributes.getDimensionPixelOffset(0, 0);
        this.w = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        this.e = obtainStyledAttributes.getInt(XZBDevice.DOWNLOAD_LIST_RECYCLE, XZBDevice.Success);
        obtainStyledAttributes.recycle();
    }

    public TaskDetailDragView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = (float) ViewConfiguration.getTouchSlop();
        this.k = -10;
        this.l = 800;
        this.m = 2.5f;
        this.n = 2;
        this.o = false;
        this.c = false;
        this.u = true;
        this.x = -1;
        this.h = 0.0f;
        this.i = 0.0f;
    }

    public int getContentListId() {
        return this.x;
    }

    public void setContentListId(int i) {
        this.x = i;
        invalidate();
    }

    public void computeScroll() {
        if (this.a.computeScrollOffset()) {
            scrollBy(0, this.a.getCurrY() - this.g);
            this.g = getScrollY();
            postInvalidate();
        } else if (this.c) {
            this.c = false;
            if (this.a.getCurrY() > 0) {
                if (this.v != null) {
                    this.v.a(XZBDevice.Fail);
                }
            } else if (this.a.getCurrY() < 0) {
                if (this.v != null) {
                    this.v.a(XZBDevice.Stop);
                }
            } else if (this.v != null) {
                this.v.a(XZBDevice.Success);
            }
            this.g = 0;
        }
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.v != null) {
            int i5 = (this.b - (this.d == 0 ? this.b - this.r : this.d)) - this.r;
            if (i2 > 0) {
                this.v.a((-((float) i2)) / ((float) this.d));
            } else if (i2 < 0) {
                this.v.a((-((float) i2)) / ((float) i5));
            } else {
                this.v.a(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            }
        }
    }

    public void scrollBy(int i, int i2) {
        super.scrollBy(i, i2);
        float scrollY = (float) getScrollY();
        float f = (float) (this.b / 2);
        if (this.v != null && i2 != 0) {
            if (this.d == 0) {
                if (scrollY > f - ((float) this.r)) {
                    this.h = (f - scrollY) / ((float) this.r);
                    if (this.h < 0.0f) {
                    }
                } else if (scrollY < (-(f - ((float) this.r)))) {
                    this.i = (scrollY + f) / ((float) this.r);
                    if (this.i < 0.0f) {
                    }
                } else if (this.h != 0.0f && this.h != 1.0f) {
                    this.h = 1.0f;
                } else if (this.i != 0.0f && this.i != 1.0f) {
                    this.i = 1.0f;
                }
            } else if (scrollY > ((float) (this.d - this.r))) {
                this.h = (((float) this.d) - scrollY) / ((float) this.r);
                if (this.h < 0.0f) {
                }
            } else if (scrollY < (-(((f - ((float) this.d)) + f) - ((float) this.r)))) {
                this.i = (scrollY + (f + (f - ((float) this.d)))) / ((float) this.r);
                if (this.i < 0.0f) {
                }
            } else if (this.h != 0.0f && this.h != 1.0f) {
                this.h = 1.0f;
            } else if (this.i != 0.0f && this.i != 1.0f) {
                this.i = 1.0f;
            }
        }
    }

    public void setScrollEnable(boolean z) {
        this.u = z;
    }

    private void b() {
        int scrollY = getScrollY();
        this.s.computeCurrentVelocity(1);
        float yVelocity = this.s.getYVelocity();
        if (((double) Math.abs(yVelocity)) > 0.1d) {
            if (this.e == 10 && yVelocity < 0.0f) {
                c(yVelocity);
                return;
            } else if (this.e == 11 && yVelocity < 0.0f) {
                a(yVelocity);
                return;
            } else if (this.e == 11 && yVelocity > 0.0f) {
                b(yVelocity);
                return;
            } else if (this.e == 12 && yVelocity > 0.0f && scrollY > this.q) {
                c(yVelocity);
                return;
            }
        }
        if (scrollY > this.p) {
            a(yVelocity);
        } else if (scrollY < this.q) {
            b(yVelocity);
        } else if (scrollY > 0) {
            c(yVelocity);
        } else {
            this.e = 11;
            c(yVelocity);
        }
    }

    private void a(float f) {
        int i;
        if (Math.abs(f) < 2.5f) {
            f = 2.5f;
        }
        int scrollY = getScrollY();
        if (this.d == 0) {
            i = (this.b / 2) - scrollY;
        } else {
            i = this.d - scrollY;
        }
        this.f = this.e;
        this.e = 12;
        this.g = scrollY;
        this.a.startScroll(0, scrollY, 0, i, ((int) Math.abs(((float) i) / f)) * 2);
        invalidate();
    }

    private void b(float f) {
        int i;
        if (Math.abs(f) < 2.5f) {
            f = 2.5f;
        }
        int scrollY = getScrollY();
        this.f = this.e;
        this.e = 10;
        if (this.d == 0) {
            i = ((-this.b) / 2) - scrollY;
        } else {
            i = (((-this.b) / 2) - ((this.b / 2) - this.d)) - scrollY;
        }
        this.g = scrollY;
        int abs = (int) (0.2f * Math.abs(((float) i) - (((float) this.r) / f)));
        this.a.startScroll(0, scrollY, 0, i - this.r, abs);
        invalidate();
    }

    private void c(float f) {
        if (Math.abs(f) < 2.5f) {
            f = 2.5f;
        }
        int scrollY = getScrollY();
        this.f = this.e;
        this.e = 11;
        int i = -scrollY;
        this.g = scrollY;
        this.a.startScroll(0, scrollY, 0, i, ((int) Math.abs(((float) i) / f)) * 2);
        invalidate();
    }

    public void setIdleY(int i) {
        this.d = i;
        invalidate();
    }

    public void setListener(a aVar) {
        this.v = aVar;
    }

    public final void a() {
        scrollTo(0, 0);
        this.f = 11;
        this.e = 11;
    }

    public void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        if (childCount > 2) {
            throw new UnsupportedOperationException("child count incorrect!");
        }
        if (childCount == 2) {
            View childAt = getChildAt(0);
            measureChild(childAt, i, i2);
            measureChild(getChildAt(1), i, MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i2) - childAt.getMeasuredHeight(), 1073741824));
        }
        setMeasuredDimension(MeasureSpec.getSize(i), MeasureSpec.getSize(i2));
    }

    private boolean c() {
        ListView listView;
        View childAt = getChildAt(1);
        if (this.x != -1) {
            listView = (ListView) childAt.findViewById(this.x);
        } else {
            listView = null;
        }
        if (listView == null) {
            return childAt.getScrollY() < 0;
        } else {
            boolean z = listView.getFirstVisiblePosition() > 0;
            if (z || getChildCount() <= 0) {
                return z;
            }
            childAt = listView.getChildAt(0);
            if (childAt != null) {
                return childAt.getTop() < listView.getPaddingTop();
            } else {
                return z;
            }
        }
    }

    private ListView getListView() {
        if (getChildCount() > 0) {
            View findViewById = getChildAt(1).findViewById(this.x);
            if (findViewById != null && (findViewById instanceof ListView)) {
                return (ListView) findViewById;
            }
        }
        return null;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 && this.y) {
            this.y = false;
            onTouchEvent(motionEvent);
            ListView listView = getListView();
            if (listView == null) {
                return true;
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction(XZBDevice.DOWNLOAD_LIST_FAILED);
            listView.onTouchEvent(obtain);
            for (int i = 0; i < listView.getChildCount(); i++) {
                View childAt = listView.getChildAt(i);
                if (childAt != null) {
                    childAt.setPressed(false);
                }
            }
            return true;
        } else if (this.y) {
            onTouchEvent(motionEvent);
            return true;
        } else {
            boolean z;
            if (getScrollY() == (this.d == 0 ? this.b / 2 : this.d) && a(motionEvent) && actionMasked == 2) {
                actionMasked = (int) (motionEvent.getY() - ((float) this.z));
                if (motionEvent.getHistorySize() > 0) {
                    z = ((float) ((int) (motionEvent.getY() - ((float) ((int) motionEvent.getHistoricalY(0)))))) > this.j && !c();
                } else if (((float) actionMasked) > this.j) {
                    z = !c();
                }
                if (z) {
                    return super.dispatchTouchEvent(motionEvent);
                }
                if (onInterceptTouchEvent(motionEvent)) {
                    return super.dispatchTouchEvent(motionEvent);
                }
                if (this.a.isFinished()) {
                    this.a.forceFinished(true);
                    return true;
                }
                this.t = motionEvent.getPointerId(0);
                this.z = (int) motionEvent.getY();
                this.y = true;
                onTouchEvent(motionEvent);
                return true;
            }
            z = false;
            if (z) {
                return super.dispatchTouchEvent(motionEvent);
            }
            if (onInterceptTouchEvent(motionEvent)) {
                return super.dispatchTouchEvent(motionEvent);
            }
            if (this.a.isFinished()) {
                this.t = motionEvent.getPointerId(0);
                this.z = (int) motionEvent.getY();
                this.y = true;
                onTouchEvent(motionEvent);
                return true;
            }
            this.a.forceFinished(true);
            return true;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (!this.u || c()) {
            return false;
        }
        if (this.o) {
            return true;
        }
        if (actionMasked == 2 && this.o) {
            return true;
        }
        if (this.e == 12 && b(motionEvent) && !a(motionEvent)) {
            return false;
        }
        if (getScrollY() == (this.d == 0 ? this.b / 2 : this.d) && a(motionEvent) && actionMasked == 2) {
            actionMasked = (int) (motionEvent.getY() - ((float) this.z));
            if (motionEvent.getHistorySize() <= 0) {
                return ((float) actionMasked) > this.j && !c();
            } else {
                return ((float) ((int) (motionEvent.getY() - ((float) ((int) motionEvent.getHistoricalY(0)))))) > this.j && !c();
            }
        } else {
            boolean z;
            switch (actionMasked) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    if (b(motionEvent)) {
                        if (this.a.isFinished()) {
                            this.t = motionEvent.getPointerId(0);
                            this.z = (int) motionEvent.getY();
                            z = false;
                        } else {
                            this.a.forceFinished(true);
                            return true;
                        }
                    }
                    z = false;
                    break;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.t = -10;
                    if (this.o) {
                        z = true;
                    }
                    z = false;
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    int abs = Math.abs((int) (motionEvent.getY() - ((float) this.z)));
                    if (!b(motionEvent) || ((float) abs) <= this.j) {
                        this.o = false;
                        z = false;
                    } else {
                        this.o = true;
                        z = true;
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    if (b(motionEvent) && this.t == -10) {
                        this.t = motionEvent.getPointerId(motionEvent.getActionIndex());
                        this.z = (int) motionEvent.getY();
                        z = false;
                    }
                    z = false;
                    break;
                default:
                    z = false;
                    break;
            }
            return z;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = 0;
        if ((!b(motionEvent) && !this.o) || !this.u) {
            return false;
        }
        switch (motionEvent.getAction() & 255) {
            case SpdyAgent.ACCS_TEST_SERVER:
                this.z = (int) motionEvent.getY();
                this.t = motionEvent.getPointerId(0);
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.c = true;
                this.o = false;
                b();
                this.s.clear();
                this.t = -10;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.o = true;
                int historySize = motionEvent.getHistorySize();
                if (historySize == 0) {
                    i = (int) motionEvent.getY();
                    historySize = this.z - i;
                    this.z = i;
                    a(historySize);
                } else {
                    while (i < historySize) {
                        int historicalY = (int) motionEvent.getHistoricalY(i);
                        int i2 = this.z - historicalY;
                        this.z = historicalY;
                        a(i2);
                        i++;
                    }
                }
                this.s.addMovement(motionEvent);
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                if (this.t == -10) {
                    if (!this.a.isFinished()) {
                        this.a.forceFinished(true);
                    }
                    this.t = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.z = (int) motionEvent.getY();
                }
                break;
            case com.xunlei.tdlive.R.styleable.Toolbar_contentInsetEnd:
                if (this.t != -10 && motionEvent.getPointerId(motionEvent.getActionIndex()) == this.t) {
                    this.c = true;
                    this.o = false;
                    b();
                    this.s.clear();
                    this.t = -10;
                }
                break;
        }
        return true;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.s = VelocityTracker.obtain();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.s.recycle();
    }

    private boolean a(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        return x > 0 && x < getMeasuredWidth() && ((int) motionEvent.getY()) > getChildAt(0).getMeasuredHeight();
    }

    private boolean b(MotionEvent motionEvent) {
        View childAt = getChildAt(0);
        int x = (int) motionEvent.getX();
        return x > 0 && x < childAt.getRight() && ((int) motionEvent.getY()) > childAt.getTop() - getScrollY();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6 = 0;
        View childAt = getChildAt(0);
        if (childAt != null) {
            int measuredHeight = childAt.getMeasuredHeight();
            if (this.d != 0) {
                i6 = this.d;
            } else {
                i6 = ((i4 - i2) - measuredHeight) / 2;
            }
            childAt.layout(i, i6, i3, i6 + measuredHeight);
            i5 = i6 + measuredHeight;
            if (this.p == 0 || this.q == 0 || this.b == 0) {
                if (this.d == 0) {
                    this.p = (i4 - i2) / 4;
                    this.q = -this.p;
                } else {
                    this.p = this.d / 2;
                    this.q = ((-(i4 - i2)) / 2) + this.d;
                }
                this.b = (getMeasuredHeight() - measuredHeight) - this.w;
                this.r = childAt.getMeasuredHeight();
            }
        } else {
            i5 = 0;
        }
        if (getChildCount() == 2 && i5 != 0) {
            childAt = getChildAt(1);
            if (childAt != null) {
                childAt.layout(i, i5, i3, i6 + i4);
            }
        }
    }

    private void a(int i) {
        if (this.d == 0) {
            if (getScrollY() + i > this.b / 2) {
                i = (this.b / 2) - getScrollY();
            } else if (getScrollY() + i < (-this.b) / 2) {
                i = ((-this.b) / 2) - getScrollY();
            }
        } else if (getScrollY() + i > this.d) {
            i = this.d - getScrollY();
        } else if (getScrollY() + i < ((-this.b) / 2) - ((this.b / 2) - this.d)) {
            i = (((-this.b) / 2) - ((this.b / 2) - this.d)) - getScrollY();
        }
        scrollBy(0, i);
    }

    public int getVisibilityState() {
        return this.e;
    }
}
