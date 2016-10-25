package com.xunlei.downloadprovider.web.base.a;

import android.view.View;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.web.base.a.ae.a;
import com.xunlei.downloadprovider.web.base.model.t;

// compiled from: CommentErrorViewHolder.java
public final class g extends af {
    private final ErrorView a;
    private final a b;

    public g(View view, a aVar) {
        super(view);
        this.a = (ErrorView) view.findViewById(R.id.ev_comment_error);
        this.a.a(null, "\u7f51\u7edc\u4e0d\u7ed9\u529b", null);
        this.a.setVisibility(0);
        this.b = aVar;
        this.a.a("\u5237\u65b0", new h(this));
    }

    public final void a(t tVar) {
    }
}
