package com.xiaomi.channel.commonutils.misc;

import com.xiaomi.channel.commonutils.misc.f.b;

class h implements Runnable {
    final /* synthetic */ b a;
    final /* synthetic */ f b;

    h(f fVar, b bVar) {
        this.b = fVar;
        this.a = bVar;
    }

    public void run() {
        this.b.a(this.a);
    }
}
