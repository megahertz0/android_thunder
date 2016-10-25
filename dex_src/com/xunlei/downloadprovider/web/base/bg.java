package com.xunlei.downloadprovider.web.base;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailFragment.a;

// compiled from: ShortMovieDetailFragment.java
final class bg implements OnDismissListener {
    final /* synthetic */ ShortMovieDetailFragment a;

    bg(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        if (ShortMovieDetailFragment.t(this.a) != null && !ShortMovieDetailFragment.u(this.a).isFinishing()) {
            a aVar = (a) ShortMovieDetailFragment.v(this.a);
            boolean z = ShortMovieDetailFragment.g(this.a) && !ShortMovieDetailFragment.h(this.a);
            aVar.c(z);
        }
    }
}
