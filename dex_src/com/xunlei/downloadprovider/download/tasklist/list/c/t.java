package com.xunlei.downloadprovider.download.tasklist.list.c;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.report.a;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: TaskDownloadCardViewHolder.java
final class t implements OnClickListener {
    final /* synthetic */ g a;

    t(g gVar) {
        this.a = gVar;
    }

    public final void onClick(View view) {
        a.b("dl_unfinish_speed", "downloading", g.d(this.a));
        g.a(this.a, SimpleLog.LOG_LEVEL_ERROR);
    }
}
