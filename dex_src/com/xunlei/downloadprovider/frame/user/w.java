package com.xunlei.downloadprovider.frame.user;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.frame.user.ah.a;

// compiled from: HistoryCommentItemViewHolder.java
final class w implements OnClickListener {
    final /* synthetic */ v a;

    w(v vVar) {
        this.a = vVar;
    }

    public final void onClick(View view) {
        if (v.a(this.a) != null && v.b(this.a) != null) {
            a a = v.a(this.a);
            v.c(this.a);
            a.a(0, v.b(this.a).s);
        }
    }
}
