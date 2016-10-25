package com.xunlei.downloadprovider.vod.protocol;

import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.i.a;

// compiled from: DownloadVodUtil.java
final class d implements a<TaskInfo> {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public final /* bridge */ /* synthetic */ void a(Object obj) {
        TaskInfo taskInfo = (TaskInfo) obj;
        if (taskInfo == null || taskInfo.mTaskId <= 0) {
            this.a.a.a(-1, null);
        } else {
            this.a.a.a(0, taskInfo);
        }
    }
}
