package com.xiaomi.push.log;

import com.xiaomi.channel.commonutils.file.c;
import com.xiaomi.channel.commonutils.misc.f.b;

class g extends b {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public void b() {
        if (!f.a().isEmpty()) {
            try {
                if (c.d()) {
                    f.b(this.a);
                } else {
                    f.a(this.a);
                }
            } catch (Exception e) {
                f.a(this.a);
            }
        }
    }
}
