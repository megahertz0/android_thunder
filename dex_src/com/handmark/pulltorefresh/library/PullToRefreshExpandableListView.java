package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.a.d;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class PullToRefreshExpandableListView extends PullToRefreshAdapterViewBase<ExpandableListView> {
    private boolean h;
    private d i;
    private FrameLayout j;
    private d k;

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

    class a extends ExpandableListView implements com.handmark.pulltorefresh.library.a.a {
        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void setEmptyView(View view) {
            PullToRefreshExpandableListView.this.setEmptyView(view);
        }

        public void setEmptyViewInternal(View view) {
            super.setEmptyView(view);
        }

        protected void onDetachedFromWindow() {
            try {
                super.onDetachedFromWindow();
            } catch (IllegalArgumentException e) {
            }
        }

        public void smoothScrollToPosition(int i, int i2) {
        }
    }

    @TargetApi(9)
    final class b extends a {
        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        protected final boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            c.a(PullToRefreshExpandableListView.this, i, i3, i2, i4, z);
            return overScrollBy;
        }
    }

    public PullToRefreshExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    protected final void a(boolean z) {
        ExpandableListAdapter expandableListAdapter = ((ExpandableListView) this.d).getExpandableListAdapter();
        if (!this.h || !getShowViewWhileRefreshing() || expandableListAdapter == null || expandableListAdapter.isEmpty()) {
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
                dVar = this.k;
                dVar2 = this.i;
                count = ((ExpandableListView) this.d).getCount() - 1;
                scrollY = getScrollY() - getFooterSize();
                break;
            default:
                dVar = getHeaderLayout();
                dVar2 = this.i;
                scrollY = getScrollY() + getHeaderSize();
                footerLayout = dVar;
                dVar = dVar2;
                dVar2 = this.k;
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
            ((ExpandableListView) this.d).setSelection(count);
            a(0);
        }
    }

    protected final void e() {
        int i = 0;
        Object obj = 1;
        if (this.h) {
            int i2;
            int i3;
            d dVar;
            int count;
            switch (AnonymousClass_1.a[getCurrentMode().ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    getFooterLayout();
                    d dVar2 = this.k;
                    count = ((ExpandableListView) this.d).getCount() - 1;
                    int footerSize = getFooterSize();
                    if (Math.abs(((ExpandableListView) this.d).getLastVisiblePosition() - count) <= 1) {
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
                    d dVar3 = this.i;
                    count = -getHeaderSize();
                    if (Math.abs(((ExpandableListView) this.d).getFirstVisiblePosition() + 0) > 1) {
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
                    ((ExpandableListView) this.d).setSelection(i);
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
        if (this.h) {
            Mode mode = getMode();
            if (z && mode.showHeaderLoadingLayout()) {
                a.a(this.i);
            }
            if (z2 && mode.showFooterLoadingLayout()) {
                a.a(this.k);
            }
        }
        return a;
    }

    protected final void a(TypedArray typedArray) {
        super.a(typedArray);
        this.h = typedArray.getBoolean(R.styleable.PullToRefresh_ptrListViewExtrasEnabled, true);
        if (this.h) {
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 1);
            View frameLayout = new FrameLayout(getContext());
            this.i = a(getContext(), Mode.PULL_FROM_START, typedArray);
            this.i.setVisibility(XZBDevice.Wait);
            frameLayout.addView(this.i, layoutParams);
            ((ExpandableListView) this.d).addHeaderView(frameLayout, null, false);
            this.j = new FrameLayout(getContext());
            this.k = a(getContext(), Mode.PULL_FROM_END, typedArray);
            this.k.setVisibility(XZBDevice.Wait);
            this.j.addView(this.k, layoutParams);
            if (!typedArray.hasValue(R.styleable.PullToRefresh_ptrScrollingWhileRefreshingEnabled)) {
                setScrollingWhileRefreshingEnabled(true);
            }
        }
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
