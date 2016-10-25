package com.xunlei.downloadprovider.download.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;

// compiled from: DownloadCenterControl.java
final class m implements OnClickListener {
    final /* synthetic */ TaskRunningInfo a;
    final /* synthetic */ a b;

    m(a aVar, TaskRunningInfo taskRunningInfo) {
        this.b = aVar;
        this.a = taskRunningInfo;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        BrothersApplication.a(true);
        n.a().a(this.a, true);
    }
}
