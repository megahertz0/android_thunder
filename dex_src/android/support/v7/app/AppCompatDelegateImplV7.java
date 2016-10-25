package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.app.NavUtils;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v7.view.menu.e;
import android.support.v7.view.menu.f;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.ad;
import android.support.v7.widget.ap;
import android.support.v7.widget.cj;
import android.support.v7.widget.cu;
import android.support.v7.widget.cw;
import android.support.v7.widget.r;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.view.Window.Callback;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

class AppCompatDelegateImplV7 extends n implements LayoutInflaterFactory, android.support.v7.view.menu.f.a {
    private View A;
    private boolean B;
    private boolean C;
    private boolean D;
    private PanelFeatureState[] E;
    private PanelFeatureState F;
    private boolean G;
    private boolean H;
    private int I;
    private final Runnable J;
    private boolean K;
    private Rect L;
    private Rect M;
    private ab N;
    private ad p;
    android.support.v7.view.b q;
    ActionBarContextView r;
    PopupWindow s;
    Runnable t;
    ViewPropertyAnimatorCompat u;
    ViewGroup v;
    private a w;
    private d x;
    private boolean y;
    private TextView z;

    private static final class PanelFeatureState {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        ViewGroup g;
        View h;
        View i;
        f j;
        e k;
        Context l;
        boolean m;
        boolean n;
        boolean o;
        public boolean p;
        boolean q;
        boolean r;
        Bundle s;

        private static class SavedState implements Parcelable {
            public static final Creator<SavedState> CREATOR;
            int a;
            boolean b;
            Bundle c;

            private SavedState() {
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.a);
                parcel.writeInt(this.b ? 1 : 0);
                if (this.b) {
                    parcel.writeBundle(this.c);
                }
            }

            static {
                CREATOR = ParcelableCompat.newCreator(new z());
            }

            static /* synthetic */ SavedState a(Parcel parcel, ClassLoader classLoader) {
                boolean z = true;
                SavedState savedState = new SavedState();
                savedState.a = parcel.readInt();
                if (parcel.readInt() != 1) {
                    z = false;
                }
                savedState.b = z;
                if (savedState.b) {
                    savedState.c = parcel.readBundle(classLoader);
                }
                return savedState;
            }
        }

        PanelFeatureState(int i) {
            this.a = i;
            this.q = false;
        }

