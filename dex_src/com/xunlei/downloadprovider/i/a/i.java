package com.xunlei.downloadprovider.i.a;

import com.xunlei.downloadprovider.b.c.g;
import com.xunlei.downloadprovider.b.c.g.d;
import java.io.IOException;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: Update.java
final class i implements d {
    final /* synthetic */ c a;

    i(c cVar) {
        this.a = cVar;
    }

    public final void a(byte[] bArr, int i, g gVar) {
        try {
            c.y(this.a).write(bArr, 0, i);
            c.b(this.a, (long) i);
            if (0 != c.w(this.a) && System.currentTimeMillis() - c.z(this.a) > 500) {
                c.c(this.a, System.currentTimeMillis());
                if (!c.l(this.a) && this.a.b != null && !c.e(this.a).isFinishing()) {
                    this.a.b.b(c.A(this.a));
                }
            }
        } catch (IOException e) {
            if (!(c.l(this.a) || this.a.b == null || c.e(this.a).isFinishing())) {
                this.a.b.dismiss();
            }
            c.v(this.a).obtainMessage(20008, SimpleLog.LOG_LEVEL_DEBUG, 0).sendToTarget();
            gVar.cancel();
            new StringBuilder("IOException1 = ").append(e.getMessage());
        }
    }
}
