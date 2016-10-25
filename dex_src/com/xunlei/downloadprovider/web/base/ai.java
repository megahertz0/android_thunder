package com.xunlei.downloadprovider.web.base;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.player.a;

// compiled from: ShortMovieDetailActivity.java
final class ai implements OnClickListener {
    final /* synthetic */ ShortMovieDetailActivity a;

    ai(ShortMovieDetailActivity shortMovieDetailActivity) {
        this.a = shortMovieDetailActivity;
    }

    public final void onClick(View view) {
        ShortMovieDetailActivity.e(this.a).removeCallbacks(ShortMovieDetailActivity.d(this.a));
        ShortMovieDetailActivity.a(this.a, false);
        this.a.a(false);
        a.a(this.a.b, "next_video");
        if (!ShortMovieDetailActivity.a(this.a).a(false)) {
            XLToast.b(this.a, XLToastType.XLTOAST_TYPE_ALARM, "\u6ca1\u6709\u89c6\u9891\u4e86");
            ShortMovieDetailActivity.f(this.a).f(false);
        }
    }
}
