package com.xunlei.downloadprovider.search.ui.widget;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: SearchBannerLayout.java
final class e implements OnClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ int b;
    final /* synthetic */ SearchBannerLayout c;

    e(SearchBannerLayout searchBannerLayout, View view, int i) {
        this.c = searchBannerLayout;
        this.a = view;
        this.b = i;
    }

    public final void onClick(View view) {
        if (this.c.f != null) {
            this.c.f.onItemClick(null, this.a, this.b, (long) this.c.getId());
        }
    }
}
