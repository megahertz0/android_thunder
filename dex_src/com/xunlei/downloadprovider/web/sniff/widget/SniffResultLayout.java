package com.xunlei.downloadprovider.web.sniff.widget;

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
import android.widget.ListView;
import android.widget.Scroller;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class SniffResultLayout extends ViewGroup {
    int a;
    float b;
    float c;
    private final float d;
    private final int e;
    private final int f;
    private final float g;
    private final int h;
    private Scroller i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;
    private boolean o;
    private VelocityTracker p;
    private int q;
    private int r;
    private int s;
    private int t;
    private a u;
    private int v;
    private int w;
    private boolean x;
    private int y;

    public SniffResultLayout(Context context) {
        this(context, null);
    }

    public SniffResultLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = (float) ViewConfiguration.getTouchSlop();
        this.e = -10;
        this.f = 800;
        this.g = 2.5f;
        this.h = 2;
        this.j = false;
        this.o = false;
        this.w = -1;
        this.b = 0.0f;
        this.c = 0.0f;
        this.i = new Scroller(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BLContainer);
        this.r = obtainStyledAttributes.getDimensionPixelOffset(0, 0);
        this.v = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        this.s = obtainStyledAttributes.getInt(XZBDevice.DOWNLOAD_LIST_RECYCLE, XZBDevice.Success);
        obtainStyledAttributes.recycle();
    }

    public int getContentListId() {
        return this.w;
    }

    public void setContentListId(int i) {
        this.w = i;
    }

    public void computeScroll() {
        if (this.i.computeScrollOffset()) {
            scrollBy(0, this.i.getCurrY() - this.a);
            this.a = getScrollY();
            postInvalidate();
        } else if (this.o) {
            this.o = false;
            if (this.i.getCurrY() > 0) {
                if (this.u != null) {
                    this.u.a(XZBDevice.Fail, this.t);
                }
            } else if (this.i.getCurrY() < 0) {
                if (this.u != null) {
                    this.u.a(XZBDevice.Stop, this.t);
                }
            } else if (this.u != null) {
                this.u.a(XZBDevice.Success, this.t);
            }
            this.a = 0;
        }
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.u != null) {
            int i5 = (this.l - (this.r == 0 ? this.l - this.n : this.r)) - this.n;
            if (i2 > 0) {
                this.u.b((-((float) i2)) / ((float) this.r));
            } else if (i2 < 0) {
                this.u.b((-((float) i2)) / ((float) i5));
            } else {
                this.u.b(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            }
        }
    }

    public void scrollBy(int i, int i2) {
        super.scrollBy(i, i2);
        float scrollY = (float) getScrollY();
        float f = (float) (this.l / 2);
        if (this.u != null && i2 != 0) {
            if (this.r == 0) {
                if (scrollY > f - ((float) this.n)) {
                    this.b = (f - scrollY) / ((float) this.n);
                    if (this.b < 0.0f) {
                    }
                } else if (scrollY < (-(f - ((float) this.n)))) {
                    this.c = (scrollY + f) / ((float) this.n);
                    if (this.c >= 0.0f) {
                        this.u.a(this.c);
                    }
                } else if (this.b != 0.0f && this.b != 1.0f) {
                    this.b = 1.0f;
                } else if (this.c != 0.0f && this.c != 1.0f) {
                    this.c = 1.0f;
                    this.u.a(this.c);
                }
            } else if (scrollY > ((float) (this.r - this.n))) {
                this.b = (((float) this.r) - scrollY) / ((float) this.n);
                if (this.b < 0.0f) {
                }
            } else if (scrollY < (-(((f - ((float) this.r)) + f) - ((float) this.n)))) {
                this.c = (scrollY + (f + (f - ((float) this.r)))) / ((float) this.n);
                if (this.c >= 0.0f) {
                    this.u.a(this.c);
                }
            } else if (this.b != 0.0f && this.b != 1.0f) {
                this.b = 1.0f;
            } else if (this.c != 0.0f && this.c != 1.0f) {
                this.c = 1.0f;
                this.u.a(this.c);
            }
        }
    }

    private void a() {
        int scrollY = getScrollY();
        this.p.computeCurrentVelocity(1);
        float yVelocity = this.p.getYVelocity();
        if (((double) Math.abs(yVelocity)) > 0.1d) {
            if (this.s == 10 && yVelocity < 0.0f) {
                c(yVelocity);
                return;
            } else if (this.s == 11 && yVelocity < 0.0f) {
                a(yVelocity);
                return;
            } else if (this.s == 11 && yVelocity > 0.0f) {
                b(yVelocity);
                return;
            } else if (this.s == 12 && yVelocity > 0.0f) {
                c(yVelocity);
                return;
            }
        }
        if (scrollY > this.k) {
            a(yVelocity);
        } else if (scrollY < this.m) {
            b(yVelocity);
        } else if (scrollY > 0) {
            c(yVelocity);
        } else {
            this.s = 11;
            c(yVelocity);
        }
    }

    private void a(float f) {
        int i;
        if (Math.abs(f) < 2.5f) {
            f = 2.5f;
        }
        int scrollY = getScrollY();
        if (this.r == 0) {
            i = (this.l / 2) - scrollY;
        } else {
            i = this.r - scrollY;
        }
        this.t = this.s;
        this.s = 12;
        this.a = scrollY;
        this.i.startScroll(0, scrollY, 0, i, ((int) Math.abs(((float) i) / f)) * 2);
        invalidate();
    }

    private void b(float f) {
        int i;
        if (Math.abs(f) < 2.5f) {
            f = 2.5f;
        }
        int scrollY = getScrollY();
        this.t = this.s;
        this.s = 10;
        if (this.r == 0) {
            i = ((-this.l) / 2) - scrollY;
        } else {
            i = (((-this.l) / 2) - ((this.l / 2) - this.r)) - scrollY;
        }
        this.a = scrollY;
        this.i.startScroll(0, scrollY, 0, i, ((int) Math.abs(((float) i) / f)) * 2);
        invalidate();
    }

    private void c(float f) {
        if (Math.abs(f) < 2.5f) {
            f = 2.5f;
        }
        int scrollY = getScrollY();
        this.t = this.s;
        this.s = 11;
        int i = -scrollY;
        this.a = scrollY;
        this.i.startScroll(0, scrollY, 0, i, ((int) Math.abs(((float) i) / f)) * 2);
        invalidate();
    }

    public void setIdleY(int i) {
        this.r = i;
        invalidate();
    }

    public final void a(int i, boolean z) {
        this.t = this.s;
        this.s = i;
        this.a = getScrollY();
        switch (i) {
            case XZBDevice.Stop:
                if (z) {
                    this.o = true;
                    if (this.r == 0) {
                        this.i.startScroll(0, getScrollY(), 0, (-this.a) - (this.l / 2), XLErrorCode.OAUTH_SCOPE_EXIST);
                    } else {
                        this.i.startScroll(0, getScrollY(), 0, ((-this.a) - (this.l / 2)) - ((this.l / 2) - this.r), XLErrorCode.OAUTH_SCOPE_EXIST);
                    }
                } else if (this.r == 0) {
                    scrollTo(0, (-this.l) / 2);
                } else {
                    scrollTo(0, ((-this.l) / 2) - ((this.l / 2) - this.r));
                }
                break;
            case XZBDevice.Success:
                if (z) {
                    this.o = true;
                    this.i.startScroll(0, getScrollY(), 0, -getScrollY(), XLErrorCode.OAUTH_SCOPE_EXIST);
                } else {
                    scrollTo(0, 0);
                }
                break;
            case XZBDevice.Fail:
                if (z) {
                    this.o = true;
                    if (this.r == 0) {
                        this.i.startScroll(0, getScrollY(), 0, (this.l / 2) + (-this.a), XLErrorCode.OAUTH_SCOPE_EXIST);
                    } else {
                        this.i.startScroll(0, getScrollY(), 0, this.r + (-this.a), XLErrorCode.OAUTH_SCOPE_EXIST);
                    }
                } else {
                    scrollTo(0, this.l / 2);
                }
                break;
        }
        invalidate();
    }

    public void setListener(a aVar) {
        this.u = aVar;
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

    private boolean b() {
        ListView listView;
        View childAt = getChildAt(1);
        if (this.w != -1) {
            listView = (ListView) childAt.findViewById(this.w);
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
            View findViewById = getChildAt(1).findViewById(this.w);
            if (findViewById != null && (findViewById instanceof ListView)) {
                return (ListView) findViewById;
            }
        }
        return null;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 && this.x) {
            this.x = false;
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
        } else if (this.x) {
            onTouchEvent(motionEvent);
            return true;
        } else {
            boolean z;
            if (getScrollY() == (this.r == 0 ? this.l / 2 : this.r) && a(motionEvent) && actionMasked == 2) {
                actionMasked = (int) (motionEvent.getY() - ((float) this.y));
                if (motionEvent.getHistorySize() > 0) {
                    z = ((float) ((int) (motionEvent.getY() - ((float) ((int) motionEvent.getHistoricalY(0)))))) > this.d && !b();
                } else if (((float) actionMasked) > this.d) {
                    z = !b();
                }
                if (z) {
                    return super.dispatchTouchEvent(motionEvent);
                }
                if (onInterceptTouchEvent(motionEvent)) {
                    return super.dispatchTouchEvent(motionEvent);
                }
                if (this.i.isFinished()) {
                    this.i.forceFinished(true);
                    return true;
                }
                this.q = motionEvent.getPointerId(0);
                this.y = (int) motionEvent.getY();
                this.x = true;
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
            if (this.i.isFinished()) {
                this.q = motionEvent.getPointerId(0);
                this.y = (int) motionEvent.getY();
                this.x = true;
                onTouchEvent(motionEvent);
                return true;
            }
            this.i.forceFinished(true);
            return true;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (this.j) {
            return true;
        }
        if (actionMasked == 2 && this.j) {
            return true;
        }
        if (this.s == 12 && b(motionEvent) && !a(motionEvent)) {
            return false;
        }
        if (getScrollY() == (this.r == 0 ? this.l / 2 : this.r) && a(motionEvent) && actionMasked == 2) {
            actionMasked = (int) (motionEvent.getY() - ((float) this.y));
            if (motionEvent.getHistorySize() > 0) {
                return ((float) ((int) (motionEvent.getY() - ((float) ((int) motionEvent.getHistoricalY(0)))))) > this.d && !b();
            } else if (((float) actionMasked) > this.d) {
                return !b();
            } else {
                return false;
            }
        }
        boolean z;
        switch (actionMasked) {
            case SpdyAgent.ACCS_TEST_SERVER:
                if (b(motionEvent)) {
                    if (this.i.isFinished()) {
                        this.q = motionEvent.getPointerId(0);
                        this.y = (int) motionEvent.getY();
                        z = false;
                    } else {
                        this.i.forceFinished(true);
                        return true;
                    }
                }
                z = false;
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.q = -10;
                if (this.j) {
                    z = true;
                }
                z = false;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                int abs = Math.abs((int) (motionEvent.getY() - ((float) this.y)));
                if (!b(motionEvent) || ((float) abs) <= this.d) {
                    this.j = false;
                    z = false;
                } else {
                    this.j = true;
                    z = true;
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                if (b(motionEvent) && this.q == -10) {
                    this.q = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.y = (int) motionEvent.getY();
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

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = 0;
        if (!b(motionEvent) && !this.j) {
            return false;
        }
        switch (motionEvent.getAction() & 255) {
            case SpdyAgent.ACCS_TEST_SERVER:
                this.y = (int) motionEvent.getY();
                this.q = motionEvent.getPointerId(0);
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.o = true;
                this.j = false;
                a();
                this.p.clear();
                this.q = -10;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.j = true;
                int historySize = motionEvent.getHistorySize();
                if (historySize == 0) {
                    i = (int) motionEvent.getY();
                    historySize = this.y - i;
                    this.y = i;
                    a(historySize);
                } else {
                    while (i < historySize) {
                        int historicalY = (int) motionEvent.getHistoricalY(i);
                        int i2 = this.y - historicalY;
                        this.y = historicalY;
                        a(i2);
                        i++;
                    }
                }
                this.p.addMovement(motionEvent);
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                if (this.q == -10) {
                    if (!this.i.isFinished()) {
                        this.i.forceFinished(true);
                    }
                    this.q = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.y = (int) motionEvent.getY();
                }
                break;
            case com.xunlei.tdlive.R.styleable.Toolbar_contentInsetEnd:
                if (this.q != -10 && motionEvent.getPointerId(motionEvent.getActionIndex()) == this.q) {
                    this.o = true;
                    this.j = false;
                    a();
                    this.p.clear();
                    this.q = -10;
                }
                break;
        }
        return true;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.p = VelocityTracker.obtain();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.p.recycle();
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
            if (this.r != 0) {
                i6 = this.r;
            } else {
                i6 = ((i4 - i2) - measuredHeight) / 2;
            }
            childAt.layout(i, i6, i3, i6 + measuredHeight);
            i5 = i6 + measuredHeight;
            if (this.k == 0 || this.m == 0 || this.l == 0) {
                if (this.r == 0) {
                    this.k = (i4 - i2) / 4;
                    this.m = -this.k;
                } else {
                    this.k = this.r / 2;
                    this.m = ((-(i4 - i2)) / 2) + this.r;
                }
                this.l = (getMeasuredHeight() - measuredHeight) - this.v;
                this.n = childAt.getMeasuredHeight();
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
        if (this.r == 0) {
            if (getScrollY() + i > this.l / 2) {
                i = (this.l / 2) - getScrollY();
            } else if (getScrollY() + i < (-this.l) / 2) {
                i = ((-this.l) / 2) - getScrollY();
            }
        } else if (getScrollY() + i > this.r) {
            i = this.r - getScrollY();
        } else if (getScrollY() + i < ((-this.l) / 2) - ((this.l / 2) - this.r)) {
            i = (((-this.l) / 2) - ((this.l / 2) - this.r)) - getScrollY();
        }
        scrollBy(0, i);
    }

    public int getVisibilityState() {
        return this.s;
    }
}
