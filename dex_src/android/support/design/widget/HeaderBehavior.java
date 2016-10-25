package android.support.design.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

abstract class HeaderBehavior<V extends View> extends ViewOffsetBehavior<V> {
    private Runnable a;
    private ScrollerCompat b;
    private boolean c;
    private int d;
    private int e;
    private int f;
    private VelocityTracker g;

    private class a implements Runnable {
        private final CoordinatorLayout b;
        private final V c;

        a(CoordinatorLayout coordinatorLayout, V v) {
            this.b = coordinatorLayout;
            this.c = v;
        }

        public final void run() {
            if (this.c != null && HeaderBehavior.this.b != null) {
                if (HeaderBehavior.this.b.computeScrollOffset()) {
                    HeaderBehavior.this.a_(this.b, this.c, HeaderBehavior.this.b.getCurrY());
                    ViewCompat.postOnAnimation(this.c, this);
                    return;
                }
                HeaderBehavior.this.a(this.b, this.c);
            }
        }
    }

    public HeaderBehavior() {
        this.d = -1;
        this.f = -1;
    }

    public HeaderBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = -1;
        this.f = -1;
    }

    public final boolean a(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (this.f < 0) {
            this.f = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getAction() == 2 && this.c) {
            return true;
        }
        int y;
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case SpdyAgent.ACCS_TEST_SERVER:
                this.c = false;
                int x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();
                if (b() && coordinatorLayout.a((View) v, x, y)) {
                    this.e = y;
                    this.d = MotionEventCompat.getPointerId(motionEvent, 0);
                    d();
                }
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.c = false;
                this.d = -1;
                if (this.g != null) {
                    this.g.recycle();
                    this.g = null;
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                y = this.d;
                if (y != -1) {
                    y = MotionEventCompat.findPointerIndex(motionEvent, y);
                    if (y != -1) {
                        y = (int) MotionEventCompat.getY(motionEvent, y);
                        if (Math.abs(y - this.e) > this.f) {
                            this.c = true;
                            this.e = y;
                        }
                    }
                }
                break;
        }
        if (this.g != null) {
            this.g.addMovement(motionEvent);
        }
        return this.c;
    }

    public final boolean b(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (this.f < 0) {
            this.f = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case SpdyAgent.ACCS_TEST_SERVER:
                int y = (int) motionEvent.getY();
                if (!coordinatorLayout.a((View) v, (int) motionEvent.getX(), y) || !b()) {
                    return false;
                }
                this.e = y;
                this.d = MotionEventCompat.getPointerId(motionEvent, 0);
                d();
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                if (this.g != null) {
                    this.g.addMovement(motionEvent);
                    this.g.computeCurrentVelocity(IHost.HOST_NOFITY_REFRESH_LIST);
                    a(coordinatorLayout, v, -a((View) v), VelocityTrackerCompat.getYVelocity(this.g, this.d));
                }
                this.c = false;
                this.d = -1;
                if (this.g != null) {
                    this.g.recycle();
                    this.g = null;
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.d);
                if (findPointerIndex == -1) {
                    return false;
                }
                findPointerIndex = (int) MotionEventCompat.getY(motionEvent, findPointerIndex);
                int i = this.e - findPointerIndex;
                if (!this.c && Math.abs(i) > this.f) {
                    this.c = true;
                    i = i > 0 ? i - this.f : i + this.f;
                }
                if (this.c) {
                    this.e = findPointerIndex;
                    b(coordinatorLayout, v, i, b(v), 0);
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.c = false;
                this.d = -1;
                if (this.g != null) {
                    this.g.recycle();
                    this.g = null;
                }
                break;
        }
        if (this.g != null) {
            this.g.addMovement(motionEvent);
        }
        return true;
    }

    final int a_(CoordinatorLayout coordinatorLayout, V v, int i) {
        return a(coordinatorLayout, v, i, ExploreByTouchHelper.INVALID_ID, InMobiClientPositioning.NO_REPEAT);
    }

    int a(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3) {
        int c = c();
        if (i2 == 0 || c < i2 || c > i3) {
            return 0;
        }
        int a = ad.a(i, i2, i3);
        if (c == a) {
            return 0;
        }
        a(a);
        return c - a;
    }

    int a() {
        return c();
    }

    final int b(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3) {
        return a(coordinatorLayout, v, a() - i, i2, i3);
    }

    final boolean a(CoordinatorLayout coordinatorLayout, V v, int i, float f) {
        if (this.a != null) {
            v.removeCallbacks(this.a);
            this.a = null;
        }
        if (this.b == null) {
            this.b = ScrollerCompat.create(v.getContext());
        }
        this.b.fling(0, c(), 0, Math.round(f), 0, 0, i, 0);
        if (this.b.computeScrollOffset()) {
            this.a = new a(coordinatorLayout, v);
            ViewCompat.postOnAnimation(v, this.a);
            return true;
        }
        a(coordinatorLayout, v);
        return false;
    }

    void a(CoordinatorLayout coordinatorLayout, V v) {
    }

    boolean b() {
        return false;
    }

    int b(V v) {
        return -v.getHeight();
    }

    int a(V v) {
        return v.getHeight();
    }

    private void d() {
        if (this.g == null) {
            this.g = VelocityTracker.obtain();
        }
    }
}
