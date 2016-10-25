package com.xunlei.downloadprovider.service.downloads.task.a;

import android.text.TextUtils;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.a.k.a;
import com.xunlei.downloadprovider.service.downloads.task.b.c;
import com.xunlei.downloadprovider.service.downloads.task.b.d;
import java.util.List;

// compiled from: CoreTaskManager.java
final class e extends a<List<TaskInfo>> {
    final /* synthetic */ a a;

    e(a aVar, List list) {
        this.a = aVar;
        super(list);
    }

    public final /* synthetic */ void a(Object obj) {
        List<TaskInfo> list = (List) obj;
        if (!list.isEmpty()) {
            try {
                for (TaskInfo taskInfo : list) {
                    c a = com.xunlei.downloadprovider.service.downloads.task.b.a.a().a((int) taskInfo.mTaskId);
                    if (a != null) {
                        taskInfo.syncExtraInfo();
                        taskInfo.mExtraInfo.k = Math.max(a.a, taskInfo.mExtraInfo.k);
                        d.a();
                        d.a(taskInfo.mTaskId, taskInfo.mExtraInfo.k);
                    }
                    com.xunlei.downloadprovider.model.a a2 = com.xunlei.downloadprovider.model.a.a();
                    com.xunlei.downloadprovider.model.a.a a3 = a2.a.a(taskInfo.mTaskId);
                    if (a3 != null) {
                        taskInfo.syncExtraInfo();
                        if (!TextUtils.isEmpty(a3.p)) {
                            taskInfo.mExtraInfo.c = a3.p;
                        }
                        if (!TextUtils.isEmpty(a3.j)) {
                            taskInfo.mExtraInfo.h = a3.j;
                        }
                        if (taskInfo.mExtraInfo.i == 0) {
                            taskInfo.mExtraInfo.i = a3.n;
                        }
                    }
                    d.a().a(taskInfo.mExtraInfo);
                }
                com.xunlei.downloadprovider.service.downloads.task.b.a.a().b();
                com.xunlei.downloadprovider.model.a.a().a.c();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
