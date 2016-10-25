package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.appcompat.R;
import android.support.v7.view.c;
import android.support.v7.view.g;
import android.support.v7.view.menu.f;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.m;
import android.support.v7.view.menu.q;
import android.support.v7.widget.ActionMenuView.d;
import android.support.v7.widget.Toolbar.LayoutParams;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyAgent;

public class Toolbar extends ViewGroup {
    private int A;
    private int B;
    private boolean C;
    private boolean D;
    private final ArrayList<View> E;
    private final int[] F;
    private b G;
    private final d H;
    private cr I;
    private android.support.v7.view.menu.m.a J;
    private android.support.v7.view.menu.f.a K;
    private boolean L;
    private final Runnable M;
    private final r N;
    ActionMenuView a;
    TextView b;
    TextView c;
    View d;
    Context e;
    int f;
    int g;
    int h;
    final bk i;
    final ArrayList<View> j;
    ActionMenuPresenter k;
    a l;
    private ImageButton m;
    private ImageView n;
    private Drawable o;
    private CharSequence p;
    private ImageButton q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private CharSequence y;
    private CharSequence z;

    public static class LayoutParams extends android.support.v7.app.ActionBar.LayoutParams {
        int b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = 0;
        }

        public LayoutParams() {
            this.b = 0;
            this.a = 8388627;
        }

        public LayoutParams(android.support.v7.widget.Toolbar.LayoutParams layoutParams) {
            super((android.support.v7.app.ActionBar.LayoutParams) layoutParams);
            this.b = 0;
            this.b = layoutParams.b;
        }

