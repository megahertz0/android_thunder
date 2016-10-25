package com.xunlei.downloadprovider.web.base;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: ShortMovieDetailActivity.java
final class ab implements OnClickListener {
    final /* synthetic */ ShortMovieDetailActivity a;

    ab(ShortMovieDetailActivity shortMovieDetailActivity) {
        this.a = shortMovieDetailActivity;
    }

    public final void onClick(View view) {
        this.a.onBackPressed();
    }
}
