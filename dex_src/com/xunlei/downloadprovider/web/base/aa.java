package com.xunlei.downloadprovider.web.base;

import android.widget.ImageView;
import com.xunlei.downloadprovider.player.MediaPlayerControllerView.a;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ShortMovieDetailActivity.java
final class aa implements a {
    final /* synthetic */ ShortMovieDetailActivity a;

    aa(ShortMovieDetailActivity shortMovieDetailActivity) {
        this.a = shortMovieDetailActivity;
    }

    public final void a(boolean z) {
        int i = XZBDevice.DOWNLOAD_LIST_ALL;
        ShortMovieDetailActivity.c(this.a, z);
        if (z) {
            ImageView r = ShortMovieDetailActivity.r(this.a);
            if (!From.HOME_PAGE_AD.getText().contentEquals(ShortMovieDetailActivity.q(this.a))) {
                i = 0;
            }
            r.setVisibility(i);
            ShortMovieDetailActivity.s(this.a).setVisibility(0);
        } else {
            ShortMovieDetailActivity.r(this.a).setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            ShortMovieDetailActivity.n(this.a).setBackgroundColor(0);
            ShortMovieDetailActivity.s(this.a).setVisibility(0);
        }
        ShortMovieDetailActivity.t(this.a);
    }
}
