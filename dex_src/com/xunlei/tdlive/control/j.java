package com.xunlei.tdlive.control;

import android.database.DataSetObserver;

// compiled from: HorizontialListView.java
class j extends DataSetObserver {
    final /* synthetic */ HorizontialListView a;

    j(HorizontialListView horizontialListView) {
        this.a = horizontialListView;
    }

    public void onChanged() {
        synchronized (this.a) {
            this.a.m = true;
        }
        this.a.invalidate();
        this.a.requestLayout();
    }

    public void onInvalidated() {
        this.a.b();
        this.a.invalidate();
        this.a.requestLayout();
    }
}
