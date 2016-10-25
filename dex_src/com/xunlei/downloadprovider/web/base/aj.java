package com.xunlei.downloadprovider.web.base;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: ShortMovieDetailActivity.java
final class aj implements OnClickListener {
    final /* synthetic */ ShortMovieDetailActivity a;

    aj(ShortMovieDetailActivity shortMovieDetailActivity) {
        this.a = shortMovieDetailActivity;
    }

    public final void onClick(View view) {
        ShortMovieDetailActivity.e(this.a).removeCallbacks(ShortMovieDetailActivity.d(this.a));
        ShortMovieDetailActivity.a(this.a, false);
        this.a.a(false);
        ShortMovieDetailActivity.b(this.a, true);
        bk.d(this.a.b);
    }
}
