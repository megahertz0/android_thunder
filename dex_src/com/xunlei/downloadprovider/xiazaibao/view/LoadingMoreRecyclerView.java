package com.xunlei.downloadprovider.xiazaibao.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager.b;
import android.util.AttributeSet;

public class LoadingMoreRecyclerView extends RecyclerView {
    private static final String e;
    public boolean a;
    public boolean b;
    public int c;
    public int d;
    private boolean f;
    private a g;

    public static interface a {
        void a();
    }

    static {
        e = LoadingMoreRecyclerView.class.getSimpleName();
    }

    public final void a(boolean z) {
        this.b = false;
        this.a = z;
    }

    public LoadingMoreRecyclerView(Context context) {
        super(context);
    }

    public LoadingMoreRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setFootEnable(boolean z) {
        this.f = z;
        if (z && this.c <= this.d - 1) {
            scrollBy(0, -1);
        }
    }

    public void setIsLoadingMore(boolean z) {
        this.b = z;
    }

    public void setLoadMoreListener(a aVar) {
        this.g = aVar;
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
    }

    public void onScrolled(int i, int i2) {
        this.c = getLastVisiblePosition();
        this.d = getTotalCount();
        new StringBuilder("onScrolled lastVisiblePosition = ").append(this.c).append(" itemCount = ").append(this.d);
        if (!this.f || this.b || this.g == null || i2 == 0 || this.d - this.c > 1) {
            super.onScrolled(i, i2);
            return;
        }
        setIsLoadingMore(true);
        this.g.a();
    }

    private int getLastVisiblePosition() {
        if (getLayoutManager() instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) getLayoutManager()).k();
        }
        if (getLayoutManager() instanceof GridLayoutManager) {
            return ((GridLayoutManager) getLayoutManager()).k();
        }
        if (!(getLayoutManager() instanceof StaggeredGridLayoutManager)) {
            return getLayoutManager().s() - 1;
        }
        StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) getLayoutManager();
        int[] iArr = new int[staggeredGridLayoutManager.a];
        if (iArr.length < staggeredGridLayoutManager.a) {
            throw new IllegalArgumentException(new StringBuilder("Provided int[]'s size must be more than or equal to span count. Expected:").append(staggeredGridLayoutManager.a).append(", array size:").append(iArr.length).toString());
        }
        for (int i = 0; i < staggeredGridLayoutManager.a; i++) {
            int a;
            b bVar = staggeredGridLayoutManager.b[i];
            if (StaggeredGridLayoutManager.c(bVar.f)) {
                a = bVar.a(0, bVar.a.size());
            } else {
                a = bVar.a(bVar.a.size() - 1, -1);
            }
            iArr[i] = a;
        }
        return a(iArr);
    }

    private int getTotalCount() {
        return getLayoutManager().s();
    }

    private static int a(int[] iArr) {
        int i = Integer.MIN_VALUE;
        for (int i2 : iArr) {
            i = Math.max(i, i2);
        }
        return i;
    }
}
