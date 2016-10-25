package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshBase.a;
import com.handmark.pulltorefresh.library.a.c;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public abstract class PullToRefreshAdapterViewBase<T extends AbsListView> extends PullToRefreshBase<T> implements OnScrollListener {
    public boolean a;
    private boolean h;
    private OnScrollListener i;
    private a j;
    private View k;
    private c l;
    private c m;
    private boolean n;
    private boolean o;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[Mode.values().length];
            try {
                a[Mode.PULL_FROM_END.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Mode.PULL_FROM_START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public PullToRefreshAdapterViewBase(Context context) {
        super(context);
        this.o = true;
        this.a = false;
        ((AbsListView) this.d).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.o = true;
        this.a = false;
        ((AbsListView) this.d).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, Mode mode) {
        super(context, mode);
        this.o = true;
        this.a = false;
        ((AbsListView) this.d).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, Mode mode, AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
        this.o = true;
        this.a = false;
        ((AbsListView) this.d).setOnScrollListener(this);
    }

    public boolean getShowIndicator() {
        return this.n;
    }

    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.j != null) {
            boolean z = i3 > 0 && i + i2 >= i3 - 2;
            this.h = z;
        }
        a();
        if (getShowIndicatorInternal()) {
            q();
        }
        if (this.i != null) {
            this.i.onScroll(absListView, i, i2, i3);
        }
    }

    public final void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 0 && this.j != null && this.h) {
            this.j.a();
        }
        if (this.i != null) {
            this.i.onScrollStateChanged(absListView, i);
        }
        if (this.a && !k() && ((AbsListView) this.d).getLastVisiblePosition() >= ((AbsListView) this.d).getCount() - 3 && this.f != null) {
            this.b = State.REFRESHING;
            this.f.onPullUpToRefresh(this);
        }
    }

    public void a() {
    }

    public void b() {
    }

    public void setAdapter(ListAdapter listAdapter) {
        ((AdapterView) this.d).setAdapter(listAdapter);
    }

    public void setAdapter(ExpandableListAdapter expandableListAdapter) {
        if (this.d instanceof ExpandableListView) {
            ((ExpandableListView) this.d).setAdapter(expandableListAdapter);
        }
    }

    public final void setEmptyView(View view) {
        FrameLayout refreshableViewWrapper = getRefreshableViewWrapper();
        if (view != null) {
            view.setClickable(true);
            ViewParent parent = view.getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(view);
            }
            LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams != null) {
                LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
                if (layoutParams instanceof LinearLayout.LayoutParams) {
                    layoutParams2.gravity = ((LinearLayout.LayoutParams) layoutParams).gravity;
                    layoutParams = layoutParams2;
                } else {
                    layoutParams2.gravity = 17;
                    layoutParams = layoutParams2;
                }
            } else {
                layoutParams = null;
            }
            if (layoutParams != null) {
                refreshableViewWrapper.addView(view, layoutParams);
            } else {
                refreshableViewWrapper.addView(view);
            }
        }
        if (this.d instanceof com.handmark.pulltorefresh.library.a.a) {
            ((com.handmark.pulltorefresh.library.a.a) this.d).setEmptyViewInternal(view);
        } else {
            ((AbsListView) this.d).setEmptyView(view);
        }
        this.k = view;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        ((AbsListView) this.d).setOnItemClickListener(onItemClickListener);
    }

    public final void setOnLastItemVisibleListener(a aVar) {
        this.j = aVar;
    }

    public final void setOnScrollListener(OnScrollListener onScrollListener) {
        this.i = onScrollListener;
    }

    public final void setScrollEmptyView(boolean z) {
        this.o = z;
    }

    public void setShowIndicator(boolean z) {
        this.n = z;
        if (getShowIndicatorInternal()) {
            o();
        } else {
            p();
        }
    }

    protected final void c() {
        super.c();
        if (getShowIndicatorInternal()) {
            switch (AnonymousClass_1.a[getCurrentMode().ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.m.e();
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    this.l.e();
                default:
                    break;
            }
        }
    }

    protected void a(boolean z) {
        super.a(z);
        if (getShowIndicatorInternal()) {
            q();
        }
    }

    protected final void d() {
        super.d();
        if (getShowIndicatorInternal()) {
            switch (AnonymousClass_1.a[getCurrentMode().ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.m.d();
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    this.l.d();
                default:
                    break;
            }
        }
    }

    protected void e() {
        super.e();
        if (getShowIndicatorInternal()) {
            q();
        }
    }

    protected void a(TypedArray typedArray) {
        this.n = typedArray.getBoolean(R.styleable.PullToRefresh_ptrShowIndicator, !j());
    }

    protected final boolean f() {
        Adapter adapter = ((AbsListView) this.d).getAdapter();
        if (adapter == null || adapter.isEmpty()) {
            return true;
        }
        if (((AbsListView) this.d).getFirstVisiblePosition() <= 1) {
            View childAt = ((AbsListView) this.d).getChildAt(0);
            if (childAt != null) {
                return childAt.getTop() >= ((AbsListView) this.d).getTop();
            }
        }
        return false;
    }

    protected final boolean g() {
        Adapter adapter = ((AbsListView) this.d).getAdapter();
        if (adapter == null || adapter.isEmpty()) {
            return true;
        }
        int count = ((AbsListView) this.d).getCount() - 1;
        int lastVisiblePosition = ((AbsListView) this.d).getLastVisiblePosition();
        if (lastVisiblePosition >= count - 1) {
            View childAt = ((AbsListView) this.d).getChildAt(lastVisiblePosition - ((AbsListView) this.d).getFirstVisiblePosition());
            if (childAt != null) {
                return childAt.getBottom() + -1 <= ((AbsListView) this.d).getBottom();
            }
        }
        return false;
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.k != null && !this.o) {
            this.k.scrollTo(-i, -i2);
        }
    }

    protected final void h() {
        super.h();
        if (getShowIndicatorInternal()) {
            o();
        } else {
            p();
        }
    }

    private void o() {
        Mode mode = getMode();
        FrameLayout refreshableViewWrapper = getRefreshableViewWrapper();
        if (mode.showHeaderLoadingLayout() && this.l == null) {
            this.l = new c(getContext(), Mode.PULL_FROM_START);
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.rightMargin = getResources().getDimensionPixelSize(R.dimen.indicator_right_padding);
            layoutParams.gravity = 53;
            refreshableViewWrapper.addView(this.l, layoutParams);
        } else if (!(mode.showHeaderLoadingLayout() || this.l == null)) {
            refreshableViewWrapper.removeView(this.l);
            this.l = null;
        }
        if (mode.showFooterLoadingLayout() && this.m == null) {
            this.m = new c(getContext(), Mode.PULL_FROM_END);
            LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
            layoutParams2.rightMargin = getResources().getDimensionPixelSize(R.dimen.indicator_right_padding);
            layoutParams2.gravity = 85;
            refreshableViewWrapper.addView(this.m, layoutParams2);
        } else if (!mode.showFooterLoadingLayout() && this.m != null) {
            refreshableViewWrapper.removeView(this.m);
            this.m = null;
        }
    }

    private boolean getShowIndicatorInternal() {
        return this.n && this.c.b();
    }

    private void p() {
        if (this.l != null) {
            getRefreshableViewWrapper().removeView(this.l);
            this.l = null;
        }
        if (this.m != null) {
            getRefreshableViewWrapper().removeView(this.m);
            this.m = null;
        }
    }

    private void q() {
        if (this.l != null) {
            if (k() || !f()) {
                if (this.l.a()) {
                    this.l.b();
                }
            } else if (!this.l.a()) {
                this.l.c();
            }
        }
        if (this.m == null) {
            return;
        }
        if (k() || !g()) {
            if (this.m.a()) {
                this.m.b();
            }
        } else if (!this.m.a()) {
            this.m.c();
        }
    }

    protected final boolean i() {
        int count = this.d == null ? 0 : ((ListAdapter) ((AbsListView) this.d).getAdapter()).getCount();
        if (getMode().showHeaderLoadingLayout()) {
            count--;
        }
        if (getMode().showFooterLoadingLayout()) {
            count--;
        }
        return count <= 0;
    }
}
