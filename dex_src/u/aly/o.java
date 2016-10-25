package u.aly;

import android.content.Context;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.h;
import u.aly.cm.a;

// compiled from: ImLatent.java
public final class o implements di {
    private static o l;
    h a;
    c b;
    long c;
    int d;
    long e;
    long f;
    Context g;
    private final long h;
    private final long i;
    private final int j;
    private final int k;

    static {
        l = null;
    }

    public static synchronized o a(Context context, c cVar) {
        o oVar;
        synchronized (o.class) {
            if (l == null) {
                oVar = new o(context, cVar);
                l = oVar;
                oVar.a(cm.a(context).c);
            }
            oVar = l;
        }
        return oVar;
    }

    private o(Context context, c cVar) {
        this.h = 1296000000;
        this.i = 129600000;
        this.j = 1800000;
        this.k = 10000;
        this.c = 1296000000;
        this.d = 10000;
        this.e = 0;
        this.f = 0;
        this.g = context;
        this.a = h.a(context);
        this.b = cVar;
    }

    public final void a(a aVar) {
        int i;
        long j = 1296000000;
        if (aVar.f != -1 && aVar.f >= 48) {
            j = 3600000 * ((long) aVar.f);
        }
        this.c = j;
        if (aVar.a == -1) {
            i = 0;
        } else if (aVar.a < 0 || aVar.a > 1800) {
            i = 0;
        } else {
            i = aVar.a * 1000;
        }
        if (i != 0) {
            this.d = i;
        } else if (AnalyticsConfig.sLatentWindow <= 0 || AnalyticsConfig.sLatentWindow > 1800000) {
            this.d = 10000;
        } else {
            this.d = AnalyticsConfig.sLatentWindow;
        }
    }
}
