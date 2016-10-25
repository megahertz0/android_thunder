package com.xunlei.downloadprovider.web.record;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

// compiled from: RecordPageView.java
final class v implements OnScrollListener {
    final /* synthetic */ RecordPageView a;

    v(RecordPageView recordPageView) {
        this.a = recordPageView;
    }

    public final void onScrollStateChanged(AbsListView absListView, int i) {
        if (i != 2) {
            RecordPageView.a(this.a, false);
            RecordPageView.c(this.a).notifyDataSetChanged();
            return;
        }
        RecordPageView.a(this.a, true);
    }

    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }
}
