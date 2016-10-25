package com.xunlei.downloadprovider.download.a;

import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;

// compiled from: DownloadControl.java
public final class n {
    private static n b;
    public a a;

    public n() {
        this.a = new a((byte) 0);
    }

    public static n a() {
        if (b == null) {
            b = new n();
        }
        return b;
    }

    public final void a(TaskRunningInfo taskRunningInfo, boolean z) {
        if (taskRunningInfo.mTaskStatus == 4 || taskRunningInfo.mTaskStatus == 16) {
            taskRunningInfo.mRunningInfo.a(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            taskRunningInfo.mRevision++;
        }
        d.a();
        d.b(z, taskRunningInfo.mTaskId);
        this.a.a(taskRunningInfo);
    }

    public final void b(TaskRunningInfo taskRunningInfo, boolean z) {
        if (taskRunningInfo.mTaskStatus != 2) {
            taskRunningInfo.mRunningInfo.a(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            taskRunningInfo.mRevision++;
        }
        d.a();
        d.a(z, taskRunningInfo.mTaskId);
        this.a.a(taskRunningInfo);
    }

    public final void c(TaskRunningInfo taskRunningInfo, boolean z) {
        d.a().c(z, taskRunningInfo.mTaskId);
        taskRunningInfo.mRunningInfo.a(R.styleable.Toolbar_maxButtonHeight);
        taskRunningInfo.mRevision++;
        a aVar = this.a;
        List arrayList = new ArrayList();
        arrayList.add(taskRunningInfo);
        aVar.c(arrayList);
    }

    public final void a(List<TaskRunningInfo> list, boolean z) {
        if (list != null && !list.isEmpty()) {
            long[] jArr = new long[list.size()];
            for (int i = 0; i < list.size(); i++) {
                TaskRunningInfo taskRunningInfo = (TaskRunningInfo) list.get(i);
                jArr[i] = taskRunningInfo.getTaskId();
                if (taskRunningInfo.mTaskStatus == 4 || taskRunningInfo.mTaskStatus == 16) {
                    taskRunningInfo.mRunningInfo.a(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    taskRunningInfo.mRevision++;
                }
            }
            d.a();
            d.b(z, jArr);
            this.a.a(list);
        }
    }

    public final void a(List<TaskRunningInfo> list) {
        if (!list.isEmpty()) {
            long[] jArr = new long[list.size()];
            for (int i = 0; i < list.size(); i++) {
                TaskRunningInfo taskRunningInfo = (TaskRunningInfo) list.get(i);
                jArr[i] = taskRunningInfo.getTaskId();
                if (taskRunningInfo.mTaskStatus == 1 || taskRunningInfo.mTaskStatus == 2) {
                    taskRunningInfo.mRunningInfo.a(XZBDevice.DOWNLOAD_LIST_ALL);
                    taskRunningInfo.mRevision++;
                }
            }
            d.a();
            d.a(jArr);
            this.a.b(list);
        }
    }

    public static void a(boolean z) {
        d.a();
        d.a(z);
    }
}
