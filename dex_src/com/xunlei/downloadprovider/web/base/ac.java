package com.xunlei.downloadprovider.web.base;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: ShortMovieDetailActivity.java
final class ac implements OnClickListener {
    final /* synthetic */ ShortMovieDetailActivity a;

    ac(ShortMovieDetailActivity shortMovieDetailActivity) {
        this.a = shortMovieDetailActivity;
    }

    public final void onClick(View view) {
        bk.c("detail_shortvideo_top");
        ShortMovieDetailActivity.a(this.a).a("detail_shortvideo_top");
    }
}
