package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import com.uc.addon.sdk.remote.Tabs;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.Method;
import org.android.spdy.SpdyAgent;

public class ListPopupWindow {
    private static Method a;
    private static Method b;
    private final c A;
    private Runnable B;
    private final Handler C;
    private Rect D;
    private boolean E;
    private int F;
    public PopupWindow c;
    public a d;
    int e;
    int f;
    int g;
    boolean h;
    public int i;
    int j;
    int k;
    public View l;
    public OnItemClickListener m;
    private Context n;
    private ListAdapter o;
    private int p;
    private int q;
    private boolean r;
    private boolean s;
    private View t;
    private DataSetObserver u;
    private Drawable v;
    private OnItemSelectedListener w;
    private final g x;
    private final f y;
    private final e z;

    public static abstract class b implements OnTouchListener {
        private final float a;
        private final int b;
        private final int c;
        private final View d;
        private Runnable e;
        private Runnable f;
        private boolean g;
        private boolean h;
        private int i;
        private final int[] j;

        private class a implements Runnable {
            private a() {
            }

            public final void run() {
                android.support.v7.widget.ListPopupWindow.b.this.d.getParent().requestDisallowInterceptTouchEvent(true);
            }
        }

        private class b implements Runnable {
            private b() {
            }

            public final void run() {
                android.support.v7.widget.ListPopupWindow.b.b(android.support.v7.widget.ListPopupWindow.b.this);
            }
        }

        public abstract ListPopupWindow a();

