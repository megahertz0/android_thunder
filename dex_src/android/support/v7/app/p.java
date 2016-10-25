package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.PermissionChecker;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.b;
import android.support.v7.view.d;
import android.support.v7.view.e;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ViewStubCompat;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.Window.Callback;
import android.widget.PopupWindow;
import anet.channel.util.ErrorConstant;
import com.uc.addon.sdk.remote.Tabs;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Calendar;
import org.android.spdy.SpdyAgent;

// compiled from: AppCompatDelegateImplV14.java
class p extends o {
    private static ae w;
    boolean p;
    private int x;
    private boolean y;

    // compiled from: AppCompatDelegateImplV14.java
    class a extends a {
        a(Callback callback) {
            super(callback);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return p.this.p ? a(callback) : super.onWindowStartingActionMode(callback);
        }

        final ActionMode a(ActionMode.Callback callback) {
            Object aVar = new android.support.v7.view.f.a(p.this, callback);
            AppCompatDelegateImplV7 appCompatDelegateImplV7 = p.this;
            if (appCompatDelegateImplV7.q != null) {
                appCompatDelegateImplV7.q.c();
            }
            android.support.v7.view.b.a bVar = new b(aVar);
            ActionBar a = appCompatDelegateImplV7.a();
            if (a != null) {
                appCompatDelegateImplV7.q = a.a(bVar);
            }
            if (appCompatDelegateImplV7.q == null) {
                appCompatDelegateImplV7.m();
                if (appCompatDelegateImplV7.q != null) {
                    appCompatDelegateImplV7.q.c();
                }
                android.support.v7.view.b.a bVar2 = new b(bVar);
                if (appCompatDelegateImplV7.r == null) {
                    if (appCompatDelegateImplV7.l) {
                        Context dVar;
                        TypedValue typedValue = new TypedValue();
                        Theme theme = p.this.getTheme();
                        theme.resolveAttribute(R.attr.actionBarTheme, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            Theme newTheme = p.this.getResources().newTheme();
                            newTheme.setTo(theme);
                            newTheme.applyStyle(typedValue.resourceId, true);
                            dVar = new d(p.this, 0);
                            dVar.getTheme().setTo(newTheme);
                        } else {
                            dVar = p.this;
                        }
                        appCompatDelegateImplV7.r = new ActionBarContextView(dVar);
                        appCompatDelegateImplV7.s = new PopupWindow(dVar, null, R.attr.actionModePopupWindowStyle);
                        PopupWindowCompat.setWindowLayoutType(appCompatDelegateImplV7.s, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                        appCompatDelegateImplV7.s.setContentView(appCompatDelegateImplV7.r);
                        appCompatDelegateImplV7.s.setWidth(-1);
                        dVar.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
                        appCompatDelegateImplV7.r.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, dVar.getResources().getDisplayMetrics()));
                        appCompatDelegateImplV7.s.setHeight(Tabs.TAB_CREATE_REACH_MAX_COUNT);
                        appCompatDelegateImplV7.t = new v(appCompatDelegateImplV7);
                    } else {
                        ViewStubCompat viewStubCompat = (ViewStubCompat) appCompatDelegateImplV7.v.findViewById(R.id.action_mode_bar_stub);
                        if (viewStubCompat != null) {
                            viewStubCompat.setLayoutInflater(LayoutInflater.from(appCompatDelegateImplV7.l()));
                            appCompatDelegateImplV7.r = (ActionBarContextView) viewStubCompat.a();
                        }
                    }
                }
                if (appCompatDelegateImplV7.r != null) {
                    boolean z;
                    appCompatDelegateImplV7.m();
                    appCompatDelegateImplV7.r.b();
                    Context context = appCompatDelegateImplV7.r.getContext();
                    ActionBarContextView actionBarContextView = appCompatDelegateImplV7.r;
                    if (appCompatDelegateImplV7.s == null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    b eVar = new e(context, actionBarContextView, bVar2, z);
                    if (bVar.a(eVar, eVar.b())) {
                        eVar.d();
                        appCompatDelegateImplV7.r.a(eVar);
                        appCompatDelegateImplV7.q = eVar;
                        ViewCompat.setAlpha(appCompatDelegateImplV7.r, AutoScrollHelper.RELATIVE_UNSPECIFIED);
                        appCompatDelegateImplV7.u = ViewCompat.animate(appCompatDelegateImplV7.r).alpha(1.0f);
                        appCompatDelegateImplV7.u.setListener(new x(appCompatDelegateImplV7));
                        if (appCompatDelegateImplV7.s != null) {
                            appCompatDelegateImplV7.c.getDecorView().post(appCompatDelegateImplV7.t);
                        }
                    } else {
                        appCompatDelegateImplV7.q = null;
                    }
                }
                appCompatDelegateImplV7.q = appCompatDelegateImplV7.q;
            }
            b bVar3 = appCompatDelegateImplV7.q;
            return bVar3 != null ? aVar.b(bVar3) : null;
        }
    }

    p(Context context, Window window, l lVar) {
        super(context, window, lVar);
        this.x = -100;
        this.p = true;
    }

    public final void a(Bundle bundle) {
        super.a(bundle);
        if (bundle != null && this.x == -100) {
            this.x = bundle.getInt("appcompat:local_night_mode", ErrorConstant.ERROR_UNKNOWN);
        }
    }

    Callback a(Callback callback) {
        return new a(callback);
    }

    public final boolean i() {
        int i;
        this.y = true;
        if (this.x == -100) {
            i = m.a;
        } else {
            i = this.x;
        }
        i = f(i);
        if (i == -1) {
            return false;
        }
        Resources resources = this.b.getResources();
        Configuration configuration = resources.getConfiguration();
        int i2 = configuration.uiMode & 48;
        i = i == 2 ? com.xunlei.tdlive.R.styleable.AppCompatTheme_actionModeCutDrawable : com.xunlei.tdlive.R.styleable.Toolbar_titleMarginBottom;
        if (i2 == i) {
            return false;
        }
        Configuration configuration2 = new Configuration(configuration);
        configuration2.uiMode = i | (configuration2.uiMode & -49);
        resources.updateConfiguration(configuration2, null);
        return true;
    }

    int f(int i) {
        Location location = null;
        switch (i) {
            case ErrorConstant.ERROR_UNKNOWN:
                return -1;
            case SpdyAgent.ACCS_TEST_SERVER:
                boolean z;
                if (w == null) {
                    w = new ae(this.b.getApplicationContext());
                }
                ae aeVar = w;
                a aVar = ae.a;
                if (ae.a(aVar)) {
                    z = aVar.a;
                } else {
                    Location a;
                    if (PermissionChecker.checkSelfPermission(aeVar.b, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                        a = aeVar.a("network");
                    } else {
                        a = null;
                    }
                    if (PermissionChecker.checkSelfPermission(aeVar.b, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                        location = aeVar.a("gps");
                    }
                    if (location == null || a == null) {
                        if (location != null) {
                            a = location;
                        }
                    } else if (location.getTime() > a.getTime()) {
                        a = location;
                    }
                    if (a != null) {
                        ae.a(a);
                        z = aVar.a;
                    } else {
                        int i2 = Calendar.getInstance().get(XZBDevice.Success);
                        z = i2 < 6 || i2 >= 22;
                    }
                }
                return z ? XZBDevice.DOWNLOAD_LIST_RECYCLE : 1;
            default:
                return i;
        }
    }

    public final void b(Bundle bundle) {
        super.b(bundle);
        if (this.x != -100) {
            bundle.putInt("appcompat:local_night_mode", this.x);
        }
    }
}
