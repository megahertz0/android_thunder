package com.xunlei.downloadprovider.web.base;

import com.xunlei.xiazaibao.BuildConfig;

// compiled from: ShortMovieDetailFragment.java
final class ay implements Runnable {
    final /* synthetic */ ShortMovieDetailFragment a;

    ay(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void run() {
        ShortMovieDetailFragment.a(this.a).setText(BuildConfig.VERSION_NAME);
    }
}
