package com.xiaomi.push.service;

import com.xiaomi.stats.g;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;

final class u implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ boolean b;

    u(List list, boolean z) {
        this.a = list;
        this.b = z;
    }

    public final void run() {
        boolean a = t.b("www.baidu.com:80");
        boolean z = a;
        for (String str : this.a) {
            Object obj;
            if (z || t.b(str)) {
                int i = 1;
            } else {
                obj = null;
            }
            if (obj != null && !this.b) {
                break;
            }
            Object obj2 = obj;
        }
        a = z;
        g.a(a ? 1 : SimpleLog.LOG_LEVEL_DEBUG);
    }
}
