package com.xiaomi.measite.smack;

import com.xiaomi.smack.util.f;
import java.util.Date;

class b implements f {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void a(String str) {
        com.xiaomi.channel.commonutils.logger.b.c(new StringBuilder("SMACK ").append(a.a(this.a).format(new Date())).append(" RCV  (").append(a.b(this.a).hashCode()).append("): ").append(str).toString());
    }
}
