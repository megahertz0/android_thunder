package com.tencent.wxop.stat;

import android.content.Context;

final class ae implements Runnable {
    final /* synthetic */ Context a;

    ae(Context context) {
        this.a = context;
    }

    public final void run() {
        try {
            new Thread(new ap(this.a, null, null), "NetworkMonitorTask").start();
        } catch (Throwable th) {
            StatServiceImpl.q.e(th);
            StatServiceImpl.a(this.a, th);
        }
    }
}
