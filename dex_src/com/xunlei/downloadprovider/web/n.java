package com.xunlei.downloadprovider.web;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: DetailPageBrowserActivity.java
final class n implements OnClickListener {
    final /* synthetic */ DetailPageBrowserActivity a;

    n(DetailPageBrowserActivity detailPageBrowserActivity) {
        this.a = detailPageBrowserActivity;
    }

    public final void onClick(View view) {
        if (this.a.n == 44 || this.a.n == 46) {
            this.a.finish();
        } else {
            this.a.a();
        }
    }
}
