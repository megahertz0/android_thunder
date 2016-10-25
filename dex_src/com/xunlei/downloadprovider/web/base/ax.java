package com.xunlei.downloadprovider.web.base;

// compiled from: ShortMovieDetailFragment.java
final class ax implements Runnable {
    final /* synthetic */ ShortMovieDetailFragment a;

    ax(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void run() {
        if (ShortMovieDetailFragment.M(this.a) && ShortMovieDetailFragment.N(this.a) != 0 && ShortMovieDetailFragment.Q(this.a) != 0) {
            ShortMovieDetailFragment.O(this.a);
            ShortMovieDetailFragment.P(this.a);
        }
    }
}
