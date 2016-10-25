package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.misc.d.a;
import com.xiaomi.push.service.v;
import com.xiaomi.push.service.w;
import com.xiaomi.xmpush.thrift.ad;
import com.xiaomi.xmpush.thrift.c;
import com.xiaomi.xmpush.thrift.f;
import com.xiaomi.xmpush.thrift.l;
import com.xiaomi.xmpush.thrift.r;
import org.apache.commons.logging.impl.SimpleLog;

public class g extends a {
    private Context a;

    public g(Context context) {
        this.a = context;
    }

    public int a() {
        return SimpleLog.LOG_LEVEL_DEBUG;
    }

    public void run() {
        v a = v.a(this.a);
        l lVar = new l();
        lVar.a(w.a(a, c.a));
        lVar.b(w.a(a, c.b));
        r rVar = new r("-1", false);
        rVar.c(f.n.p);
        rVar.a(ad.a(lVar));
        j.a(this.a).a(rVar, com.xiaomi.xmpush.thrift.a.i, null);
    }
}
