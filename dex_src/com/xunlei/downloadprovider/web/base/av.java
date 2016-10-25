package com.xunlei.downloadprovider.web.base;

import com.xunlei.downloadprovider.web.base.ShortMovieDetailFragment.a;

// compiled from: ShortMovieDetailFragment.java
final class av implements Runnable {
    final /* synthetic */ ShortMovieDetailFragment a;

    av(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void run() {
        a aVar = (a) ShortMovieDetailFragment.L(this.a);
        boolean z = ShortMovieDetailFragment.g(this.a) && !ShortMovieDetailFragment.h(this.a);
        aVar.c(z);
    }
}
