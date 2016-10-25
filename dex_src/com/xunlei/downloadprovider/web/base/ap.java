package com.xunlei.downloadprovider.web.base;

import android.content.ClipData;
import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.socialize.media.WeiXinShareContent;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;

// compiled from: ShortMovieDetailFragment.java
final class ap implements OnClickListener {
    final /* synthetic */ ShortMovieDetailFragment a;

    ap(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void onClick(View view) {
        ShortMovieDetailFragment.C(this.a).dismiss();
        c cVar = ShortMovieDetailFragment.C(this.a).a;
        if (cVar == null) {
            throw new IllegalStateException("comment target is null, call method setTargetComment First");
        }
        ShortMovieDetailFragment.D(this.a).setPrimaryClip(ClipData.newPlainText(WeiXinShareContent.TYPE_TEXT, cVar.b));
        XLToast.b(ShortMovieDetailFragment.E(this.a), XLToastType.XLTOAST_TYPE_SUC, "\u590d\u5236\u6210\u529f");
        bk.a(cVar.a, "copy");
    }
}
