package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.a.d;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class PullToRefreshListView extends PullToRefreshAdapterViewBase<ListView> {
    private d h;
    private d i;
    private FrameLayout j;
    private View k;
    private boolean l;
    private boolean m;
    private boolean n;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[Mode.values().length];
            try {
                a[Mode.MANUAL_REFRESH_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Mode.PULL_FROM_END.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[Mode.PULL_FROM_START.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    protected class a extends ListView implements com.handmark.pulltorefresh.library.a.a {
        private boolean b;

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = false;
        }

        public void onRestoreInstanceState(Parcelable parcelable) {
            super.onRestoreInstanceState(onSaveInstanceState());
        }

        public Parcelable onSaveInstanceState() {
            return super.onSaveInstanceState();
        }

        protected void dispatchDraw(Canvas canvas) {
            try {
                super.dispatchDraw(canvas);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            try {
                return super.dispatchTouchEvent(motionEvent);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        public void setAdapter(ListAdapter listAdapter) {
            if (!(PullToRefreshListView.this.j == null || this.b)) {
                addFooterView(PullToRefreshListView.this.j, null, false);
                this.b = true;
            }
            super.setAdapter(listAdapter);
        }

        public void setEmptyView(View view) {
            PullToRefreshListView.this.setEmptyView(view);
        }

        public void setEmptyViewInternal(View view) {
            super.setEmptyView(view);
        }
    }

    @TargetApi(9)
    final class b extends a {
        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        protected final boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            c.a(PullToRefreshListView.this, i, i3, i2, i4, z);
            return overScrollBy;
        }
    }

    public PullToRefreshListView(Context context) {
        super(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullToRefreshListView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshListView(Context context, Mode mode, AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }

    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    protected final void a(boolean z) {
        ListAdapter adapter = ((ListView) this.d).getAdapter();
        if (!this.l || !getShowViewWhileRefreshing() || adapter == null || adapter.isEmpty()) {
            super.a(z);
            return;
        }
        d footerLayout;
        d dVar;
        d dVar2;
        int count;
        int scrollY;
        super.a(false);
        switch (AnonymousClass_1.a[getCurrentMode().ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                footerLayout = getFooterLayout();
                dVar = this.i;
                dVar2 = this.h;
                count = ((ListView) this.d).getCount() - 1;
                scrollY = getScrollY() - getFooterSize();
                break;
            default:
                dVar = getHeaderLayout();
                dVar2 = this.h;
                scrollY = getScrollY() + getHeaderSize();
                footerLayout = dVar;
                dVar = dVar2;
                dVar2 = this.i;
                count = 0;
                break;
        }
        footerLayout.f();
        d.c();
        dVar2.setVisibility(XZBDevice.Wait);
        dVar.setVisibility(0);
        dVar.e();
        if (z) {
            this.e = false;
            setScrollValue(scrollY);
            ((ListView) this.d).setSelection(count);
            a(0);
        }
    }

    protected final void e() {
        int i = 0;
        Object obj = 1;
        if (this.l) {
            int i2;
            int i3;
            d dVar;
            int count;
            switch (AnonymousClass_1.a[getCurrentMode().ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    getFooterLayout();
                    d dVar2 = this.i;
                    count = ((ListView) this.d).getCount() - 1;
                    int footerSize = getFooterSize();
                    if (Math.abs(((ListView) this.d).getLastVisiblePosition() - count) <= 1) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    i = count;
                    i3 = footerSize;
                    dVar = dVar2;
                    break;
                default:
                    getHeaderLayout();
                    d dVar3 = this.h;
                    count = -getHeaderSize();
                    if (Math.abs(((ListView) this.d).getFirstVisiblePosition() + 0) > 1) {
                        i3 = 0;
                    }
                    i2 = i3;
                    i3 = count;
                    dVar = dVar3;
                    break;
            }
            if (dVar.getVisibility() == 0) {
                d.g();
                dVar.setVisibility(XZBDevice.Wait);
                if (!(i2 == 0 || getState() == State.MANUAL_REFRESHING)) {
                    ((ListView) this.d).setSelection(i);
                    setScrollValue(i3);
                }
            }
            super.e();
            return;
        }
        super.e();
    }

    protected final b a(boolean z, boolean z2) {
        b a = super.a(z, z2);
        if (this.l) {
            Mode mode = getMode();
            if (z && mode.showHeaderLoadingLayout()) {
                a.a(this.h);
            }
            if (z2 && mode.showFooterLoadingLayout()) {
                a.a(this.i);
            }
        }
        return a;
    }

    public void setSelection(int i) {
        if (getRefreshableView() instanceof ListView) {
            ((ListView) getRefreshableView()).setSelection(i);
        }
    }

    public final void o() {
        if (l()) {
            ((ListView) getRefreshableView()).setSelection(0);
            postDelayed(new g(this), 300);
        }
    }

    public void setSelector(int i) {
        if (getRefreshableView() instanceof ListView) {
            ((ListView) getRefreshableView()).setSelector(i);
        }
    }

    public int getLastVisiblePosition() {
        return getRefreshableView() instanceof ListView ? ((ListView) getRefreshableView()).getLastVisiblePosition() : -1;
    }

    public int getCount() {
        return ((ListView) getRefreshableView()).getCount();
    }

    public d getHeaderLoadingView() {
        return this.h;
    }

    public d getFooterLoadingView() {
        return this.i;
    }

    protected final void a(TypedArray typedArray) {
        super.a(typedArray);
        this.l = typedArray.getBoolean(R.styleable.PullToRefresh_ptrListViewExtrasEnabled, true);
        if (this.l) {
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 1);
            View frameLayout = new FrameLayout(getContext());
            this.h = a(getContext(), Mode.PULL_FROM_START, typedArray);
            this.h.setVisibility(XZBDevice.Wait);
            frameLayout.addView(this.h, layoutParams);
            ((ListView) this.d).addHeaderView(frameLayout, null, false);
            this.i = a(getContext(), Mode.PULL_FROM_END, typedArray);
            this.k = LayoutInflater.from(getContext()).inflate(R.layout.common_pull_up_refresh_item, null);
            if (!typedArray.hasValue(R.styleable.PullToRefresh_ptrScrollingWhileRefreshingEnabled)) {
                setScrollingWhileRefreshingEnabled(true);
            }
        }
    }

    public void setFooterNeedShow(boolean z) {
        this.m = z;
        if (z) {
            a();
        } else {
            b();
        }
    }

    public final void a() {
        if (this.m && !this.n) {
            ((ListView) this.d).addFooterView(this.k, null, false);
            this.n = true;
        }
    }

    public final void b() {
        if (this.n) {
            ((ListView) this.d).removeFooterView(this.k);
            this.n = false;
        }
    }

    public final void m() {
        super.m();
        b();
    }

    protected final /* synthetic */ View a(Context context, AttributeSet attributeSet) {
        View bVar;
        if (VERSION.SDK_INT >= 9) {
            bVar = new b(context, attributeSet);
        } else {
            bVar = new a(context, attributeSet);
        }
        bVar.setId(16908298);
        return bVar;
    }
}
