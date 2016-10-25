package com.xunlei.downloadprovider.web.base.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xllib.R;

// compiled from: CommentItemViewHolder.java
final class q implements OnClickListener {
    final /* synthetic */ j a;

    q(j jVar) {
        this.a = jVar;
    }

    public final void onClick(View view) {
        if (this.a.h != null) {
            this.a.h.a(this.a.b, R.styleable.Toolbar_titleMarginTop, this.a.q);
        }
    }
}
