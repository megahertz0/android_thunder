package android.support.v7.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.view.menu.n.a;
import android.support.v7.widget.r;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewDebug.CapturedViewProperty;
import android.widget.LinearLayout;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: MenuItemImpl.java
public final class h implements SupportMenuItem {
    static String e;
    static String f;
    static String g;
    static String h;
    final int a;
    f b;
    int c;
    ContextMenuInfo d;
    private final int i;
    private final int j;
    private final int k;
    private CharSequence l;
    private CharSequence m;
    private Intent n;
    private char o;
    private char p;
    private Drawable q;
    private int r;
    private q s;
    private Runnable t;
    private OnMenuItemClickListener u;
    private int v;
    private View w;
    private ActionProvider x;
    private OnActionExpandListener y;
    private boolean z;

    public final /* synthetic */ MenuItem setActionView(View view) {
        return a(view);
    }

    h(f fVar, int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        this.r = 0;
        this.v = 16;
        this.c = 0;
        this.z = false;
        this.b = fVar;
        this.i = i2;
        this.j = i;
        this.k = i3;
        this.a = i4;
        this.l = charSequence;
        this.c = i5;
    }

    public final boolean a() {
        if ((this.u != null && this.u.onMenuItemClick(this)) || this.b.a(this.b.k(), (MenuItem) this)) {
            return true;
        }
        if (this.t != null) {
            this.t.run();
            return true;
        }
        if (this.n != null) {
            try {
                this.b.a.startActivity(this.n);
                return true;
            } catch (ActivityNotFoundException e) {
            }
        }
        return this.x != null && this.x.onPerformDefaultAction();
    }

    public final boolean isEnabled() {
        return (this.v & 16) != 0;
    }

    public final MenuItem setEnabled(boolean z) {
        if (z) {
            this.v |= 16;
        } else {
            this.v &= -17;
        }
        this.b.a(false);
        return this;
    }

    public final int getGroupId() {
        return this.j;
    }

    @CapturedViewProperty
    public final int getItemId() {
        return this.i;
    }

    public final int getOrder() {
        return this.k;
    }

    public final Intent getIntent() {
        return this.n;
    }

    public final MenuItem setIntent(Intent intent) {
        this.n = intent;
        return this;
    }

    public final char getAlphabeticShortcut() {
        return this.p;
    }

    public final MenuItem setAlphabeticShortcut(char c) {
        if (this.p != c) {
            this.p = Character.toLowerCase(c);
            this.b.a(false);
        }
        return this;
    }

    public final char getNumericShortcut() {
        return this.o;
    }

    public final MenuItem setNumericShortcut(char c) {
        if (this.o != c) {
            this.o = c;
            this.b.a(false);
        }
        return this;
    }

    public final MenuItem setShortcut(char c, char c2) {
        this.o = c;
        this.p = Character.toLowerCase(c2);
        this.b.a(false);
        return this;
    }

    final char b() {
        return this.b.b() ? this.p : this.o;
    }

    final boolean c() {
        return this.b.c() && b() != '\u0000';
    }

    public final SubMenu getSubMenu() {
        return this.s;
    }

    public final boolean hasSubMenu() {
        return this.s != null;
    }

    public final void a(q qVar) {
        this.s = qVar;
        qVar.setHeaderTitle(getTitle());
    }

    @CapturedViewProperty
    public final CharSequence getTitle() {
        return this.l;
    }

    final CharSequence a(a aVar) {
        return (aVar == null || !aVar.a()) ? getTitle() : getTitleCondensed();
    }

    public final MenuItem setTitle(CharSequence charSequence) {
        this.l = charSequence;
        this.b.a(false);
        if (this.s != null) {
            this.s.setHeaderTitle(charSequence);
        }
        return this;
    }

