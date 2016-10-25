package com.xunlei.downloadprovider.download.tasklist.list.c;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.xunlei.downloadprovider.download.report.a;

// compiled from: TaskDownloadCardViewHolder.java
final class k implements OnLongClickListener {
    final /* synthetic */ g a;

    k(g gVar) {
        this.a = gVar;
    }

    public final boolean onLongClick(View view) {
        if (this.a.k()) {
            return false;
        }
        a.b(a.a(g.e(this.a)), g.d(this.a));
        g.i(this.a);
        g.h(this.a);
        return true;
    }
}
