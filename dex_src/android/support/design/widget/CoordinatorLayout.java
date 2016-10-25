package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.design.R;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.design.widget.CoordinatorLayout.d;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.android.spdy.SpdyAgent;

public class CoordinatorLayout extends ViewGroup implements NestedScrollingParent {
    static final String a;
    static final Class<?>[] b;
    static final ThreadLocal<Map<String, Constructor<Behavior>>> c;
    static final Comparator<View> e;
    static final q f;
    final Comparator<View> d;
    final List<View> g;
    final Rect h;
    final Rect i;
    private final List<View> j;
    private final List<View> k;
    private final Rect l;
    private final int[] m;
    private boolean n;
    private boolean o;
    private int[] p;
    private View q;
    private View r;
    private View s;
    private e t;
    private boolean u;
    private WindowInsetsCompat v;
    private boolean w;
    private Drawable x;
    private OnHierarchyChangeListener y;
    private final NestedScrollingParentHelper z;

    public static abstract class Behavior<V extends View> {
        public Behavior(Context context, AttributeSet attributeSet) {
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
            return false;
        }

        public boolean b(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
            return false;
        }

        public boolean a_(View view) {
            return false;
        }

        public boolean b(CoordinatorLayout coordinatorLayout, V v, View view) {
            return false;
        }