        public LayoutParams(android.support.v7.app.ActionBar.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = 0;
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super((android.view.ViewGroup.LayoutParams) marginLayoutParams);
            this.b = 0;
            this.leftMargin = marginLayoutParams.leftMargin;
            this.topMargin = marginLayoutParams.topMargin;
            this.rightMargin = marginLayoutParams.rightMargin;
            this.bottomMargin = marginLayoutParams.bottomMargin;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = 0;
        }
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<android.support.v7.widget.Toolbar.SavedState> CREATOR;
        int a;
        boolean b;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
            this.b = parcel.readInt() != 0;
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeInt(this.b ? 1 : 0);
        }

        static {
            CREATOR = new cq();
        }
    }

    private class a implements m {
        f a;
        h b;

        private a() {
        }

        public final void a(Context context, f fVar) {
            if (!(this.a == null || this.b == null)) {
                this.a.b(this.b);
            }
            this.a = fVar;
        }

        public final void a(boolean z) {
            Object obj = null;
            if (this.b != null) {
                if (this.a != null) {
                    int size = this.a.size();
                    for (int i = 0; i < size; i++) {
                        if (this.a.getItem(i) == this.b) {
                            obj = 1;
                            break;
                        }
                    }
                }
                if (obj == null) {
                    b(this.b);
                }
            }
        }

        public final boolean a(q qVar) {
            return false;
        }

        public final void a(f fVar, boolean z) {
        }

        public final boolean a() {
            return false;
        }

        public final boolean a(h hVar) {
            Toolbar.b(Toolbar.this);
            if (Toolbar.this.q.getParent() != Toolbar.this) {
                Toolbar.this.addView(Toolbar.this.q);
            }
            Toolbar.this.d = hVar.getActionView();
            this.b = hVar;
            if (Toolbar.this.d.getParent() != Toolbar.this) {
                android.view.ViewGroup.LayoutParams e = Toolbar.e();
                e.a = 8388611 | (Toolbar.this.r & 112);
                e.b = 2;
                Toolbar.this.d.setLayoutParams(e);
                Toolbar.this.addView(Toolbar.this.d);
            }
            Toolbar toolbar = Toolbar.this;
            for (int childCount = toolbar.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = toolbar.getChildAt(childCount);
                if (((LayoutParams) childAt.getLayoutParams()).b != 2 && childAt != toolbar.a) {
                    toolbar.removeViewAt(childCount);
                    toolbar.j.add(childAt);
                }
            }
            Toolbar.this.requestLayout();
            hVar.d(true);
            if (Toolbar.this.d instanceof c) {
                ((c) Toolbar.this.d).a();
            }
            return true;
        }

        public final boolean b(h hVar) {
            if (Toolbar.this.d instanceof c) {
                ((c) Toolbar.this.d).b();
            }
            Toolbar.this.removeView(Toolbar.this.d);
            Toolbar.this.removeView(Toolbar.this.q);
            Toolbar.this.d = null;
            Toolbar toolbar = Toolbar.this;
            for (int size = toolbar.j.size() - 1; size >= 0; size--) {
                toolbar.addView((View) toolbar.j.get(size));
            }
            toolbar.j.clear();
            this.b = null;
            Toolbar.this.requestLayout();
            hVar.d(false);
            return true;
        }

        public final int b() {
            return 0;
        }

        public final Parcelable c() {
            return null;
        }

        public final void a(Parcelable parcelable) {
        }
    }

    public static interface b {
        boolean a();
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return a(layoutParams);
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.toolbarStyle);
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = new bk();
        this.x = 8388627;
        this.E = new ArrayList();
        this.j = new ArrayList();
        this.F = new int[2];
        this.H = new cn(this);
        this.M = new co(this);
        cm a = cm.a(getContext(), attributeSet, R.styleable.Toolbar, i);
        this.g = a.e(R.styleable.Toolbar_titleTextAppearance, 0);
        this.h = a.e(R.styleable.Toolbar_subtitleTextAppearance, 0);
        this.x = a.a.getInteger(R.styleable.Toolbar_android_gravity, this.x);
        this.r = 48;
        int b = a.b(R.styleable.Toolbar_titleMargins, 0);
        this.w = b;
        this.v = b;
        this.u = b;
        this.t = b;
        b = a.b(R.styleable.Toolbar_titleMarginStart, -1);
        if (b >= 0) {
            this.t = b;
        }
        b = a.b(R.styleable.Toolbar_titleMarginEnd, -1);
        if (b >= 0) {
            this.u = b;
        }
        b = a.b(R.styleable.Toolbar_titleMarginTop, -1);
        if (b >= 0) {
            this.v = b;
        }
        b = a.b(R.styleable.Toolbar_titleMarginBottom, -1);
        if (b >= 0) {
            this.w = b;
        }
        this.s = a.c(R.styleable.Toolbar_maxButtonHeight, -1);
        b = a.b(R.styleable.Toolbar_contentInsetStart, ExploreByTouchHelper.INVALID_ID);
        int b2 = a.b(R.styleable.Toolbar_contentInsetEnd, ExploreByTouchHelper.INVALID_ID);
        int c = a.c(R.styleable.Toolbar_contentInsetLeft, 0);
        int c2 = a.c(R.styleable.Toolbar_contentInsetRight, 0);
        bk bkVar = this.i;
        bkVar.h = false;
        if (c != Integer.MIN_VALUE) {
            bkVar.e = c;
            bkVar.a = c;
        }
        if (c2 != Integer.MIN_VALUE) {
            bkVar.f = c2;
            bkVar.b = c2;
        }
        if (!(b == Integer.MIN_VALUE && b2 == Integer.MIN_VALUE)) {
            this.i.a(b, b2);
        }
        this.o = a.a(R.styleable.Toolbar_collapseIcon);
        this.p = a.c(R.styleable.Toolbar_collapseContentDescription);
        CharSequence c3 = a.c(R.styleable.Toolbar_title);
        if (!TextUtils.isEmpty(c3)) {
            setTitle(c3);
        }
        c3 = a.c(R.styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty(c3)) {
            setSubtitle(c3);
        }
        this.e = getContext();
        setPopupTheme(a.e(R.styleable.Toolbar_popupTheme, 0));
        Drawable a2 = a.a(R.styleable.Toolbar_navigationIcon);
        if (a2 != null) {
            setNavigationIcon(a2);
        }
        c3 = a.c(R.styleable.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(c3)) {
            setNavigationContentDescription(c3);
        }
        a2 = a.a(R.styleable.Toolbar_logo);
        if (a2 != null) {
            setLogo(a2);
        }
        c3 = a.c(R.styleable.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(c3)) {
            setLogoDescription(c3);
        }
        if (a.e(R.styleable.Toolbar_titleTextColor)) {
            setTitleTextColor(a.d(R.styleable.Toolbar_titleTextColor));
        }
        if (a.e(R.styleable.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(a.d(R.styleable.Toolbar_subtitleTextColor));
        }
        a.a.recycle();
        this.N = r.a();
    }

    public void setPopupTheme(int i) {
        if (this.f != i) {
            this.f = i;
            if (i == 0) {
                this.e = getContext();
            } else {
                this.e = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public int getPopupTheme() {
        return this.f;
    }

    public void onRtlPropertiesChanged(int i) {
        boolean z = true;
        if (VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i);
        }
        bk bkVar = this.i;
        if (i != 1) {
            z = false;
        }
        if (z != bkVar.g) {
            bkVar.g = z;
            if (!bkVar.h) {
                bkVar.a = bkVar.e;
                bkVar.b = bkVar.f;
            } else if (z) {
                bkVar.a = bkVar.d != Integer.MIN_VALUE ? bkVar.d : bkVar.e;
                bkVar.b = bkVar.c != Integer.MIN_VALUE ? bkVar.c : bkVar.f;
            } else {
                bkVar.a = bkVar.c != Integer.MIN_VALUE ? bkVar.c : bkVar.e;
                bkVar.b = bkVar.d != Integer.MIN_VALUE ? bkVar.d : bkVar.f;
            }
        }
    }

    public void setLogo(int i) {
        setLogo(this.N.a(getContext(), i, false));
    }

    public final boolean a() {
        if (this.a != null) {
            boolean z;
            ActionMenuView actionMenuView = this.a;
            if (actionMenuView.c == null || !actionMenuView.c.i()) {
                Object obj = null;
            } else {
                z = true;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final boolean b() {
        if (this.a != null) {
            boolean z;
            ActionMenuView actionMenuView = this.a;
            if (actionMenuView.c == null || !actionMenuView.c.e()) {
                Object obj = null;
            } else {
                z = true;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            f();
            if (!d(this.n)) {
                a(this.n, true);
            }
        } else if (this.n != null && d(this.n)) {
            removeView(this.n);
            this.j.remove(this.n);
        }
        if (this.n != null) {
            this.n.setImageDrawable(drawable);
        }
    }

    public Drawable getLogo() {
        return this.n != null ? this.n.getDrawable() : null;
    }

    public void setLogoDescription(int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            f();
        }
        if (this.n != null) {
            this.n.setContentDescription(charSequence);
        }
    }

    public CharSequence getLogoDescription() {
        return this.n != null ? this.n.getContentDescription() : null;
    }

    private void f() {
        if (this.n == null) {
            this.n = new ImageView(getContext());
        }
    }

    public final void c() {
        h hVar = this.l == null ? null : this.l.b;
        if (hVar != null) {
            hVar.collapseActionView();
        }
    }

    public CharSequence getTitle() {
        return this.y;
    }

    public void setTitle(int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.b == null) {
                Context context = getContext();
                this.b = new TextView(context);
                this.b.setSingleLine();
                this.b.setEllipsize(TruncateAt.END);
                if (this.g != 0) {
                    this.b.setTextAppearance(context, this.g);
                }
                if (this.A != 0) {
                    this.b.setTextColor(this.A);
                }
            }
            if (!d(this.b)) {
                a(this.b, true);
            }
        } else if (this.b != null && d(this.b)) {
            removeView(this.b);
            this.j.remove(this.b);
        }
        if (this.b != null) {
            this.b.setText(charSequence);
        }
        this.y = charSequence;
    }

    public CharSequence getSubtitle() {
        return this.z;
    }

    public void setSubtitle(int i) {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.c == null) {
                Context context = getContext();
                this.c = new TextView(context);
                this.c.setSingleLine();
                this.c.setEllipsize(TruncateAt.END);
                if (this.h != 0) {
                    this.c.setTextAppearance(context, this.h);
                }
                if (this.B != 0) {
                    this.c.setTextColor(this.B);
                }
            }
            if (!d(this.c)) {
                a(this.c, true);
            }
        } else if (this.c != null && d(this.c)) {
            removeView(this.c);
            this.j.remove(this.c);
        }
        if (this.c != null) {
            this.c.setText(charSequence);
        }
        this.z = charSequence;
    }

    public void setTitleTextColor(int i) {
        this.A = i;
        if (this.b != null) {
            this.b.setTextColor(i);
        }
    }

    public void setSubtitleTextColor(int i) {
        this.B = i;
        if (this.c != null) {
            this.c.setTextColor(i);
        }
    }

    public CharSequence getNavigationContentDescription() {
        return this.m != null ? this.m.getContentDescription() : null;
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            h();
        }
        if (this.m != null) {
            this.m.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(this.N.a(getContext(), i, false));
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            h();
            if (!d(this.m)) {
                a(this.m, true);
            }
        } else if (this.m != null && d(this.m)) {
            removeView(this.m);
            this.j.remove(this.m);
        }
        if (this.m != null) {
            this.m.setImageDrawable(drawable);
        }
    }

    public Drawable getNavigationIcon() {
        return this.m != null ? this.m.getDrawable() : null;
    }

    public void setNavigationOnClickListener(OnClickListener onClickListener) {
        h();
        this.m.setOnClickListener(onClickListener);
    }

    public Menu getMenu() {
        g();
        return this.a.getMenu();
    }

    public void setOverflowIcon(Drawable drawable) {
        g();
        this.a.setOverflowIcon(drawable);
    }

    public Drawable getOverflowIcon() {
        g();
        return this.a.getOverflowIcon();
    }

    private void g() {
        d();
        if (this.a.a == null) {
            f fVar = (f) this.a.getMenu();
            if (this.l == null) {
                this.l = new a();
            }
            this.a.setExpandedActionViewsExclusive(true);
            fVar.a(this.l, this.e);
        }
    }

    final void d() {
        if (this.a == null) {
            this.a = new ActionMenuView(getContext());
            this.a.setPopupTheme(this.f);
            this.a.setOnMenuItemClickListener(this.H);
            this.a.a(this.J, this.K);
            android.view.ViewGroup.LayoutParams layoutParams = new LayoutParams();
            layoutParams.a = 8388613 | (this.r & 112);
            this.a.setLayoutParams(layoutParams);
            a(this.a, false);
        }
    }

    public MenuInflater getMenuInflater() {
        return new g(getContext());
    }

    public void setOnMenuItemClickListener(b bVar) {
        this.G = bVar;
    }

    public int getContentInsetStart() {
        bk bkVar = this.i;
        return bkVar.g ? bkVar.b : bkVar.a;
    }

    public int getContentInsetEnd() {
        bk bkVar = this.i;
        return bkVar.g ? bkVar.a : bkVar.b;
    }

    public int getContentInsetLeft() {
        return this.i.a;
    }

    public int getContentInsetRight() {
        return this.i.b;
    }

    private void h() {
        if (this.m == null) {
            this.m = new ImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            android.view.ViewGroup.LayoutParams layoutParams = new LayoutParams();
            layoutParams.a = 8388611 | (this.r & 112);
            this.m.setLayoutParams(layoutParams);
        }
    }

    private void a(View view, boolean z) {
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams();
        } else if (checkLayoutParams(layoutParams)) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        } else {
            layoutParams = a(layoutParams);
        }
        layoutParams.b = 1;
        if (!z || this.d == null) {
            addView(view, layoutParams);
            return;
        }
        view.setLayoutParams(layoutParams);
        this.j.add(view);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        if (!(this.l == null || this.l.b == null)) {
            savedState.a = this.l.b.getItemId();
        }
        savedState.b = a();
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            Menu menu;
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            if (this.a != null) {
                menu = this.a.a;
            } else {
                menu = null;
            }
            if (!(savedState.a == 0 || this.l == null || menu == null)) {
                MenuItem findItem = menu.findItem(savedState.a);
                if (findItem != null) {
                    MenuItemCompat.expandActionView(findItem);
                }
            }
            if (savedState.b) {
                removeCallbacks(this.M);
                post(this.M);
                return;
            }
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.M);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            this.C = false;
        }
        if (!this.C) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.C = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.C = false;
        }
        return true;
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 9) {
            this.D = false;
        }
        if (!this.D) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.D = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.D = false;
        }
        return true;
    }

    private void a(View view, int i, int i2, int i3, int i4) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = getChildMeasureSpec(i, (((getPaddingLeft() + getPaddingRight()) + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin) + i2, marginLayoutParams.width);
        int childMeasureSpec2 = getChildMeasureSpec(i3, (((getPaddingTop() + getPaddingBottom()) + marginLayoutParams.topMargin) + marginLayoutParams.bottomMargin) + 0, marginLayoutParams.height);
        int mode = MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i4 >= 0) {
            if (mode != 0) {
                i4 = Math.min(MeasureSpec.getSize(childMeasureSpec2), i4);
            }
            childMeasureSpec2 = MeasureSpec.makeMeasureSpec(i4, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    private int a(View view, int i, int i2, int i3, int i4, int[] iArr) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i5) + Math.max(0, i6);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(getChildMeasureSpec(i, ((getPaddingLeft() + getPaddingRight()) + max) + i2, marginLayoutParams.width), getChildMeasureSpec(i3, (((getPaddingTop() + getPaddingBottom()) + marginLayoutParams.topMargin) + marginLayoutParams.bottomMargin) + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int a;
        int max;
        int max2;
        Object obj;
        int[] iArr = this.F;
        if (cw.a(this)) {
            i3 = 0;
            i4 = 1;
        } else {
            i3 = 1;
            i4 = 0;
        }
        int i5 = 0;
        if (a(this.m)) {
            a(this.m, i, 0, i2, this.s);
            i5 = this.m.getMeasuredWidth() + b(this.m);
            a = cw.a(0, ViewCompat.getMeasuredState(this.m));
            max = Math.max(0, this.m.getMeasuredHeight() + c(this.m));
        } else {
            a = 0;
            max = 0;
        }
        if (a(this.q)) {
            a(this.q, i, 0, i2, this.s);
            i5 = this.q.getMeasuredWidth() + b(this.q);
            max = Math.max(max, this.q.getMeasuredHeight() + c(this.q));
            a = cw.a(a, ViewCompat.getMeasuredState(this.q));
        }
        int contentInsetStart = getContentInsetStart();
        int max3 = Math.max(contentInsetStart, i5) + 0;
        iArr[i4] = Math.max(0, contentInsetStart - i5);
        i5 = 0;
        if (a(this.a)) {
            a(this.a, i, max3, i2, this.s);
            i5 = this.a.getMeasuredWidth() + b(this.a);
            max = Math.max(max, this.a.getMeasuredHeight() + c(this.a));
            a = cw.a(a, ViewCompat.getMeasuredState(this.a));
        }
        contentInsetStart = getContentInsetEnd();
        max3 += Math.max(contentInsetStart, i5);
        iArr[i3] = Math.max(0, contentInsetStart - i5);
        if (a(this.d)) {
            max3 += a(this.d, i, max3, i2, 0, iArr);
            max = Math.max(max, this.d.getMeasuredHeight() + c(this.d));
            a = cw.a(a, ViewCompat.getMeasuredState(this.d));
        }
        if (a(this.n)) {
            max3 += a(this.n, i, max3, i2, 0, iArr);
            max = Math.max(max, this.n.getMeasuredHeight() + c(this.n));
            a = cw.a(a, ViewCompat.getMeasuredState(this.n));
        }
        i4 = getChildCount();
        i3 = 0;
        int i6 = a;
        int i7 = max;
        while (i3 < i4) {
            View childAt = getChildAt(i3);
            if (((LayoutParams) childAt.getLayoutParams()).b == 0 && a(childAt)) {
                max3 += a(childAt, i, max3, i2, 0, iArr);
                max2 = Math.max(i7, childAt.getMeasuredHeight() + c(childAt));
                i5 = cw.a(i6, ViewCompat.getMeasuredState(childAt));
                contentInsetStart = max2;
            } else {
                i5 = i6;
                contentInsetStart = i7;
            }
            i3++;
            i6 = i5;
            i7 = contentInsetStart;
        }
        contentInsetStart = 0;
        i5 = 0;
        int i8 = this.v + this.w;
        max2 = this.t + this.u;
        if (a(this.b)) {
            a(this.b, i, max3 + max2, i2, i8, iArr);
            contentInsetStart = b(this.b) + this.b.getMeasuredWidth();
            i5 = this.b.getMeasuredHeight() + c(this.b);
            i6 = cw.a(i6, ViewCompat.getMeasuredState(this.b));
        }
        if (a(this.c)) {
            contentInsetStart = Math.max(contentInsetStart, a(this.c, i, max3 + max2, i2, i8 + i5, iArr));
            i5 += this.c.getMeasuredHeight() + c(this.c);
            i6 = cw.a(i6, ViewCompat.getMeasuredState(this.c));
        }
        contentInsetStart += max3;
        i5 = Math.max(i7, i5) + (getPaddingTop() + getPaddingBottom());
        int i9 = i;
        max2 = ViewCompat.resolveSizeAndState(Math.max(contentInsetStart + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), i9, -16777216 & i6);
        i5 = ViewCompat.resolveSizeAndState(Math.max(i5, getSuggestedMinimumHeight()), i2, i6 << 16);
        if (this.L) {
            max3 = getChildCount();
            for (contentInsetStart = 0; contentInsetStart < max3; contentInsetStart++) {
                View childAt2 = getChildAt(contentInsetStart);
                if (a(childAt2) && childAt2.getMeasuredWidth() > 0 && childAt2.getMeasuredHeight() > 0) {
                    obj = null;
                    break;
                }
            }
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            i5 = 0;
        }
        setMeasuredDimension(max2, i5);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int measuredHeight;
        int paddingTop;
        if (ViewCompat.getLayoutDirection(this) == 1) {
            i5 = 1;
        } else {
            Object obj = null;
        }
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop2 = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i9 = width - paddingRight;
        int[] iArr = this.F;
        iArr[1] = 0;
        iArr[0] = 0;
        int minimumHeight = ViewCompat.getMinimumHeight(this);
        if (!a(this.m)) {
            i6 = paddingLeft;
        } else if (obj != null) {
            i9 = b(this.m, i9, iArr, minimumHeight);
            i6 = paddingLeft;
        } else {
            i6 = a(this.m, paddingLeft, iArr, minimumHeight);
        }
        if (a(this.q)) {
            if (obj != null) {
                i9 = b(this.q, i9, iArr, minimumHeight);
            } else {
                i6 = a(this.q, i6, iArr, minimumHeight);
            }
        }
        if (a(this.a)) {
            if (obj != null) {
                i6 = a(this.a, i6, iArr, minimumHeight);
            } else {
                i9 = b(this.a, i9, iArr, minimumHeight);
            }
        }
        iArr[0] = Math.max(0, getContentInsetLeft() - i6);
        iArr[1] = Math.max(0, getContentInsetRight() - ((width - paddingRight) - i9));
        i6 = Math.max(i6, getContentInsetLeft());
        i9 = Math.min(i9, (width - paddingRight) - getContentInsetRight());
        if (a(this.d)) {
            if (obj != null) {
                i9 = b(this.d, i9, iArr, minimumHeight);
            } else {
                i6 = a(this.d, i6, iArr, minimumHeight);
            }
        }
        if (!a(this.n)) {
            i7 = i9;
            i8 = i6;
        } else if (obj != null) {
            i7 = b(this.n, i9, iArr, minimumHeight);
            i8 = i6;
        } else {
            i7 = i9;
            i8 = a(this.n, i6, iArr, minimumHeight);
        }
        boolean a = a(this.b);
        boolean a2 = a(this.c);
        i6 = 0;
        if (a) {
            LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
            i6 = (layoutParams.bottomMargin + (layoutParams.topMargin + this.b.getMeasuredHeight())) + 0;
        }
        if (a2) {
            layoutParams = (LayoutParams) this.c.getLayoutParams();
            measuredHeight = (layoutParams.bottomMargin + (layoutParams.topMargin + this.c.getMeasuredHeight())) + i6;
        } else {
            measuredHeight = i6;
        }
        if (a || a2) {
            layoutParams = (LayoutParams) (a ? this.b : this.c).getLayoutParams();
            LayoutParams layoutParams2 = (LayoutParams) (a2 ? this.c : this.b).getLayoutParams();
            Object obj2 = ((!a || this.b.getMeasuredWidth() <= 0) && (!a2 || this.c.getMeasuredWidth() <= 0)) ? null : 1;
            switch (this.x & 112) {
                case com.xunlei.tdlive.R.styleable.AppCompatTheme_homeAsUpIndicator:
                    paddingTop = (layoutParams.topMargin + getPaddingTop()) + this.v;
                    break;
                case com.xunlei.tdlive.R.styleable.AppCompatTheme_panelMenuListTheme:
                    paddingTop = (((height - paddingBottom) - layoutParams2.bottomMargin) - this.w) - measuredHeight;
                    break;
                default:
                    paddingTop = (((height - paddingTop2) - paddingBottom) - measuredHeight) / 2;
                    if (paddingTop < layoutParams.topMargin + this.v) {
                        i9 = layoutParams.topMargin + this.v;
                    } else {
                        measuredHeight = (((height - paddingBottom) - measuredHeight) - paddingTop) - paddingTop2;
                        if (measuredHeight < layoutParams.bottomMargin + this.w) {
                            i9 = Math.max(0, paddingTop - ((layoutParams2.bottomMargin + this.w) - measuredHeight));
                        } else {
                            i9 = paddingTop;
                        }
                    }
                    paddingTop = paddingTop2 + i9;
                    break;
            }
            if (obj != null) {
                i9 = (obj2 != null ? this.t : 0) - iArr[1];
                i6 = i7 - Math.max(0, i9);
                iArr[1] = Math.max(0, -i9);
                if (a) {
                    layoutParams = (LayoutParams) this.b.getLayoutParams();
                    i5 = i6 - this.b.getMeasuredWidth();
                    i7 = this.b.getMeasuredHeight() + paddingTop;
                    this.b.layout(i5, paddingTop, i6, i7);
                    paddingTop = i7 + layoutParams.bottomMargin;
                    i7 = i5 - this.u;
                } else {
                    i7 = i6;
                }
                if (a2) {
                    layoutParams = (LayoutParams) this.c.getLayoutParams();
                    i5 = layoutParams.topMargin + paddingTop;
                    this.c.layout(i6 - this.c.getMeasuredWidth(), i5, i6, this.c.getMeasuredHeight() + i5);
                    i5 = i6 - this.u;
                    i9 = layoutParams.bottomMargin;
                    i9 = i5;
                } else {
                    i9 = i6;
                }
                if (obj2 != null) {
                    i9 = Math.min(i7, i9);
                } else {
                    i9 = i6;
                }
                i7 = i9;
            } else {
                i9 = (obj2 != null ? this.t : 0) - iArr[0];
                i8 += Math.max(0, i9);
                iArr[0] = Math.max(0, -i9);
                if (a) {
                    layoutParams = (LayoutParams) this.b.getLayoutParams();
                    i6 = this.b.getMeasuredWidth() + i8;
                    i5 = this.b.getMeasuredHeight() + paddingTop;
                    this.b.layout(i8, paddingTop, i6, i5);
                    i9 = layoutParams.bottomMargin + i5;
                    i5 = i6 + this.u;
                    i6 = i9;
                } else {
                    i5 = i8;
                    i6 = paddingTop;
                }
                if (a2) {
                    layoutParams = (LayoutParams) this.c.getLayoutParams();
                    i6 += layoutParams.topMargin;
                    paddingTop = this.c.getMeasuredWidth() + i8;
                    this.c.layout(i8, i6, paddingTop, this.c.getMeasuredHeight() + i6);
                    i6 = this.u + paddingTop;
                    i9 = layoutParams.bottomMargin;
                    i9 = i6;
                } else {
                    i9 = i8;
                }
                if (obj2 != null) {
                    i8 = Math.max(i5, i9);
                }
            }
        }
        a(this.E, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        int size = this.E.size();
        i5 = i8;
        for (i6 = 0; i6 < size; i6++) {
            i5 = a((View) this.E.get(i6), i5, iArr, minimumHeight);
        }
        a(this.E, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
        i8 = this.E.size();
        i6 = 0;
        measuredHeight = i7;
        while (i6 < i8) {
            i7 = b((View) this.E.get(i6), measuredHeight, iArr, minimumHeight);
            i6++;
            measuredHeight = i7;
        }
        a(this.E, 1);
        List list = this.E;
        i7 = iArr[0];
        i8 = iArr[1];
        paddingTop2 = list.size();
        size = i7;
        paddingTop = i8;
        i7 = 0;
        i8 = 0;
        while (i7 < paddingTop2) {
            View view = (View) list.get(i7);
            layoutParams = (LayoutParams) view.getLayoutParams();
            size = layoutParams.leftMargin - size;
            i9 = layoutParams.rightMargin - paddingTop;
            paddingBottom = Math.max(0, size);
            int max = Math.max(0, i9);
            size = Math.max(0, -size);
            paddingTop = Math.max(0, -i9);
            i7++;
            i8 += (view.getMeasuredWidth() + paddingBottom) + max;
        }
        i9 = ((((width - paddingLeft) - paddingRight) / 2) + paddingLeft) - (i8 / 2);
        i6 = i9 + i8;
        if (i9 < i5) {
            i9 = i5;
        } else if (i6 > measuredHeight) {
            i9 -= i6 - measuredHeight;
        }
        paddingLeft = this.E.size();
        i5 = 0;
        i6 = i9;
        while (i5 < paddingLeft) {
            i9 = a((View) this.E.get(i5), i6, iArr, minimumHeight);
            i5++;
            i6 = i9;
        }
        this.E.clear();
    }

    private int a(View view, int i, int[] iArr, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = layoutParams.leftMargin - iArr[0];
        int max = Math.max(0, i3) + i;
        iArr[0] = Math.max(0, -i3);
        i3 = a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, i3, max + measuredWidth, view.getMeasuredHeight() + i3);
        return (layoutParams.rightMargin + measuredWidth) + max;
    }

    private int b(View view, int i, int[] iArr, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = layoutParams.rightMargin - iArr[1];
        int max = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        i3 = a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, i3, max, view.getMeasuredHeight() + i3);
        return max - (layoutParams.leftMargin + measuredWidth);
    }

    private int a(View view, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int i3 = layoutParams.a & 112;
        switch (i3) {
            case com.xunlei.tdlive.R.styleable.Toolbar_titleMarginBottom:
            case com.xunlei.tdlive.R.styleable.AppCompatTheme_homeAsUpIndicator:
            case com.xunlei.tdlive.R.styleable.AppCompatTheme_panelMenuListTheme:
                break;
            default:
                i3 = this.x & 112;
                break;
        }
        switch (i3) {
            case com.xunlei.tdlive.R.styleable.AppCompatTheme_homeAsUpIndicator:
                return getPaddingTop() - i2;
            case com.xunlei.tdlive.R.styleable.AppCompatTheme_panelMenuListTheme:
                return (((getHeight() - getPaddingBottom()) - measuredHeight) - layoutParams.bottomMargin) - i2;
            default:
                int i4;
                i3 = getPaddingTop();
                int paddingBottom = getPaddingBottom();
                int height = getHeight();
                i2 = (((height - i3) - paddingBottom) - measuredHeight) / 2;
                if (i2 < layoutParams.topMargin) {
                    i4 = layoutParams.topMargin;
                } else {
                    measuredHeight = (((height - paddingBottom) - measuredHeight) - i2) - i3;
                    i4 = measuredHeight < layoutParams.bottomMargin ? Math.max(0, i2 - (layoutParams.bottomMargin - measuredHeight)) : i2;
                }
                return i4 + i3;
        }
    }

    private void a(List<View> list, int i) {
        Object obj = 1;
        int i2 = 0;
        if (ViewCompat.getLayoutDirection(this) != 1) {
            int i3 = 0;
        }
        int childCount = getChildCount();
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        list.clear();
        LayoutParams layoutParams;
        if (i3 != 0) {
            for (i2 = childCount - 1; i2 >= 0; i2--) {
                View childAt = getChildAt(i2);
                layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.b == 0 && a(childAt) && a(layoutParams.a) == absoluteGravity) {
                    list.add(childAt);
                }
            }
            return;
        }
        while (i2 < childCount) {
            View childAt2 = getChildAt(i2);
            layoutParams = (LayoutParams) childAt2.getLayoutParams();
            if (layoutParams.b == 0 && a(childAt2) && a(layoutParams.a) == absoluteGravity) {
                list.add(childAt2);
            }
            i2++;
        }
    }

    private int a(int i) {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i, layoutDirection) & 7;
        switch (absoluteGravity) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return absoluteGravity;
            default:
                return layoutDirection == 1 ? XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED : XZBDevice.DOWNLOAD_LIST_FAILED;
        }
    }

    private boolean a(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    private static int b(View view) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        return MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams) + MarginLayoutParamsCompat.getMarginStart(marginLayoutParams);
    }

    private static int c(View view) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.bottomMargin + marginLayoutParams.topMargin;
    }

    private static LayoutParams a(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof android.support.v7.app.ActionBar.LayoutParams) {
            return new LayoutParams((android.support.v7.app.ActionBar.LayoutParams) layoutParams);
        }
        return layoutParams instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    protected static LayoutParams e() {
        return new LayoutParams();
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof LayoutParams);
    }

    public ae getWrapper() {
        if (this.I == null) {
            this.I = new cr(this);
        }
        return this.I;
    }

    private boolean d(View view) {
        return view.getParent() == this || this.j.contains(view);
    }

    public void setCollapsible(boolean z) {
        this.L = z;
        requestLayout();
    }

    public final void a(android.support.v7.view.menu.m.a aVar, android.support.v7.view.menu.f.a aVar2) {
        this.J = aVar;
        this.K = aVar2;
        if (this.a != null) {
            this.a.a(aVar, aVar2);
        }
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    static /* synthetic */ void b(Toolbar toolbar) {
        if (toolbar.q == null) {
            toolbar.q = new ImageButton(toolbar.getContext(), null, R.attr.toolbarNavigationButtonStyle);
            toolbar.q.setImageDrawable(toolbar.o);
            toolbar.q.setContentDescription(toolbar.p);
            android.view.ViewGroup.LayoutParams layoutParams = new LayoutParams();
            layoutParams.a = 8388611 | (toolbar.r & 112);
            layoutParams.b = 2;
            toolbar.q.setLayoutParams(layoutParams);
            toolbar.q.setOnClickListener(new cp(toolbar));
        }
    }
}
