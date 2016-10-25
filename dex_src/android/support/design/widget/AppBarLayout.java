package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.design.R;
import android.support.design.widget.AppBarLayout.Behavior;
import android.support.design.widget.AppBarLayout.LayoutParams;
import android.support.design.widget.CoordinatorLayout.b;
import android.support.design.widget.CoordinatorLayout.d;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@b(a = Behavior.class)
public class AppBarLayout extends LinearLayout {
    boolean a;
    final List<a> b;
    private int c;
    private int d;
    private int e;
    private float f;
    private int g;
    private WindowInsetsCompat h;

    public static class Behavior extends HeaderBehavior<AppBarLayout> {
        private int a;
        private boolean b;
        private boolean c;
        private bf d;
        private int e;
        private boolean f;
        private float g;
        private WeakReference<View> h;
        private a i;

        protected static class SavedState extends BaseSavedState {
            public static final Creator<SavedState> CREATOR;
            int a;
            float b;
            boolean c;

            public SavedState(Parcel parcel) {
                super(parcel);
                this.a = parcel.readInt();
                this.b = parcel.readFloat();
                this.c = parcel.readByte() != null;
            }

            public SavedState(Parcelable parcelable) {
                super(parcelable);
            }

            public void writeToParcel(Parcel parcel, int i) {
                super.writeToParcel(parcel, i);
                parcel.writeInt(this.a);
                parcel.writeFloat(this.b);
                parcel.writeByte((byte) (this.c ? 1 : 0));
            }

            static {
                CREATOR = ParcelableCompat.newCreator(new d());
            }
        }

        public static abstract class a {
            public abstract boolean a();
        }

        final /* synthetic */ int a(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            int a = a();
            if (i2 == 0 || a < i2 || a > i3) {
                this.a = 0;
                return 0;
            }
            int a2 = ad.a(i, i2, i3);
            if (a == a2) {
                return 0;
            }
            int abs;
            int i4;
            int height;
            if (appBarLayout.a) {
                abs = Math.abs(a2);
                int childCount = appBarLayout.getChildCount();
                for (i4 = 0; i4 < childCount; i4++) {
                    View childAt = appBarLayout.getChildAt(i4);
                    LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                    Interpolator interpolator = layoutParams.b;
                    if (abs >= childAt.getTop() && abs <= childAt.getBottom()) {
                        if (interpolator != null) {
                            i4 = layoutParams.a;
                            if ((i4 & 1) != 0) {
                                height = (layoutParams.bottomMargin + (childAt.getHeight() + layoutParams.topMargin)) + 0;
                                if ((i4 & 2) != 0) {
                                    height -= ViewCompat.getMinimumHeight(childAt);
                                }
                            } else {
                                height = 0;
                            }
                            if (ViewCompat.getFitsSystemWindows(childAt)) {
                                height -= appBarLayout.getTopInset();
                            }
                            if (height > 0) {
                                i4 = abs - childAt.getTop();
                                height = Math.round(interpolator.getInterpolation(((float) i4) / ((float) height)) * ((float) height));
                                height = (height + childAt.getTop()) * Integer.signum(a2);
                            }
                        }
                        height = a2;
                    }
                }
                height = a2;
            } else {
                height = a2;
            }
            boolean a3 = super.a(height);
            i4 = a - a2;
            this.a = a2 - height;
            if (!a3 && appBarLayout.a) {
                abs = coordinatorLayout.g.size();
                a = 0;
                a2 = 0;
                while (a < abs) {
                    View view2 = (View) coordinatorLayout.g.get(a);
                    if (view2 == appBarLayout) {
                        Object obj = 1;
                    } else {
                        if (a2 != 0) {
                            d dVar = (d) view2.getLayoutParams();
                            android.support.design.widget.CoordinatorLayout.Behavior behavior = dVar.a;
                            if (behavior != null && dVar.a((View) appBarLayout)) {
                                behavior.b(coordinatorLayout, view2, (View) appBarLayout);
                            }
                        }
                        height = a2;
                    }
                    a++;
                    a2 = height;
                }
            }
            a(appBarLayout);
            return i4;
        }

        final /* synthetic */ int a(View view) {
            return ((AppBarLayout) view).getTotalScrollRange();
        }

