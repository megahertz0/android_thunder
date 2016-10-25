package com.xunlei.downloadprovider.service.downloads.task.b;

import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import java.util.Hashtable;

// compiled from: SpeedCounter.java
public final class b {
    public a a;
    public a b;
    public long c;
    protected Hashtable<Long, c> d;

    // compiled from: SpeedCounter.java
    static class a {
        public long a;
        public long b;
        public long c;
        public double d;
        public long e;
        public long f;

        a() {
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.e = 0;
            this.f = 0;
        }
    }

    public b() {
        this.a = new a();
        this.b = new a();
        this.c = 0;
        this.d = new Hashtable();
    }

    public final c a(long j) {
        return (c) this.d.get(Long.valueOf(j));
    }

    public final void a(TaskInfo taskInfo) {
        a aVar;
        try {
            long j = taskInfo.mTaskId;
            c cVar = (c) this.d.get(Long.valueOf(j));
            if (cVar != null && taskInfo.mDownloadSpeed > cVar.a) {
                cVar.a = taskInfo.mDownloadSpeed;
                cVar.g = j;
                if (taskInfo.mExtraInfo == null) {
                    taskInfo.syncExtraInfo();
                }
                if (taskInfo.mExtraInfo != null) {
                    taskInfo.mExtraInfo.k = cVar.a;
                }
                d.a();
                d.a(j, cVar.a);
            }
            if (taskInfo.mTaskStatus == 8) {
                this.d.remove(Long.valueOf(j));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (taskInfo.mTaskStatus == 2) {
            aVar = this.b;
            aVar.a = taskInfo.mDownloadSpeed + aVar.a;
            aVar = this.b;
            aVar.c = (taskInfo.mVipChannelSpeed + taskInfo.mLixianSpeed) + aVar.c;
            aVar = this.b;
            aVar.b = taskInfo.mOriginSpeed + aVar.b;
        }
        aVar = this.b;
        aVar.f = taskInfo.mFileSize + aVar.f;
        aVar = this.b;
        aVar.e = taskInfo.mDownloadedSize + aVar.e;
    }

    public final void a(long j, boolean z) {
        if (j != -1) {
            if (z || !this.d.containsKey(Long.valueOf(j))) {
                c cVar = new c();
                cVar.g = j;
                this.d.put(Long.valueOf(cVar.g), cVar);
            }
        }
    }
}
