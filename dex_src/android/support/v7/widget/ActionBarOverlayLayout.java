package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window.Callback;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;

public class ActionBarOverlayLayout extends ViewGroup implements NestedScrollingParent, ad {
    static final int[] b;
    private final Runnable A;
    private final NestedScrollingParentHelper B;
    public boolean a;
    private int c;
    private int d;
    private ContentFrameLayout e;
    private ActionBarContainer f;
    private ae g;
    private Drawable h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private int m;
    private int n;
    private final Rect o;
    private final Rect p;
    private final Rect q;
    private final Rect r;
    private final Rect s;
    private final Rect t;
    private a u;
    private final int v;
    private ScrollerCompat w;
    private ViewPropertyAnimatorCompat x;
    private final ViewPropertyAnimatorListener y;
    private final Runnable z;

    public static interface a {
        void a(int i);

        void d(boolean z);

        void h();

        void i();

        void j();
    }

    public static class LayoutParams extends MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    static {
        b = new int[]{R.attr.actionBarSize, 16842841};
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = 0;
        this.o = new Rect();
        this.p = new Rect();
        this.q = new Rect();
        this.r = new Rect();
        this.s = new Rect();
        this.t = new Rect();
        this.v = 600;
        this.y = new e(this);
        this.z = new f(this);
        this.A = new g(this);
        a(context);
        this.B = new NestedScrollingParentHelper(this);
    }

    private void a(Context context) {
        boolean z = true;
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(b);
        this.c = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.h = obtainStyledAttributes.getDrawable(1);
        setWillNotDraw(this.h == null);
        obtainStyledAttributes.recycle();
        if (context.getApplicationInfo().targetSdkVersion >= 19) {
            z = false;
        }
        this.i = z;
        this.w = ScrollerCompat.create(context);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        i();
    }

    public void setActionBarVisibilityCallback(a aVar) {
        this.u = aVar;
        if (getWindowToken() != null) {
            this.u.a(this.d);
            if (this.n != 0) {
                onWindowSystemUiVisibilityChanged(this.n);
                ViewCompat.requestApplyInsets(this);
            }
        }
    }

    public void setOverlayMode(boolean z) {
        this.a = z;
        boolean z2 = z && getContext().getApplicationInfo().targetSdkVersion < 19;
        this.i = z2;
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.j = z;
    }

    public void setShowingForActionMode(boolean z) {
    }

    protected void onConfigurationChanged(Configuration configuration) {
        if (VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        a(getContext());
        ViewCompat.requestApplyInsets(this);
    }

    public void onWindowSystemUiVisibilityChanged(int i) {
        boolean z = true;
        if (VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i);
        }
        h();
        int i2 = this.n ^ i;
        this.n = i;
        if ((i & 4) == 0) {
            boolean z2 = true;
        } else {
            Object obj = null;
        }
        if ((i & 256) != 0) {
            boolean z3 = true;
        } else {
            Object obj2 = null;
        }
        if (this.u != null) {
            a aVar = this.u;
            if (z3) {
                z = false;
            }
            aVar.d(z);
            if (z2 || !z3) {
                this.u.h();
            } else {
                this.u.i();
            }
        }
        if ((i2 & 256) != 0 && this.u != null) {
            ViewCompat.requestApplyInsets(this);
        }
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.d = i;
        if (this.u != null) {
            this.u.a(i);
        }
    }

