package com.xunlei.downloadprovider.web.base.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xllib.R;

// compiled from: CommentItemViewHolder.java
final class p implements OnClickListener {
    final /* synthetic */ j a;

    p(j jVar) {
        this.a = jVar;
    }

    public final void onClick(View view) {
        if (this.a.h != null) {
            this.a.h.a(this.a.a, R.styleable.Toolbar_titleMarginEnd, this.a.q);
        }
    }
}
