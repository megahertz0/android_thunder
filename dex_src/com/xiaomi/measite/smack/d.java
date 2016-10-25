package com.xiaomi.measite.smack;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.smack.f;
import java.util.Date;

class d implements f {
    final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    public void a(com.xiaomi.smack.packet.d dVar) {
        if (a.a) {
            b.c(new StringBuilder("SMACK ").append(a.a(this.a).format(new Date())).append(" RCV PKT (").append(a.b(this.a).hashCode()).append("): ").append(dVar.a()).toString());
        }
    }
}
