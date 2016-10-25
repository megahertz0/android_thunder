package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v7.view.b;
import android.support.v7.view.g;
import android.support.v7.view.h;
import android.support.v7.view.menu.f;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ae;
import android.support.v7.widget.bm;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.taobao.accs.common.Constants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

// compiled from: WindowDecorActionBar.java
public class af extends ActionBar implements android.support.v7.widget.ActionBarOverlayLayout.a {
    static final /* synthetic */ boolean h;
    private static final Interpolator i;
    private static final Interpolator j;
    private static final boolean k;
    private boolean A;
    private int B;
    private boolean C;
    private boolean D;
    private boolean E;
    private boolean F;
    private boolean G;
    private h H;
    private boolean I;
    a a;
    b b;
    android.support.v7.view.b.a c;
    boolean d;
    final ViewPropertyAnimatorListener e;
    final ViewPropertyAnimatorListener f;
    final ViewPropertyAnimatorUpdateListener g;
    private Context l;
    private Context m;
    private Activity n;
    private Dialog o;
    private ActionBarOverlayLayout p;
    private ActionBarContainer q;
    private ae r;
    private ActionBarContextView s;
    private View t;
    private bm u;
    private ArrayList<Object> v;
    private int w;
    private boolean x;
    private boolean y;
    private ArrayList<Object> z;

    // compiled from: WindowDecorActionBar.java
    public class a extends b implements android.support.v7.view.menu.f.a {
        private final Context d;
        private final f e;
        private android.support.v7.view.b.a f;
        private WeakReference<View> g;

        public a(Context context, android.support.v7.view.b.a aVar) {
            this.d = context;
            this.f = aVar;
            f fVar = new f(context);
            fVar.e = 1;
            this.e = fVar;
            this.e.a((android.support.v7.view.menu.f.a) this);
        }

        public final MenuInflater a() {
            return new g(this.d);
        }

        public final Menu b() {
            return this.e;
        }

        public final void c() {
            if (af.this == this) {
                if (af.a(af.this.D, af.this.E, false)) {
                    this.f.a(this);
                } else {
                    af.this.b = this;
                    af.this.c = this.f;
                }
                this.f = null;
                af.this.e(false);
                ActionBarContextView h = af.this.s;
                if (h.g == null) {
                    h.b();
                }
                af.this.r.a().sendAccessibilityEvent(R.styleable.AppCompatTheme_actionModeCutDrawable);
                af.this.p.setHideOnContentScrollEnabled(af.this.d);
                af.this = null;
            }
        }

        public final void d() {
            if (af.this == this) {
                this.e.d();
                this.f.b(this, this.e);
                this.e.e();
            }
        }

        public final boolean e() {
            this.e.d();
            boolean a = this.f.a((b) this, this.e);
            this.e.e();
            return a;
        }

        public final void a(View view) {
            af.this.s.setCustomView(view);
            this.g = new WeakReference(view);
        }

        public final void a(CharSequence charSequence) {
            af.this.s.setSubtitle(charSequence);
        }

        public final void b(CharSequence charSequence) {
            af.this.s.setTitle(charSequence);
        }

        public final void a(int i) {
            b(af.this.l.getResources().getString(i));
        }

        public final void b(int i) {
            a(af.this.l.getResources().getString(i));
        }

        public final CharSequence f() {
            return af.this.s.getTitle();
        }

        public final CharSequence g() {
            return af.this.s.getSubtitle();
        }

        public final void a(boolean z) {
            super.a(z);
            af.this.s.setTitleOptional(z);
        }

        public final boolean h() {
            return af.this.s.h;
        }

        public final View i() {
            return this.g != null ? (View) this.g.get() : null;
        }

        public final boolean a(f fVar, MenuItem menuItem) {
            return this.f != null ? this.f.a((b) this, menuItem) : false;
        }

        public final void a(f fVar) {
            if (this.f != null) {
                d();
                af.this.s.a();
            }
        }
    }

    static {
        boolean z = true;
        h = !af.class.desiredAssertionStatus();
        i = new AccelerateInterpolator();
        j = new DecelerateInterpolator();
        if (VERSION.SDK_INT < 14) {
            z = false;
        }
        k = z;
    }

    public af(Activity activity, boolean z) {
        this.v = new ArrayList();
        this.w = -1;
        this.z = new ArrayList();
        this.B = 0;
        this.C = true;
        this.G = true;
        this.e = new ag(this);
        this.f = new ah(this);
        this.g = new ai(this);
        this.n = activity;
        View decorView = activity.getWindow().getDecorView();
        a(decorView);
        if (!z) {
            this.t = decorView.findViewById(16908290);
        }
    }

