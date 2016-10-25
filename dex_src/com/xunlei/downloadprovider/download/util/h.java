package com.xunlei.downloadprovider.download.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.download.a.n;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;

// compiled from: FreeTrialHelper.java
public final class h implements OnClickListener {
    final /* synthetic */ TaskRunningInfo a;
    final /* synthetic */ g b;

    public h(g gVar, TaskRunningInfo taskRunningInfo) {
        this.b = gVar;
        this.a = taskRunningInfo;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        BrothersApplication.a(true);
        n.a().a(this.a, true);
    }
}
