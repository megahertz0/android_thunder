package com.tencent.wxop.stat;

import android.content.Context;

final class ad implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ int b;

    ad(Context context, int i) {
        this.a = context;
        this.b = i;
    }

    public final void run() {
        try {
            StatServiceImpl.flushDataToDB(this.a);
            au.a(this.a).a(this.b);
        } catch (Throwable th) {
            StatServiceImpl.q.e(th);
            StatServiceImpl.a(this.a, th);
        }
    }
}
