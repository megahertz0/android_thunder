package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.R;
import android.support.design.widget.CollapsingToolbarLayout.LayoutParams;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class CollapsingToolbarLayout extends FrameLayout {
    private boolean a;
    private int b;
    private Toolbar c;
    private View d;
    private View e;
    private int f;
    private int g;
    private int h;
    private int i;
    private final Rect j;
    private final l k;
    private boolean l;
    private boolean m;
    private Drawable n;
    private Drawable o;
    private int p;
    private boolean q;
    private bf r;
    private android.support.design.widget.AppBarLayout.a s;
    private int t;
    private WindowInsetsCompat u;

    public static class LayoutParams extends android.widget.FrameLayout.LayoutParams {
        int a;
        float b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 0;
            this.b = 0.5f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CollapsingAppBarLayout_LayoutParams);
            this.a = obtainStyledAttributes.getInt(R.styleable.CollapsingAppBarLayout_LayoutParams_layout_collapseMode, 0);
            this.b = obtainStyledAttributes.getFloat(R.styleable.CollapsingAppBarLayout_LayoutParams_layout_collapseParallaxMultiplier, 0.5f);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 0;
            this.b = 0.5f;
        }

        public LayoutParams(android.widget.FrameLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 0;
            this.b = 0.5f;
        }
    }

    private class a implements android.support.design.widget.AppBarLayout.a {
        private a() {
        }

        public final void a(AppBarLayout appBarLayout, int i) {
            int systemWindowInsetTop;
            boolean z = false;
            CollapsingToolbarLayout.this.t = i;
            if (CollapsingToolbarLayout.this.u != null) {
                systemWindowInsetTop = CollapsingToolbarLayout.this.u.getSystemWindowInsetTop();
            } else {
                systemWindowInsetTop = 0;
            }
            int totalScrollRange = appBarLayout.getTotalScrollRange();
            int childCount = CollapsingToolbarLayout.this.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = CollapsingToolbarLayout.this.getChildAt(i2);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                bp a = CollapsingToolbarLayout.c(childAt);
                switch (CollapsingToolbarLayout.this) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        if ((CollapsingToolbarLayout.this.getHeight() - systemWindowInsetTop) + i >= childAt.getHeight()) {
                            a.a(-i);
                        }
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        a.a(Math.round(layoutParams.b * ((float) (-i))));
                        break;
                    default:
                        break;
                }
            }
            if (!(CollapsingToolbarLayout.this.n == null && CollapsingToolbarLayout.this.o == null)) {
                CollapsingToolbarLayout collapsingToolbarLayout = CollapsingToolbarLayout.this;
                if (CollapsingToolbarLayout.this.getHeight() + i < CollapsingToolbarLayout.this.getScrimTriggerOffset() + systemWindowInsetTop) {
                    z = true;
                }
                collapsingToolbarLayout.setScrimsShown(z);
            }
            if (CollapsingToolbarLayout.this.o != null && systemWindowInsetTop > 0) {
                ViewCompat.postInvalidateOnAnimation(CollapsingToolbarLayout.this);
            }
            CollapsingToolbarLayout.this.k.a(((float) Math.abs(i)) / ((float) ((CollapsingToolbarLayout.this.getHeight() - ViewCompat.getMinimumHeight(CollapsingToolbarLayout.this)) - systemWindowInsetTop)));
            if (Math.abs(i) == totalScrollRange) {
                ViewCompat.setElevation(appBarLayout, appBarLayout.getTargetElevation());
            } else {
                ViewCompat.setElevation(appBarLayout, AutoScrollHelper.RELATIVE_UNSPECIFIED);
            }
        }
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return c();
    }

    protected /* synthetic */ android.widget.FrameLayout.LayoutParams generateDefaultLayoutParams() {
        return c();
    }

    public CollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = true;
        this.j = new Rect();
        be.a(context);
        this.k = new l(this);
        this.k.a(a.e);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CollapsingToolbarLayout, i, R.style.Widget_Design_CollapsingToolbar);
        this.k.c(obtainStyledAttributes.getInt(R.styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
        this.k.d(obtainStyledAttributes.getInt(R.styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.i = dimensionPixelSize;
        this.h = dimensionPixelSize;
        this.g = dimensionPixelSize;
        this.f = dimensionPixelSize;
        if (obtainStyledAttributes.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart)) {
            this.f = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart, 0);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd)) {
            this.h = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd, 0);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop)) {
            this.g = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop, 0);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom)) {
            this.i = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom, 0);
        }
        this.l = obtainStyledAttributes.getBoolean(R.styleable.CollapsingToolbarLayout_titleEnabled, true);
        setTitle(obtainStyledAttributes.getText(R.styleable.CollapsingToolbarLayout_title));
        this.k.f(R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
        this.k.e(R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        if (obtainStyledAttributes.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance)) {
            this.k.f(obtainStyledAttributes.getResourceId(R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance)) {
            this.k.e(obtainStyledAttributes.getResourceId(R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance, 0));
        }
        setContentScrim(obtainStyledAttributes.getDrawable(R.styleable.CollapsingToolbarLayout_contentScrim));
        setStatusBarScrim(obtainStyledAttributes.getDrawable(R.styleable.CollapsingToolbarLayout_statusBarScrim));
        this.b = obtainStyledAttributes.getResourceId(R.styleable.CollapsingToolbarLayout_toolbarId, -1);
        obtainStyledAttributes.recycle();
        setWillNotDraw(false);
        ViewCompat.setOnApplyWindowInsetsListener(this, new m(this));
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            if (this.s == null) {
                this.s = new a();
            }
            ((AppBarLayout) parent).a(this.s);
        }
        ViewCompat.requestApplyInsets(this);
    }

    protected void onDetachedFromWindow() {
        ViewParent parent = getParent();
        if (this.s != null && (parent instanceof AppBarLayout)) {
            AppBarLayout appBarLayout = (AppBarLayout) parent;
            android.support.design.widget.AppBarLayout.a aVar = this.s;
            if (aVar != null) {
                appBarLayout.b.remove(aVar);
            }
        }
        super.onDetachedFromWindow();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        a();
        if (this.c == null && this.n != null && this.p > 0) {
            this.n.mutate().setAlpha(this.p);
            this.n.draw(canvas);
        }
        if (this.l && this.m) {
            this.k.a(canvas);
        }
        if (this.o != null && this.p > 0) {
            int systemWindowInsetTop = this.u != null ? this.u.getSystemWindowInsetTop() : 0;
            if (systemWindowInsetTop > 0) {
                this.o.setBounds(0, -this.t, getWidth(), systemWindowInsetTop - this.t);
                this.o.mutate().setAlpha(this.p);
                this.o.draw(canvas);
            }
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        a();
        if (view == this.c && this.n != null && this.p > 0) {
            this.n.mutate().setAlpha(this.p);
            this.n.draw(canvas);
        }
        return super.drawChild(canvas, view, j);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.n != null) {
            this.n.setBounds(0, 0, i, i2);
        }
    }

    private void a() {
        if (this.a) {
            View view;
            this.c = null;
            this.d = null;
            if (this.b != -1) {
                this.c = (Toolbar) findViewById(this.b);
                if (this.c != null) {
                    view = this.c;
                    ViewParent parent = view.getParent();
                    while (r1 != this && r1 != null) {
                        if (r1 instanceof View) {
                            view = r1;
                        }
                        parent = r1.getParent();
                    }
                    this.d = view;
                }
            }
            if (this.c == null) {
                Toolbar toolbar;
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    view = getChildAt(i);
                    if (view instanceof Toolbar) {
                        toolbar = (Toolbar) view;
                        break;
                    }
                }
                toolbar = null;
                this.c = toolbar;
            }
            b();
            this.a = false;
        }
    }

    private void b() {
        if (!(this.l || this.e == null)) {
            ViewParent parent = this.e.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.e);
            }
        }
        if (this.l && this.c != null) {
            if (this.e == null) {
                this.e = new View(getContext());
            }
            if (this.e.getParent() == null) {
                this.c.addView(this.e, -1, -1);
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        a();
        super.onMeasure(i, i2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        Object obj = 1;
        int i6 = 0;
        super.onLayout(z, i, i2, i3, i4);
        if (this.l && this.e != null) {
            boolean z2 = ViewCompat.isAttachedToWindow(this.e) && this.e.getVisibility() == 0;
            this.m = z2;
            if (this.m) {
                int i7;
                if (this.d == null || this.d == this) {
                    i5 = 0;
                } else {
                    i5 = ((LayoutParams) this.d.getLayoutParams()).bottomMargin;
                }
                bn.a(this, this.e, this.j);
                this.k.b(this.j.left, (i4 - this.j.height()) - i5, this.j.right, i4 - i5);
                if (ViewCompat.getLayoutDirection(this) != 1) {
                    i7 = 0;
                }
                l lVar = this.k;
                i5 = i7 != 0 ? this.h : this.f;
                int i8 = this.j.bottom + this.g;
                int i9 = i3 - i;
                if (i7 != 0) {
                    i7 = this.f;
                } else {
                    i7 = this.h;
                }
                lVar.a(i5, i8, i9 - i7, (i4 - i2) - this.i);
                this.k.b();
            }
        }
        i5 = getChildCount();
        while (i6 < i5) {
            View childAt = getChildAt(i6);
            if (this.u != null && !ViewCompat.getFitsSystemWindows(childAt)) {
                int systemWindowInsetTop = this.u.getSystemWindowInsetTop();
                if (childAt.getTop() < systemWindowInsetTop) {
                    ViewCompat.offsetTopAndBottom(childAt, systemWindowInsetTop);
                }
            }
            c(childAt).a();
            i6++;
        }
        if (this.c != null) {
            if (this.l && TextUtils.isEmpty(this.k.i)) {
                this.k.a(this.c.getTitle());
            }
            if (this.d == null || this.d == this) {
                setMinimumHeight(b(this.c));
            } else {
                setMinimumHeight(b(this.d));
            }
        }
    }

    private static int b(View view) {
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof MarginLayoutParams)) {
            return view.getHeight();
        }
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
        return marginLayoutParams.bottomMargin + (view.getHeight() + marginLayoutParams.topMargin);
    }

    private static bp c(View view) {
        bp bpVar = (bp) view.getTag(R.id.view_offset_helper);
        if (bpVar != null) {
            return bpVar;
        }
        bpVar = new bp(view);
        view.setTag(R.id.view_offset_helper, bpVar);
        return bpVar;
    }

    public void setTitle(CharSequence charSequence) {
        this.k.a(charSequence);
    }

    public CharSequence getTitle() {
        return this.l ? this.k.i : null;
    }

    public void setTitleEnabled(boolean z) {
        if (z != this.l) {
            this.l = z;
            b();
            requestLayout();
        }
    }

    public void setScrimsShown(boolean z) {
        int i = VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX;
        int i2 = 0;
        if (!ViewCompat.isLaidOut(this) || isInEditMode()) {
            int i3 = 0;
        } else {
            Object obj = 1;
        }
        if (this.q != z) {
            if (i3 != 0) {
                if (z) {
                    i2 = 255;
                }
                a();
                if (this.r == null) {
                    this.r = bq.a();
                    this.r.a((int) Impl.STATUS_LX_VIP_ERROR_START);
                    this.r.a(i2 > this.p ? a.c : a.d);
                    this.r.a(new n(this));
                } else if (this.r.a.b()) {
                    this.r.a.e();
                }
                this.r.a(this.p, i2);
                this.r.a.a();
            } else {
                if (!z) {
                    i = 0;
                }
                setScrimAlpha(i);
            }
            this.q = z;
        }
    }

    private void setScrimAlpha(int i) {
        if (i != this.p) {
            if (!(this.n == null || this.c == null)) {
                ViewCompat.postInvalidateOnAnimation(this.c);
            }
            this.p = i;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setContentScrim(Drawable drawable) {
        Drawable drawable2 = null;
        if (this.n != drawable) {
            if (this.n != null) {
                this.n.setCallback(null);
            }
            if (drawable != null) {
                drawable2 = drawable.mutate();
            }
            this.n = drawable2;
            if (this.n != null) {
                this.n.setBounds(0, 0, getWidth(), getHeight());
                this.n.setCallback(this);
                this.n.setAlpha(this.p);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setContentScrimColor(int i) {
        setContentScrim(new ColorDrawable(i));
    }

    public void setContentScrimResource(int i) {
        setContentScrim(ContextCompat.getDrawable(getContext(), i));
    }

    public Drawable getContentScrim() {
        return this.n;
    }

    public void setStatusBarScrim(Drawable drawable) {
        Drawable drawable2 = null;
        if (this.o != drawable) {
            if (this.o != null) {
                this.o.setCallback(null);
            }
            if (drawable != null) {
                drawable2 = drawable.mutate();
            }
            this.o = drawable2;
            if (this.o != null) {
                if (this.o.isStateful()) {
                    this.o.setState(getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.o, ViewCompat.getLayoutDirection(this));
                this.o.setVisible(getVisibility() == 0, false);
                this.o.setCallback(this);
                this.o.setAlpha(this.p);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        int i = 0;
        Drawable drawable = this.o;
        if (drawable != null && drawable.isStateful()) {
            i = drawable.setState(drawableState) | 0;
        }
        drawable = this.n;
        if (drawable != null && drawable.isStateful()) {
            i |= drawable.setState(drawableState);
        }
        if (i != 0) {
            invalidate();
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.n || drawable == this.o;
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        if (!(this.o == null || this.o.isVisible() == z)) {
            this.o.setVisible(z, false);
        }
        if (this.n != null && this.n.isVisible() != z) {
            this.n.setVisible(z, false);
        }
    }

    public void setStatusBarScrimColor(int i) {
        setStatusBarScrim(new ColorDrawable(i));
    }

    public void setStatusBarScrimResource(int i) {
        setStatusBarScrim(ContextCompat.getDrawable(getContext(), i));
    }

    public Drawable getStatusBarScrim() {
        return this.o;
    }

    public void setCollapsedTitleTextAppearance(int i) {
        this.k.e(i);
    }

    public void setCollapsedTitleTextColor(int i) {
        this.k.a(i);
    }

    public void setCollapsedTitleGravity(int i) {
        this.k.d(i);
    }

    public int getCollapsedTitleGravity() {
        return this.k.c;
    }

    public void setExpandedTitleTextAppearance(int i) {
        this.k.f(i);
    }

    public void setExpandedTitleColor(int i) {
        this.k.b(i);
    }

    public void setExpandedTitleGravity(int i) {
        this.k.c(i);
    }

    public int getExpandedTitleGravity() {
        return this.k.b;
    }

    public void setCollapsedTitleTypeface(Typeface typeface) {
        l lVar = this.k;
        if (lVar.g != typeface) {
            lVar.g = typeface;
            lVar.b();
        }
    }

    public Typeface getCollapsedTitleTypeface() {
        return this.k.a();
    }

    public void setExpandedTitleTypeface(Typeface typeface) {
        l lVar = this.k;
        if (lVar.h != typeface) {
            lVar.h = typeface;
            lVar.b();
        }
    }

    public Typeface getExpandedTitleTypeface() {
        l lVar = this.k;
        return lVar.h != null ? lVar.h : Typeface.DEFAULT;
    }

    public int getExpandedTitleMarginStart() {
        return this.f;
    }

    public void setExpandedTitleMarginStart(int i) {
        this.f = i;
        requestLayout();
    }

    public int getExpandedTitleMarginTop() {
        return this.g;
    }

    public void setExpandedTitleMarginTop(int i) {
        this.g = i;
        requestLayout();
    }

    public int getExpandedTitleMarginEnd() {
        return this.h;
    }

    public void setExpandedTitleMarginEnd(int i) {
        this.h = i;
        requestLayout();
    }

    public int getExpandedTitleMarginBottom() {
        return this.i;
    }

    public void setExpandedTitleMarginBottom(int i) {
        this.i = i;
        requestLayout();
    }

    final int getScrimTriggerOffset() {
        return ViewCompat.getMinimumHeight(this) * 2;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    private LayoutParams c() {
        return new LayoutParams(super.generateDefaultLayoutParams());
    }

    public android.widget.FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    static /* synthetic */ WindowInsetsCompat a(CollapsingToolbarLayout collapsingToolbarLayout, WindowInsetsCompat windowInsetsCompat) {
        if (collapsingToolbarLayout.u != windowInsetsCompat) {
            collapsingToolbarLayout.u = windowInsetsCompat;
            collapsingToolbarLayout.requestLayout();
        }
        return windowInsetsCompat.consumeSystemWindowInsets();
    }
}
