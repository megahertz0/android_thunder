package com.xunlei.downloadprovider.download.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;

// compiled from: DownloadCenterControl.java
final class f implements OnClickListener {
    final /* synthetic */ TaskRunningInfo a;
    final /* synthetic */ a b;

    f(a aVar, TaskRunningInfo taskRunningInfo) {
        this.b = aVar;
        this.a = taskRunningInfo;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            this.b.c(this.a);
        }
    }
}
