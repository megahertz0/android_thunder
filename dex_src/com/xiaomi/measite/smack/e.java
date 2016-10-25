package com.xiaomi.measite.smack;

import com.umeng.message.proguard.j;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.smack.a;
import com.xiaomi.smack.d;
import java.util.Date;

class e implements d {
    final /* synthetic */ a a;

    e(a aVar) {
        this.a = aVar;
    }

    public void a(a aVar) {
        b.c(new StringBuilder("SMACK ").append(a.a(this.a).format(new Date())).append(" Connection reconnected (").append(a.b(this.a).hashCode()).append(j.t).toString());
    }

    public void a(a aVar, int i, Exception exception) {
        b.c(new StringBuilder("SMACK ").append(a.a(this.a).format(new Date())).append(" Connection closed (").append(a.b(this.a).hashCode()).append(j.t).toString());
    }

    public void a(a aVar, Exception exception) {
        b.c(new StringBuilder("SMACK ").append(a.a(this.a).format(new Date())).append(" Reconnection failed due to an exception (").append(a.b(this.a).hashCode()).append(j.t).toString());
        exception.printStackTrace();
    }

    public void b(a aVar) {
        b.c(new StringBuilder("SMACK ").append(a.a(this.a).format(new Date())).append(" Connection started (").append(a.b(this.a).hashCode()).append(j.t).toString());
    }
}