        final void a(f fVar) {
            if (fVar != this.j) {
                if (this.j != null) {
                    this.j.b(this.k);
                }
                this.j = fVar;
                if (fVar != null && this.k != null) {
                    fVar.a(this.k);
                }
            }
        }
    }

    private final class a implements android.support.v7.view.menu.m.a {
        private a() {
        }

        public final boolean a(f fVar) {
            Callback callback = AppCompatDelegateImplV7.this.c.getCallback();
            if (callback != null) {
                callback.onMenuOpened(R.styleable.AppCompatTheme_ratingBarStyleSmall, fVar);
            }
            return true;
        }

        public final void a(f fVar, boolean z) {
            AppCompatDelegateImplV7.this.b(fVar);
        }
    }

    class b implements android.support.v7.view.b.a {
        private android.support.v7.view.b.a b;

        public b(android.support.v7.view.b.a aVar) {
            this.b = aVar;
        }

        public final boolean a(android.support.v7.view.b bVar, Menu menu) {
            return this.b.a(bVar, menu);
        }

        public final boolean b(android.support.v7.view.b bVar, Menu menu) {
            return this.b.b(bVar, menu);
        }

        public final boolean a(android.support.v7.view.b bVar, MenuItem menuItem) {
            return this.b.a(bVar, menuItem);
        }

        public final void a(android.support.v7.view.b bVar) {
            this.b.a(bVar);
            if (AppCompatDelegateImplV7.this.s != null) {
                AppCompatDelegateImplV7.this.c.getDecorView().removeCallbacks(AppCompatDelegateImplV7.this.t);
            }
            if (AppCompatDelegateImplV7.this.r != null) {
                AppCompatDelegateImplV7.this.m();
                AppCompatDelegateImplV7.this.u = ViewCompat.animate(AppCompatDelegateImplV7.this.r).alpha(AutoScrollHelper.RELATIVE_UNSPECIFIED);
                AppCompatDelegateImplV7.this.u.setListener(new y(this));
            }
            AppCompatDelegateImplV7.this.q = null;
        }
    }

    private class c extends ContentFrameLayout {
        public c(Context context) {
            super(context);
        }

        public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImplV7.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                boolean z;
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (x < -5 || y < -5 || x > getWidth() + 5 || y > getHeight() + 5) {
                    z = true;
                } else {
                    Object obj = null;
                }
                if (z) {
                    AppCompatDelegateImplV7.this.a(AppCompatDelegateImplV7.this.f(0), true);
                    return true;
                }
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        public final void setBackgroundResource(int i) {
            setBackgroundDrawable(r.a().a(getContext(), i, false));
        }
    }

    private final class d implements android.support.v7.view.menu.m.a {
        private d() {
        }

        public final void a(f fVar, boolean z) {
            f k = fVar.k();
            if (k != fVar) {
                boolean z2 = true;
            } else {
                Object obj = null;
            }
            AppCompatDelegateImplV7 appCompatDelegateImplV7 = AppCompatDelegateImplV7.this;
            if (z2) {
                fVar = k;
            }
            PanelFeatureState a = appCompatDelegateImplV7.a(r6);
            if (a == null) {
                return;
            }
            if (z2) {
                AppCompatDelegateImplV7.this.a(AppCompatDelegateImplV7.this, a, (Menu) k);
                AppCompatDelegateImplV7.this.a(a, true);
                return;
            }
            AppCompatDelegateImplV7.this.a(a, z);
        }

        public final boolean a(f fVar) {
            if (fVar == null && AppCompatDelegateImplV7.this.i) {
                Callback callback = AppCompatDelegateImplV7.this.c.getCallback();
                if (!(callback == null || AppCompatDelegateImplV7.this.o)) {
                    callback.onMenuOpened(R.styleable.AppCompatTheme_ratingBarStyleSmall, fVar);
                }
            }
            return true;
        }
    }

    static /* synthetic */ void a(AppCompatDelegateImplV7 appCompatDelegateImplV7, int i) {
        PanelFeatureState f = appCompatDelegateImplV7.f(i);
        if (f.j != null) {
            Bundle bundle = new Bundle();
            f.j.b(bundle);
            if (bundle.size() > 0) {
                f.s = bundle;
            }
            f.j.d();
            f.j.clear();
        }
        f.r = true;
        f.q = true;
        if ((i == 108 || i == 0) && appCompatDelegateImplV7.p != null) {
            f = appCompatDelegateImplV7.f(0);
            if (f != null) {
                f.m = false;
                appCompatDelegateImplV7.b(f, null);
            }
        }
    }

    AppCompatDelegateImplV7(Context context, Window window, l lVar) {
        super(context, window, lVar);
        this.u = null;
        this.J = new r(this);
    }

    public void a(Bundle bundle) {
        if ((this.d instanceof Activity) && NavUtils.getParentActivityName((Activity) this.d) != null) {
            ActionBar actionBar = this.g;
            if (actionBar == null) {
                this.K = true;
            } else {
                actionBar.a(true);
            }
        }
    }

    public final void c() {
        n();
    }

    public final void k() {
        n();
        if (this.i && this.g == null) {
            if (this.d instanceof Activity) {
                this.g = new af((Activity) this.d, this.j);
            } else if (this.d instanceof Dialog) {
                this.g = new af((Dialog) this.d);
            }
            if (this.g != null) {
                this.g.a(this.K);
            }
        }
    }

    public final View a(int i) {
        n();
        return this.c.findViewById(i);
    }

    public final void a(Configuration configuration) {
        if (this.i && this.y) {
            ActionBar a = a();
            if (a != null) {
                a.a(configuration);
            }
        }
        i();
    }

    public final void d() {
        ActionBar a = a();
        if (a != null) {
            a.b(false);
        }
    }

    public final void e() {
        ActionBar a = a();
        if (a != null) {
            a.b(true);
        }
    }

    public final void a(View view) {
        n();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.d.onContentChanged();
    }

    public final void b(int i) {
        n();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.b).inflate(i, viewGroup);
        this.d.onContentChanged();
    }

    public final void a(View view, LayoutParams layoutParams) {
        n();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.d.onContentChanged();
    }

    public final void b(View view, LayoutParams layoutParams) {
        n();
        ((ViewGroup) this.v.findViewById(16908290)).addView(view, layoutParams);
        this.d.onContentChanged();
    }

    public final void g() {
        super.g();
        if (this.g != null) {
            this.g.g();
        }
    }

    private void n() {
        if (!this.y) {
            TypedArray obtainStyledAttributes = this.b.obtainStyledAttributes(android.support.v7.appcompat.R.styleable.AppCompatTheme);
            if (obtainStyledAttributes.hasValue(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowActionBar)) {
                View view;
                View view2;
                if (obtainStyledAttributes.getBoolean(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowNoTitle, false)) {
                    c(1);
                } else if (obtainStyledAttributes.getBoolean(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowActionBar, false)) {
                    c((int) R.styleable.AppCompatTheme_ratingBarStyleSmall);
                }
                if (obtainStyledAttributes.getBoolean(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowActionBarOverlay, false)) {
                    c((int) R.styleable.AppCompatTheme_seekBarStyle);
                }
                if (obtainStyledAttributes.getBoolean(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowActionModeOverlay, false)) {
                    c((int) XZBDevice.Stop);
                }
                this.l = obtainStyledAttributes.getBoolean(android.support.v7.appcompat.R.styleable.AppCompatTheme_android_windowIsFloating, false);
                obtainStyledAttributes.recycle();
                this.c.getDecorView();
                LayoutInflater from = LayoutInflater.from(this.b);
                if (this.m) {
                    if (this.k) {
                        view = (ViewGroup) from.inflate(android.support.v7.appcompat.R.layout.abc_screen_simple_overlay_action_mode, null);
                    } else {
                        view = (ViewGroup) from.inflate(android.support.v7.appcompat.R.layout.abc_screen_simple, null);
                    }
                    if (VERSION.SDK_INT >= 21) {
                        ViewCompat.setOnApplyWindowInsetsListener(view, new s(this));
                        view2 = view;
                    } else {
                        ((ap) view).setOnFitSystemWindowsListener(new t(this));
                        view2 = view;
                    }
                } else if (this.l) {
                    r0 = (ViewGroup) from.inflate(android.support.v7.appcompat.R.layout.abc_dialog_title_material, null);
                    this.j = false;
                    this.i = false;
                    view2 = r0;
                } else if (this.i) {
                    Context dVar;
                    TypedValue typedValue = new TypedValue();
                    this.b.getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        dVar = new android.support.v7.view.d(this.b, typedValue.resourceId);
                    } else {
                        dVar = this.b;
                    }
                    r0 = (ViewGroup) LayoutInflater.from(dVar).inflate(android.support.v7.appcompat.R.layout.abc_screen_toolbar, null);
                    this.p = (ad) r0.findViewById(android.support.v7.appcompat.R.id.decor_content_parent);
                    this.p.setWindowCallback(this.c.getCallback());
                    if (this.j) {
                        this.p.a(R.styleable.AppCompatTheme_seekBarStyle);
                    }
                    if (this.B) {
                        this.p.a(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    }
                    if (this.C) {
                        this.p.a(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                    }
                    view2 = r0;
                } else {
                    view2 = null;
                }
                if (view2 == null) {
                    throw new IllegalArgumentException(new StringBuilder("AppCompat does not support the current theme features: { windowActionBar: ").append(this.i).append(", windowActionBarOverlay: ").append(this.j).append(", android:windowIsFloating: ").append(this.l).append(", windowActionModeOverlay: ").append(this.k).append(", windowNoTitle: ").append(this.m).append(" }").toString());
                }
                CharSequence title;
                if (this.p == null) {
                    this.z = (TextView) view2.findViewById(android.support.v7.appcompat.R.id.title);
                }
                cw.b(view2);
                ContentFrameLayout contentFrameLayout = (ContentFrameLayout) view2.findViewById(android.support.v7.appcompat.R.id.action_bar_activity_content);
                ViewGroup viewGroup = (ViewGroup) this.c.findViewById(16908290);
                if (viewGroup != null) {
                    while (viewGroup.getChildCount() > 0) {
                        View childAt = viewGroup.getChildAt(0);
                        viewGroup.removeViewAt(0);
                        contentFrameLayout.addView(childAt);
                    }
                    viewGroup.setId(-1);
                    contentFrameLayout.setId(16908290);
                    if (viewGroup instanceof FrameLayout) {
                        ((FrameLayout) viewGroup).setForeground(null);
                    }
                }
                this.c.setContentView(view2);
                contentFrameLayout.setAttachListener(new u(this));
                this.v = view2;
                if (this.d instanceof Activity) {
                    title = ((Activity) this.d).getTitle();
                } else {
                    title = this.n;
                }
                if (!TextUtils.isEmpty(title)) {
                    b(title);
                }
                contentFrameLayout = (ContentFrameLayout) this.v.findViewById(16908290);
                view = this.c.getDecorView();
                contentFrameLayout.b.set(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
                if (ViewCompat.isLaidOut(contentFrameLayout)) {
                    contentFrameLayout.requestLayout();
                }
                TypedArray obtainStyledAttributes2 = this.b.obtainStyledAttributes(android.support.v7.appcompat.R.styleable.AppCompatTheme);
                obtainStyledAttributes2.getValue(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
                obtainStyledAttributes2.getValue(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
                if (obtainStyledAttributes2.hasValue(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMajor)) {
                    obtainStyledAttributes2.getValue(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
                }
                if (obtainStyledAttributes2.hasValue(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMinor)) {
                    obtainStyledAttributes2.getValue(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
                }
                if (obtainStyledAttributes2.hasValue(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMajor)) {
                    obtainStyledAttributes2.getValue(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
                }
                if (obtainStyledAttributes2.hasValue(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMinor)) {
                    obtainStyledAttributes2.getValue(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
                }
                obtainStyledAttributes2.recycle();
                contentFrameLayout.requestLayout();
                this.y = true;
                PanelFeatureState f = f(0);
                if (!this.o) {
                    if (f == null || f.j == null) {
                        g(R.styleable.AppCompatTheme_ratingBarStyleSmall);
                        return;
                    }
                    return;
                }
                return;
            }
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
    }

    public final boolean c(int i) {
        if (i == 8) {
            i = 108;
        } else if (i == 9) {
            i = R.styleable.AppCompatTheme_seekBarStyle;
        }
        if (this.m && i == 108) {
            return false;
        }
        if (this.i && i == 1) {
            this.i = false;
        }
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                o();
                this.m = true;
                return true;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                o();
                this.B = true;
                return true;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                o();
                this.C = true;
                return true;
            case XZBDevice.Stop:
                o();
                this.k = true;
                return true;
            case R.styleable.AppCompatTheme_ratingBarStyleSmall:
                o();
                this.i = true;
                return true;
            case R.styleable.AppCompatTheme_seekBarStyle:
                o();
                this.j = true;
                return true;
            default:
                return this.c.requestFeature(i);
        }
    }

    final void b(CharSequence charSequence) {
        if (this.p != null) {
            this.p.setWindowTitle(charSequence);
        } else if (this.g != null) {
            this.g.a(charSequence);
        } else if (this.z != null) {
            this.z.setText(charSequence);
        }
    }

    final void d(int i) {
        if (i == 108) {
            ActionBar a = a();
            if (a != null) {
                a.c(false);
            }
        } else if (i == 0) {
            PanelFeatureState f = f(i);
            if (f.o) {
                a(f, false);
            }
        }
    }

    final boolean e(int i) {
        if (i != 108) {
            return false;
        }
        ActionBar a = a();
        if (a == null) {
            return true;
        }
        a.c(true);
        return true;
    }

    public final void a(f fVar) {
        if (this.p == null || !this.p.a() || (ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get(this.b)) && !this.p.c())) {
            PanelFeatureState f = f(0);
            f.q = true;
            a(f, false);
            a(f, null);
            return;
        }
        Callback callback = this.c.getCallback();
        if (this.p.b()) {
            this.p.e();
            if (!this.o) {
                callback.onPanelClosed(R.styleable.AppCompatTheme_ratingBarStyleSmall, f(0).j);
            }
        } else if (callback != null && !this.o) {
            if (this.H && (this.I & 1) != 0) {
                this.c.getDecorView().removeCallbacks(this.J);
                this.J.run();
            }
            PanelFeatureState f2 = f(0);
            if (f2.j != null && !f2.r && callback.onPreparePanel(0, f2.i, f2.j)) {
                callback.onMenuOpened(R.styleable.AppCompatTheme_ratingBarStyleSmall, f2.j);
                this.p.d();
            }
        }
    }

    public final void f() {
        ActionBar a = a();
        if (a == null || !a.d()) {
            g(0);
        }
    }

    final void m() {
        if (this.u != null) {
            this.u.cancel();
        }
    }

    final boolean a(int i, KeyEvent keyEvent) {
        ActionBar a = a();
        if (a != null && a.a(i, keyEvent)) {
            return true;
        }
        if (this.F == null || !a(this.F, keyEvent.getKeyCode(), keyEvent)) {
            if (this.F == null) {
                PanelFeatureState f = f(0);
                b(f, keyEvent);
                boolean a2 = a(f, keyEvent.getKeyCode(), keyEvent);
                f.m = false;
                if (a2) {
                    return true;
                }
            }
            return false;
        } else if (this.F == null) {
            return true;
        } else {
            this.F.n = true;
            return true;
        }
    }

    final boolean a(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 82 && this.d.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        boolean z;
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            switch (keyCode) {
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    this.G = (keyEvent.getFlags() & 128) != 0;
                    if (VERSION.SDK_INT < 11) {
                        a(keyCode, keyEvent);
                    }
                    return false;
                case R.styleable.AppCompatTheme_colorPrimary:
                    if (keyEvent.getRepeatCount() != 0) {
                        return true;
                    }
                    PanelFeatureState f = f(0);
                    if (f.o) {
                        return true;
                    }
                    b(f, keyEvent);
                    return true;
                default:
                    if (VERSION.SDK_INT < 11) {
                        a(keyCode, keyEvent);
                    }
                    return false;
            }
        }
        PanelFeatureState f2;
        switch (keyCode) {
            case XZBDevice.DOWNLOAD_LIST_ALL:
                z = this.G;
                this.G = false;
                f2 = f(0);
                if (f2 == null || !f2.o) {
                    if (this.q != null) {
                        this.q.c();
                        z = true;
                    } else {
                        ActionBar a = a();
                        if (a == null || !a.e()) {
                            z = false;
                        } else {
                            z = true;
                        }
                    }
                    if (z) {
                        return true;
                    }
                    return false;
                } else if (z) {
                    return true;
                } else {
                    a(f2, true);
                    return true;
                }
            case R.styleable.AppCompatTheme_colorPrimary:
                if (this.q != null) {
                    return true;
                }
                f2 = f(0);
                if (this.p == null || !this.p.a() || ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get(this.b))) {
                    if (f2.o || f2.n) {
                        z = f2.o;
                        a(f2, true);
                    } else {
                        if (f2.m) {
                            if (f2.r) {
                                f2.m = false;
                                z = b(f2, keyEvent);
                            } else {
                                z = true;
                            }
                            if (z) {
                                a(f2, keyEvent);
                                z = true;
                            }
                        }
                        z = false;
                    }
                } else if (this.p.b()) {
                    z = this.p.e();
                } else {
                    if (!this.o && b(f2, keyEvent)) {
                        z = this.p.d();
                    }
                    z = false;
                }
                if (!z) {
                    return true;
                }
                AudioManager audioManager = (AudioManager) this.b.getSystemService("audio");
                if (audioManager == null) {
                    return true;
                }
                audioManager.playSoundEffect(0);
                return true;
            default:
                return false;
        }
    }

    public final void h() {
        LayoutInflater from = LayoutInflater.from(this.b);
        if (from.getFactory() == null) {
            LayoutInflaterCompat.setFactory(from, this);
        } else {
            LayoutInflaterCompat.getFactory(from);
        }
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        Object obj = null;
        View a = a(str, context, attributeSet);
        if (a == null) {
            Object obj2;
            ab abVar;
            boolean a2;
            Context context2;
            AppCompatTextView appCompatTextView;
            int i;
            boolean z = VERSION.SDK_INT < 21;
            if (this.N == null) {
                this.N = new ab();
            }
            if (z) {
                int i2;
                ViewParent viewParent = (ViewParent) view;
                if (viewParent == null) {
                    obj2 = null;
                } else {
                    ViewParent decorView = this.c.getDecorView();
                    ViewParent viewParent2 = viewParent;
                    while (viewParent2 != null) {
                        if (viewParent2 != decorView && (viewParent2 instanceof View) && !ViewCompat.isAttachedToWindow((View) viewParent2)) {
                            viewParent2 = viewParent2.getParent();
                        }
                        obj2 = null;
                        break;
                    }
                    i2 = 1;
                }
                if (obj2 != null) {
                    i2 = 1;
                    abVar = this.N;
                    a2 = cu.a();
                    if (obj2 != null || view == null) {
                        context2 = context;
                    } else {
                        context2 = view.getContext();
                    }
                    context2 = ab.a(context2, attributeSet, z);
                    if (a2) {
                        context2 = cj.a(context2);
                    }
                    appCompatTextView = null;
                    switch (str.hashCode()) {
                        case -1946472170:
                            if (str.equals("RatingBar")) {
                                obj = XZBDevice.Success;
                            }
                            i = -1;
                            break;
                        case -1455429095:
                            if (str.equals("CheckedTextView")) {
                                obj = XZBDevice.Wait;
                            }
                            i = -1;
                            break;
                        case -1346021293:
                            if (str.equals("MultiAutoCompleteTextView")) {
                                obj = XZBDevice.Stop;
                            }
                            i = -1;
                            break;
                        case -938935918:
                            if (str.equals("TextView")) {
                            }
                            i = -1;
                            break;
                        case -937446323:
                            if (str.equals("ImageButton")) {
                                obj = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                            }
                            i = -1;
                            break;
                        case -658531749:
                            if (str.equals("SeekBar")) {
                                obj = XZBDevice.Fail;
                            }
                            i = -1;
                            break;
                        case -339785223:
                            if (str.equals("Spinner")) {
                                obj = XZBDevice.DOWNLOAD_LIST_ALL;
                            }
                            i = -1;
                            break;
                        case 776382189:
                            if (str.equals("RadioButton")) {
                                obj = R.styleable.Toolbar_contentInsetLeft;
                            }
                            i = -1;
                            break;
                        case 1125864064:
                            if (str.equals("ImageView")) {
                                i = 1;
                            }
                            i = -1;
                            break;
                        case 1413872058:
                            if (str.equals("AutoCompleteTextView")) {
                                obj = XZBDevice.Pause;
                            }
                            i = -1;
                            break;
                        case 1601505219:
                            if (str.equals("CheckBox")) {
                                obj = R.styleable.Toolbar_contentInsetEnd;
                            }
                            i = -1;
                            break;
                        case 1666676343:
                            if (str.equals("EditText")) {
                                obj = XZBDevice.DOWNLOAD_LIST_FAILED;
                            }
                            i = -1;
                            break;
                        case 2001146706:
                            if (str.equals("Button")) {
                                obj = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                            }
                            i = -1;
                            break;
                        default:
                            i = -1;
                            break;
                    }
                    switch (obj) {
                        case SpdyAgent.ACCS_TEST_SERVER:
                            appCompatTextView = new AppCompatTextView(context2, attributeSet);
                            break;
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            appCompatTextView = new AppCompatImageView(context2, attributeSet);
                            break;
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            appCompatTextView = new AppCompatButton(context2, attributeSet);
                            break;
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                            appCompatTextView = new AppCompatEditText(context2, attributeSet);
                            break;
                        case XZBDevice.DOWNLOAD_LIST_ALL:
                            appCompatTextView = new AppCompatSpinner(context2, attributeSet);
                            break;
                        case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                            appCompatTextView = new AppCompatImageButton(context2, attributeSet);
                            break;
                        case R.styleable.Toolbar_contentInsetEnd:
                            appCompatTextView = new AppCompatCheckBox(context2, attributeSet);
                            break;
                        case R.styleable.Toolbar_contentInsetLeft:
                            appCompatTextView = new AppCompatRadioButton(context2, attributeSet);
                            break;
                        case XZBDevice.Wait:
                            appCompatTextView = new AppCompatCheckedTextView(context2, attributeSet);
                            break;
                        case XZBDevice.Pause:
                            appCompatTextView = new AppCompatAutoCompleteTextView(context2, attributeSet);
                            break;
                        case XZBDevice.Stop:
                            appCompatTextView = new AppCompatMultiAutoCompleteTextView(context2, attributeSet);
                            break;
                        case XZBDevice.Success:
                            appCompatTextView = new AppCompatRatingBar(context2, attributeSet);
                            break;
                        case XZBDevice.Fail:
                            appCompatTextView = new AppCompatSeekBar(context2, attributeSet);
                            break;
                    }
                    if (r1 == null || context == context2) {
                        a = r1;
                    } else {
                        a = abVar.a(context2, str, attributeSet);
                    }
                    if (a != null) {
                        ab.a(a, attributeSet);
                    }
                }
            }
            obj2 = null;
            abVar = this.N;
            a2 = cu.a();
            if (obj2 != null) {
            }
            context2 = context;
            context2 = ab.a(context2, attributeSet, z);
            if (a2) {
                context2 = cj.a(context2);
            }
            appCompatTextView = null;
            switch (str.hashCode()) {
                case -1946472170:
                    if (str.equals("RatingBar")) {
                        obj = XZBDevice.Success;
                    }
                    i = -1;
                    break;
                case -1455429095:
                    if (str.equals("CheckedTextView")) {
                        obj = XZBDevice.Wait;
                    }
                    i = -1;
                    break;
                case -1346021293:
                    if (str.equals("MultiAutoCompleteTextView")) {
                        obj = XZBDevice.Stop;
                    }
                    i = -1;
                    break;
                case -938935918:
                    if (str.equals("TextView")) {
                    }
                    i = -1;
                    break;
                case -937446323:
                    if (str.equals("ImageButton")) {
                        obj = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                    }
                    i = -1;
                    break;
                case -658531749:
                    if (str.equals("SeekBar")) {
                        obj = XZBDevice.Fail;
                    }
                    i = -1;
                    break;
                case -339785223:
                    if (str.equals("Spinner")) {
                        obj = XZBDevice.DOWNLOAD_LIST_ALL;
                    }
                    i = -1;
                    break;
                case 776382189:
                    if (str.equals("RadioButton")) {
                        obj = R.styleable.Toolbar_contentInsetLeft;
                    }
                    i = -1;
                    break;
                case 1125864064:
                    if (str.equals("ImageView")) {
                        i = 1;
                    }
                    i = -1;
                    break;
                case 1413872058:
                    if (str.equals("AutoCompleteTextView")) {
                        obj = XZBDevice.Pause;
                    }
                    i = -1;
                    break;
                case 1601505219:
                    if (str.equals("CheckBox")) {
                        obj = R.styleable.Toolbar_contentInsetEnd;
                    }
                    i = -1;
                    break;
                case 1666676343:
                    if (str.equals("EditText")) {
                        obj = XZBDevice.DOWNLOAD_LIST_FAILED;
                    }
                    i = -1;
                    break;
                case 2001146706:
                    if (str.equals("Button")) {
                        obj = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                    }
                    i = -1;
                    break;
                default:
                    i = -1;
                    break;
            }
            switch (obj) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    appCompatTextView = new AppCompatTextView(context2, attributeSet);
                    break;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    appCompatTextView = new AppCompatImageView(context2, attributeSet);
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    appCompatTextView = new AppCompatButton(context2, attributeSet);
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    appCompatTextView = new AppCompatEditText(context2, attributeSet);
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    appCompatTextView = new AppCompatSpinner(context2, attributeSet);
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    appCompatTextView = new AppCompatImageButton(context2, attributeSet);
                    break;
                case R.styleable.Toolbar_contentInsetEnd:
                    appCompatTextView = new AppCompatCheckBox(context2, attributeSet);
                    break;
                case R.styleable.Toolbar_contentInsetLeft:
                    appCompatTextView = new AppCompatRadioButton(context2, attributeSet);
                    break;
                case XZBDevice.Wait:
                    appCompatTextView = new AppCompatCheckedTextView(context2, attributeSet);
                    break;
                case XZBDevice.Pause:
                    appCompatTextView = new AppCompatAutoCompleteTextView(context2, attributeSet);
                    break;
                case XZBDevice.Stop:
                    appCompatTextView = new AppCompatMultiAutoCompleteTextView(context2, attributeSet);
                    break;
                case XZBDevice.Success:
                    appCompatTextView = new AppCompatRatingBar(context2, attributeSet);
                    break;
                case XZBDevice.Fail:
                    appCompatTextView = new AppCompatSeekBar(context2, attributeSet);
                    break;
            }
            if (r1 == null) {
            }
            a = r1;
            if (a != null) {
                ab.a(a, attributeSet);
            }
        }
        return a;
    }

    View a(String str, Context context, AttributeSet attributeSet) {
        if (this.d instanceof Factory) {
            View onCreateView = ((Factory) this.d).onCreateView(str, context, attributeSet);
            if (onCreateView != null) {
                return onCreateView;
            }
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.support.v7.app.AppCompatDelegateImplV7.PanelFeatureState r11, android.view.KeyEvent r12) {
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImplV7.a(android.support.v7.app.AppCompatDelegateImplV7$PanelFeatureState, android.view.KeyEvent):void");
        /*
        this = this;
        r1 = -1;
        r2 = -2;
        r3 = 0;
        r9 = 1;
        r0 = r11.o;
        if (r0 != 0) goto L_0x000c;
    L_0x0008:
        r0 = r10.o;
        if (r0 == 0) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        r0 = r11.a;
        if (r0 != 0) goto L_0x0032;
    L_0x0011:
        r4 = r10.b;
        r0 = r4.getResources();
        r0 = r0.getConfiguration();
        r0 = r0.screenLayout;
        r0 = r0 & 15;
        r5 = 4;
        if (r0 != r5) goto L_0x0048;
    L_0x0022:
        r0 = r9;
    L_0x0023:
        r4 = r4.getApplicationInfo();
        r4 = r4.targetSdkVersion;
        r5 = 11;
        if (r4 < r5) goto L_0x004a;
    L_0x002d:
        r4 = r9;
    L_0x002e:
        if (r0 == 0) goto L_0x0032;
    L_0x0030:
        if (r4 != 0) goto L_0x000c;
    L_0x0032:
        r0 = r10.c;
        r0 = r0.getCallback();
        if (r0 == 0) goto L_0x004c;
    L_0x003a:
        r4 = r11.a;
        r5 = r11.j;
        r0 = r0.onMenuOpened(r4, r5);
        if (r0 != 0) goto L_0x004c;
    L_0x0044:
        r10.a(r11, r9);
        goto L_0x000c;
    L_0x0048:
        r0 = r3;
        goto L_0x0023;
    L_0x004a:
        r4 = r3;
        goto L_0x002e;
    L_0x004c:
        r0 = r10.b;
        r4 = "window";
        r0 = r0.getSystemService(r4);
        r8 = r0;
        r8 = (android.view.WindowManager) r8;
        if (r8 == 0) goto L_0x000c;
    L_0x005a:
        r0 = r10.b(r11, r12);
        if (r0 == 0) goto L_0x000c;
    L_0x0060:
        r0 = r11.g;
        if (r0 == 0) goto L_0x0068;
    L_0x0064:
        r0 = r11.q;
        if (r0 == 0) goto L_0x01e4;
    L_0x0068:
        r0 = r11.g;
        if (r0 != 0) goto L_0x0153;
    L_0x006c:
        r0 = r10.l();
        r1 = new android.util.TypedValue;
        r1.<init>();
        r4 = r0.getResources();
        r4 = r4.newTheme();
        r5 = r0.getTheme();
        r4.setTo(r5);
        r5 = android.support.v7.appcompat.R.attr.actionBarPopupTheme;
        r4.resolveAttribute(r5, r1, r9);
        r5 = r1.resourceId;
        if (r5 == 0) goto L_0x0092;
    L_0x008d:
        r5 = r1.resourceId;
        r4.applyStyle(r5, r9);
    L_0x0092:
        r5 = android.support.v7.appcompat.R.attr.panelMenuListTheme;
        r4.resolveAttribute(r5, r1, r9);
        r5 = r1.resourceId;
        if (r5 == 0) goto L_0x014c;
    L_0x009b:
        r1 = r1.resourceId;
        r4.applyStyle(r1, r9);
    L_0x00a0:
        r1 = new android.support.v7.view.d;
        r1.<init>(r0, r3);
        r0 = r1.getTheme();
        r0.setTo(r4);
        r11.l = r1;
        r0 = android.support.v7.appcompat.R.styleable.AppCompatTheme;
        r0 = r1.obtainStyledAttributes(r0);
        r1 = android.support.v7.appcompat.R.styleable.AppCompatTheme_panelBackground;
        r1 = r0.getResourceId(r1, r3);
        r11.b = r1;
        r1 = android.support.v7.appcompat.R.styleable.AppCompatTheme_android_windowAnimationStyle;
        r1 = r0.getResourceId(r1, r3);
        r11.f = r1;
        r0.recycle();
        r0 = new android.support.v7.app.AppCompatDelegateImplV7$c;
        r1 = r11.l;
        r0.<init>(r1);
        r11.g = r0;
        r0 = 81;
        r11.c = r0;
        r0 = r11.g;
        if (r0 == 0) goto L_0x000c;
    L_0x00d8:
        r0 = r11.i;
        if (r0 == 0) goto L_0x0166;
    L_0x00dc:
        r0 = r11.i;
        r11.h = r0;
        r0 = r9;
    L_0x00e1:
        if (r0 == 0) goto L_0x000c;
    L_0x00e3:
        r0 = r11.h;
        if (r0 == 0) goto L_0x01e1;
    L_0x00e7:
        r0 = r11.i;
        if (r0 == 0) goto L_0x01d2;
    L_0x00eb:
        r0 = r9;
    L_0x00ec:
        if (r0 == 0) goto L_0x000c;
    L_0x00ee:
        r0 = r11.h;
        r0 = r0.getLayoutParams();
        if (r0 != 0) goto L_0x01f7;
    L_0x00f6:
        r0 = new android.view.ViewGroup$LayoutParams;
        r0.<init>(r2, r2);
        r1 = r0;
    L_0x00fc:
        r0 = r11.b;
        r4 = r11.g;
        r4.setBackgroundResource(r0);
        r0 = r11.h;
        r0 = r0.getParent();
        if (r0 == 0) goto L_0x0116;
    L_0x010b:
        r4 = r0 instanceof android.view.ViewGroup;
        if (r4 == 0) goto L_0x0116;
    L_0x010f:
        r0 = (android.view.ViewGroup) r0;
        r4 = r11.h;
        r0.removeView(r4);
    L_0x0116:
        r0 = r11.g;
        r4 = r11.h;
        r0.addView(r4, r1);
        r0 = r11.h;
        r0 = r0.hasFocus();
        if (r0 != 0) goto L_0x012a;
    L_0x0125:
        r0 = r11.h;
        r0.requestFocus();
    L_0x012a:
        r1 = r2;
    L_0x012b:
        r11.n = r3;
        r0 = new android.view.WindowManager$LayoutParams;
        r3 = r11.d;
        r4 = r11.e;
        r5 = 1002; // 0x3ea float:1.404E-42 double:4.95E-321;
        r6 = 8519680; // 0x820000 float:1.1938615E-38 double:4.209281E-317;
        r7 = -3;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7);
        r1 = r11.c;
        r0.gravity = r1;
        r1 = r11.f;
        r0.windowAnimations = r1;
        r1 = r11.g;
        r8.addView(r1, r0);
        r11.o = r9;
        goto L_0x000c;
    L_0x014c:
        r1 = android.support.v7.appcompat.R.style.Theme_AppCompat_CompactMenu;
        r4.applyStyle(r1, r9);
        goto L_0x00a0;
    L_0x0153:
        r0 = r11.q;
        if (r0 == 0) goto L_0x00d8;
    L_0x0157:
        r0 = r11.g;
        r0 = r0.getChildCount();
        if (r0 <= 0) goto L_0x00d8;
    L_0x015f:
        r0 = r11.g;
        r0.removeAllViews();
        goto L_0x00d8;
    L_0x0166:
        r0 = r11.j;
        if (r0 == 0) goto L_0x01cf;
    L_0x016a:
        r0 = r10.x;
        if (r0 != 0) goto L_0x0175;
    L_0x016e:
        r0 = new android.support.v7.app.AppCompatDelegateImplV7$d;
        r0.<init>(r3);
        r10.x = r0;
    L_0x0175:
        r0 = r10.x;
        r1 = r11.j;
        if (r1 != 0) goto L_0x0187;
    L_0x017b:
        r0 = 0;
    L_0x017c:
        r0 = (android.view.View) r0;
        r11.h = r0;
        r0 = r11.h;
        if (r0 == 0) goto L_0x01cf;
    L_0x0184:
        r0 = r9;
        goto L_0x00e1;
    L_0x0187:
        r1 = r11.k;
        if (r1 != 0) goto L_0x01a1;
    L_0x018b:
        r1 = new android.support.v7.view.menu.e;
        r4 = r11.l;
        r5 = android.support.v7.appcompat.R.layout.abc_list_menu_item_layout;
        r1.<init>(r4, r5);
        r11.k = r1;
        r1 = r11.k;
        r1.g = r0;
        r0 = r11.j;
        r1 = r11.k;
        r0.a(r1);
    L_0x01a1:
        r1 = r11.k;
        r0 = r11.g;
        r4 = r1.d;
        if (r4 != 0) goto L_0x01cc;
    L_0x01a9:
        r4 = r1.b;
        r5 = android.support.v7.appcompat.R.layout.abc_expanded_menu_layout;
        r0 = r4.inflate(r5, r0, r3);
        r0 = (android.support.v7.view.menu.ExpandedMenuView) r0;
        r1.d = r0;
        r0 = r1.h;
        if (r0 != 0) goto L_0x01c0;
    L_0x01b9:
        r0 = new android.support.v7.view.menu.e$a;
        r0.<init>(r1);
        r1.h = r0;
    L_0x01c0:
        r0 = r1.d;
        r4 = r1.h;
        r0.setAdapter(r4);
        r0 = r1.d;
        r0.setOnItemClickListener(r1);
    L_0x01cc:
        r0 = r1.d;
        goto L_0x017c;
    L_0x01cf:
        r0 = r3;
        goto L_0x00e1;
    L_0x01d2:
        r0 = r11.k;
        r0 = r0.d();
        r0 = r0.getCount();
        if (r0 <= 0) goto L_0x01e1;
    L_0x01de:
        r0 = r9;
        goto L_0x00ec;
    L_0x01e1:
        r0 = r3;
        goto L_0x00ec;
    L_0x01e4:
        r0 = r11.i;
        if (r0 == 0) goto L_0x01f4;
    L_0x01e8:
        r0 = r11.i;
        r0 = r0.getLayoutParams();
        if (r0 == 0) goto L_0x01f4;
    L_0x01f0:
        r0 = r0.width;
        if (r0 == r1) goto L_0x012b;
    L_0x01f4:
        r1 = r2;
        goto L_0x012b;
    L_0x01f7:
        r1 = r0;
        goto L_0x00fc;
        */
    }

    private boolean b(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        if (this.o) {
            return false;
        }
        if (panelFeatureState.m) {
            return true;
        }
        if (!(this.F == null || this.F == panelFeatureState)) {
            a(this.F, false);
        }
        Callback callback = this.c.getCallback();
        if (callback != null) {
            panelFeatureState.i = callback.onCreatePanelView(panelFeatureState.a);
        }
        boolean z = panelFeatureState.a == 0 || panelFeatureState.a == 108;
        if (z && this.p != null) {
            this.p.f();
        }
        if (panelFeatureState.i == null) {
            if (!(z && (this.g instanceof ac))) {
                if (panelFeatureState.j == null || panelFeatureState.r) {
                    if (panelFeatureState.j == null) {
                        Context dVar;
                        f fVar;
                        Context context = this.b;
                        if ((panelFeatureState.a == 0 || panelFeatureState.a == 108) && this.p != null) {
                            Theme newTheme;
                            TypedValue typedValue = new TypedValue();
                            Theme theme = context.getTheme();
                            theme.resolveAttribute(android.support.v7.appcompat.R.attr.actionBarTheme, typedValue, true);
                            if (typedValue.resourceId != 0) {
                                newTheme = context.getResources().newTheme();
                                newTheme.setTo(theme);
                                newTheme.applyStyle(typedValue.resourceId, true);
                                newTheme.resolveAttribute(android.support.v7.appcompat.R.attr.actionBarWidgetTheme, typedValue, true);
                            } else {
                                theme.resolveAttribute(android.support.v7.appcompat.R.attr.actionBarWidgetTheme, typedValue, true);
                                newTheme = null;
                            }
                            if (typedValue.resourceId != 0) {
                                if (newTheme == null) {
                                    newTheme = context.getResources().newTheme();
                                    newTheme.setTo(theme);
                                }
                                newTheme.applyStyle(typedValue.resourceId, true);
                            }
                            Theme theme2 = newTheme;
                            if (theme2 != null) {
                                dVar = new android.support.v7.view.d(context, 0);
                                dVar.getTheme().setTo(theme2);
                                fVar = new f(dVar);
                                fVar.a((android.support.v7.view.menu.f.a) this);
                                panelFeatureState.a(fVar);
                                if (panelFeatureState.j == null) {
                                    return false;
                                }
                            }
                        }
                        dVar = context;
                        fVar = new f(dVar);
                        fVar.a((android.support.v7.view.menu.f.a) this);
                        panelFeatureState.a(fVar);
                        if (panelFeatureState.j == null) {
                            return false;
                        }
                    }
                    if (z && this.p != null) {
                        if (this.w == null) {
                            this.w = new a();
                        }
                        this.p.a(panelFeatureState.j, this.w);
                    }
                    panelFeatureState.j.d();
                    if (callback.onCreatePanelMenu(panelFeatureState.a, panelFeatureState.j)) {
                        panelFeatureState.r = false;
                    } else {
                        panelFeatureState.a(null);
                        if (!z || this.p == null) {
                            return false;
                        }
                        this.p.a(null, this.w);
                        return false;
                    }
                }
                panelFeatureState.j.d();
                if (panelFeatureState.s != null) {
                    panelFeatureState.j.c(panelFeatureState.s);
                    panelFeatureState.s = null;
                }
                if (callback.onPreparePanel(0, panelFeatureState.i, panelFeatureState.j)) {
                    boolean z2;
                    if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    panelFeatureState.p = z2;
                    panelFeatureState.j.setQwertyMode(panelFeatureState.p);
                    panelFeatureState.j.e();
                } else {
                    if (z && this.p != null) {
                        this.p.a(null, this.w);
                    }
                    panelFeatureState.j.e();
                    return false;
                }
            }
        }
        panelFeatureState.m = true;
        panelFeatureState.n = false;
        this.F = panelFeatureState;
        return true;
    }

    private void b(f fVar) {
        if (!this.D) {
            this.D = true;
            this.p.g();
            Callback callback = this.c.getCallback();
            if (!(callback == null || this.o)) {
                callback.onPanelClosed(R.styleable.AppCompatTheme_ratingBarStyleSmall, fVar);
            }
            this.D = false;
        }
    }

    private void a(PanelFeatureState panelFeatureState, boolean z) {
        if (z && panelFeatureState.a == 0 && this.p != null && this.p.b()) {
            b(panelFeatureState.j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.b.getSystemService("window");
        if (!(windowManager == null || !panelFeatureState.o || panelFeatureState.g == null)) {
            windowManager.removeView(panelFeatureState.g);
            if (z) {
                a(panelFeatureState.a, panelFeatureState, null);
            }
        }
        panelFeatureState.m = false;
        panelFeatureState.n = false;
        panelFeatureState.o = false;
        panelFeatureState.h = null;
        panelFeatureState.q = true;
        if (this.F == panelFeatureState) {
            this.F = null;
        }
    }

    private void a(int i, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i >= 0 && i < this.E.length) {
                panelFeatureState = this.E[i];
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.o) && !this.o) {
            this.d.onPanelClosed(i, menu);
        }
    }

    private PanelFeatureState a(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.E;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i = 0; i < length; i++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i];
            if (panelFeatureState != null && panelFeatureState.j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    private PanelFeatureState f(int i) {
        Object obj = this.E;
        if (obj == null || obj.length <= i) {
            Object obj2 = new Object[(i + 1)];
            if (obj != null) {
                System.arraycopy(obj, 0, obj2, 0, obj.length);
            }
            this.E = obj2;
            obj = obj2;
        }
        PanelFeatureState panelFeatureState = obj[i];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        panelFeatureState = new PanelFeatureState(i);
        obj[i] = panelFeatureState;
        return panelFeatureState;
    }

    private boolean a(PanelFeatureState panelFeatureState, int i, KeyEvent keyEvent) {
        if (keyEvent.isSystem()) {
            return false;
        }
        return ((panelFeatureState.m || b(panelFeatureState, keyEvent)) && panelFeatureState.j != null) ? panelFeatureState.j.performShortcut(i, keyEvent, 1) : false;
    }

    private void g(int i) {
        this.I |= 1 << i;
        if (!this.H) {
            ViewCompat.postOnAnimation(this.c.getDecorView(), this.J);
            this.H = true;
        }
    }

    private void o() {
        if (this.y) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final boolean a(f fVar, MenuItem menuItem) {
        Callback callback = this.c.getCallback();
        if (!(callback == null || this.o)) {
            PanelFeatureState a = a(fVar.k());
            if (a != null) {
                return callback.onMenuItemSelected(a.a, menuItem);
            }
        }
        return false;
    }

    static /* synthetic */ int b(AppCompatDelegateImplV7 appCompatDelegateImplV7, int i) {
        int i2;
        Object obj = 1;
        int i3 = 0;
        if (appCompatDelegateImplV7.r == null || !(appCompatDelegateImplV7.r.getLayoutParams() instanceof MarginLayoutParams)) {
            i2 = 0;
        } else {
            int i4;
            int i5;
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) appCompatDelegateImplV7.r.getLayoutParams();
            if (appCompatDelegateImplV7.r.isShown()) {
                if (appCompatDelegateImplV7.L == null) {
                    appCompatDelegateImplV7.L = new Rect();
                    appCompatDelegateImplV7.M = new Rect();
                }
                Rect rect = appCompatDelegateImplV7.L;
                Rect rect2 = appCompatDelegateImplV7.M;
                rect.set(0, i, 0, 0);
                cw.a(appCompatDelegateImplV7.v, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i : 0)) {
                    marginLayoutParams.topMargin = i;
                    if (appCompatDelegateImplV7.A == null) {
                        appCompatDelegateImplV7.A = new View(appCompatDelegateImplV7.b);
                        appCompatDelegateImplV7.A.setBackgroundColor(appCompatDelegateImplV7.b.getResources().getColor(android.support.v7.appcompat.R.color.abc_input_method_navigation_guard));
                        appCompatDelegateImplV7.v.addView(appCompatDelegateImplV7.A, -1, new LayoutParams(-1, i));
                        i4 = 1;
                    } else {
                        LayoutParams layoutParams = appCompatDelegateImplV7.A.getLayoutParams();
                        if (layoutParams.height != i) {
                            layoutParams.height = i;
                            appCompatDelegateImplV7.A.setLayoutParams(layoutParams);
                        }
                        i4 = 1;
                    }
                } else {
                    i4 = 0;
                }
                if (appCompatDelegateImplV7.A == null) {
                    i5 = 0;
                }
                if (!(appCompatDelegateImplV7.k || i5 == 0)) {
                    i = 0;
                }
                int i6 = i4;
                i4 = i5;
                i5 = i6;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                i4 = 0;
            } else {
                i5 = 0;
                i4 = 0;
            }
            if (i5 != 0) {
                appCompatDelegateImplV7.r.setLayoutParams(marginLayoutParams);
            }
            i2 = i4;
        }
        if (appCompatDelegateImplV7.A != null) {
            View view = appCompatDelegateImplV7.A;
            if (i2 == 0) {
                i3 = XZBDevice.Wait;
            }
            view.setVisibility(i3);
        }
        return i;
    }

    static /* synthetic */ void d(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
        if (appCompatDelegateImplV7.p != null) {
            appCompatDelegateImplV7.p.g();
        }
        if (appCompatDelegateImplV7.s != null) {
            appCompatDelegateImplV7.c.getDecorView().removeCallbacks(appCompatDelegateImplV7.t);
            if (appCompatDelegateImplV7.s.isShowing()) {
                try {
                    appCompatDelegateImplV7.s.dismiss();
                } catch (IllegalArgumentException e) {
                }
            }
            appCompatDelegateImplV7.s = null;
        }
        appCompatDelegateImplV7.m();
        PanelFeatureState f = appCompatDelegateImplV7.f(0);
        if (f != null && f.j != null) {
            f.j.close();
        }
    }
}
