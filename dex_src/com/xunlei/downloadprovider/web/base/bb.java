package com.xunlei.downloadprovider.web.base;

// compiled from: ShortMovieDetailFragment.java
final class bb implements Runnable {
    final /* synthetic */ ShortMovieDetailFragment a;

    bb(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void run() {
        if (ShortMovieDetailFragment.M(this.a) && ShortMovieDetailFragment.k(this.a) != 0 && ShortMovieDetailFragment.Q(this.a) != 0) {
            ShortMovieDetailFragment.O(this.a);
            ShortMovieDetailFragment.P(this.a);
        }
    }
}
