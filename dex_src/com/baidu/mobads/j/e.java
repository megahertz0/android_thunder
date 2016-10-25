package com.baidu.mobads.j;

import android.content.Context;
import android.content.Intent;

class e implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ Intent b;
    final /* synthetic */ d c;

    e(d dVar, Context context, Intent intent) {
        this.c = dVar;
        this.a = context;
        this.b = intent;
    }

    public void run() {
        this.a.startActivity(this.b);
    }
}
