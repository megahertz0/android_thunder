package com.xunlei.downloadprovider.download.tasklist.list.c;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.report.a;

// compiled from: TaskDownloadCardViewHolder.java
final class p implements OnClickListener {
    final /* synthetic */ g a;

    p(g gVar) {
        this.a = gVar;
    }

    public final void onClick(View view) {
        a.b("dl_unfinish_pause", "downloading", g.d(this.a));
        g.a(this.a, 1);
    }
}