        static /* synthetic */ void b(android.support.v7.widget.ListPopupWindow.b bVar) {
            bVar.d();
            View view = bVar.d;
            if (view.isEnabled() && !view.isLongClickable() && bVar.b()) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, XZBDevice.DOWNLOAD_LIST_FAILED, AutoScrollHelper.RELATIVE_UNSPECIFIED, 0.0f, 0);
                view.onTouchEvent(obtain);
                obtain.recycle();
                bVar.g = true;
                bVar.h = true;
            }
        }

        public b(View view) {
            this.j = new int[2];
            this.d = view;
            this.a = (float) ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.b = ViewConfiguration.getTapTimeout();
            this.c = (this.b + ViewConfiguration.getLongPressTimeout()) / 2;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            boolean a;
            boolean z = this.g;
            if (z) {
                a = this.h ? a(motionEvent) : a(motionEvent) || !c();
            } else {
                boolean z2;
                long uptimeMillis;
                MotionEvent obtain;
                View view2 = this.d;
                if (view2.isEnabled()) {
                    switch (MotionEventCompat.getActionMasked(motionEvent)) {
                        case SpdyAgent.ACCS_TEST_SERVER:
                            this.i = motionEvent.getPointerId(0);
                            this.h = false;
                            if (this.e == null) {
                                this.e = new a();
                            }
                            view2.postDelayed(this.e, (long) this.b);
                            if (this.f == null) {
                                this.f = new b();
                            }
                            view2.postDelayed(this.f, (long) this.c);
                            break;
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                            d();
                            break;
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            int findPointerIndex = motionEvent.findPointerIndex(this.i);
                            if (findPointerIndex >= 0) {
                                float x = motionEvent.getX(findPointerIndex);
                                float y = motionEvent.getY(findPointerIndex);
                                float f = this.a;
                                if (x < (-f) || y < (-f) || x >= ((float) (view2.getRight() - view2.getLeft())) + f || y >= ((float) (view2.getBottom() - view2.getTop())) + f) {
                                    a = false;
                                } else {
                                    a = true;
                                }
                                if (!a) {
                                    d();
                                    view2.getParent().requestDisallowInterceptTouchEvent(true);
                                    a = true;
                                    z2 = a && b();
                                    if (z2) {
                                        uptimeMillis = SystemClock.uptimeMillis();
                                        obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, XZBDevice.DOWNLOAD_LIST_FAILED, AutoScrollHelper.RELATIVE_UNSPECIFIED, 0.0f, 0);
                                        this.d.onTouchEvent(obtain);
                                        obtain.recycle();
                                    }
                                    a = z2;
                                }
                            }
                            break;
                    }
                }
                a = false;
                if (!a) {
                }
                if (z2) {
                    uptimeMillis = SystemClock.uptimeMillis();
                    obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, XZBDevice.DOWNLOAD_LIST_FAILED, AutoScrollHelper.RELATIVE_UNSPECIFIED, 0.0f, 0);
                    this.d.onTouchEvent(obtain);
                    obtain.recycle();
                }
                a = z2;
            }
            this.g = a;
            return a || z;
        }

        public boolean b() {
            ListPopupWindow a = a();
            if (!(a == null || a.c.isShowing())) {
                a.b();
            }
            return true;
        }

        protected boolean c() {
            ListPopupWindow a = a();
            if (a != null && a.c.isShowing()) {
                a.d();
            }
            return true;
        }

        private void d() {
            if (this.f != null) {
                this.d.removeCallbacks(this.f);
            }
            if (this.e != null) {
                this.d.removeCallbacks(this.e);
            }
        }

        private boolean a(MotionEvent motionEvent) {
            View view = this.d;
            ListPopupWindow a = a();
            if (a == null || !a.c.isShowing()) {
                return false;
            }
            View a2 = a.d;
            if (a2 == null || !a2.isShown()) {
                return false;
            }
            boolean z;
            MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
            int[] iArr = this.j;
            view.getLocationOnScreen(iArr);
            obtainNoHistory.offsetLocation((float) iArr[0], (float) iArr[1]);
            int[] iArr2 = this.j;
            a2.getLocationOnScreen(iArr2);
            obtainNoHistory.offsetLocation((float) (-iArr2[0]), (float) (-iArr2[1]));
            boolean a3 = a2.a(obtainNoHistory, this.i);
            obtainNoHistory.recycle();
            int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
            if (actionMasked == 1 || actionMasked == 3) {
                actionMasked = 0;
            } else {
                z = true;
            }
            return a3 && z;
        }
    }

    private static class a extends ListViewCompat {
        private boolean h;
        private boolean i;
        private boolean j;
        private ViewPropertyAnimatorCompat k;
        private ListViewAutoScrollHelper l;

        public a(Context context, boolean z) {
            super(context, null, R.attr.dropDownListViewStyle);
            this.i = z;
            setCacheColorHint(0);
        }

        public final boolean a(MotionEvent motionEvent, int i) {
            boolean z;
            View childAt;
            Object obj = 1;
            int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
            int findPointerIndex;
            int x;
            int pointToPosition;
            boolean z2;
            int i2;
            View childAt2;
            float f;
            float f2;
            float left;
            float top;
            Drawable selector;
            Object obj2;
            Rect rect;
            float exactCenterY;
            Drawable selector2;
            switch (actionMasked) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    obj = null;
                    findPointerIndex = motionEvent.findPointerIndex(i);
                    if (findPointerIndex >= 0) {
                        z = false;
                        obj = null;
                    } else {
                        x = (int) motionEvent.getX(findPointerIndex);
                        findPointerIndex = (int) motionEvent.getY(findPointerIndex);
                        pointToPosition = pointToPosition(x, findPointerIndex);
                        if (pointToPosition != -1) {
                            z = z2;
                            i2 = 1;
                        } else {
                            childAt2 = getChildAt(pointToPosition - getFirstVisiblePosition());
                            f = (float) x;
                            f2 = (float) findPointerIndex;
                            this.j = true;
                            if (VERSION.SDK_INT >= 21) {
                                drawableHotspotChanged(f, f2);
                            }
                            if (!isPressed()) {
                                setPressed(true);
                            }
                            layoutChildren();
                            if (this.f != -1) {
                                childAt = getChildAt(this.f - getFirstVisiblePosition());
                                if (!(childAt == null || childAt == childAt2 || !childAt.isPressed())) {
                                    childAt.setPressed(false);
                                }
                            }
                            this.f = pointToPosition;
                            left = f - ((float) childAt2.getLeft());
                            top = f2 - ((float) childAt2.getTop());
                            if (VERSION.SDK_INT >= 21) {
                                childAt2.drawableHotspotChanged(left, top);
                            }
                            if (!childAt2.isPressed()) {
                                childAt2.setPressed(true);
                            }
                            selector = getSelector();
                            if (selector != null || pointToPosition == -1) {
                                obj2 = null;
                            } else {
                                findPointerIndex = 1;
                            }
                            if (obj2 != null) {
                                selector.setVisible(false, false);
                            }
                            rect = this.a;
                            rect.set(childAt2.getLeft(), childAt2.getTop(), childAt2.getRight(), childAt2.getBottom());
                            rect.left -= this.b;
                            rect.top -= this.c;
                            rect.right += this.d;
                            rect.bottom += this.e;
                            try {
                                z2 = this.g.getBoolean(this);
                                if (childAt2.isEnabled() != z2) {
                                    this.g.set(this, Boolean.valueOf(z2));
                                    if (pointToPosition != -1) {
                                        refreshDrawableState();
                                    }
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            if (obj2 != null) {
                                rect = this.a;
                                top = rect.exactCenterX();
                                exactCenterY = rect.exactCenterY();
                                selector.setVisible(getVisibility() != 0, false);
                                DrawableCompat.setHotspot(selector, top, exactCenterY);
                            }
                            selector2 = getSelector();
                            if (!(selector2 == null || pointToPosition == -1)) {
                                DrawableCompat.setHotspot(selector2, f, f2);
                            }
                            setSelectorEnabled(false);
                            refreshDrawableState();
                            obj = 1;
                            if (actionMasked == 1) {
                                performItemClick(childAt2, pointToPosition, getItemIdAtPosition(pointToPosition));
                            }
                            z = z2;
                            obj = null;
                        }
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    findPointerIndex = motionEvent.findPointerIndex(i);
                    if (findPointerIndex >= 0) {
                        x = (int) motionEvent.getX(findPointerIndex);
                        findPointerIndex = (int) motionEvent.getY(findPointerIndex);
                        pointToPosition = pointToPosition(x, findPointerIndex);
                        if (pointToPosition != -1) {
                            childAt2 = getChildAt(pointToPosition - getFirstVisiblePosition());
                            f = (float) x;
                            f2 = (float) findPointerIndex;
                            this.j = true;
                            if (VERSION.SDK_INT >= 21) {
                                drawableHotspotChanged(f, f2);
                            }
                            if (isPressed()) {
                                setPressed(true);
                            }
                            layoutChildren();
                            if (this.f != -1) {
                                childAt = getChildAt(this.f - getFirstVisiblePosition());
                                childAt.setPressed(false);
                            }
                            this.f = pointToPosition;
                            left = f - ((float) childAt2.getLeft());
                            top = f2 - ((float) childAt2.getTop());
                            if (VERSION.SDK_INT >= 21) {
                                childAt2.drawableHotspotChanged(left, top);
                            }
                            if (childAt2.isPressed()) {
                                childAt2.setPressed(true);
                            }
                            selector = getSelector();
                            if (selector != null) {
                            }
                            obj2 = null;
                            if (obj2 != null) {
                                selector.setVisible(false, false);
                            }
                            rect = this.a;
                            rect.set(childAt2.getLeft(), childAt2.getTop(), childAt2.getRight(), childAt2.getBottom());
                            rect.left -= this.b;
                            rect.top -= this.c;
                            rect.right += this.d;
                            rect.bottom += this.e;
                            z2 = this.g.getBoolean(this);
                            if (childAt2.isEnabled() != z2) {
                                if (z2) {
                                }
                                this.g.set(this, Boolean.valueOf(z2));
                                if (pointToPosition != -1) {
                                    refreshDrawableState();
                                }
                            }
                            if (obj2 != null) {
                                rect = this.a;
                                top = rect.exactCenterX();
                                exactCenterY = rect.exactCenterY();
                                if (getVisibility() != 0) {
                                }
                                selector.setVisible(getVisibility() != 0, false);
                                DrawableCompat.setHotspot(selector, top, exactCenterY);
                            }
                            selector2 = getSelector();
                            DrawableCompat.setHotspot(selector2, f, f2);
                            setSelectorEnabled(false);
                            refreshDrawableState();
                            obj = 1;
                            if (actionMasked == 1) {
                                performItemClick(childAt2, pointToPosition, getItemIdAtPosition(pointToPosition));
                            }
                            z = z2;
                            obj = null;
                        } else {
                            z = z2;
                            i2 = 1;
                        }
                    } else {
                        z = false;
                        obj = null;
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    z = false;
                    obj = null;
                    break;
                default:
                    z = z2;
                    obj = null;
                    break;
            }
            if (!(z && r0 == null)) {
                this.j = false;
                setPressed(false);
                drawableStateChanged();
                childAt = getChildAt(this.f - getFirstVisiblePosition());
                if (childAt != null) {
                    childAt.setPressed(false);
                }
                if (this.k != null) {
                    this.k.cancel();
                    this.k = null;
                }
            }
            if (z) {
                if (this.l == null) {
                    this.l = new ListViewAutoScrollHelper(this);
                }
                this.l.setEnabled(true);
                this.l.onTouch(this, motionEvent);
            } else if (this.l != null) {
                this.l.setEnabled(false);
            }
            return z;
        }

        protected final boolean a() {
            return this.j || super.a();
        }

        public final boolean isInTouchMode() {
            return (this.i && this.h) || super.isInTouchMode();
        }

        public final boolean hasWindowFocus() {
            return this.i || super.hasWindowFocus();
        }

        public final boolean isFocused() {
            return this.i || super.isFocused();
        }

        public final boolean hasFocus() {
            return this.i || super.hasFocus();
        }
    }

    private class c implements Runnable {
        private c() {
        }

        public final void run() {
            ListPopupWindow.this.f();
        }
    }

    private class d extends DataSetObserver {
        private d() {
        }

        public final void onChanged() {
            if (ListPopupWindow.this.c.isShowing()) {
                ListPopupWindow.this.b();
            }
        }

        public final void onInvalidated() {
            ListPopupWindow.this.d();
        }
    }

    private class e implements OnScrollListener {
        private e() {
        }

        public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        public final void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 1 && !ListPopupWindow.this.g() && ListPopupWindow.this.c.getContentView() != null) {
                ListPopupWindow.this.C.removeCallbacks(ListPopupWindow.this.x);
                ListPopupWindow.this.x.run();
            }
        }
    }

    private class f implements OnTouchListener {
        private f() {
        }

        public final boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && ListPopupWindow.this.c != null && ListPopupWindow.this.c.isShowing() && x >= 0 && x < ListPopupWindow.this.c.getWidth() && y >= 0 && y < ListPopupWindow.this.c.getHeight()) {
                ListPopupWindow.this.C.postDelayed(ListPopupWindow.this.x, 250);
            } else if (action == 1) {
                ListPopupWindow.this.C.removeCallbacks(ListPopupWindow.this.x);
            }
            return false;
        }
    }

    private class g implements Runnable {
        private g() {
        }

        public final void run() {
            if (ListPopupWindow.this.d != null && ViewCompat.isAttachedToWindow(ListPopupWindow.this.d) && ListPopupWindow.this.d.getCount() > ListPopupWindow.this.d.getChildCount() && ListPopupWindow.this.d.getChildCount() <= ListPopupWindow.this.j) {
                ListPopupWindow.this.c.setInputMethodMode(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                ListPopupWindow.this.b();
            }
        }
    }

    static {
        try {
            a = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", new Class[]{Boolean.TYPE});
        } catch (NoSuchMethodException e) {
        }
        try {
            b = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", new Class[]{View.class, Integer.TYPE, Boolean.TYPE});
        } catch (NoSuchMethodException e2) {
        }
    }

    public ListPopupWindow(Context context) {
        this(context, null, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        this.p = -2;
        this.e = -2;
        this.q = 1002;
        this.i = 0;
        this.r = false;
        this.s = false;
        this.j = Integer.MAX_VALUE;
        this.k = 0;
        this.x = new g();
        this.y = new f();
        this.z = new e();
        this.A = new c();
        this.D = new Rect();
        this.n = context;
        this.C = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ListPopupWindow, i, i2);
        this.f = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.g = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.g != 0) {
            this.h = true;
        }
        obtainStyledAttributes.recycle();
        this.c = new AppCompatPopupWindow(context, attributeSet, i);
        this.c.setInputMethodMode(1);
        this.F = TextUtilsCompat.getLayoutDirectionFromLocale(this.n.getResources().getConfiguration().locale);
    }

    public void a(ListAdapter listAdapter) {
        if (this.u == null) {
            this.u = new d();
        } else if (this.o != null) {
            this.o.unregisterDataSetObserver(this.u);
        }
        this.o = listAdapter;
        if (this.o != null) {
            listAdapter.registerDataSetObserver(this.u);
        }
        if (this.d != null) {
            this.d.setAdapter(this.o);
        }
    }

    public final void c() {
        this.E = true;
        this.c.setFocusable(true);
    }

    public final void a(Drawable drawable) {
        this.c.setBackgroundDrawable(drawable);
    }

    public final void a(int i) {
        Drawable background = this.c.getBackground();
        if (background != null) {
            background.getPadding(this.D);
            this.e = (this.D.left + this.D.right) + i;
            return;
        }
        this.e = i;
    }

    public void b() {
        int i;
        int i2;
        boolean z;
        int makeMeasureSpec;
        boolean z2 = true;
        LayoutParams layoutParams;
        View view;
        if (this.d == null) {
            Context context = this.n;
            this.B = new au(this);
            this.d = new a(context, !this.E);
            if (this.v != null) {
                this.d.setSelector(this.v);
            }
            this.d.setAdapter(this.o);
            this.d.setOnItemClickListener(this.m);
            this.d.setFocusable(true);
            this.d.setFocusableInTouchMode(true);
            this.d.setOnItemSelectedListener(new av(this));
            this.d.setOnScrollListener(this.z);
            if (this.w != null) {
                this.d.setOnItemSelectedListener(this.w);
            }
            View view2 = this.d;
            View view3 = this.t;
            if (view3 != null) {
                View linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, 0, 1.0f);
                switch (this.k) {
                    case SpdyAgent.ACCS_TEST_SERVER:
                        linearLayout.addView(view3);
                        linearLayout.addView(view2, layoutParams2);
                        break;
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        linearLayout.addView(view2, layoutParams2);
                        linearLayout.addView(view3);
                        break;
                    default:
                        new StringBuilder("Invalid hint position ").append(this.k);
                        break;
                }
                if (this.e >= 0) {
                    i = this.e;
                    i2 = Integer.MIN_VALUE;
                } else {
                    i2 = 0;
                    i = 0;
                }
                view3.measure(MeasureSpec.makeMeasureSpec(i, i2), 0);
                layoutParams = (LayoutParams) view3.getLayoutParams();
                i2 = layoutParams.bottomMargin + (view3.getMeasuredHeight() + layoutParams.topMargin);
                view = linearLayout;
            } else {
                view = view2;
                i2 = 0;
            }
            this.c.setContentView(view);
        } else {
            this.c.getContentView();
            view = this.t;
            if (view != null) {
                layoutParams = (LayoutParams) view.getLayoutParams();
                i2 = layoutParams.bottomMargin + (view.getMeasuredHeight() + layoutParams.topMargin);
            } else {
                i2 = 0;
            }
        }
        Drawable background = this.c.getBackground();
        if (background != null) {
            background.getPadding(this.D);
            i = this.D.top + this.D.bottom;
            if (!this.h) {
                this.g = -this.D.top;
            }
        } else {
            this.D.setEmpty();
            i = 0;
        }
        if (this.c.getInputMethodMode() == 2) {
            z = true;
        } else {
            z = false;
        }
        int a = a(this.l, this.g, z);
        if (this.r || this.p == -1) {
            i2 = a + i;
        } else {
            switch (this.e) {
                case Tabs.TAB_CREATE_REACH_MAX_COUNT:
                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.n.getResources().getDisplayMetrics().widthPixels - (this.D.left + this.D.right), ExploreByTouchHelper.INVALID_ID);
                    break;
                case SniffingResourceGroup.PAGETYPE_NONE:
                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.n.getResources().getDisplayMetrics().widthPixels - (this.D.left + this.D.right), 1073741824);
                    break;
                default:
                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.e, 1073741824);
                    break;
            }
            makeMeasureSpec = this.d.a(makeMeasureSpec, a - i2);
            if (makeMeasureSpec > 0) {
                i2 += i;
            }
            i2 += makeMeasureSpec;
        }
        z = g();
        PopupWindowCompat.setWindowLayoutType(this.c, this.q);
        if (this.c.isShowing()) {
            if (this.e == -1) {
                i = -1;
            } else if (this.e == -2) {
                i = this.l.getWidth();
            } else {
                i = this.e;
            }
            if (this.p == -1) {
                if (z) {
                    makeMeasureSpec = i2;
                } else {
                    makeMeasureSpec = -1;
                }
                PopupWindow popupWindow;
                if (z) {
                    popupWindow = this.c;
                    if (this.e == -1) {
                        i2 = -1;
                    } else {
                        i2 = 0;
                    }
                    popupWindow.setWidth(i2);
                    this.c.setHeight(0);
                    a = makeMeasureSpec;
                } else {
                    popupWindow = this.c;
                    if (this.e == -1) {
                        i2 = -1;
                    } else {
                        i2 = 0;
                    }
                    popupWindow.setWidth(i2);
                    this.c.setHeight(-1);
                    a = makeMeasureSpec;
                }
            } else if (this.p != -2) {
                a = this.p;
            } else {
                a = i2;
            }
            PopupWindow popupWindow2 = this.c;
            if (this.s || this.r) {
                z2 = false;
            }
            popupWindow2.setOutsideTouchable(z2);
            popupWindow2 = this.c;
            View view4 = this.l;
            int i3 = this.f;
            makeMeasureSpec = this.g;
            if (i < 0) {
                i = -1;
            }
            if (a < 0) {
                a = -1;
            }
            popupWindow2.update(view4, i3, makeMeasureSpec, i, a);
            return;
        }
        if (this.e == -1) {
            makeMeasureSpec = -1;
        } else if (this.e == -2) {
            makeMeasureSpec = this.l.getWidth();
        } else {
            makeMeasureSpec = this.e;
        }
        if (this.p == -1) {
            i2 = -1;
        } else if (this.p != -2) {
            i2 = this.p;
        }
        this.c.setWidth(makeMeasureSpec);
        this.c.setHeight(i2);
        if (a != null) {
            try {
                a.invoke(this.c, new Object[]{Boolean.valueOf(true)});
            } catch (Exception e) {
            }
        }
        popupWindow2 = this.c;
        if (this.s || this.r) {
            z2 = false;
        }
        popupWindow2.setOutsideTouchable(z2);
        this.c.setTouchInterceptor(this.y);
        PopupWindowCompat.showAsDropDown(this.c, this.l, this.f, this.g, this.i);
        this.d.setSelection(-1);
        if (!this.E || this.d.isInTouchMode()) {
            f();
        }
        if (!this.E) {
            this.C.post(this.A);
        }
    }

    public final void d() {
        this.c.dismiss();
        if (this.t != null) {
            ViewParent parent = this.t.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.t);
            }
        }
        this.c.setContentView(null);
        this.d = null;
        this.C.removeCallbacks(this.x);
    }

    public final void a(OnDismissListener onDismissListener) {
        this.c.setOnDismissListener(onDismissListener);
    }

    public final void e() {
        this.c.setInputMethodMode(XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }

    public final void f() {
        a aVar = this.d;
        if (aVar != null) {
            aVar.h = true;
            aVar.requestLayout();
        }
    }

    public final boolean g() {
        return this.c.getInputMethodMode() == 2;
    }

    private int a(View view, int i, boolean z) {
        if (b != null) {
            try {
                return ((Integer) b.invoke(this.c, new Object[]{view, Integer.valueOf(i), Boolean.valueOf(z)})).intValue();
            } catch (Exception e) {
            }
        }
        return this.c.getMaxAvailableHeight(view, i);
    }
}
