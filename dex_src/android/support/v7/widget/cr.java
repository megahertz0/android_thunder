package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.f;
import android.support.v7.view.menu.m;
import android.support.v7.view.menu.m.a;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window.Callback;

// compiled from: ToolbarWidgetWrapper.java
public final class cr implements ae {
    Toolbar a;
    CharSequence b;
    Callback c;
    boolean d;
    private int e;
    private View f;
    private View g;
    private Drawable h;
    private Drawable i;
    private Drawable j;
    private boolean k;
    private CharSequence l;
    private CharSequence m;
    private ActionMenuPresenter n;
    private int o;
    private final r p;
    private int q;
    private Drawable r;

    public cr(Toolbar toolbar) {
        this(toolbar, R.string.abc_action_bar_up_description, R.drawable.abc_ic_ab_back_mtrl_am_alpha);
    }

    private cr(Toolbar toolbar, int i, int i2) {
        Context context;
        this.o = 0;
        this.q = 0;
        this.a = toolbar;
        this.b = toolbar.getTitle();
        this.l = toolbar.getSubtitle();
        this.k = this.b != null;
        this.j = toolbar.getNavigationIcon();
        cm a = cm.a(toolbar.getContext(), null, R.styleable.ActionBar, R.attr.actionBarStyle);
        CharSequence c = a.c(R.styleable.ActionBar_title);
        if (!TextUtils.isEmpty(c)) {
            this.k = true;
            b(c);
        }
        CharSequence c2 = a.c(R.styleable.ActionBar_subtitle);
        if (!TextUtils.isEmpty(c2)) {
            this.l = c2;
            if ((this.e & 8) != 0) {
                this.a.setSubtitle(c2);
            }
        }
        Drawable a2 = a.a(R.styleable.ActionBar_logo);
        if (a2 != null) {
            b(a2);
        }
        a2 = a.a(R.styleable.ActionBar_icon);
        if (this.j == null && a2 != null) {
            a(a2);
        }
        a2 = a.a(R.styleable.ActionBar_homeAsUpIndicator);
        if (a2 != null) {
            this.j = a2;
            s();
        }
        c(a.a(R.styleable.ActionBar_displayOptions, 0));
        int e = a.e(R.styleable.ActionBar_customNavigationLayout, 0);
        if (e != 0) {
            View inflate = LayoutInflater.from(this.a.getContext()).inflate(e, this.a, false);
            if (!(this.g == null || (this.e & 16) == 0)) {
                this.a.removeView(this.g);
            }
            this.g = inflate;
            if (!(inflate == null || (this.e & 16) == 0)) {
                this.a.addView(this.g);
            }
            c(this.e | 16);
        }
        e = a.d(R.styleable.ActionBar_height, 0);
        if (e > 0) {
            LayoutParams layoutParams = this.a.getLayoutParams();
            layoutParams.height = e;
            this.a.setLayoutParams(layoutParams);
        }
        e = a.b(R.styleable.ActionBar_contentInsetStart, -1);
        int b = a.b(R.styleable.ActionBar_contentInsetEnd, -1);
        if (e >= 0 || b >= 0) {
            this.a.i.a(Math.max(e, 0), Math.max(b, 0));
        }
        e = a.e(R.styleable.ActionBar_titleTextStyle, 0);
        if (e != 0) {
            Toolbar toolbar2;
            toolbar2 = this.a;
            context = this.a.getContext();
            toolbar2.g = e;
            if (toolbar2.b != null) {
                toolbar2.b.setTextAppearance(context, e);
            }
        }
        e = a.e(R.styleable.ActionBar_subtitleTextStyle, 0);
        if (e != 0) {
            toolbar2 = this.a;
            context = this.a.getContext();
            toolbar2.h = e;
            if (toolbar2.c != null) {
                toolbar2.c.setTextAppearance(context, e);
            }
        }
        e = a.e(R.styleable.ActionBar_popupTheme, 0);
        if (e != 0) {
            this.a.setPopupTheme(e);
        }
        a.a.recycle();
        this.p = r.a();
        if (i != this.q) {
            this.q = i;
            if (TextUtils.isEmpty(this.a.getNavigationContentDescription())) {
                CharSequence charSequence;
                int i3 = this.q;
                if (i3 == 0) {
                    charSequence = null;
                } else {
                    charSequence = this.a.getContext().getString(i3);
                }
                this.m = charSequence;
                r();
            }
        }
        this.m = this.a.getNavigationContentDescription();
        Drawable a3 = this.p.a(this.a.getContext(), i2, false);
        if (this.r != a3) {
            this.r = a3;
            s();
        }
        this.a.setNavigationOnClickListener(new cs(this));
    }

    public final ViewGroup a() {
        return this.a;
    }

    public final Context b() {
        return this.a.getContext();
    }

    public final boolean c() {
        Toolbar toolbar = this.a;
        return (toolbar.l == null || toolbar.l.b == null) ? false : true;
    }

    public final void d() {
        this.a.c();
    }

    public final void a(Callback callback) {
        this.c = callback;
    }

    public final void a(CharSequence charSequence) {
        if (!this.k) {
            b(charSequence);
        }
    }

    public final CharSequence e() {
        return this.a.getTitle();
    }

    private void b(CharSequence charSequence) {
        this.b = charSequence;
        if ((this.e & 8) != 0) {
            this.a.setTitle(charSequence);
        }
    }