        public void c(CoordinatorLayout coordinatorLayout, V v, View view) {
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3, int i4) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, int i) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, View view, int i) {
            return false;
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, View view) {
        }

        public void b(CoordinatorLayout coordinatorLayout, V v, int i) {
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int[] iArr) {
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, float f, boolean z) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2) {
            return false;
        }

        public static WindowInsetsCompat a(WindowInsetsCompat windowInsetsCompat) {
            return windowInsetsCompat;
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        }

        public Parcelable b(CoordinatorLayout coordinatorLayout, V v) {
            return BaseSavedState.EMPTY_STATE;
        }
    }

    protected static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        SparseArray<Parcelable> a;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            int readInt = parcel.readInt();
            int[] iArr = new int[readInt];
            parcel.readIntArray(iArr);
            Parcelable[] readParcelableArray = parcel.readParcelableArray(classLoader);
            this.a = new SparseArray(readInt);
            for (int i = 0; i < readInt; i++) {
                this.a.append(iArr[i], readParcelableArray[i]);
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            int i2 = 0;
            super.writeToParcel(parcel, i);
            int size = this.a != null ? this.a.size() : 0;
            parcel.writeInt(size);
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            while (i2 < size) {
                iArr[i2] = this.a.keyAt(i2);
                parcelableArr[i2] = (Parcelable) this.a.valueAt(i2);
                i2++;
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i);
        }

        static {
            CREATOR = ParcelableCompat.newCreator(new p());
        }
    }

    private class a implements OnApplyWindowInsetsListener {
        private a() {
        }

        public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            return CoordinatorLayout.a(CoordinatorLayout.this, windowInsetsCompat);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public static @interface b {
        Class<? extends Behavior> a();
    }

    private class c implements OnHierarchyChangeListener {
        private c() {
        }

        public final void onChildViewAdded(View view, View view2) {
            if (CoordinatorLayout.this.y != null) {
                CoordinatorLayout.this.y.onChildViewAdded(view, view2);
            }
        }

        public final void onChildViewRemoved(View view, View view2) {
            CoordinatorLayout coordinatorLayout = CoordinatorLayout.this;
            int size = coordinatorLayout.g.size();
            int i = 0;
            Object obj = null;
            while (i < size) {
                Object obj2;
                View view3 = (View) coordinatorLayout.g.get(i);
                if (view3 == view2) {
                    obj2 = 1;
                } else {
                    if (obj != null) {
                        d dVar = (d) view3.getLayoutParams();
                        Behavior behavior = CoordinatorLayout.this;
                        if (behavior != null && dVar.a(view2)) {
                            behavior.c(coordinatorLayout, view3, view2);
                        }
                    }
                    obj2 = obj;
                }
                i++;
                obj = obj2;
            }
            if (CoordinatorLayout.this.y != null) {
                CoordinatorLayout.this.y.onChildViewRemoved(view, view2);
            }
        }
    }

    public static class d extends MarginLayoutParams {
        Behavior a;
        boolean b;
        public int c;
        public int d;
        public int e;
        int f;
        View g;
        View h;
        boolean i;
        boolean j;
        boolean k;
        final Rect l;
        Object m;

        public d() {
            super(-2, -2);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.l = new Rect();
        }

        d(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.l = new Rect();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CoordinatorLayout_LayoutParams);
            this.c = obtainStyledAttributes.getInteger(R.styleable.CoordinatorLayout_LayoutParams_android_layout_gravity, 0);
            this.f = obtainStyledAttributes.getResourceId(R.styleable.CoordinatorLayout_LayoutParams_layout_anchor, -1);
            this.d = obtainStyledAttributes.getInteger(R.styleable.CoordinatorLayout_LayoutParams_layout_anchorGravity, 0);
            this.e = obtainStyledAttributes.getInteger(R.styleable.CoordinatorLayout_LayoutParams_layout_keyline, -1);
            this.b = obtainStyledAttributes.hasValue(R.styleable.CoordinatorLayout_LayoutParams_layout_behavior);
            if (this.b) {
                this.a = CoordinatorLayout.a(context, attributeSet, obtainStyledAttributes.getString(R.styleable.CoordinatorLayout_LayoutParams_layout_behavior));
            }
            obtainStyledAttributes.recycle();
        }

        public d(android.support.design.widget.CoordinatorLayout.d dVar) {
            super(dVar);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.l = new Rect();
        }

        public d(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.l = new Rect();
        }

        public d(LayoutParams layoutParams) {
            super(layoutParams);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.l = new Rect();
        }

        public final void a(Behavior behavior) {
            if (this.a != behavior) {
                this.a = behavior;
                this.m = null;
                this.b = true;
            }
        }

        final boolean a(View view) {
            return view == this.h || (this.a != null && this.a.a_(view));
        }
    }

    class e implements OnPreDrawListener {
        e() {
        }

        public final boolean onPreDraw() {
            CoordinatorLayout.this.a(false);
            return true;
        }
    }

    static class f implements Comparator<View> {
        f() {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            View view = (View) obj2;
            float z = ViewCompat.getZ((View) obj);
            float z2 = ViewCompat.getZ(view);
            if (z > z2) {
                return -1;
            }
            return z < z2 ? 1 : 0;
        }
    }

    static /* synthetic */ WindowInsetsCompat a(CoordinatorLayout coordinatorLayout, WindowInsetsCompat windowInsetsCompat) {
        boolean z = true;
        int i = 0;
        if (coordinatorLayout.v == windowInsetsCompat) {
            return windowInsetsCompat;
        }
        WindowInsetsCompat windowInsetsCompat2;
        coordinatorLayout.v = windowInsetsCompat;
        boolean z2 = windowInsetsCompat != null && windowInsetsCompat.getSystemWindowInsetTop() > 0;
        coordinatorLayout.w = z2;
        if (coordinatorLayout.w || coordinatorLayout.getBackground() != null) {
            z = false;
        }
        coordinatorLayout.setWillNotDraw(z);
        if (windowInsetsCompat.isConsumed()) {
            windowInsetsCompat2 = windowInsetsCompat;
        } else {
            int childCount = coordinatorLayout.getChildCount();
            WindowInsetsCompat windowInsetsCompat3 = windowInsetsCompat;
            while (i < childCount) {
                View childAt = coordinatorLayout.getChildAt(i);
                if (ViewCompat.getFitsSystemWindows(childAt) && ((d) childAt.getLayoutParams()).a != null) {
                    windowInsetsCompat2 = Behavior.a(windowInsetsCompat3);
                    if (windowInsetsCompat2.isConsumed()) {
                        break;
                    }
                } else {
                    windowInsetsCompat2 = windowInsetsCompat3;
                }
                i++;
                windowInsetsCompat3 = windowInsetsCompat2;
            }
            windowInsetsCompat2 = windowInsetsCompat3;
        }
        coordinatorLayout.requestLayout();
        return windowInsetsCompat2;
    }

    static {
        Package packageR = CoordinatorLayout.class.getPackage();
        a = packageR != null ? packageR.getName() : null;
        if (VERSION.SDK_INT >= 21) {
            e = new f();
            f = new r();
        } else {
            e = null;
            f = null;
        }
        b = new Class[]{Context.class, AttributeSet.class};
        c = new ThreadLocal();
    }

    public CoordinatorLayout(Context context) {
        this(context, null);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new o(this);
        this.g = new ArrayList();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.h = new Rect();
        this.i = new Rect();
        this.l = new Rect();
        this.m = new int[2];
        this.z = new NestedScrollingParentHelper(this);
        be.a(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CoordinatorLayout, i, R.style.Widget_Design_CoordinatorLayout);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.CoordinatorLayout_keylines, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            this.p = resources.getIntArray(resourceId);
            float f = resources.getDisplayMetrics().density;
            int length = this.p.length;
            for (resourceId = 0; resourceId < length; resourceId++) {
                int[] iArr = this.p;
                iArr[resourceId] = (int) (((float) iArr[resourceId]) * f);
            }
        }
        this.x = obtainStyledAttributes.getDrawable(R.styleable.CoordinatorLayout_statusBarBackground);
        obtainStyledAttributes.recycle();
        if (f != null) {
            f.a(this, new a());
        }
        super.setOnHierarchyChangeListener(new c());
    }

    public void setOnHierarchyChangeListener(OnHierarchyChangeListener onHierarchyChangeListener) {
        this.y = onHierarchyChangeListener;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
        if (this.u) {
            if (this.t == null) {
                this.t = new e();
            }
            getViewTreeObserver().addOnPreDrawListener(this.t);
        }
        if (this.v == null && ViewCompat.getFitsSystemWindows(this)) {
            ViewCompat.requestApplyInsets(this);
        }
        this.o = true;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
        if (this.u && this.t != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.t);
        }
        if (this.s != null) {
            onStopNestedScroll(this.s);
        }
        this.o = false;
    }

    public void setStatusBarBackground(Drawable drawable) {
        Drawable drawable2 = null;
        if (this.x != drawable) {
            if (this.x != null) {
                this.x.setCallback(null);
            }
            if (drawable != null) {
                drawable2 = drawable.mutate();
            }
            this.x = drawable2;
            if (this.x != null) {
                if (this.x.isStateful()) {
                    this.x.setState(getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.x, ViewCompat.getLayoutDirection(this));
                this.x.setVisible(getVisibility() == 0, false);
                this.x.setCallback(this);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public Drawable getStatusBarBackground() {
        return this.x;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        int i = 0;
        Drawable drawable = this.x;
        if (drawable != null && drawable.isStateful()) {
            i = drawable.setState(drawableState) | 0;
        }
        if (i != 0) {
            invalidate();
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.x;
    }

    public void setVisibility(int i) {
        boolean z;
        super.setVisibility(i);
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        if (this.x != null && this.x.isVisible() != z) {
            this.x.setVisible(z, false);
        }
    }

    public void setStatusBarBackgroundResource(int i) {
        setStatusBarBackground(i != 0 ? ContextCompat.getDrawable(getContext(), i) : null);
    }

    public void setStatusBarBackgroundColor(int i) {
        setStatusBarBackground(new ColorDrawable(i));
    }

    private void a() {
        if (this.q != null) {
            Behavior behavior = ((d) this.q.getLayoutParams()).a;
            if (behavior != null) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, XZBDevice.DOWNLOAD_LIST_FAILED, AutoScrollHelper.RELATIVE_UNSPECIFIED, 0.0f, 0);
                behavior.b(this, this.q, obtain);
                obtain.recycle();
            }
            this.q = null;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ((d) getChildAt(i).getLayoutParams()).i = false;
        }
        this.n = false;
    }

    private boolean a(MotionEvent motionEvent, int i) {
        boolean z;
        boolean z2 = false;
        Object obj = null;
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        List list = this.j;
        list.clear();
        boolean isChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            int childDrawingOrder;
            if (isChildrenDrawingOrderEnabled) {
                childDrawingOrder = getChildDrawingOrder(childCount, i2);
            } else {
                childDrawingOrder = i2;
            }
            list.add(getChildAt(childDrawingOrder));
        }
        if (e != null) {
            Collections.sort(list, e);
        }
        int size = list.size();
        int i3 = 0;
        Object obj2 = null;
        while (i3 < size) {
            MotionEvent motionEvent2;
            View view = (View) list.get(i3);
            d dVar = (d) view.getLayoutParams();
            Behavior behavior = dVar.a;
            if ((!z2 && obj == null) || actionMasked == 0) {
                if (!(z2 || behavior == null)) {
                    switch (i) {
                        case SpdyAgent.ACCS_TEST_SERVER:
                            z2 = behavior.a(this, view, motionEvent);
                            break;
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            z2 = behavior.b(this, view, motionEvent);
                            break;
                    }
                    if (z2) {
                        this.q = view;
                    }
                }
                z = z2;
                if (dVar.a == null) {
                    dVar.i = false;
                }
                boolean z3 = dVar.i;
                if (dVar.i) {
                    isChildrenDrawingOrderEnabled = true;
                } else {
                    isChildrenDrawingOrderEnabled = dVar.i | 0;
                    dVar.i = isChildrenDrawingOrderEnabled;
                }
                Object obj3 = (!isChildrenDrawingOrderEnabled || z3) ? null : 1;
                if (isChildrenDrawingOrderEnabled && obj3 == null) {
                    list.clear();
                    return z;
                }
                MotionEvent motionEvent3 = r5;
                obj2 = obj3;
                motionEvent2 = motionEvent3;
            } else if (behavior != null) {
                if (r5 == null) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    motionEvent2 = MotionEvent.obtain(uptimeMillis, uptimeMillis, XZBDevice.DOWNLOAD_LIST_FAILED, AutoScrollHelper.RELATIVE_UNSPECIFIED, AutoScrollHelper.RELATIVE_UNSPECIFIED, 0);
                } else {
                    motionEvent2 = r5;
                }
                switch (i) {
                    case SpdyAgent.ACCS_TEST_SERVER:
                        behavior.a(this, view, motionEvent2);
                        obj2 = obj;
                        z = z2;
                        break;
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        behavior.b(this, view, motionEvent2);
                        obj2 = obj;
                        z = z2;
                        break;
                    default:
                        obj2 = obj;
                        z = z2;
                        break;
                }
            } else {
                motionEvent2 = r5;
                z = z2;
                obj2 = obj;
            }
            i3++;
            obj = obj2;
            z2 = z;
            MotionEvent motionEvent4 = motionEvent2;
        }
        z = z2;
        list.clear();
        return z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            a();
        }
        boolean a = a(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            a();
        }
        return a;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean onTouchEvent;
        MotionEvent motionEvent2;
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (this.q == null) {
            boolean a = a(motionEvent, 1);
            if (a) {
                z = a;
            } else {
                z = a;
                int i = 0;
                if (this.q == null) {
                    onTouchEvent |= super.onTouchEvent(motionEvent);
                    motionEvent2 = null;
                } else if (z) {
                    motionEvent2 = null;
                } else {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    motionEvent2 = MotionEvent.obtain(uptimeMillis, uptimeMillis, XZBDevice.DOWNLOAD_LIST_FAILED, AutoScrollHelper.RELATIVE_UNSPECIFIED, 0.0f, 0);
                    super.onTouchEvent(motionEvent2);
                }
                if (motionEvent2 != null) {
                    motionEvent2.recycle();
                }
                if (actionMasked == 1 || actionMasked == 3) {
                    a();
                }
                return onTouchEvent;
            }
        }
        int i2 = 0;
        Behavior behavior = ((d) this.q.getLayoutParams()).a;
        if (behavior != null) {
            onTouchEvent = behavior.b(this, this.q, motionEvent);
        } else {
            i = 0;
        }
        if (this.q == null) {
            onTouchEvent |= super.onTouchEvent(motionEvent);
            motionEvent2 = null;
        } else if (z) {
            motionEvent2 = null;
        } else {
            long uptimeMillis2 = SystemClock.uptimeMillis();
            motionEvent2 = MotionEvent.obtain(uptimeMillis2, uptimeMillis2, XZBDevice.DOWNLOAD_LIST_FAILED, AutoScrollHelper.RELATIVE_UNSPECIFIED, 0.0f, 0);
            super.onTouchEvent(motionEvent2);
        }
        if (motionEvent2 != null) {
            motionEvent2.recycle();
        }
        a();
        return onTouchEvent;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (z && !this.n) {
            a();
            this.n = true;
        }
    }

    private int a(int i) {
        if (this.p == null) {
            new StringBuilder("No keylines defined for ").append(this).append(" - attempted index lookup ").append(i);
            return 0;
        } else if (i >= 0 && i < this.p.length) {
            return this.p[i];
        } else {
            new StringBuilder("Keyline index ").append(i).append(" out of range for ").append(this);
            return 0;
        }
    }

    static Behavior a(Context context, AttributeSet attributeSet, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith(".")) {
            str = context.getPackageName() + str;
        } else if (str.indexOf(com.xunlei.tdlive.R.styleable.AppCompatTheme_dropdownListPreferredItemHeight) < 0 && !TextUtils.isEmpty(a)) {
            str = a + '.' + str;
        }
        try {
            Map map;
            Map map2 = (Map) c.get();
            if (map2 == null) {
                HashMap hashMap = new HashMap();
                c.set(hashMap);
                HashMap hashMap2 = hashMap;
            } else {
                map = map2;
            }
            Constructor constructor = (Constructor) map.get(str);
            if (constructor == null) {
                constructor = Class.forName(str, true, context.getClassLoader()).getConstructor(b);
                constructor.setAccessible(true);
                map.put(str, constructor);
            }
            return (Behavior) constructor.newInstance(new Object[]{context, attributeSet});
        } catch (Throwable e) {
            throw new RuntimeException(new StringBuilder("Could not inflate Behavior subclass ").append(str).toString(), e);
        }
    }

    private static d b(View view) {
        d dVar = (d) view.getLayoutParams();
        if (!dVar.b) {
            b bVar = null;
            for (Class cls = view.getClass(); cls != null; cls = cls.getSuperclass()) {
                bVar = (b) cls.getAnnotation(b.class);
                if (bVar != null) {
                    break;
                }
            }
            b bVar2 = bVar;
            if (bVar2 != null) {
                try {
                    dVar.a((Behavior) bVar2.a().newInstance());
                } catch (Exception e) {
                    new StringBuilder("Default behavior class ").append(bVar2.a().getName()).append(" could not be instantiated. Did you forget a default constructor?");
                }
            }
            dVar.b = true;
        }
        return dVar;
    }

    protected int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    protected int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    public final void a(View view, int i, int i2, int i3, int i4) {
        measureChildWithMargins(view, i, i2, i3, i4);
    }

    public final void a(View view, int i) {
        d dVar = (d) view.getLayoutParams();
        if (dVar.g != null || dVar.f == -1) {
            Object obj = null;
        } else {
            int i2 = 1;
        }
        if (obj != null) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        } else if (dVar.g != null) {
            View view2 = dVar.g;
            view.getLayoutParams();
            Rect rect = this.h;
            Rect rect2 = this.i;
            bn.a(this, view2, rect);
            a(view, i, rect, rect2);
            view.layout(rect2.left, rect2.top, rect2.right, rect2.bottom);
        } else if (dVar.e >= 0) {
            int i3;
            i2 = dVar.e;
            dVar = (d) view.getLayoutParams();
            int absoluteGravity = GravityCompat.getAbsoluteGravity(c(dVar.c), i);
            int i4 = absoluteGravity & 7;
            absoluteGravity &= 112;
            int width = getWidth();
            int height = getHeight();
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            if (i == 1) {
                i2 = width - i2;
            }
            i2 = a(i2) - measuredWidth;
            switch (i4) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    i3 = i2 + (measuredWidth / 2);
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    i3 = i2 + measuredWidth;
                    break;
                default:
                    i3 = i2;
                    break;
            }
            switch (absoluteGravity) {
                case com.xunlei.tdlive.R.styleable.Toolbar_titleMarginBottom:
                    i2 = (measuredHeight / 2) + 0;
                    break;
                case com.xunlei.tdlive.R.styleable.AppCompatTheme_panelMenuListTheme:
                    i2 = measuredHeight + 0;
                    break;
                default:
                    i2 = 0;
                    break;
            }
            int max = Math.max(getPaddingLeft() + dVar.leftMargin, Math.min(i3, ((width - getPaddingRight()) - measuredWidth) - dVar.rightMargin));
            int max2 = Math.max(getPaddingTop() + dVar.topMargin, Math.min(i2, ((height - getPaddingBottom()) - measuredHeight) - dVar.bottomMargin));
            view.layout(max, max2, max + measuredWidth, max2 + measuredHeight);
        } else {
            dVar = (d) view.getLayoutParams();
            Rect rect3 = this.h;
            rect3.set(getPaddingLeft() + dVar.leftMargin, getPaddingTop() + dVar.topMargin, (getWidth() - getPaddingRight()) - dVar.rightMargin, (getHeight() - getPaddingBottom()) - dVar.bottomMargin);
            if (!(this.v == null || !ViewCompat.getFitsSystemWindows(this) || ViewCompat.getFitsSystemWindows(view))) {
                rect3.left += this.v.getSystemWindowInsetLeft();
                rect3.top += this.v.getSystemWindowInsetTop();
                rect3.right -= this.v.getSystemWindowInsetRight();
                rect3.bottom -= this.v.getSystemWindowInsetBottom();
            }
            Rect rect4 = this.i;
            GravityCompat.apply(b(dVar.c), view.getMeasuredWidth(), view.getMeasuredHeight(), rect3, rect4, i);
            view.layout(rect4.left, rect4.top, rect4.right, rect4.bottom);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int size = this.g.size();
        for (int i5 = 0; i5 < size; i5++) {
            View view = (View) this.g.get(i5);
            Behavior behavior = ((d) view.getLayoutParams()).a;
            if (behavior == null || !behavior.a(this, view, layoutDirection)) {
                a(view, layoutDirection);
            }
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.w && this.x != null) {
            int systemWindowInsetTop = this.v != null ? this.v.getSystemWindowInsetTop() : 0;
            if (systemWindowInsetTop > 0) {
                this.x.setBounds(0, 0, getWidth(), systemWindowInsetTop);
                this.x.draw(canvas);
            }
        }
    }

    final void a(View view, boolean z, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.set(0, 0, 0, 0);
        } else if (z) {
            bn.a(this, view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    private void a(View view, int i, Rect rect, Rect rect2) {
        d dVar = (d) view.getLayoutParams();
        int i2 = dVar.c;
        if (i2 == 0) {
            i2 = com.xunlei.tdlive.R.styleable.Toolbar_maxButtonHeight;
        }
        i2 = GravityCompat.getAbsoluteGravity(i2, i);
        int absoluteGravity = GravityCompat.getAbsoluteGravity(b(dVar.d), i);
        int i3 = i2 & 7;
        int i4 = i2 & 112;
        i2 = absoluteGravity & 7;
        int i5 = absoluteGravity & 112;
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        switch (i2) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                absoluteGravity = (rect.width() / 2) + rect.left;
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                absoluteGravity = rect.right;
                break;
            default:
                absoluteGravity = rect.left;
                break;
        }
        switch (i5) {
            case com.xunlei.tdlive.R.styleable.Toolbar_titleMarginBottom:
                i2 = rect.top + (rect.height() / 2);
                break;
            case com.xunlei.tdlive.R.styleable.AppCompatTheme_panelMenuListTheme:
                i2 = rect.bottom;
                break;
            default:
                i2 = rect.top;
                break;
        }
        switch (i3) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                absoluteGravity -= measuredWidth / 2;
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                break;
            default:
                absoluteGravity -= measuredWidth;
                break;
        }
        switch (i4) {
            case com.xunlei.tdlive.R.styleable.Toolbar_titleMarginBottom:
                i2 -= measuredHeight / 2;
                break;
            case com.xunlei.tdlive.R.styleable.AppCompatTheme_panelMenuListTheme:
                break;
            default:
                i2 -= measuredHeight;
                break;
        }
        i3 = getWidth();
        i4 = getHeight();
        absoluteGravity = Math.max(getPaddingLeft() + dVar.leftMargin, Math.min(absoluteGravity, ((i3 - getPaddingRight()) - measuredWidth) - dVar.rightMargin));
        int max = Math.max(getPaddingTop() + dVar.topMargin, Math.min(i2, ((i4 - getPaddingBottom()) - measuredHeight) - dVar.bottomMargin));
        rect2.set(absoluteGravity, max, absoluteGravity + measuredWidth, max + measuredHeight);
    }

    private static int b(int i) {
        return i == 0 ? 8388659 : i;
    }

    private static int c(int i) {
        return i == 0 ? 8388661 : i;
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        view.getLayoutParams();
        return super.drawChild(canvas, view, j);
    }

    final void a(boolean z) {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            int i2;
            Behavior behavior;
            View view = (View) this.g.get(i);
            d dVar = (d) view.getLayoutParams();
            for (i2 = 0; i2 < i; i2++) {
                d dVar2;
                if (dVar.h == ((View) this.g.get(i2))) {
                    dVar2 = (d) view.getLayoutParams();
                    if (dVar2.g != null) {
                        Rect rect = this.h;
                        Rect rect2 = this.i;
                        Rect rect3 = this.l;
                        bn.a(this, dVar2.g, rect);
                        a(view, false, rect2);
                        a(view, layoutDirection, rect, rect3);
                        int i3 = rect3.left - rect2.left;
                        int i4 = rect3.top - rect2.top;
                        if (i3 != 0) {
                            view.offsetLeftAndRight(i3);
                        }
                        if (i4 != 0) {
                            view.offsetTopAndBottom(i4);
                        }
                        if (i3 != 0 || i4 != 0) {
                            behavior = dVar2.a;
                            if (behavior != null) {
                                behavior.b(this, view, dVar2.g);
                            }
                        }
                    }
                }
            }
            Rect rect4 = this.h;
            Rect rect5 = this.i;
            rect4.set(((d) view.getLayoutParams()).l);
            a(view, true, rect5);
            if (!rect4.equals(rect5)) {
                ((d) view.getLayoutParams()).l.set(rect5);
                for (i2 = i + 1; i2 < size; i2++) {
                    View view2 = (View) this.g.get(i2);
                    dVar2 = (d) view2.getLayoutParams();
                    behavior = dVar2.a;
                    if (behavior != null && behavior.a_(view)) {
                        if (z || !dVar2.k) {
                            behavior.b(this, view2, view);
                            if (z) {
                                dVar2.k = false;
                            }
                        } else {
                            dVar2.k = false;
                        }
                    }
                }
            }
        }
    }

    public final List<View> a(View view) {
        d dVar = (d) view.getLayoutParams();
        List<View> list = this.k;
        list.clear();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != view && dVar.a(childAt)) {
                list.add(childAt);
            }
        }
        return list;
    }

    private void b() {
        boolean z;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            d dVar = (d) childAt.getLayoutParams();
            if (dVar.g != null) {
                z = true;
            } else {
                int childCount2 = getChildCount();
                for (int i2 = 0; i2 < childCount2; i2++) {
                    View childAt2 = getChildAt(i2);
                    if (childAt2 != childAt && dVar.a(childAt2)) {
                        z = true;
                        break;
                    }
                }
                z = false;
            }
            if (z) {
                z = true;
                break;
            }
        }
        z = false;
        if (z == this.u) {
            return;
        }
        if (z) {
            if (this.o) {
                if (this.t == null) {
                    this.t = new e();
                }
                getViewTreeObserver().addOnPreDrawListener(this.t);
            }
            this.u = true;
            return;
        }
        if (this.o && this.t != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.t);
        }
        this.u = false;
    }

    public final boolean a(View view, int i, int i2) {
        Rect rect = this.h;
        bn.a(this, view, rect);
        return rect.contains(i, i2);
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return (layoutParams instanceof d) && super.checkLayoutParams(layoutParams);
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        int childCount = getChildCount();
        int i2 = 0;
        boolean z = false;
        while (i2 < childCount) {
            boolean z2;
            View childAt = getChildAt(i2);
            d dVar = (d) childAt.getLayoutParams();
            Behavior behavior = dVar.a;
            if (behavior != null) {
                boolean a = behavior.a(this, childAt, view, i);
                int i3 = z | a;
                dVar.j = a;
                int i4 = i3;
            } else {
                dVar.j = false;
                z2 = z;
            }
            i2++;
            z = z2;
        }
        return z;
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.z.onNestedScrollAccepted(view, view2, i);
        this.r = view;
        this.s = view2;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).getLayoutParams();
        }
    }

    public void onStopNestedScroll(View view) {
        this.z.onStopNestedScroll(view);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            d dVar = (d) childAt.getLayoutParams();
            if (dVar.j) {
                Behavior behavior = dVar.a;
                if (behavior != null) {
                    behavior.a(this, childAt, view);
                }
                dVar.j = false;
                dVar.k = false;
            }
        }
        this.r = null;
        this.s = null;
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = 0;
        Object obj = null;
        while (i5 < childCount) {
            boolean z;
            boolean z2;
            View childAt = getChildAt(i5);
            d dVar = (d) childAt.getLayoutParams();
            if (dVar.j) {
                Behavior behavior = dVar.a;
                if (behavior != null) {
                    behavior.b(this, childAt, i4);
                    z = true;
                    i5++;
                    z2 = z;
                }
            }
            z = z2;
            i5++;
            z2 = z;
        }
        if (z2) {
            a(true);
        }
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        int i3 = 0;
        int i4 = 0;
        Object obj = null;
        int childCount = getChildCount();
        int i5 = 0;
        while (i5 < childCount) {
            int max;
            int max2;
            View childAt = getChildAt(i5);
            d dVar = (d) childAt.getLayoutParams();
            if (dVar.j) {
                Behavior behavior = dVar.a;
                if (behavior != null) {
                    int[] iArr2 = this.m;
                    this.m[1] = 0;
                    iArr2[0] = 0;
                    behavior.a(this, childAt, view, i2, this.m);
                    max = i > 0 ? Math.max(i3, this.m[0]) : Math.min(i3, this.m[0]);
                    max2 = i2 > 0 ? Math.max(i4, this.m[1]) : Math.min(i4, this.m[1]);
                    int i6 = 1;
                    i5++;
                    i4 = max2;
                    i3 = max;
                    obj = r0;
                }
            }
            Object obj2 = obj;
            max = i3;
            max2 = i4;
            i5++;
            i4 = max2;
            i3 = max;
            obj = obj2;
        }
        iArr[0] = i3;
        iArr[1] = i4;
        if (obj != null) {
            a(true);
        }
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        int childCount = getChildCount();
        int i = 0;
        boolean z2 = false;
        while (i < childCount) {
            View childAt = getChildAt(i);
            d dVar = (d) childAt.getLayoutParams();
            if (dVar.j) {
                Behavior behavior = dVar.a;
                if (behavior != null) {
                    int a = behavior.a(this, childAt, f2, z) | z2;
                    i++;
                    z2 = r0;
                }
            }
            boolean z3 = z2;
            i++;
            z2 = z3;
        }
        if (z2) {
            a(true);
        }
        return z2;
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        int childCount = getChildCount();
        int i = 0;
        boolean z = false;
        while (i < childCount) {
            View childAt = getChildAt(i);
            d dVar = (d) childAt.getLayoutParams();
            if (dVar.j) {
                Behavior behavior = dVar.a;
                if (behavior != null) {
                    int a = behavior.a(this, childAt, view, f, f2) | z;
                    i++;
                    z = r0;
                }
            }
            boolean z2 = z;
            i++;
            z = z2;
        }
        return z;
    }

    public int getNestedScrollAxes() {
        return this.z.getNestedScrollAxes();
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            SparseArray sparseArray = savedState.a;
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                int id = childAt.getId();
                Behavior behavior = b(childAt).a;
                if (id != -1 && behavior != null) {
                    Parcelable parcelable2 = (Parcelable) sparseArray.get(id);
                    if (parcelable2 != null) {
                        behavior.a(this, childAt, parcelable2);
                    }
                }
            }
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        SparseArray sparseArray = new SparseArray();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            Behavior behavior = ((d) childAt.getLayoutParams()).a;
            if (id != -1 && behavior != null) {
                Parcelable b = behavior.b(this, childAt);
                if (b != null) {
                    sparseArray.append(id, b);
                }
            }
        }
        savedState.a = sparseArray;
        return savedState;
    }

    private static void a(List<View> list, Comparator<View> comparator) {
        if (list != null && list.size() >= 2) {
            int i;
            View[] viewArr = new View[list.size()];
            list.toArray(viewArr);
            int length = viewArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                i = i2;
                for (int i3 = i2 + 1; i3 < length; i3++) {
                    if (comparator.compare(viewArr[i3], viewArr[i]) < 0) {
                        i = i3;
                    }
                }
                if (i2 != i) {
                    View view = viewArr[i];
                    viewArr[i] = viewArr[i2];
                    viewArr[i2] = view;
                }
            }
            list.clear();
            for (i = 0; i < length; i++) {
                list.add(viewArr[i]);
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        this.g.clear();
        int childCount = getChildCount();
        for (i3 = 0; i3 < childCount; i3++) {
            ViewParent childAt = getChildAt(i3);
            d b = b((View) childAt);
            if (b.f == -1) {
                b.h = null;
                b.g = null;
            } else {
                View view;
                ViewParent parent;
                if (b.g != null) {
                    Object obj;
                    if (b.g.getId() != b.f) {
                        obj = null;
                    } else {
                        view = b.g;
                        parent = b.g.getParent();
                        while (parent != this) {
                            if (parent != null && parent != childAt) {
                                if (parent instanceof View) {
                                    view = (View) parent;
                                }
                                parent = parent.getParent();
                            }
                            b.h = null;
                            b.g = null;
                            obj = null;
                            break;
                        }
                        b.h = view;
                        obj = 1;
                    }
                    if (obj != null) {
                        continue;
                    }
                }
                b.g = findViewById(b.f);
                if (b.g != null) {
                    if (b.g != this) {
                        view = b.g;
                        parent = b.g.getParent();
                        while (parent != this && parent != null) {
                            if (parent != childAt) {
                                if (parent instanceof View) {
                                    view = (View) parent;
                                }
                                parent = parent.getParent();
                            } else if (isInEditMode()) {
                                b.h = null;
                                b.g = null;
                            } else {
                                throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                            }
                        }
                        b.h = view;
                    } else if (isInEditMode()) {
                        b.h = null;
                        b.g = null;
                    } else {
                        throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
                    }
                } else if (isInEditMode()) {
                    b.h = null;
                    b.g = null;
                } else {
                    throw new IllegalStateException(new StringBuilder("Could not find CoordinatorLayout descendant view with id ").append(getResources().getResourceName(b.f)).append(" to anchor view ").append(childAt).toString());
                }
            }
            this.g.add(childAt);
        }
        a(this.g, this.d);
        b();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        i3 = getPaddingBottom();
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        if (layoutDirection == 1) {
            int i4 = 1;
        } else {
            Object obj2 = null;
        }
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        int i5 = paddingLeft + paddingRight;
        int i6 = paddingTop + i3;
        childCount = getSuggestedMinimumWidth();
        i3 = getSuggestedMinimumHeight();
        if (this.v == null || !ViewCompat.getFitsSystemWindows(this)) {
            Object obj3 = null;
        } else {
            int i7 = 1;
        }
        int size3 = this.g.size();
        int i8 = 0;
        int i9 = 0;
        int i10 = i3;
        int i11 = childCount;
        while (i8 < size3) {
            int i12;
            View view2 = (View) this.g.get(i8);
            d dVar = (d) view2.getLayoutParams();
            int i13 = 0;
            if (dVar.e >= 0 && mode != 0) {
                int a = a(dVar.e);
                paddingTop = GravityCompat.getAbsoluteGravity(c(dVar.c), layoutDirection) & 7;
                if ((paddingTop == 3 && r9 == null) || (paddingTop == 5 && r9 != null)) {
                    i13 = Math.max(0, (size - paddingRight) - a);
                } else if ((paddingTop == 5 && r9 == null) || (paddingTop == 3 && r9 != null)) {
                    i13 = Math.max(0, a - paddingLeft);
                }
            }
            if (obj3 == null || ViewCompat.getFitsSystemWindows(view2)) {
                i12 = i2;
                childCount = i;
            } else {
                paddingTop = this.v.getSystemWindowInsetTop() + this.v.getSystemWindowInsetBottom();
                childCount = MeasureSpec.makeMeasureSpec(size - (this.v.getSystemWindowInsetLeft() + this.v.getSystemWindowInsetRight()), mode);
                i12 = MeasureSpec.makeMeasureSpec(size2 - paddingTop, mode2);
            }
            Behavior behavior = dVar.a;
            if (behavior == null || !behavior.a(this, view2, childCount, i13, i12, 0)) {
                a(view2, childCount, i13, i12, 0);
            }
            i13 = Math.max(i11, ((view2.getMeasuredWidth() + i5) + dVar.leftMargin) + dVar.rightMargin);
            childCount = Math.max(i10, ((view2.getMeasuredHeight() + i6) + dVar.topMargin) + dVar.bottomMargin);
            i8++;
            i9 = ViewCompat.combineMeasuredStates(i9, ViewCompat.getMeasuredState(view2));
            i10 = childCount;
            i11 = i13;
        }
        setMeasuredDimension(ViewCompat.resolveSizeAndState(i11, i, -16777216 & i9), ViewCompat.resolveSizeAndState(i10, i2, i9 << 16));
    }

    protected /* synthetic */ LayoutParams generateDefaultLayoutParams() {
        return new d();
    }

    protected /* synthetic */ LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        if (layoutParams instanceof d) {
            return new d((d) layoutParams);
        }
        return layoutParams instanceof MarginLayoutParams ? new d((MarginLayoutParams) layoutParams) : new d(layoutParams);
    }

    public /* synthetic */ LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new d(getContext(), attributeSet);
    }
}
