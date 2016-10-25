package com.bumptech.glide.load.engine.executor;

import android.os.Process;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: FifoPriorityThreadPoolExecutor.java
final class a extends Thread {
    final /* synthetic */ com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor.a a;

    a(com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor.a aVar, Runnable runnable, String str) {
        this.a = aVar;
        super(runnable, str);
    }

    public final void run() {
        Process.setThreadPriority(XZBDevice.Stop);
        super.run();
    }
}
