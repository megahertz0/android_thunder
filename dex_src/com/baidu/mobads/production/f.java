package com.baidu.mobads.production;

import android.content.Context;

class f implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ a b;

    f(a aVar, Context context) {
        this.b = aVar;
        this.a = context;
    }

    public void run() {
        new Thread(new g(this)).start();
    }
}
