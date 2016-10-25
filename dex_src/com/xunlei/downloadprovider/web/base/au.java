package com.xunlei.downloadprovider.web.base;

import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailFragment.a;

// compiled from: ShortMovieDetailFragment.java
final class au implements OnShowListener {
    final /* synthetic */ ShortMovieDetailFragment a;

    au(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void onShow(DialogInterface dialogInterface) {
        a aVar = (a) ShortMovieDetailFragment.K(this.a);
        boolean z = ShortMovieDetailFragment.g(this.a) && !ShortMovieDetailFragment.h(this.a);
        aVar.c(z);
    }
}
