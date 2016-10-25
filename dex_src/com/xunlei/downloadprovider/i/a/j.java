package com.xunlei.downloadprovider.i.a;

import com.xunlei.downloadprovider.b.c.g.b;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: Update.java
final class j implements b {
    final /* synthetic */ c a;

    j(c cVar) {
        this.a = cVar;
    }

    public final void a(int i, Map<String, List<String>> map, byte[] bArr) {
        try {
            c.y(this.a).close();
        } catch (IOException e) {
            if (!(c.l(this.a) || this.a.b == null || c.e(this.a).isFinishing())) {
                this.a.b.dismiss();
            }
            c.v(this.a).obtainMessage(20008, SimpleLog.LOG_LEVEL_DEBUG, 0).sendToTarget();
            new StringBuilder("IOException2 = ").append(e.getMessage());
        }
        if (200 == i) {
            c.a(this.a, true);
            c.a(this.a, c.a(this.a).a, true);
            c.v(this.a).post(new k(this));
        }
    }
}
