package u.aly;

import android.content.Context;
import com.umeng.analytics.f;

// compiled from: CacheService.java
public final class ct implements db {
    private static ct c;
    db a;
    private Context b;

    private ct(Context context) {
        this.b = context.getApplicationContext();
        this.a = new cr(this.b);
    }

    public static synchronized ct a(Context context) {
        ct ctVar;
        synchronized (ct.class) {
            if (c == null && context != null) {
                c = new ct(context);
            }
            ctVar = c;
        }
        return ctVar;
    }

    public final void a(dc dcVar) {
        f.b(new cu(this, dcVar));
    }

    public final void b(dc dcVar) {
        this.a.b(dcVar);
    }

    public final void a() {
        f.b(new cv(this));
    }

    public final void b() {
        f.b(new cw(this));
    }

    public final void c() {
        f.c(new cx(this));
    }
}
