package com.xunlei.downloadprovider.download.tasklist.a;

import android.os.SystemClock;
import com.xunlei.downloadprovider.service.downloads.task.a.k.a;
import java.util.List;

// compiled from: TaskListManager.java
final class l extends a<List<a>> {
    final /* synthetic */ k a;

    l(k kVar, List list) {
        this.a = kVar;
        super(list);
    }

    public final /* synthetic */ void a(Object obj) {
        List<a> list = (List) obj;
        if (!list.isEmpty()) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            for (a aVar : list) {
                h.c(aVar);
            }
            new StringBuilder("UpdateTaskInfoList: refreshTaskInfo - cost = ").append(SystemClock.elapsedRealtime() - elapsedRealtime).append("ms, size =  ").append(list.size());
        }
    }
}