    public final MenuItem setTitle(int i) {
        return setTitle(this.b.a.getString(i));
    }

    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.m != null ? this.m : this.l;
        return (VERSION.SDK_INT >= 18 || charSequence == null || (charSequence instanceof String)) ? charSequence : charSequence.toString();
    }

    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.m = charSequence;
        this.b.a(false);
        return this;
    }

    public final Drawable getIcon() {
        if (this.q != null) {
            return this.q;
        }
        if (this.r == 0) {
            return null;
        }
        Drawable a = r.a().a(this.b.a, this.r, false);
        this.r = 0;
        this.q = a;
        return a;
    }

    public final MenuItem setIcon(Drawable drawable) {
        this.r = 0;
        this.q = drawable;
        this.b.a(false);
        return this;
    }

    public final MenuItem setIcon(int i) {
        this.q = null;
        this.r = i;
        this.b.a(false);
        return this;
    }

    public final boolean isCheckable() {
        return (this.v & 1) == 1;
    }

    public final MenuItem setCheckable(boolean z) {
        int i = this.v;
        this.v = (z ? 1 : 0) | (this.v & -2);
        if (i != this.v) {
            this.b.a(false);
        }
        return this;
    }

    public final void a(boolean z) {
        this.v = (z ? XZBDevice.DOWNLOAD_LIST_ALL : 0) | (this.v & -5);
    }

    public final boolean d() {
        return (this.v & 4) != 0;
    }

    public final boolean isChecked() {
        return (this.v & 2) == 2;
    }

    public final MenuItem setChecked(boolean z) {
        if ((this.v & 4) != 0) {
            f fVar = this.b;
            int groupId = getGroupId();
            int size = fVar.c.size();
            for (int i = 0; i < size; i++) {
                h hVar = (h) fVar.c.get(i);
                if (hVar.getGroupId() == groupId && hVar.d() && hVar.isCheckable()) {
                    hVar.e(hVar == this);
                }
            }
        } else {
            e(z);
        }
        return this;
    }

    private void e(boolean z) {
        int i = this.v;
        this.v = (z ? XZBDevice.DOWNLOAD_LIST_RECYCLE : 0) | (this.v & -3);
        if (i != this.v) {
            this.b.a(false);
        }
    }

    public final boolean isVisible() {
        return (this.x == null || !this.x.overridesItemVisibility()) ? (this.v & 8) == 0 : (this.v & 8) == 0 && this.x.isVisible();
    }

    final boolean b(boolean z) {
        int i = this.v;
        this.v = (z ? 0 : XZBDevice.Wait) | (this.v & -9);
        return i != this.v;
    }

    public final MenuItem setVisible(boolean z) {
        if (b(z)) {
            this.b.f();
        }
        return this;
    }

    public final MenuItem setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.u = onMenuItemClickListener;
        return this;
    }

    public final String toString() {
        return this.l != null ? this.l.toString() : null;
    }

    public final ContextMenuInfo getMenuInfo() {
        return this.d;
    }

    public final boolean e() {
        return (this.v & 32) == 32;
    }

    public final boolean f() {
        return (this.c & 1) == 1;
    }

    public final boolean g() {
        return (this.c & 2) == 2;
    }

    public final void c(boolean z) {
        if (z) {
            this.v |= 32;
        } else {
            this.v &= -33;
        }
    }

    public final void setShowAsAction(int i) {
        switch (i & 3) {
            case SpdyAgent.ACCS_TEST_SERVER:
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.c = i;
                this.b.g();
            default:
                throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
    }

    private SupportMenuItem a(View view) {
        this.w = view;
        this.x = null;
        if (view != null && view.getId() == -1 && this.i > 0) {
            view.setId(this.i);
        }
        this.b.g();
        return this;
    }

    public final View getActionView() {
        if (this.w != null) {
            return this.w;
        }
        if (this.x == null) {
            return null;
        }
        this.w = this.x.onCreateActionView(this);
        return this.w;
    }

    public final MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    public final android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    public final ActionProvider getSupportActionProvider() {
        return this.x;
    }

    public final SupportMenuItem setSupportActionProvider(ActionProvider actionProvider) {
        if (this.x != null) {
            this.x.reset();
        }
        this.w = null;
        this.x = actionProvider;
        this.b.a(true);
        if (this.x != null) {
            this.x.setVisibilityListener(new i(this));
        }
        return this;
    }

    public final boolean expandActionView() {
        if (h()) {
            return (this.y == null || this.y.onMenuItemActionExpand(this)) ? this.b.a(this) : false;
        } else {
            return false;
        }
    }

    public final boolean collapseActionView() {
        if ((this.c & 8) == 0) {
            return false;
        }
        if (this.w == null) {
            return true;
        }
        return (this.y == null || this.y.onMenuItemActionCollapse(this)) ? this.b.b(this) : false;
    }

    public final SupportMenuItem setSupportOnActionExpandListener(OnActionExpandListener onActionExpandListener) {
        this.y = onActionExpandListener;
        return this;
    }

    public final boolean h() {
        if ((this.c & 8) == 0) {
            return false;
        }
        if (this.w == null && this.x != null) {
            this.w = this.x.onCreateActionView(this);
        }
        return this.w != null;
    }

    public final void d(boolean z) {
        this.z = z;
        this.b.a(false);
    }

    public final boolean isActionViewExpanded() {
        return this.z;
    }

    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setOnActionExpandListener()");
    }

    public final /* synthetic */ MenuItem setActionView(int i) {
        Context context = this.b.a;
        a(LayoutInflater.from(context).inflate(i, new LinearLayout(context), false));
        return this;
    }

    public final /* synthetic */ MenuItem setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }
}
