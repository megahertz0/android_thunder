package com.xunlei.tdlive.a;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

// compiled from: RecordAdapter.java
class ab implements OnScrollListener {
    final /* synthetic */ x a;
    private int b;

    ab(x xVar) {
        this.a = xVar;
        this.b = 0;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.b = i;
        if (this.b != 0) {
            x.a(this.a, System.currentTimeMillis());
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.b != 0) {
            x.a(this.a, x.a(this.a).getFirstVisiblePosition(), x.a(this.a).getLastVisiblePosition());
        }
    }
}
