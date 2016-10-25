package com.xunlei.downloadprovider.download.tasklist.list.c;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.report.a;

// compiled from: TaskDownloadCardViewHolder.java
final class j implements OnClickListener {
    final /* synthetic */ g a;

    j(g gVar) {
        this.a = gVar;
    }

    public final void onClick(View view) {
        if (this.a.k()) {
            g.g(this.a);
            return;
        }
        g.h(this.a);
        if (this.a.h() != null) {
            if (g.e(this.a).mTaskStatus == 8) {
                a.b("dl_finish_content", "finish", g.d(this.a));
            } else {
                a.b("dl_unfinish_content", a.a(g.e(this.a)), g.d(this.a));
            }
            com.xunlei.downloadprovider.download.a.a.a(this.a.c(), g.e(this.a));
        }
    }
}
