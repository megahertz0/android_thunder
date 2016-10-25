package com.xunlei.downloadprovider.service.downloads.task.a;

import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.a.k.a;

// compiled from: CoreTaskManager.java
public final class f extends a<TaskInfo> {
    final /* synthetic */ a a;

    public f(a aVar, TaskInfo taskInfo) {
        this.a = aVar;
        super(taskInfo);
    }

    public final /* synthetic */ void a(Object obj) {
        TaskInfo taskInfo = (TaskInfo) obj;
        taskInfo.syncExtraInfo();
        this.a.l.a(taskInfo.mExtraInfo);
    }
}
