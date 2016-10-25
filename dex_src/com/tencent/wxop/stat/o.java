package com.tencent.wxop.stat;

import android.content.Context;

final class o implements Runnable {
    final /* synthetic */ Context a;

    o(Context context) {
        this.a = context;
    }

    public final void run() {
        StatServiceImpl.flushDataToDB(this.a);
    }
}
