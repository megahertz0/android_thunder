package com.xunlei.downloadprovider.web.base;

import android.view.View;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.d.c;

// compiled from: ShortMovieDetailActivity.java
final class ad extends c {
    final /* synthetic */ ShortMovieDetailActivity a;

    ad(ShortMovieDetailActivity shortMovieDetailActivity) {
        this.a = shortMovieDetailActivity;
    }

    public final void onLoadingFailed(String str, View view, FailReason failReason) {
        ShortMovieDetailActivity.u(this.a).setImageResource(2130839160);
    }
}
