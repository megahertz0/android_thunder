package com.xunlei.downloadprovider.j;

import java.util.concurrent.Executor;

// compiled from: VolleyRequestManager.java
final class b implements Executor {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void execute(Runnable runnable) {
        if (runnable != null) {
            a.a(this.a).execute(runnable);
        }
    }
}
