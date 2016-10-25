package com.xunlei.downloadprovider.download.a;

import android.database.Observable;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// compiled from: DownloadControl.java
private class n$a extends Observable<o> implements o {
    private n$a() {
    }

    public final void a(TaskRunningInfo taskRunningInfo) {
        List arrayList = new ArrayList();
        arrayList.add(taskRunningInfo);
        a(arrayList);
    }

    public final void a(List<TaskRunningInfo> list) {
        if (!this.mObservers.isEmpty()) {
            Iterator it = new ArrayList(this.mObservers).iterator();
            while (it.hasNext()) {
                ((o) it.next()).a(list);
            }
        }
    }

    public final void b(List<TaskRunningInfo> list) {
        if (!this.mObservers.isEmpty()) {
            Iterator it = new ArrayList(this.mObservers).iterator();
            while (it.hasNext()) {
                ((o) it.next()).b(list);
            }
        }
    }

    public final void c(List<TaskRunningInfo> list) {
        if (!this.mObservers.isEmpty()) {
            Iterator it = new ArrayList(this.mObservers).iterator();
            while (it.hasNext()) {
                ((o) it.next()).c(list);
            }
        }
    }

    public final void a() {
        Iterator it = new ArrayList(this.mObservers).iterator();
        while (it.hasNext()) {
            ((o) it.next()).a();
        }
    }
}
