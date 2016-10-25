package com.baidu.mobads.production;

import android.content.Context;

class e implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ a b;

    e(a aVar, Context context) {
        this.b = aVar;
        this.a = context;
    }

    public void run() {
        this.b.a(this.a);
    }
}
