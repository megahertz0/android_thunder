package com.xunlei.downloadprovider.web.base;

import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailFragment.a;

// compiled from: ShortMovieDetailFragment.java
final class bf implements OnShowListener {
    final /* synthetic */ ShortMovieDetailFragment a;

    bf(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void onShow(DialogInterface dialogInterface) {
        if (ShortMovieDetailFragment.q(this.a) != null && !ShortMovieDetailFragment.r(this.a).isFinishing()) {
            a aVar = (a) ShortMovieDetailFragment.s(this.a);
            boolean z = ShortMovieDetailFragment.g(this.a) && !ShortMovieDetailFragment.h(this.a);
            aVar.c(z);
        }
    }
}
