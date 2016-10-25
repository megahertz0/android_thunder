package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.design.R;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.design.widget.CoordinatorLayout.d;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import org.android.spdy.SpdyAgent;

public class BottomSheetBehavior<V extends View> extends Behavior<V> {
    a a;
    private float b;
    private int c;
    private int d;
    private int e;
    private boolean f;
    private int g;
    private ViewDragHelper h;
    private boolean i;
    private int j;
    private boolean k;
    private int l;
    private WeakReference<V> m;
    private WeakReference<View> n;
    private VelocityTracker o;
    private int p;
    private int q;
    private boolean r;
    private final Callback s;

    protected static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        final int a;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, int i) {
            super(parcelable);
            this.a = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }

        static {
            CREATOR = new f();
        }
    }

    public static abstract class a {
        public abstract void a(int i);
    }

    private class b implements Runnable {
        private final View b;
        private final int c;

        b(View view, int i) {
            this.b = view;
            this.c = i;
        }

        public final void run() {
            if (BottomSheetBehavior.this.h == null || !BottomSheetBehavior.this.h.continueSettling(true)) {
                BottomSheetBehavior.this.a(this.c);
            } else {
                ViewCompat.postOnAnimation(this.b, this);
            }
        }
    }

    public BottomSheetBehavior() {
        this.g = 4;
        this.s = new e(this);
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = 4;
        this.s = new e(this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BottomSheetBehavior_Params);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Params_behavior_peekHeight, 0);
        this.c = Math.max(0, dimensionPixelSize);
        this.e = this.l - dimensionPixelSize;
        this.f = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Params_behavior_hideable, false);
        obtainStyledAttributes.recycle();
        this.b = (float) ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public final Parcelable b(CoordinatorLayout coordinatorLayout, V v) {
        return new SavedState(super.b(coordinatorLayout, v), this.g);
    }

    public final void a(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.a(coordinatorLayout, (View) v, savedState.getSuperState());
        if (savedState.a == 1 || savedState.a == 2) {
            this.g = 4;
        } else {
            this.g = savedState.a;
        }
    }

    public final boolean a(CoordinatorLayout coordinatorLayout, V v, int i) {
        if (!(this.g == 1 || this.g == 2)) {
            if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v)) {
                ViewCompat.setFitsSystemWindows(v, true);
            }
            coordinatorLayout.a((View) v, i);
        }
        this.l = coordinatorLayout.getHeight();
        this.d = Math.max(0, this.l - v.getHeight());
        this.e = Math.max(this.l - this.c, this.d);
        if (this.g == 3) {
            ViewCompat.offsetTopAndBottom(v, this.d);
        } else if (this.f && this.g == 5) {
            ViewCompat.offsetTopAndBottom(v, this.l);
        } else if (this.g == 4) {
            ViewCompat.offsetTopAndBottom(v, this.e);
        }
        if (this.h == null) {
            this.h = ViewDragHelper.create(coordinatorLayout, this.s);
        }
        this.m = new WeakReference(v);
        this.n = new WeakReference(c((View) v));
        return true;
    }

    public final boolean a(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        View view;
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            a();
        }
        if (this.o == null) {
            this.o = VelocityTracker.obtain();
        }
        this.o.addMovement(motionEvent);
        switch (actionMasked) {
            case SpdyAgent.ACCS_TEST_SERVER:
                int x = (int) motionEvent.getX();
                this.q = (int) motionEvent.getY();
                view = (View) this.n.get();
                if (view != null && coordinatorLayout.a(view, x, this.q)) {
                    this.p = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.r = true;
                }
                boolean z = this.p == -1 && !coordinatorLayout.a((View) v, x, this.q);
                this.i = z;
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.r = false;
                this.p = -1;
                if (this.i) {
                    this.i = false;
                    return false;
                }
        }
        if (!this.i && this.h.shouldInterceptTouchEvent(motionEvent)) {
            return true;
        }
        view = (View) this.n.get();
        return (actionMasked != 2 || view == null || this.i || this.g == 1 || coordinatorLayout.a(view, (int) motionEvent.getX(), (int) motionEvent.getY()) || Math.abs(((float) this.q) - motionEvent.getY()) <= ((float) this.h.getTouchSlop())) ? false : true;
    }

    public final boolean b(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (this.g == 1 && actionMasked == 0) {
            return true;
        }
        this.h.processTouchEvent(motionEvent);
        if (actionMasked == 0) {
            a();
        }
        if (this.o == null) {
            this.o = VelocityTracker.obtain();
        }
        this.o.addMovement(motionEvent);
        if (actionMasked == 2 && !this.i && Math.abs(((float) this.q) - motionEvent.getY()) > ((float) this.h.getTouchSlop())) {
            this.h.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.i;
    }

    public final boolean a(CoordinatorLayout coordinatorLayout, V v, View view, int i) {
        this.j = 0;
        this.k = false;
        return (i & 2) != 0;
    }

    public final void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int[] iArr) {
        if (view == ((View) this.n.get())) {
            int top = v.getTop();
            int i2 = top - i;
            if (i > 0) {
                if (i2 < this.d) {
                    iArr[1] = top - this.d;
                    ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                    a((int) XZBDevice.DOWNLOAD_LIST_FAILED);
                } else {
                    iArr[1] = i;
                    ViewCompat.offsetTopAndBottom(v, -i);
                    a(1);
                }
            } else if (i < 0 && !ViewCompat.canScrollVertically(view, -1)) {
                if (i2 <= this.e || this.f) {
                    iArr[1] = i;
                    ViewCompat.offsetTopAndBottom(v, -i);
                    a(1);
                } else {
                    iArr[1] = top - this.e;
                    ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                    a((int) XZBDevice.DOWNLOAD_LIST_ALL);
                }
            }
            v.getTop();
            b();
            this.j = i;
            this.k = true;
        }
    }

    public final void a(CoordinatorLayout coordinatorLayout, V v, View view) {
        int i = XZBDevice.DOWNLOAD_LIST_FAILED;
        if (v.getTop() == this.d) {
            a((int) XZBDevice.DOWNLOAD_LIST_FAILED);
        } else if (view == this.n.get() && this.k) {
            int i2;
            if (this.j > 0) {
                i2 = this.d;
            } else {
                if (this.f) {
                    this.o.computeCurrentVelocity(IHost.HOST_NOFITY_REFRESH_LIST, this.b);
                    if (a((View) v, VelocityTrackerCompat.getYVelocity(this.o, this.p))) {
                        i2 = this.l;
                        i = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                    }
                }
                if (this.j == 0) {
                    int top = v.getTop();
                    if (Math.abs(top - this.d) < Math.abs(top - this.e)) {
                        i2 = this.d;
                    } else {
                        i2 = this.e;
                        i = 4;
                    }
                } else {
                    i2 = this.e;
                    i = 4;
                }
            }
            if (this.h.smoothSlideViewTo(v, v.getLeft(), i2)) {
                a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                ViewCompat.postOnAnimation(v, new b(v, i));
            } else {
                a(i);
            }
            this.k = false;
        }
    }

    public final boolean a(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2) {
        return view == this.n.get() && (this.g != 3 || super.a(coordinatorLayout, (View) v, view, f, f2));
    }

    private void a(int i) {
        if (this.g != i) {
            this.g = i;
            if (((View) this.m.get()) != null && this.a != null) {
                this.a.a(i);
            }
        }
    }

    private void a() {
        this.p = -1;
        if (this.o != null) {
            this.o.recycle();
            this.o = null;
        }
    }

    private boolean a(View view, float f) {
        return view.getTop() >= this.e && Math.abs((((float) view.getTop()) + (0.1f * f)) - ((float) this.e)) / ((float) this.c) > 0.5f;
    }

    private View c(View view) {
        if (view instanceof NestedScrollingChild) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View c = c(viewGroup.getChildAt(i));
                if (c != null) {
                    return c;
                }
            }
        }
        return null;
    }

    private void b() {
        this.m.get();
    }

    public static <V extends View> BottomSheetBehavior<V> b(V v) {
        LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof d) {
            Behavior behavior = ((d) layoutParams).a;
            if (behavior instanceof BottomSheetBehavior) {
                return (BottomSheetBehavior) behavior;
            }
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }
}
