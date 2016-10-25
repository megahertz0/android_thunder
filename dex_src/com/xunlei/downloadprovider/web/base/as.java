package com.xunlei.downloadprovider.web.base;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.c.a.c;

// compiled from: ShortMovieDetailFragment.java
final class as implements OnClickListener {
    final /* synthetic */ ShortMovieDetailFragment a;

    as(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void onClick(View view) {
        ShortMovieDetailFragment.C(this.a).dismiss();
        c cVar = ShortMovieDetailFragment.C(this.a).a;
        if (cVar == null) {
            throw new IllegalStateException("comment target is null, call method setTargetComment First");
        }
        bk.a(cVar.a, "cancel");
    }
}
