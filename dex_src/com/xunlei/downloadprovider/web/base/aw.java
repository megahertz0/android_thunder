package com.xunlei.downloadprovider.web.base;

// compiled from: ShortMovieDetailFragment.java
final class aw implements Runnable {
    final /* synthetic */ ShortMovieDetailFragment a;

    aw(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void run() {
        if (ShortMovieDetailFragment.M(this.a) && ShortMovieDetailFragment.N(this.a) != 0 && ShortMovieDetailFragment.k(this.a) != 0) {
            ShortMovieDetailFragment.O(this.a);
            ShortMovieDetailFragment.P(this.a);
        }
    }
}