    public final void a(int i) {
        a(i != 0 ? this.p.a(this.a.getContext(), i, false) : null);
    }

    public final void a(Drawable drawable) {
        this.h = drawable;
        q();
    }

    public final void b(int i) {
        b(i != 0 ? this.p.a(this.a.getContext(), i, false) : null);
    }

    private void b(Drawable drawable) {
        this.i = drawable;
        q();
    }

    private void q() {
        Drawable drawable = null;
        if ((this.e & 2) != 0) {
            drawable = (this.e & 1) != 0 ? this.i != null ? this.i : this.h : this.h;
        }
        this.a.setLogo(drawable);
    }

    public final boolean f() {
        Toolbar toolbar = this.a;
        return toolbar.getVisibility() == 0 && toolbar.a != null && toolbar.a.b;
    }

    public final boolean g() {
        return this.a.a();
    }

    public final boolean h() {
        Toolbar toolbar = this.a;
        if (toolbar.a != null) {
            boolean z;
            Object obj;
            ActionMenuView actionMenuView = toolbar.a;
            if (actionMenuView.c != null) {
                ActionMenuPresenter actionMenuPresenter = actionMenuView.c;
                if (actionMenuPresenter.o != null || actionMenuPresenter.i()) {
                    z = true;
                } else {
                    obj = null;
                }
                if (z) {
                    z = true;
                    if (z) {
                        return true;
                    }
                }
            }
            obj = null;
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final boolean i() {
        return this.a.b();
    }

    public final boolean j() {
        Toolbar toolbar = this.a;
        if (toolbar.a != null) {
            boolean z;
            ActionMenuView actionMenuView = toolbar.a;
            if (actionMenuView.c == null || !actionMenuView.c.f()) {
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

    public final void k() {
        this.d = true;
    }

    public final void a(Menu menu, a aVar) {
        if (this.n == null) {
            this.n = new ActionMenuPresenter(this.a.getContext());
            this.n.h = R.id.action_menu_presenter;
        }
        this.n.f = aVar;
        Toolbar toolbar = this.a;
        f fVar = (f) menu;
        m mVar = this.n;
        if (fVar != null || toolbar.a != null) {
            toolbar.d();
            f fVar2 = toolbar.a.a;
            if (fVar2 != fVar) {
                if (fVar2 != null) {
                    fVar2.b(toolbar.k);
                    fVar2.b(toolbar.l);
                }
                if (toolbar.l == null) {
                    toolbar.l = new a((byte) 0);
                }
                mVar.n = true;
                if (fVar != null) {
                    fVar.a(mVar, toolbar.e);
                    fVar.a(toolbar.l, toolbar.e);
                } else {
                    mVar.a(toolbar.e, null);
                    toolbar.l.a(toolbar.e, null);
                    mVar.a(true);
                    toolbar.l.a(true);
                }
                toolbar.a.setPopupTheme(toolbar.f);
                toolbar.a.setPresenter(mVar);
                toolbar.k = mVar;
            }
        }
    }

    public final void l() {
        Toolbar toolbar = this.a;
        if (toolbar.a != null) {
            toolbar.a.b();
        }
    }

    public final int m() {
        return this.e;
    }

    public final void c(int i) {
        int i2 = this.e ^ i;
        this.e = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    s();
                    r();
                } else {
                    this.a.setNavigationIcon(null);
                }
            }
            if ((i2 & 3) != 0) {
                q();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.a.setTitle(this.b);
                    this.a.setSubtitle(this.l);
                } else {
                    this.a.setTitle(null);
                    this.a.setSubtitle(null);
                }
            }
            if ((i2 & 16) != 0 && this.g != null) {
                if ((i & 16) != 0) {
                    this.a.addView(this.g);
                } else {
                    this.a.removeView(this.g);
                }
            }
        }
    }

    public final void a(bm bmVar) {
        if (this.f != null && this.f.getParent() == this.a) {
            this.a.removeView(this.f);
        }
        this.f = bmVar;
        if (bmVar != null && this.o == 2) {
            this.a.addView(this.f, 0);
            Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) this.f.getLayoutParams();
            layoutParams.width = -2;
            layoutParams.height = -2;
            layoutParams.a = 8388691;
            bmVar.setAllowCollapse(true);
        }
    }

    public final void a(boolean z) {
        this.a.setCollapsible(z);
    }

    public final int n() {
        return this.o;
    }

    public final ViewPropertyAnimatorCompat a(int i, long j) {
        return ViewCompat.animate(this.a).alpha(i == 0 ? 1.0f : AutoScrollHelper.RELATIVE_UNSPECIFIED).setDuration(j).setListener(new ct(this, i));
    }

    private void r() {
        if ((this.e & 4) == 0) {
            return;
        }
        if (TextUtils.isEmpty(this.m)) {
            this.a.setNavigationContentDescription(this.q);
        } else {
            this.a.setNavigationContentDescription(this.m);
        }
    }

    private void s() {
        if ((this.e & 4) != 0) {
            this.a.setNavigationIcon(this.j != null ? this.j : this.r);
        }
    }

    public final int o() {
        return this.a.getVisibility();
    }

    public final void a(a aVar, f.a aVar2) {
        this.a.a(aVar, aVar2);
    }

    public final Menu p() {
        return this.a.getMenu();
    }
}