    private static boolean a(View view, Rect rect, boolean z) {
        Object obj = null;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams.leftMargin != rect.left) {
            layoutParams.leftMargin = rect.left;
            boolean z2 = true;
        }
        if (layoutParams.topMargin != rect.top) {
            layoutParams.topMargin = rect.top;
            z2 = true;
        }
        if (layoutParams.rightMargin != rect.right) {
            layoutParams.rightMargin = rect.right;
            z2 = true;
        }
        if (!z || layoutParams.bottomMargin == rect.bottom) {
            return z2;
        }
        layoutParams.bottomMargin = rect.bottom;
        return true;
    }

    protected boolean fitSystemWindows(Rect rect) {
        h();
        ViewCompat.getWindowSystemUiVisibility(this);
        boolean a = a(this.f, rect, false);
        this.r.set(rect);
        cw.a(this, this.r, this.o);
        if (!this.p.equals(this.o)) {
            this.p.set(this.o);
            a = true;
        }
        if (a) {
            requestLayout();
        }
        return true;
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        h();
        measureChildWithMargins(this.f, i, 0, i2, 0);
        LayoutParams layoutParams = (LayoutParams) this.f.getLayoutParams();
        int max = Math.max(0, (this.f.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin);
        int max2 = Math.max(0, layoutParams.bottomMargin + (this.f.getMeasuredHeight() + layoutParams.topMargin));
        int a = cw.a(0, ViewCompat.getMeasuredState(this.f));
        if ((ViewCompat.getWindowSystemUiVisibility(this) & 256) != 0) {
            boolean z = true;
        } else {
            i3 = 0;
        }
        if (z) {
            i4 = this.c;
            if (this.j && this.f.getTabContainer() != null) {
                i4 += this.c;
            }
        } else {
            i4 = this.f.getVisibility() != 8 ? this.f.getMeasuredHeight() : 0;
        }
        this.q.set(this.o);
        this.s.set(this.r);
        Rect rect;
        Rect rect2;
        if (this.a || z) {
            rect = this.s;
            rect.top = i4 + rect.top;
            rect2 = this.s;
            rect2.bottom += 0;
        } else {
            rect = this.q;
            rect.top = i4 + rect.top;
            rect2 = this.q;
            rect2.bottom += 0;
        }
        a(this.e, this.q, true);
        if (!this.t.equals(this.s)) {
            this.t.set(this.s);
            this.e.a(this.s);
        }
        measureChildWithMargins(this.e, i, 0, i2, 0);
        layoutParams = (LayoutParams) this.e.getLayoutParams();
        i3 = Math.max(max, (this.e.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin);
        i4 = Math.max(max2, layoutParams.bottomMargin + (this.e.getMeasuredHeight() + layoutParams.topMargin));
        int a2 = cw.a(a, ViewCompat.getMeasuredState(this.e));
        setMeasuredDimension(ViewCompat.resolveSizeAndState(Math.max(i3 + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), i, a2), ViewCompat.resolveSizeAndState(Math.max(i4 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i2, a2 << 16));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        getPaddingRight();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int i6 = layoutParams.leftMargin + paddingLeft;
                int i7 = layoutParams.topMargin + paddingTop;
                childAt.layout(i6, i7, childAt.getMeasuredWidth() + i6, childAt.getMeasuredHeight() + i7);
            }
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.h != null && !this.i) {
            int bottom = this.f.getVisibility() == 0 ? (int) ((((float) this.f.getBottom()) + ViewCompat.getTranslationY(this.f)) + 0.5f) : 0;
            this.h.setBounds(0, bottom, getWidth(), this.h.getIntrinsicHeight() + bottom);
            this.h.draw(canvas);
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        return ((i & 2) == 0 || this.f.getVisibility() != 0) ? false : this.k;
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.B.onNestedScrollAccepted(view, view2, i);
        this.m = getActionBarHideOffset();
        i();
        if (this.u != null) {
            this.u.j();
        }
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        this.m += i2;
        setActionBarHideOffset(this.m);
    }

    public void onStopNestedScroll(View view) {
        if (this.k && !this.l) {
            if (this.m <= this.f.getHeight()) {
                i();
                postDelayed(this.z, 600);
                return;
            }
            i();
            postDelayed(this.A, 600);
        }
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        boolean z2 = false;
        if (!this.k || !z) {
            return false;
        }
        this.w.fling(0, 0, 0, (int) f2, 0, 0, ExploreByTouchHelper.INVALID_ID, InMobiClientPositioning.NO_REPEAT);
        if (this.w.getFinalY() > this.f.getHeight()) {
            z2 = true;
        }
        if (z2) {
            i();
            this.A.run();
        } else {
            i();
            this.z.run();
        }
        this.l = true;
        return true;
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    public int getNestedScrollAxes() {
        return this.B.getNestedScrollAxes();
    }

    private void h() {
        if (this.e == null) {
            ae aeVar;
            this.e = (ContentFrameLayout) findViewById(R.id.action_bar_activity_content);
            this.f = (ActionBarContainer) findViewById(R.id.action_bar_container);
            View findViewById = findViewById(R.id.action_bar);
            if (findViewById instanceof ae) {
                aeVar = (ae) findViewById;
            } else if (findViewById instanceof Toolbar) {
                aeVar = ((Toolbar) findViewById).getWrapper();
            } else {
                throw new IllegalStateException(new StringBuilder("Can't make a decor toolbar out of ").append(findViewById.getClass().getSimpleName()).toString());
            }
            this.g = aeVar;
        }
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.k) {
            this.k = z;
            if (!z) {
                i();
                setActionBarHideOffset(0);
            }
        }
    }

    public int getActionBarHideOffset() {
        return this.f != null ? -((int) ViewCompat.getTranslationY(this.f)) : 0;
    }

    public void setActionBarHideOffset(int i) {
        i();
        ViewCompat.setTranslationY(this.f, (float) (-Math.max(0, Math.min(i, this.f.getHeight()))));
    }

    private void i() {
        removeCallbacks(this.z);
        removeCallbacks(this.A);
        if (this.x != null) {
            this.x.cancel();
        }
    }

    public void setWindowCallback(Callback callback) {
        h();
        this.g.a(callback);
    }

    public void setWindowTitle(CharSequence charSequence) {
        h();
        this.g.a(charSequence);
    }

    public CharSequence getTitle() {
        h();
        return this.g.e();
    }

    public final void a(int i) {
        h();
        switch (i) {
            case com.xunlei.tdlive.R.styleable.AppCompatTheme_seekBarStyle:
                setOverlayMode(true);
            default:
                break;
        }
    }

    public void setUiOptions(int i) {
    }

    public void setIcon(int i) {
        h();
        this.g.a(i);
    }

    public void setIcon(Drawable drawable) {
        h();
        this.g.a(drawable);
    }

    public void setLogo(int i) {
        h();
        this.g.b(i);
    }

    public final boolean a() {
        h();
        return this.g.f();
    }

    public final boolean b() {
        h();
        return this.g.g();
    }

    public final boolean c() {
        h();
        return this.g.h();
    }

    public final boolean d() {
        h();
        return this.g.i();
    }

    public final boolean e() {
        h();
        return this.g.j();
    }

    public final void f() {
        h();
        this.g.k();
    }

    public final void a(Menu menu, android.support.v7.view.menu.m.a aVar) {
        h();
        this.g.a(menu, aVar);
    }

    public final void g() {
        h();
        this.g.l();
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }
}
