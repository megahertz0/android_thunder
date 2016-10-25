package com.xunlei.downloadprovider.download.tasklist.a;

import android.os.SystemClock;

// compiled from: TaskListManager.java
final class m implements Runnable {
    final /* synthetic */ h a;

    m(h hVar) {
        this.a = hVar;
    }

    public final void run() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.a.a != null && !this.a.a.isEmpty()) {
            Object obj = null;
            for (a aVar : this.a.a.values()) {
                boolean z;
                h.a(aVar, elapsedRealtime);
                h.b(aVar, elapsedRealtime);
                aVar.mRevision++;
                if (aVar.mTaskStatus == 2 || aVar.mTaskStatus == 1) {
                    z = true;
                } else {
                    z = r1;
                }
                h.a(this.a).i.a(Boolean.valueOf(z));
                boolean z2 = z;
            }
            h.c(this.a);
        }
    }
}
