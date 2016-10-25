package com.xunlei.downloadprovider.web.base.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xllib.R;

// compiled from: CommentItemViewHolder.java
final class n implements OnClickListener {
    final /* synthetic */ j a;

    n(j jVar) {
        this.a = jVar;
    }

    public final void onClick(View view) {
        if (this.a.h != null) {
            this.a.h.a(this.a.i, R.styleable.Toolbar_titleMargins, this.a.q);
        }
    }
}
