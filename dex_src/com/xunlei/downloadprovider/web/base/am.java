package com.xunlei.downloadprovider.web.base;

import com.xunlei.downloadprovider.player.n;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ShortMovieDetailActivity.java
final class am implements n {
    final /* synthetic */ ShortMovieDetailActivity a;

    am(ShortMovieDetailActivity shortMovieDetailActivity) {
        this.a = shortMovieDetailActivity;
    }

    public final void a() {
        ShortMovieDetailActivity.m(this.a).setVisibility(XZBDevice.Wait);
        ShortMovieDetailActivity.n(this.a).setVisibility(XZBDevice.Wait);
        ShortMovieDetailActivity.o(this.a).getLayoutParams().height = -1;
        ShortMovieDetailActivity.o(this.a).requestLayout();
        ShortMovieDetailActivity.a(this.a).e();
    }

    public final void b() {
        ShortMovieDetailActivity.m(this.a).setVisibility(0);
        ShortMovieDetailActivity.n(this.a).setVisibility(0);
        ShortMovieDetailActivity.o(this.a).getLayoutParams().height = ShortMovieDetailActivity.p(this.a);
        ShortMovieDetailActivity.o(this.a).requestLayout();
        ShortMovieDetailActivity.a(this.a).e();
    }
}