    public af(Dialog dialog) {
        this.v = new ArrayList();
        this.w = -1;
        this.z = new ArrayList();
        this.B = 0;
        this.C = true;
        this.G = true;
        this.e = new ag(this);
        this.f = new ah(this);
        this.g = new ai(this);
        this.o = dialog;
        a(dialog.getWindow().getDecorView());
    }

    private void a(View view) {
        ae aeVar;
        this.p = (ActionBarOverlayLayout) view.findViewById(android.support.v7.appcompat.R.id.decor_content_parent);
        if (this.p != null) {
            this.p.setActionBarVisibilityCallback(this);
        }
        View findViewById = view.findViewById(android.support.v7.appcompat.R.id.action_bar);
        if (findViewById instanceof ae) {
            aeVar = (ae) findViewById;
        } else if (findViewById instanceof Toolbar) {
            aeVar = ((Toolbar) findViewById).getWrapper();
        } else {
            throw new IllegalStateException(new StringBuilder("Can't make a decor toolbar out of ").append(findViewById).toString() != null ? findViewById.getClass().getSimpleName() : "null");
        }
        this.r = aeVar;
        this.s = (ActionBarContextView) view.findViewById(android.support.v7.appcompat.R.id.action_context_bar);
        this.q = (ActionBarContainer) view.findViewById(android.support.v7.appcompat.R.id.action_bar_container);
        if (this.r == null || this.s == null || this.q == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.l = this.r.b();
        if ((this.r.m() & 4) != 0) {
            this.x = true;
        }
        android.support.v7.view.a a = android.support.v7.view.a.a(this.l);
        int i = a.a.getApplicationInfo().targetSdkVersion;
        f(a.a());
        TypedArray obtainStyledAttributes = this.l.obtainStyledAttributes(null, android.support.v7.appcompat.R.styleable.ActionBar, android.support.v7.appcompat.R.attr.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(android.support.v7.appcompat.R.styleable.ActionBar_hideOnContentScroll, false)) {
            if (this.p.a) {
                this.d = true;
                this.p.setHideOnContentScrollEnabled(true);
            } else {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(android.support.v7.appcompat.R.styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            ViewCompat.setElevation(this.q, (float) dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    public final void a(Configuration configuration) {
        f(android.support.v7.view.a.a(this.l).a());
    }

    private void f(boolean z) {
        boolean z2;
        boolean z3 = true;
        this.A = z;
        if (this.A) {
            this.q.setTabContainer(null);
            this.r.a(this.u);
        } else {
            this.r.a(null);
            this.q.setTabContainer(this.u);
        }
        if (this.r.n() == 2) {
            boolean z4 = true;
        } else {
            int i = 0;
        }
        if (this.u != null) {
            if (z4) {
                this.u.setVisibility(0);
                if (this.p != null) {
                    ViewCompat.requestApplyInsets(this.p);
                }
            } else {
                this.u.setVisibility(XZBDevice.Wait);
            }
        }
        ae aeVar = this.r;
        if (this.A || !z4) {
            z2 = false;
        } else {
            z2 = true;
        }
        aeVar.a(z2);
        ActionBarOverlayLayout actionBarOverlayLayout = this.p;
        if (this.A || !z4) {
            z3 = false;
        }
        actionBarOverlayLayout.setHasNonEmbeddedTabs(z3);
    }

    public final void a(int i) {
        this.B = i;
    }

    public final void b(boolean z) {
        this.I = z;
        if (!z && this.H != null) {
            this.H.b();
        }
    }

    public final void c(boolean z) {
        if (z != this.y) {
            this.y = z;
            int size = this.z.size();
            for (int i = 0; i < size; i++) {
                this.z.get(i);
            }
        }
    }

    public final void a(CharSequence charSequence) {
        this.r.a(charSequence);
    }

    public final boolean f() {
        ViewGroup a = this.r.a();
        if (a == null || a.hasFocus()) {
            return false;
        }
        a.requestFocus();
        return true;
    }

    public final int a() {
        return this.r.m();
    }

    public final b a(android.support.v7.view.b.a aVar) {
        if (this.a != null) {
            this.a.c();
        }
        this.p.setHideOnContentScrollEnabled(false);
        this.s.b();
        b aVar2 = new a(this.s.getContext(), aVar);
        if (!aVar2.e()) {
            return null;
        }
        aVar2.d();
        this.s.a(aVar2);
        e(true);
        this.s.sendAccessibilityEvent(R.styleable.AppCompatTheme_actionModeCutDrawable);
        this.a = aVar2;
        return aVar2;
    }

    public final void d(boolean z) {
        this.C = z;
    }

    public final void h() {
        if (this.E) {
            this.E = false;
            g(true);
        }
    }

    public final void i() {
        if (!this.E) {
            this.E = true;
            g(true);
        }
    }

    private static boolean a(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return (z || z2) ? false : true;
    }

    private void g(boolean z) {
        float f;
        h hVar;
        ViewPropertyAnimatorCompat translationY;
        if (a(this.D, this.E, this.F)) {
            if (!this.G) {
                this.G = true;
                if (this.H != null) {
                    this.H.b();
                }
                this.q.setVisibility(0);
                if (this.B == 0 && k && (this.I || z)) {
                    ViewCompat.setTranslationY(this.q, AutoScrollHelper.RELATIVE_UNSPECIFIED);
                    f = (float) (-this.q.getHeight());
                    if (z) {
                        int[] iArr = new int[]{0, 0};
                        this.q.getLocationInWindow(iArr);
                        f -= (float) iArr[1];
                    }
                    ViewCompat.setTranslationY(this.q, f);
                    hVar = new h();
                    translationY = ViewCompat.animate(this.q).translationY(AutoScrollHelper.RELATIVE_UNSPECIFIED);
                    translationY.setUpdateListener(this.g);
                    hVar.a(translationY);
                    if (this.C && this.t != null) {
                        ViewCompat.setTranslationY(this.t, f);
                        hVar.a(ViewCompat.animate(this.t).translationY(AutoScrollHelper.RELATIVE_UNSPECIFIED));
                    }
                    hVar.a(j);
                    hVar.c();
                    hVar.a(this.f);
                    this.H = hVar;
                    hVar.a();
                } else {
                    ViewCompat.setAlpha(this.q, 1.0f);
                    ViewCompat.setTranslationY(this.q, AutoScrollHelper.RELATIVE_UNSPECIFIED);
                    if (this.C && this.t != null) {
                        ViewCompat.setTranslationY(this.t, AutoScrollHelper.RELATIVE_UNSPECIFIED);
                    }
                    this.f.onAnimationEnd(null);
                }
                if (this.p != null) {
                    ViewCompat.requestApplyInsets(this.p);
                }
            }
        } else if (this.G) {
            this.G = false;
            if (this.H != null) {
                this.H.b();
            }
            if (this.B == 0 && k && (this.I || z)) {
                ViewCompat.setAlpha(this.q, 1.0f);
                this.q.setTransitioning(true);
                hVar = new h();
                f = (float) (-this.q.getHeight());
                if (z) {
                    int[] iArr2 = new int[]{0, 0};
                    this.q.getLocationInWindow(iArr2);
                    f -= (float) iArr2[1];
                }
                translationY = ViewCompat.animate(this.q).translationY(f);
                translationY.setUpdateListener(this.g);
                hVar.a(translationY);
                if (this.C && this.t != null) {
                    hVar.a(ViewCompat.animate(this.t).translationY(f));
                }
                hVar.a(i);
                hVar.c();
                hVar.a(this.e);
                this.H = hVar;
                hVar.a();
                return;
            }
            this.e.onAnimationEnd(null);
        }
    }

    public final void e(boolean z) {
        ViewPropertyAnimatorCompat a;
        ViewPropertyAnimatorCompat a2;
        if (z) {
            if (!this.F) {
                this.F = true;
                if (this.p != null) {
                    this.p.setShowingForActionMode(true);
                }
                g(false);
            }
        } else if (this.F) {
            this.F = false;
            if (this.p != null) {
                this.p.setShowingForActionMode(false);
            }
            g(false);
        }
        if (z) {
            a = this.r.a((int) XZBDevice.DOWNLOAD_LIST_ALL, 100);
            a2 = this.s.a(0, Constants.ST_UPLOAD_MAX_COUNT);
        } else {
            a2 = this.r.a(0, (long) Constants.ST_UPLOAD_MAX_COUNT);
            a = this.s.a(XZBDevice.Wait, 100);
        }
        h hVar = new h();
        hVar.a.add(a);
        a2.setStartDelay(a.getDuration());
        hVar.a.add(a2);
        hVar.a();
    }

    public final Context c() {
        if (this.m == null) {
            TypedValue typedValue = new TypedValue();
            this.l.getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.m = new ContextThemeWrapper(this.l, i);
            } else {
                this.m = this.l;
            }
        }
        return this.m;
    }

    public final void j() {
        if (this.H != null) {
            this.H.b();
            this.H = null;
        }
    }

    public final boolean e() {
        if (this.r == null || !this.r.c()) {
            return false;
        }
        this.r.d();
        return true;
    }

    public final void a(boolean z) {
        if (!this.x) {
            int i = z ? XZBDevice.DOWNLOAD_LIST_ALL : 0;
            int m = this.r.m();
            this.x = true;
            this.r.c((i & 4) | (m & -5));
        }
    }

    public final boolean b() {
        int height = this.q.getHeight();
        return this.G && (height == 0 || this.p.getActionBarHideOffset() < height);
    }
}
