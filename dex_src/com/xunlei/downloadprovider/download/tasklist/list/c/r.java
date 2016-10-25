package com.xunlei.downloadprovider.download.tasklist.list.c;

import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.common.inter.ITagManager;
import com.xunlei.downloadprovider.download.report.a;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: TaskDownloadCardViewHolder.java
final class r implements OnClickListener {
    final /* synthetic */ g a;

    r(g gVar) {
        this.a = gVar;
    }

    public final void onClick(View view) {
        a.b("dl_fail_retry", ITagManager.FAIL, g.d(this.a));
        g.a(this.a, SimpleLog.LOG_LEVEL_DEBUG);
    }
}
