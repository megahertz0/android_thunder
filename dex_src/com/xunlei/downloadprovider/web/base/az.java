package com.xunlei.downloadprovider.web.base;

import com.xunlei.downloadprovider.web.base.ShortMovieDetailFragment.a;

// compiled from: ShortMovieDetailFragment.java
final class az implements Runnable {
    final /* synthetic */ ShortMovieDetailFragment a;

    az(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void run() {
        a aVar = (a) ShortMovieDetailFragment.R(this.a);
        boolean z = ShortMovieDetailFragment.g(this.a) && !ShortMovieDetailFragment.h(this.a);
        aVar.c(z);
    }
}
