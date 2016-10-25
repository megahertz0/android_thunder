package com.xunlei.downloadprovider.download.tasklist.list.c;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.tasklist.a.a;

// compiled from: TaskDownloadCardViewHolder.java
final class u implements OnClickListener {
    final /* synthetic */ g a;

    u(g gVar) {
        this.a = gVar;
    }

    public final void onClick(View view) {
        if (g.e(this.a) != null) {
            a e = g.e(this.a);
            e.mRevision++;
        }
        g.f(this.a).notifyDataSetChanged();
    }
}
