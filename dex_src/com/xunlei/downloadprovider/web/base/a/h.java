package com.xunlei.downloadprovider.web.base.a;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: CommentErrorViewHolder.java
final class h implements OnClickListener {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public final void onClick(View view) {
        if (this.a.b != null) {
            this.a.b.a(this.a.a, 0, null);
        }
    }
}
