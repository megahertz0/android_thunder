package com.handmark.pulltorefresh.library;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public abstract class PullToRefreshBase<T extends View> extends LinearLayout {
    private int A;
    private int B;
    private int a;
    protected State b;
    Mode c;
    T d;
    boolean e;
    protected e<T> f;
    public boolean g;
    private float h;
    private float i;
    private float j;
    private float k;
    private boolean l;
    private Mode m;
    private FrameLayout n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private Interpolator s;
    private AnimationStyle t;
    private com.handmark.pulltorefresh.library.a.d u;
    private com.handmark.pulltorefresh.library.a.d v;
    private d<T> w;
    private b<T> x;
    private c y;
    private g z;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;
        static final /* synthetic */ int[] c;
        static final /* synthetic */ int[] d;

        static {
            d = new int[AnimationStyle.values().length];
            try {
                d[AnimationStyle.ROTATE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                d[AnimationStyle.FLIP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            c = new int[Mode.values().length];
            try {
                c[Mode.PULL_FROM_END.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                c[Mode.PULL_FROM_START.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                c[Mode.MANUAL_REFRESH_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                c[Mode.BOTH.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            b = new int[State.values().length];
            try {
                b[State.RESET.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                b[State.PULL_TO_REFRESH.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                b[State.RELEASE_TO_REFRESH.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
            try {
                b[State.REFRESHING.ordinal()] = 4;
            } catch (NoSuchFieldError e10) {
            }
            try {
                b[State.MANUAL_REFRESHING.ordinal()] = 5;
            } catch (NoSuchFieldError e11) {
            }
            try {
                b[State.OVERSCROLLING.ordinal()] = 6;
            } catch (NoSuchFieldError e12) {
            }
            a = new int[Orientation.values().length];
            try {
                a[Orientation.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[Orientation.VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError e14) {
            }
        }
    }

    public enum AnimationStyle {
        ROTATE,
        FLIP;

        static {
            ROTATE = new com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle("ROTATE", 0);
            FLIP = new com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle("FLIP", 1);
            a = new com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle[]{ROTATE, FLIP};
        }

        static com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle a() {
            return ROTATE;
        }

        static com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle a(int i) {
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    return FLIP;
                default:
                    return ROTATE;
            }
        }
    }

    public enum Mode {
        DISABLED(0),
        PULL_FROM_START(1),
        PULL_FROM_END(2),
        BOTH(3),
        MANUAL_REFRESH_ONLY(4);
        int a;

        static {
            DISABLED = new com.handmark.pulltorefresh.library.PullToRefreshBase.Mode("DISABLED", 0, 0);
            PULL_FROM_START = new com.handmark.pulltorefresh.library.PullToRefreshBase.Mode("PULL_FROM_START", 1, 1);
            PULL_FROM_END = new com.handmark.pulltorefresh.library.PullToRefreshBase.Mode("PULL_FROM_END", 2, 2);
            BOTH = new com.handmark.pulltorefresh.library.PullToRefreshBase.Mode("BOTH", 3, 3);
            MANUAL_REFRESH_ONLY = new com.handmark.pulltorefresh.library.PullToRefreshBase.Mode("MANUAL_REFRESH_ONLY", 4, 4);
            b = new com.handmark.pulltorefresh.library.PullToRefreshBase.Mode[]{DISABLED, PULL_FROM_START, PULL_FROM_END, BOTH, MANUAL_REFRESH_ONLY};
        }

        static com.handmark.pulltorefresh.library.PullToRefreshBase.Mode a(int i) {
            com.handmark.pulltorefresh.library.PullToRefreshBase.Mode[] values = values();
            int length = values.length;
            for (int i2 = 0; i2 < length; i2++) {
                com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mode = values[i2];
                if (i == mode.a) {
                    return mode;
                }
            }
            return PULL_FROM_START;
        }

        static com.handmark.pulltorefresh.library.PullToRefreshBase.Mode a() {
            return PULL_FROM_START;
        }

        private Mode(int i) {
            this.a = i;
        }

        final boolean b() {
            return (this == DISABLED || this == MANUAL_REFRESH_ONLY) ? false : true;
        }

        public final boolean showHeaderLoadingLayout() {
            return this == PULL_FROM_START || this == BOTH;
        }

        public final boolean showFooterLoadingLayout() {
            return this == PULL_FROM_END || this == BOTH || this == MANUAL_REFRESH_ONLY;
        }
    }

    public enum Orientation {
        VERTICAL,
        HORIZONTAL;

        static {
            VERTICAL = new com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation("VERTICAL", 0);
            HORIZONTAL = new com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation("HORIZONTAL", 1);
            a = new com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation[]{VERTICAL, HORIZONTAL};
        }
    }

    public enum State {
        RESET(0),
        PULL_TO_REFRESH(1),
        RELEASE_TO_REFRESH(2),
        REFRESHING(8),
        MANUAL_REFRESHING(9),
        OVERSCROLLING(16);
        int a;

        static {
            RESET = new com.handmark.pulltorefresh.library.PullToRefreshBase.State("RESET", 0, 0);
            PULL_TO_REFRESH = new com.handmark.pulltorefresh.library.PullToRefreshBase.State("PULL_TO_REFRESH", 1, 1);
            RELEASE_TO_REFRESH = new com.handmark.pulltorefresh.library.PullToRefreshBase.State("RELEASE_TO_REFRESH", 2, 2);
            REFRESHING = new com.handmark.pulltorefresh.library.PullToRefreshBase.State("REFRESHING", 3, 8);
            MANUAL_REFRESHING = new com.handmark.pulltorefresh.library.PullToRefreshBase.State("MANUAL_REFRESHING", 4, 9);
            OVERSCROLLING = new com.handmark.pulltorefresh.library.PullToRefreshBase.State("OVERSCROLLING", 5, 16);
            b = new com.handmark.pulltorefresh.library.PullToRefreshBase.State[]{RESET, PULL_TO_REFRESH, RELEASE_TO_REFRESH, REFRESHING, MANUAL_REFRESHING, OVERSCROLLING};
        }

        static com.handmark.pulltorefresh.library.PullToRefreshBase.State a(int i) {
            com.handmark.pulltorefresh.library.PullToRefreshBase.State[] values = values();
            int length = values.length;
            for (int i2 = 0; i2 < length; i2++) {
                com.handmark.pulltorefresh.library.PullToRefreshBase.State state = values[i2];
                if (i == state.a) {
                    return state;
                }
            }
            return RESET;
        }

        private State(int i) {
            this.a = i;
        }
    }

    public static interface a {
        void a();
    }

    public static interface b<V extends View> {
    }

    public static interface c {
    }

    public static interface d<V extends View> {
        void onRefresh(PullToRefreshBase<V> pullToRefreshBase);
    }

    public static interface e<V extends View> {
        void onPullDownToRefresh(PullToRefreshBase<V> pullToRefreshBase);

        void onPullUpToRefresh(PullToRefreshBase<V> pullToRefreshBase);
    }

    static interface f {
        void a();
    }

    final class g implements Runnable {
        boolean a;
        private final Interpolator c;
        private final int d;
        private final int e;
        private final long f;
        private f g;
        private long h;
        private int i;

        public g(int i, int i2, long j, f fVar) {
            this.a = true;
            this.h = -1;
            this.i = -1;
            this.e = i;
            this.d = i2;
            this.c = PullToRefreshBase.this.s;
            this.f = j;
            this.g = fVar;
        }

        public final void run() {
            if (this.h == -1) {
                this.h = System.currentTimeMillis();
            } else {
                float f = (float) (this.e - this.d);
                this.i = this.e - Math.round(this.c.getInterpolation(((float) Math.max(Math.min(((System.currentTimeMillis() - this.h) * 1000) / this.f, 1000), 0)) / 1000.0f) * f);
                PullToRefreshBase.this.setScrollValue(this.i);
            }
            if (this.a && this.d != this.i) {
                View view = PullToRefreshBase.this;
                if (VERSION.SDK_INT >= 16) {
                    view.postOnAnimation(this);
                } else {
                    view.postDelayed(this, PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS);
                }
            } else if (this.g != null) {
                this.g.a();
            }
        }
    }

    public abstract T a(Context context, AttributeSet attributeSet);

    public abstract boolean f();

    public abstract boolean g();

    public abstract Orientation getPullToRefreshScrollDirection();

    public PullToRefreshBase(Context context) {
        super(context);
        this.l = false;
        this.b = State.RESET;
        this.c = Mode.a();
        this.o = true;
        this.p = false;
        this.q = true;
        this.r = true;
        this.e = true;
        this.t = AnimationStyle.a();
        b(context, null);
    }

    public PullToRefreshBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = false;
        this.b = State.RESET;
        this.c = Mode.a();
        this.o = true;
        this.p = false;
        this.q = true;
        this.r = true;
        this.e = true;
        this.t = AnimationStyle.a();
        b(context, attributeSet);
    }

    public PullToRefreshBase(Context context, Mode mode) {
        super(context);
        this.l = false;
        this.b = State.RESET;
        this.c = Mode.a();
        this.o = true;
        this.p = false;
        this.q = true;
        this.r = true;
        this.e = true;
        this.t = AnimationStyle.a();
        this.c = mode;
        b(context, null);
    }

    public PullToRefreshBase(Context context, Mode mode, AnimationStyle animationStyle) {
        super(context);
        this.l = false;
        this.b = State.RESET;
        this.c = Mode.a();
        this.o = true;
        this.p = false;
        this.q = true;
        this.r = true;
        this.e = true;
        this.t = AnimationStyle.a();
        this.c = mode;
        this.t = animationStyle;
        b(context, null);
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        View refreshableView = getRefreshableView();
        if (refreshableView instanceof ViewGroup) {
            ((ViewGroup) refreshableView).addView(view, i, layoutParams);
            return;
        }
        throw new UnsupportedOperationException("Refreshable View is not a ViewGroup so can't addView");
    }

    public final Mode getCurrentMode() {
        return this.m;
    }

    public final boolean getFilterTouchEvents() {
        return this.q;
    }

    public final a getLoadingLayoutProxy() {
        return a(true, true);
    }

    public final Mode getMode() {
        return this.c;
    }

    public final T getRefreshableView() {
        return this.d;
    }

    public final boolean getShowViewWhileRefreshing() {
        return this.o;
    }

    public final State getState() {
        return this.b;
    }

    public final boolean j() {
        if (VERSION.SDK_INT >= 9 && this.r) {
            boolean z;
            if (this.d.getOverScrollMode() != 2) {
                z = true;
            } else {
                Object obj = null;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final boolean k() {
        return this.b == State.REFRESHING || this.b == State.MANUAL_REFRESHING;
    }

    protected final boolean l() {
        return ((this.b == State.REFRESHING && this.m == Mode.PULL_FROM_START) || this.b == State.MANUAL_REFRESHING) ? false : true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.c.b()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.l = false;
            return false;
        } else if (action != 0 && this.l) {
            return true;
        } else {
            float y;
            switch (action) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    if (o()) {
                        y = motionEvent.getY();
                        this.k = y;
                        this.i = y;
                        y = motionEvent.getX();
                        this.j = y;
                        this.h = y;
                        this.l = false;
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (!this.p && k()) {
                        return true;
                    }
                    if (o()) {
                        float y2 = motionEvent.getY();
                        float x = motionEvent.getX();
                        float f;
                        switch (AnonymousClass_1.a[getPullToRefreshScrollDirection().ordinal()]) {
                            case SpdyAgent.ACCS_ONLINE_SERVER:
                                y = x - this.h;
                                f = y2 - this.i;
                                break;
                            default:
                                y = y2 - this.i;
                                f = x - this.h;
                                break;
                        }
                        float abs = Math.abs(y);
                        if (abs > ((float) this.a)) {
                            if (!this.q || abs > Math.abs(r0)) {
                                if (this.c.showHeaderLoadingLayout() && y >= 1.0f && f()) {
                                    this.i = y2;
                                    this.h = x;
                                    this.l = true;
                                    if (this.c == Mode.BOTH) {
                                        this.m = Mode.PULL_FROM_START;
                                    }
                                } else if (this.c.showFooterLoadingLayout() && y <= -1.0f && g()) {
                                    this.i = y2;
                                    this.h = x;
                                    this.l = true;
                                    if (this.c == Mode.BOTH) {
                                        this.m = Mode.PULL_FROM_END;
                                    }
                                }
                            }
                        }
                    }
                    break;
            }
            new StringBuilder("BeingDragger:  ============ ").append(this.l);
            return this.l;
        }
    }

    public void m() {
        if (k()) {
            a(State.RESET, new boolean[0]);
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        new StringBuilder("touch action ").append(motionEvent.getAction()).append(" y ").append(motionEvent.getY());
        if (!this.c.b()) {
            return false;
        }
        if (!this.p && k()) {
            return true;
        }
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        float y;
        switch (motionEvent.getAction()) {
            case SpdyAgent.ACCS_TEST_SERVER:
                this.g = true;
                if (o()) {
                    y = motionEvent.getY();
                    this.k = y;
                    this.i = y;
                    y = motionEvent.getX();
                    this.j = y;
                    this.h = y;
                    return true;
                }
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.g = false;
                if (this.l) {
                    this.l = false;
                    if (this.b == State.RELEASE_TO_REFRESH && (this.w != null || this.f != null)) {
                        a(State.REFRESHING, true);
                        return true;
                    } else if (k()) {
                        if (i()) {
                            a(-getHeaderSize());
                        } else {
                            a(0);
                        }
                        return true;
                    } else {
                        a(State.RESET, new boolean[0]);
                        return true;
                    }
                }
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.g = true;
                if (this.l) {
                    float f;
                    int round;
                    int footerSize;
                    this.i = motionEvent.getY();
                    this.h = motionEvent.getX();
                    switch (AnonymousClass_1.a[getPullToRefreshScrollDirection().ordinal()]) {
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            f = this.j;
                            y = this.h;
                            break;
                        default:
                            f = this.k;
                            y = this.i;
                            break;
                    }
                    switch (AnonymousClass_1.c[this.m.ordinal()]) {
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            round = Math.round(Math.max(f - y, AutoScrollHelper.RELATIVE_UNSPECIFIED) / 2.0f);
                            footerSize = getFooterSize();
                            break;
                        default:
                            round = Math.round(Math.min(f - y, AutoScrollHelper.RELATIVE_UNSPECIFIED) / 2.0f);
                            footerSize = getHeaderSize();
                            break;
                    }
                    setScrollValue(round);
                    if (!(round == 0 || k())) {
                        float abs = ((float) Math.abs(round)) / ((float) footerSize);
                        switch (AnonymousClass_1.c[this.m.ordinal()]) {
                            case SpdyAgent.ACCS_ONLINE_SERVER:
                                this.v.b(abs);
                                break;
                            default:
                                this.u.b(abs);
                                break;
                        }
                        if (this.b != State.PULL_TO_REFRESH && footerSize >= Math.abs(round)) {
                            a(State.PULL_TO_REFRESH, new boolean[0]);
                        } else if (this.b == State.PULL_TO_REFRESH && footerSize < Math.abs(round)) {
                            a(State.RELEASE_TO_REFRESH, new boolean[0]);
                        }
                    }
                    return true;
                }
        }
        return false;
    }

    public final void setScrollingWhileRefreshingEnabled(boolean z) {
        this.p = z;
    }

    public final void setFilterTouchEvents(boolean z) {
        this.q = z;
    }

    public void setLongClickable(boolean z) {
        getRefreshableView().setLongClickable(z);
    }

    public final void setMode(Mode mode) {
        if (mode != this.c) {
            this.c = mode;
            h();
        }
    }

    public void setOnPullEventListener(b<T> bVar) {
        this.x = bVar;
    }

    public void setOnPullScrollListener(c cVar) {
        this.y = cVar;
    }

    public final void setOnRefreshListener(d<T> dVar) {
        this.w = dVar;
        this.f = null;
    }

    public final void setOnRefreshListener(e<T> eVar) {
        this.f = eVar;
        this.w = null;
    }

    public final void setPullToRefreshOverScrollEnabled(boolean z) {
        this.r = z;
    }

    public final void setRefreshing(boolean z) {
        if (!k()) {
            a(State.MANUAL_REFRESHING, z);
        }
    }

    public void setScrollAnimationInterpolator(Interpolator interpolator) {
        this.s = interpolator;
    }

    public final void setShowViewWhileRefreshing(boolean z) {
        this.o = z;
    }

    final void a(State state, boolean... zArr) {
        this.b = state;
        switch (AnonymousClass_1.b[this.b.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                e();
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                c();
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                d();
            case XZBDevice.DOWNLOAD_LIST_ALL:
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                a(zArr[0]);
            default:
                break;
        }
    }

    protected final void n() {
        this.b = State.MANUAL_REFRESHING;
        this.m = Mode.PULL_FROM_START;
        if (this.c.showHeaderLoadingLayout()) {
            this.u.e();
        }
        a(-getHeaderSize(), new d(this));
    }

    protected final com.handmark.pulltorefresh.library.a.d a(Context context, Mode mode, TypedArray typedArray) {
        com.handmark.pulltorefresh.library.a.d bVar;
        AnimationStyle animationStyle = this.t;
        Orientation pullToRefreshScrollDirection = getPullToRefreshScrollDirection();
        switch (AnonymousClass_1.d[animationStyle.ordinal()]) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                bVar = new com.handmark.pulltorefresh.library.a.b(context, mode, pullToRefreshScrollDirection, typedArray);
                break;
            default:
                bVar = new com.handmark.pulltorefresh.library.a.e(context, mode, pullToRefreshScrollDirection, typedArray);
                break;
        }
        bVar.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        return bVar;
    }

    protected b a(boolean z, boolean z2) {
        b bVar = new b();
        if (z && this.c.showHeaderLoadingLayout()) {
            bVar.a(this.u);
        }
        if (z2 && this.c.showFooterLoadingLayout()) {
            bVar.a(this.v);
        }
        return bVar;
    }

    protected final com.handmark.pulltorefresh.library.a.d getFooterLayout() {
        return this.v;
    }

    protected final int getFooterSize() {
        return this.v.getContentSize();
    }

    protected final com.handmark.pulltorefresh.library.a.d getHeaderLayout() {
        return this.u;
    }

    protected final int getHeaderSize() {
        if (this.B == 0) {
            this.B = this.u.getContentSize();
        }
        return this.B;
    }

    protected int getPullToRefreshScrollDuration() {
        return Impl.STATUS_SUCCESS;
    }

    protected int getPullToRefreshScrollDurationLonger() {
        return 325;
    }

    public FrameLayout getRefreshableViewWrapper() {
        return this.n;
    }

    protected void a(TypedArray typedArray) {
    }

    protected void a(Bundle bundle) {
    }

    protected void b(Bundle bundle) {
    }

    protected void c() {
        switch (AnonymousClass_1.c[this.m.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.v.d();
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.u.d();
            default:
                break;
        }
    }

    protected void a(boolean z) {
        if (this.c.showHeaderLoadingLayout()) {
            this.u.e();
        }
        if (this.c.showFooterLoadingLayout()) {
            this.v.e();
        }
        if (!z) {
            b();
        } else if (this.o) {
            f eVar = new e(this);
            switch (AnonymousClass_1.c[this.m.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    a(getFooterSize(), eVar);
                default:
                    a(-getHeaderSize(), eVar);
            }
        } else {
            a(0);
        }
    }

    protected void d() {
        switch (AnonymousClass_1.c[this.m.ordinal()]) {
        }
    }

    protected void e() {
        this.l = false;
        this.e = true;
        this.u.f();
        this.v.f();
        a(0);
    }

    protected final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            setMode(Mode.a(bundle.getInt("ptr_mode", 0)));
            this.m = Mode.a(bundle.getInt("ptr_current_mode", 0));
            this.p = bundle.getBoolean("ptr_disable_scrolling", false);
            this.o = bundle.getBoolean("ptr_show_refreshing_view", true);
            super.onRestoreInstanceState(bundle.getParcelable("ptr_super"));
            State a = State.a(bundle.getInt("ptr_state", 0));
            if (a == State.REFRESHING || a == State.MANUAL_REFRESHING) {
                a(a, true);
            }
            a(bundle);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected final Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        b(bundle);
        bundle.putInt("ptr_state", this.b.a);
        bundle.putInt("ptr_mode", this.c.a);
        bundle.putInt("ptr_current_mode", this.m.a);
        bundle.putBoolean("ptr_disable_scrolling", this.p);
        bundle.putBoolean("ptr_show_refreshing_view", this.o);
        bundle.putParcelable("ptr_super", super.onSaveInstanceState());
        return bundle;
    }

    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        a();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.n.getLayoutParams();
        switch (AnonymousClass_1.a[getPullToRefreshScrollDirection().ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                if (layoutParams.width != i) {
                    layoutParams.width = i;
                    this.n.requestLayout();
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                if (layoutParams.height != i2) {
                    layoutParams.height = i2;
                    this.n.requestLayout();
                }
                break;
        }
        post(new f(this));
    }

    private void a() {
        int i;
        int i2 = 0;
        int maximumPullScroll = (int) (((float) getMaximumPullScroll()) * 1.2f);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        switch (AnonymousClass_1.a[getPullToRefreshScrollDirection().ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                if (this.c.showHeaderLoadingLayout()) {
                    this.u.setWidth(maximumPullScroll);
                    i = -maximumPullScroll;
                } else {
                    i = 0;
                }
                if (this.c.showFooterLoadingLayout()) {
                    this.v.setWidth(maximumPullScroll);
                    paddingRight = i;
                    i = -maximumPullScroll;
                    i2 = paddingBottom;
                    paddingBottom = paddingTop;
                } else {
                    paddingRight = i;
                    i = 0;
                    i2 = paddingBottom;
                    paddingBottom = paddingTop;
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                if (this.c.showHeaderLoadingLayout()) {
                    this.u.setHeight(maximumPullScroll);
                    i = -maximumPullScroll;
                } else {
                    i = 0;
                }
                if (this.c.showFooterLoadingLayout()) {
                    this.v.setHeight(maximumPullScroll);
                    i2 = -maximumPullScroll;
                    paddingBottom = i;
                    i = paddingRight;
                    paddingRight = paddingLeft;
                } else {
                    paddingBottom = i;
                    i = paddingRight;
                    paddingRight = paddingLeft;
                }
                break;
            default:
                i2 = paddingBottom;
                i = paddingRight;
                paddingBottom = paddingTop;
                paddingRight = paddingLeft;
                break;
        }
        setPadding(paddingRight, paddingBottom, i, i2);
    }

    @SuppressLint({"InlinedApi"})
    protected final void setScrollValue(int i) {
        int maximumPullScroll = getMaximumPullScroll();
        maximumPullScroll = Math.min(maximumPullScroll, Math.max(-maximumPullScroll, i));
        if (this.e) {
            if (maximumPullScroll < 0) {
                this.u.setVisibility(0);
                this.u.a(maximumPullScroll, this.b, this.m);
            } else if (maximumPullScroll > 0) {
                this.v.setVisibility(0);
                this.v.a(maximumPullScroll, this.b, this.m);
            } else {
                this.u.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                this.v.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            }
        }
        int i2 = VERSION.SDK_INT;
        if (this.b == State.MANUAL_REFRESHING || getMode() != Mode.PULL_FROM_START || this.g || maximumPullScroll >= 0 || maximumPullScroll >= this.A) {
            switch (AnonymousClass_1.a[getPullToRefreshScrollDirection().ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    scrollTo(maximumPullScroll, 0);
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    scrollTo(0, maximumPullScroll);
                    break;
            }
            this.A = maximumPullScroll;
        }
    }

    protected final void a(int i) {
        a(i, (long) getPullToRefreshScrollDuration(), null);
    }

    private void a(int i, f fVar) {
        a(i, (long) getPullToRefreshScrollDuration(), fVar);
    }

    protected void h() {
        LayoutParams loadingLayoutLayoutParams = getLoadingLayoutLayoutParams();
        if (this == this.u.getParent()) {
            removeView(this.u);
        }
        if (this.c.showHeaderLoadingLayout()) {
            super.addView(this.u, 0, loadingLayoutLayoutParams);
        }
        if (this == this.v.getParent()) {
            removeView(this.v);
        }
        if (this.c.showFooterLoadingLayout()) {
            super.addView(this.v, -1, loadingLayoutLayoutParams);
        }
        a();
        this.m = this.c != Mode.BOTH ? this.c : Mode.PULL_FROM_START;
    }

    private void b() {
        if (this.w != null) {
            this.w.onRefresh(this);
        } else if (this.f == null) {
        } else {
            if (this.m == Mode.PULL_FROM_START) {
                this.f.onPullDownToRefresh(this);
            } else if (this.m == Mode.PULL_FROM_END) {
                this.f.onPullUpToRefresh(this);
            }
        }
    }

    private void b(Context context, AttributeSet attributeSet) {
        switch (AnonymousClass_1.a[getPullToRefreshScrollDirection().ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                setOrientation(0);
                break;
            default:
                setOrientation(1);
                break;
        }
        setGravity(R.styleable.Toolbar_maxButtonHeight);
        this.a = ViewConfiguration.get(context).getScaledTouchSlop();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh, 0, 0);
        if (obtainStyledAttributes.hasValue(com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh_ptrMode)) {
            this.c = Mode.a(obtainStyledAttributes.getInteger(com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh_ptrMode, 0));
        }
        if (obtainStyledAttributes.hasValue(com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh_ptrAnimationStyle)) {
            this.t = AnimationStyle.a(obtainStyledAttributes.getInteger(com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh_ptrAnimationStyle, 0));
        }
        this.d = a(context, attributeSet);
        View view = this.d;
        this.n = new FrameLayout(context);
        this.n.addView(view, -1, -1);
        super.addView(this.n, -1, new LinearLayout.LayoutParams(-1, -1));
        this.u = a(context, Mode.PULL_FROM_START, obtainStyledAttributes);
        this.v = a(context, Mode.PULL_FROM_END, obtainStyledAttributes);
        Drawable drawable;
        if (obtainStyledAttributes.hasValue(com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh_ptrRefreshableViewBackground)) {
            drawable = obtainStyledAttributes.getDrawable(com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh_ptrRefreshableViewBackground);
            if (drawable != null) {
                this.d.setBackgroundDrawable(drawable);
            }
        } else if (obtainStyledAttributes.hasValue(com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh_ptrAdapterViewBackground)) {
            new StringBuilder("You're using the deprecated ").append("ptrAdapterViewBackground").append(" attr, please switch over to ").append("ptrRefreshableViewBackground");
            drawable = obtainStyledAttributes.getDrawable(com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh_ptrAdapterViewBackground);
            if (drawable != null) {
                this.d.setBackgroundDrawable(drawable);
            }
        }
        if (obtainStyledAttributes.hasValue(com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh_ptrOverScroll)) {
            this.r = obtainStyledAttributes.getBoolean(com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh_ptrOverScroll, true);
        }
        if (obtainStyledAttributes.hasValue(com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh_ptrScrollingWhileRefreshingEnabled)) {
            this.p = obtainStyledAttributes.getBoolean(com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh_ptrScrollingWhileRefreshingEnabled, false);
        }
        if (!(!obtainStyledAttributes.hasValue(com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh_ptrFooterRefreshViewShow) || obtainStyledAttributes.getBoolean(com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh_ptrFooterRefreshViewShow, true) || this.v == null)) {
            com.handmark.pulltorefresh.library.a.d.c();
            this.v.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            this.v.removeAllViews();
        }
        if (!(!obtainStyledAttributes.hasValue(com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh_ptrHeaderRefreshViewShow) || obtainStyledAttributes.getBoolean(com.xunlei.downloadprovidercommon.R.styleable.PullToRefresh_ptrHeaderRefreshViewShow, true) || this.u == null)) {
            this.u.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            this.u.removeAllViews();
        }
        a(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        h();
    }

    private boolean o() {
        switch (AnonymousClass_1.c[this.c.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return g();
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return f();
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return g() || f();
            default:
                return false;
        }
    }

    private LinearLayout.LayoutParams getLoadingLayoutLayoutParams() {
        switch (AnonymousClass_1.a[getPullToRefreshScrollDirection().ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return new LinearLayout.LayoutParams(-2, -1);
            default:
                return new LinearLayout.LayoutParams(-1, -2);
        }
    }

    private int getMaximumPullScroll() {
        switch (AnonymousClass_1.a[getPullToRefreshScrollDirection().ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return Math.round(((float) getWidth()) / 2.0f);
            default:
                return Math.round(((float) getHeight()) / 2.0f);
        }
    }

    private final void a(int i, long j, f fVar) {
        int scrollX;
        if (this.z != null) {
            Runnable runnable = this.z;
            runnable.a = false;
            runnable.b.removeCallbacks(runnable);
        }
        switch (AnonymousClass_1.a[getPullToRefreshScrollDirection().ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                scrollX = getScrollX();
                break;
            default:
                scrollX = getScrollY();
                break;
        }
        if (scrollX != i) {
            if (this.s == null) {
                this.s = new DecelerateInterpolator();
            }
            this.z = new g(scrollX, i, j, fVar);
            if (0 > 0) {
                postDelayed(this.z, 0);
            } else {
                post(this.z);
            }
        }
    }

    public void setScrollBar(int i) {
    }

    protected boolean i() {
        return false;
    }
}
