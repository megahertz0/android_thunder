package com.xunlei.downloadprovider.web.base;

import android.support.v4.widget.AutoScrollHelper;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

// compiled from: KandanListActivity.java
final class j implements OnScrollListener {
    final /* synthetic */ KandanListActivity a;

    j(KandanListActivity kandanListActivity) {
        this.a = kandanListActivity;
    }

    public final void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
        float f = 1.0f;
        if (this.a.h) {
            KandanListActivity.a(this.a, 1.0f);
            return;
        }
        int d = KandanListActivity.d(this.a);
        KandanListActivity.a;
        int height = this.a.q.getHeight() - this.a.c.getHeight();
        int i4 = height / 2;
        if (d < height) {
            f = d >= i4 ? 1.0f - (((float) (height - d)) / ((float) (height - i4))) : AutoScrollHelper.RELATIVE_UNSPECIFIED;
        }
        KandanListActivity.a(this.a, f);
    }
}
