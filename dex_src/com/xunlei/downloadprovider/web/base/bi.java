package com.xunlei.downloadprovider.web.base;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailFragment.a;

// compiled from: ShortMovieDetailFragment.java
final class bi implements OnDismissListener {
    final /* synthetic */ ShortMovieDetailFragment a;

    bi(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        CharSequence charSequence;
        a w = ShortMovieDetailFragment.w(this.a);
        if (w.c != null) {
            charSequence = (CharSequence) w.c.get(Long.valueOf(-1));
        } else {
            charSequence = null;
        }
        ShortMovieDetailFragment.a(this.a).setText(charSequence);
        a aVar = (a) ShortMovieDetailFragment.A(this.a);
        boolean z = ShortMovieDetailFragment.g(this.a) && !ShortMovieDetailFragment.h(this.a);
        aVar.c(z);
    }
}
