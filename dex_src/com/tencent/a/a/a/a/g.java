package com.tencent.a.a.a.a;

import android.content.Context;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class g {
    private static g V;
    private Map<Integer, f> U;
    private int b;
    private Context c;

    static {
        V = null;
    }

    private g(Context context) {
        this.U = null;
        this.b = 0;
        this.c = null;
        this.c = context.getApplicationContext();
        this.U = new HashMap(3);
        this.U.put(Integer.valueOf(1), new e(context));
        this.U.put(Integer.valueOf(XZBDevice.DOWNLOAD_LIST_RECYCLE), new b(context));
        this.U.put(Integer.valueOf(XZBDevice.DOWNLOAD_LIST_ALL), new d(context));
    }

    public static synchronized g E(Context context) {
        g gVar;
        synchronized (g.class) {
            if (V == null) {
                V = new g(context);
            }
            gVar = V;
        }
        return gVar;
    }

    private c b(List<Integer> list) {
        if (list.size() >= 0) {
            for (Integer num : list) {
                f fVar = (f) this.U.get(num);
                if (fVar != null) {
                    c o = fVar.o();
                    if (o == null || h.c(o.c)) {
                        return o;
                    }
                }
            }
        }
        return new c();
    }

    public final void a(String str) {
        c p = p();
        p.c = str;
        if (!h.b(p.a)) {
            p.a = h.a(this.c);
        }
        if (!h.b(p.b)) {
            p.b = h.b(this.c);
        }
        p.T = System.currentTimeMillis();
        for (Entry entry : this.U.entrySet()) {
            ((f) entry.getValue()).a(p);
        }
    }

    public final c p() {
        return b(new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(XZBDevice.DOWNLOAD_LIST_RECYCLE), Integer.valueOf(XZBDevice.DOWNLOAD_LIST_ALL)})));
    }
}
