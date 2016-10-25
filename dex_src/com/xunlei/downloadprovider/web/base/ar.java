package com.xunlei.downloadprovider.web.base;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.xllib.a.b;

// compiled from: ShortMovieDetailFragment.java
final class ar implements OnClickListener {
    final /* synthetic */ ShortMovieDetailFragment a;

    ar(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void onClick(View view) {
        ShortMovieDetailFragment.C(this.a).dismiss();
        if (b.a(ShortMovieDetailFragment.H(this.a))) {
            c cVar = ShortMovieDetailFragment.C(this.a).a;
            if (cVar == null) {
                throw new IllegalStateException("comment target is null, call method setTargetComment First");
            }
            ShortMovieDetailFragment.p(this.a).a(cVar.a);
            bk.a(cVar.a, "delete");
            return;
        }
        XLToast.b(ShortMovieDetailFragment.I(this.a), XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
    }
}