        public final /* synthetic */ void a(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (parcelable instanceof SavedState) {
                SavedState savedState = (SavedState) parcelable;
                super.a(coordinatorLayout, appBarLayout, savedState.getSuperState());
                this.e = savedState.a;
                this.g = savedState.b;
                this.f = savedState.c;
                return;
            }
            super.a(coordinatorLayout, appBarLayout, parcelable);
            this.e = -1;
        }

        public final /* synthetic */ void a(CoordinatorLayout coordinatorLayout, View view, View view2) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (!this.c) {
                a(coordinatorLayout, appBarLayout);
            }
            this.b = false;
            this.c = false;
            this.h = new WeakReference(view2);
        }

        public final /* synthetic */ void a(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int[] iArr) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (i != 0 && !this.b) {
                int i2;
                int b;
                if (i < 0) {
                    i2 = -appBarLayout.getTotalScrollRange();
                    b = i2 + appBarLayout.getDownNestedPreScrollRange();
                } else {
                    i2 = -appBarLayout.getUpNestedPreScrollRange();
                    b = 0;
                }
                iArr[1] = b(coordinatorLayout, appBarLayout, i, i2, b);
            }
        }

        public final /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, View view, float f, boolean z) {
            boolean z2 = true;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (z) {
                int b;
                if (f < 0.0f) {
                    b = (-appBarLayout.getTotalScrollRange()) + appBarLayout.getDownNestedPreScrollRange();
                    if (a() < b) {
                        a(coordinatorLayout, appBarLayout, b);
                    }
                } else {
                    b = -appBarLayout.getUpNestedPreScrollRange();
                    if (a() > b) {
                        a(coordinatorLayout, appBarLayout, b);
                    }
                }
                z2 = false;
            } else {
                z2 = a(coordinatorLayout, appBarLayout, -appBarLayout.getTotalScrollRange(), -f);
            }
            this.c = z2;
            return z2;
        }

        public final /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, View view, int i) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            boolean a = super.a(coordinatorLayout, appBarLayout, i);
            int f = appBarLayout.getPendingAction();
            int i2;
            if (f != 0) {
                if ((f & 4) != 0) {
                    Object obj = 1;
                } else {
                    i2 = 0;
                }
                if ((f & 2) != 0) {
                    f = -appBarLayout.getUpNestedPreScrollRange();
                    if (i2 != 0) {
                        a(coordinatorLayout, appBarLayout, f);
                    } else {
                        a_(coordinatorLayout, appBarLayout, f);
                    }
                } else if ((f & 1) != 0) {
                    if (i2 != 0) {
                        a(coordinatorLayout, appBarLayout, 0);
                    } else {
                        a_(coordinatorLayout, appBarLayout, 0);
                    }
                }
            } else if (this.e >= 0) {
                View childAt = appBarLayout.getChildAt(this.e);
                f = -childAt.getBottom();
                if (this.f) {
                    i2 = ViewCompat.getMinimumHeight(childAt) + f;
                } else {
                    i2 = Math.round(((float) childAt.getHeight()) * this.g) + f;
                }
                super.a(i2);
            }
            appBarLayout.g = 0;
            this.e = -1;
            super.a(ad.a(super.c(), -appBarLayout.getTotalScrollRange(), 0));
            a(appBarLayout);
            return a;
        }

        public final /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
            View view2 = (AppBarLayout) view;
            if (((d) view2.getLayoutParams()).height != -2) {
                return super.a(coordinatorLayout, view2, i, i2, i3, i4);
            }
            coordinatorLayout.a(view2, i, i2, MeasureSpec.makeMeasureSpec(0, 0), i4);
            return true;
        }

        public final /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, View view, View view2, int i) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            boolean z = (i & 2) != 0 && AppBarLayout.a(appBarLayout) && coordinatorLayout.getHeight() - view2.getHeight() <= appBarLayout.getHeight();
            if (z && this.d != null) {
                this.d.a.e();
            }
            this.h = null;
            return z;
        }

        final /* synthetic */ int b(View view) {
            return -((AppBarLayout) view).getDownNestedScrollRange();
        }

        public final /* synthetic */ Parcelable b(CoordinatorLayout coordinatorLayout, View view) {
            boolean z = false;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            Parcelable b = super.b(coordinatorLayout, appBarLayout);
            int c = super.c();
            int childCount = appBarLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = appBarLayout.getChildAt(i);
                int bottom = childAt.getBottom() + c;
                if (childAt.getTop() + c <= 0 && bottom >= 0) {
                    SavedState savedState = new SavedState(b);
                    savedState.a = i;
                    if (bottom == ViewCompat.getMinimumHeight(childAt)) {
                        z = true;
                    }
                    savedState.c = z;
                    savedState.b = ((float) bottom) / ((float) childAt.getHeight());
                    return savedState;
                }
            }
            return b;
        }

        public final /* synthetic */ void b(CoordinatorLayout coordinatorLayout, View view, int i) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (i < 0) {
                b(coordinatorLayout, appBarLayout, i, -appBarLayout.getDownNestedScrollRange(), 0);
                this.b = true;
                return;
            }
            this.b = false;
        }

        public final /* bridge */ /* synthetic */ int c() {
            return super.c();
        }

        public Behavior() {
            this.e = -1;
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.e = -1;
        }

        private void a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i) {
            int a = a();
            if (a != i) {
                if (this.d == null) {
                    this.d = bq.a();
                    this.d.a(a.e);
                    this.d.a(new c(this, coordinatorLayout, appBarLayout));
                } else {
                    this.d.a.e();
                }
                this.d.a(Math.round(((((float) Math.abs(a - i)) / coordinatorLayout.getResources().getDisplayMetrics().density) * 1000.0f) / 300.0f));
                this.d.a(a, i);
                this.d.a.a();
            } else if (this.d != null && this.d.a.b()) {
                this.d.a.e();
            }
        }

        private void a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            int i;
            int a = a();
            int childCount = appBarLayout.getChildCount();
            for (i = 0; i < childCount; i++) {
                View childAt = appBarLayout.getChildAt(i);
                if (childAt.getTop() <= (-a) && childAt.getBottom() >= (-a)) {
                    childCount = i;
                    break;
                }
            }
            childCount = -1;
            if (childCount >= 0) {
                View childAt2 = appBarLayout.getChildAt(childCount);
                int i2 = ((LayoutParams) childAt2.getLayoutParams()).a;
                if ((i2 & 17) == 17) {
                    int i3 = -childAt2.getTop();
                    i = -childAt2.getBottom();
                    if (childCount == appBarLayout.getChildCount() - 1) {
                        i += appBarLayout.getTopInset();
                    }
                    if (a(i2, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                        i += ViewCompat.getMinimumHeight(childAt2);
                        childCount = i3;
                    } else if (a(i2, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED)) {
                        childCount = ViewCompat.getMinimumHeight(childAt2) + i;
                        if (a >= childCount) {
                            i = childCount;
                            childCount = i3;
                        }
                    } else {
                        childCount = i3;
                    }
                    if (a >= (i + childCount) / 2) {
                        i = childCount;
                    }
                    a(coordinatorLayout, appBarLayout, ad.a(i, -appBarLayout.getTotalScrollRange(), 0));
                }
            }
        }

        private static boolean a(int i, int i2) {
            return (i & i2) == i2;
        }

        private void a(AppBarLayout appBarLayout) {
            List i = appBarLayout.b;
            int size = i.size();
            for (int i2 = 0; i2 < size; i2++) {
                android.support.design.widget.AppBarLayout.a aVar = (android.support.design.widget.AppBarLayout.a) i.get(i2);
                if (aVar != null) {
                    aVar.a(appBarLayout, super.c());
                }
            }
        }

        final int a() {
            return super.c() + this.a;
        }

        final /* synthetic */ boolean b() {
            if (this.i != null) {
                return this.i.a();
            }
            if (this.h != null) {
                View view = (View) this.h.get();
                if (view == null || !view.isShown() || ViewCompat.canScrollVertically(view, -1)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static class LayoutParams extends android.widget.LinearLayout.LayoutParams {
        int a;
        Interpolator b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AppBarLayout_LayoutParams);
            this.a = obtainStyledAttributes.getInt(R.styleable.AppBarLayout_LayoutParams_layout_scrollFlags, 0);
            if (obtainStyledAttributes.hasValue(R.styleable.AppBarLayout_LayoutParams_layout_scrollInterpolator)) {
                this.b = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(R.styleable.AppBarLayout_LayoutParams_layout_scrollInterpolator, 0));
            }
            obtainStyledAttributes.recycle();
        }

        public LayoutParams() {
            super(-1, -2);
            this.a = 1;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 1;
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.a = 1;
        }

        public LayoutParams(android.widget.LinearLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 1;
        }
    }

    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
        public final /* bridge */ /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, View view, int i) {
            return super.a(coordinatorLayout, view, i);
        }

        public final /* bridge */ /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
            return super.a(coordinatorLayout, view, i, i2, i3, i4);
        }

        public final /* bridge */ /* synthetic */ int c() {
            return super.c();
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ScrollingViewBehavior_Params);
            this.b = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ScrollingViewBehavior_Params_behavior_overlapTop, 0);
            obtainStyledAttributes.recycle();
        }

        public final boolean a_(View view) {
            return view instanceof AppBarLayout;
        }

        final float b(View view) {
            if (view instanceof AppBarLayout) {
                int a;
                AppBarLayout appBarLayout = (AppBarLayout) view;
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                int b = appBarLayout.getDownNestedPreScrollRange();
                android.support.design.widget.CoordinatorLayout.Behavior behavior = ((d) appBarLayout.getLayoutParams()).a;
                if (behavior instanceof Behavior) {
                    a = ((Behavior) behavior).a();
                } else {
                    a = 0;
                }
                if (b != 0 && totalScrollRange + a <= b) {
                    return 0.0f;
                }
                totalScrollRange -= b;
                if (totalScrollRange != 0) {
                    return (((float) a) / ((float) totalScrollRange)) + 1.0f;
                }
            }
            return 0.0f;
        }

        final View a(List<View> list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view = (View) list.get(i);
                if (view instanceof AppBarLayout) {
                    return view;
                }
            }
            return null;
        }

        final int c(View view) {
            return view instanceof AppBarLayout ? ((AppBarLayout) view).getTotalScrollRange() : super.c(view);
        }

        public final boolean b(CoordinatorLayout coordinatorLayout, View view, View view2) {
            android.support.design.widget.CoordinatorLayout.Behavior behavior = ((d) view2.getLayoutParams()).a;
            if (behavior instanceof Behavior) {
                int bottom = view2.getBottom() - view.getTop();
                ViewCompat.offsetTopAndBottom(view, ((((Behavior) behavior).a + bottom) + this.a) - d(view2));
            }
            return false;
        }
    }

    public static interface a {
        void a(AppBarLayout appBarLayout, int i);
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return a(attributeSet);
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return a(layoutParams);
    }

    public /* synthetic */ android.widget.LinearLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return a(attributeSet);
    }

    protected /* synthetic */ android.widget.LinearLayout.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return a(layoutParams);
    }

    public AppBarLayout(Context context) {
        this(context, null);
    }

    public AppBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = -1;
        this.d = -1;
        this.e = -1;
        this.g = 0;
        setOrientation(1);
        be.a(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AppBarLayout, 0, R.style.Widget_Design_AppBarLayout);
        this.f = (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppBarLayout_elevation, 0);
        setBackgroundDrawable(obtainStyledAttributes.getDrawable(R.styleable.AppBarLayout_android_background));
        if (obtainStyledAttributes.hasValue(R.styleable.AppBarLayout_expanded)) {
            setExpanded(obtainStyledAttributes.getBoolean(R.styleable.AppBarLayout_expanded, false));
        }
        obtainStyledAttributes.recycle();
        bq.a(this);
        this.b = new ArrayList();
        ViewCompat.setElevation(this, this.f);
        ViewCompat.setOnApplyWindowInsetsListener(this, new b(this));
    }

    public final void a(a aVar) {
        if (aVar != null && !this.b.contains(aVar)) {
            this.b.add(aVar);
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        a();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a();
        this.a = false;
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            if (((LayoutParams) getChildAt(i5).getLayoutParams()).b != null) {
                this.a = true;
                return;
            }
        }
    }

    private void a() {
        this.c = -1;
        this.d = -1;
        this.e = -1;
    }

    public void setOrientation(int i) {
        if (i != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(i);
    }

    public void setExpanded(boolean z) {
        boolean isLaidOut = ViewCompat.isLaidOut(this);
        this.g = (isLaidOut ? XZBDevice.DOWNLOAD_LIST_ALL : 0) | (z ? 1 : 2);
        requestLayout();
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    private LayoutParams a(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    private static LayoutParams a(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof android.widget.LinearLayout.LayoutParams) {
            return new LayoutParams((android.widget.LinearLayout.LayoutParams) layoutParams);
        }
        return layoutParams instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public final int getTotalScrollRange() {
        if (this.c != -1) {
            return this.c;
        }
        int minimumHeight;
        int childCount = getChildCount();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i3 = layoutParams.a;
            if ((i3 & 1) == 0) {
                break;
            }
            i += layoutParams.bottomMargin + (measuredHeight + layoutParams.topMargin);
            if ((i3 & 2) != 0) {
                minimumHeight = i - ViewCompat.getMinimumHeight(childAt);
                break;
            }
        }
        minimumHeight = i;
        minimumHeight = Math.max(0, minimumHeight - getTopInset());
        this.c = minimumHeight;
        return minimumHeight;
    }

    private int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    private int getDownNestedPreScrollRange() {
        if (this.d != -1) {
            return this.d;
        }
        int i;
        int childCount = getChildCount() - 1;
        int i2 = 0;
        while (childCount >= 0) {
            View childAt = getChildAt(childCount);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i3 = layoutParams.a;
            if ((i3 & 5) != 5) {
                if (i2 > 0) {
                    break;
                }
                i = i2;
            } else {
                i = (layoutParams.bottomMargin + layoutParams.topMargin) + i2;
                if ((i3 & 8) != 0) {
                    i += ViewCompat.getMinimumHeight(childAt);
                } else if ((i3 & 2) != 0) {
                    i += measuredHeight - ViewCompat.getMinimumHeight(childAt);
                } else {
                    i += measuredHeight;
                }
            }
            childCount--;
            i2 = i;
        }
        i = Math.max(0, i2 - getTopInset());
        this.d = i;
        return i;
    }

    private int getDownNestedScrollRange() {
        if (this.e != -1) {
            return this.e;
        }
        int i;
        int childCount = getChildCount();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight() + (layoutParams.topMargin + layoutParams.bottomMargin);
            i = layoutParams.a;
            if ((i & 1) == 0) {
                break;
            }
            i2 += measuredHeight;
            if ((i & 2) != 0) {
                i = i2 - (ViewCompat.getMinimumHeight(childAt) + getTopInset());
                break;
            }
        }
        i = i2;
        i = Math.max(0, i);
        this.e = i;
        return i;
    }

    final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        int minimumHeight = ViewCompat.getMinimumHeight(this);
        if (minimumHeight != 0) {
            return topInset + (minimumHeight * 2);
        }
        minimumHeight = getChildCount();
        return minimumHeight > 0 ? topInset + (ViewCompat.getMinimumHeight(getChildAt(minimumHeight - 1)) * 2) : 0;
    }

    public void setTargetElevation(float f) {
        this.f = f;
    }

    public float getTargetElevation() {
        return this.f;
    }

    private int getPendingAction() {
        return this.g;
    }

    private int getTopInset() {
        return this.h != null ? this.h.getSystemWindowInsetTop() : 0;
    }

    protected /* synthetic */ android.widget.LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    static /* synthetic */ WindowInsetsCompat a(AppBarLayout appBarLayout, WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat windowInsetsCompat2 = null;
        if (ViewCompat.getFitsSystemWindows(appBarLayout)) {
            windowInsetsCompat2 = windowInsetsCompat;
        }
        if (windowInsetsCompat2 != appBarLayout.h) {
            appBarLayout.h = windowInsetsCompat2;
            appBarLayout.a();
        }
        return windowInsetsCompat;
    }

    static /* synthetic */ boolean a(AppBarLayout appBarLayout) {
        return appBarLayout.getTotalScrollRange() != 0;
    }
}
