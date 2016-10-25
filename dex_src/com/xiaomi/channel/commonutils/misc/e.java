package com.xiaomi.channel.commonutils.misc;

import com.xiaomi.channel.commonutils.misc.d.a;

class e extends b {
    final /* synthetic */ String a;
    final /* synthetic */ d b;

    e(d dVar, a aVar, String str) {
        this.b = dVar;
        this.a = str;
        super(aVar);
    }

    void a() {
        super.a();
    }

    void b() {
        d.a(this.b).edit().putLong(this.a, System.currentTimeMillis()).commit();
    }
}
