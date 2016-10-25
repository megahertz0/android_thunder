package com.xunlei.downloadprovider.download.taskDetail;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.util.a;
import com.xunlei.downloadprovider.download.util.n;

// compiled from: TaskDetailDeleteDialog.java
final class as implements OnClickListener {
    final /* synthetic */ ao a;

    as(ao aoVar) {
        this.a = aoVar;
    }

    public final void onClick(View view) {
        a.a(this.a.e, this.a.getContext());
        com.xunlei.downloadprovider.download.report.a.a("dl_more_copy", n.e(this.a.e), n.c(this.a.e) ? 1 : 0);
        this.a.dismiss();
    }
}
