package com.xunlei.downloadprovider.web.base;

import com.xunlei.downloadprovider.player.MediaPlayerState;

// compiled from: ShortMovieDetailActivity.java
final class af implements Runnable {
    final /* synthetic */ ShortMovieDetailActivity a;

    af(ShortMovieDetailActivity shortMovieDetailActivity) {
        this.a = shortMovieDetailActivity;
    }

    public final void run() {
        boolean z = true;
        if (!this.a.isFinishing()) {
            if (ShortMovieDetailActivity.b(this.a) <= 0) {
                ShortMovieDetailActivity.a(this.a, 0);
                ShortMovieDetailActivity.a(this.a, false);
                this.a.a(false);
                if (!(ShortMovieDetailActivity.a(this.a) == null || ShortMovieDetailActivity.c(this.a))) {
                    ShortMovieDetailFragment a = ShortMovieDetailActivity.a(this.a);
                    if (a.c && !a.d()) {
                        a.a(true);
                    }
                }
                ShortMovieDetailActivity.b(this.a, false);
            } else {
                this.a.d.setText(ShortMovieDetailActivity.b(this.a) + "\u79d2\u540e\u64ad\u653e");
                ShortMovieDetailActivity.e(this.a).postDelayed(ShortMovieDetailActivity.d(this.a), 1000);
            }
            if (ShortMovieDetailActivity.f(this.a).f() != MediaPlayerState.PAUSED) {
                z = false;
            }
            if (!z) {
                ShortMovieDetailActivity.g(this.a);
            }
        }
    }
}
