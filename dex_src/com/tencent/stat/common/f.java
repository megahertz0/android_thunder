package com.tencent.stat.common;

import android.content.Context;

public class f {
    static long a;

    static {
        a = -1;
    }

    static long a(Context context, String str) {
        return p.a(context, str, a);
    }

    static void a(Context context, String str, long j) {
        p.b(context, str, j);
    }

    public static synchronized boolean a(Context context) {
        boolean z;
        synchronized (f.class) {
            long a = a(context, "1.6.2_begin_protection");
            long a2 = a(context, "1.6.2_end__protection");
            if (a <= 0 || a2 != a) {
                if (a == a) {
                    a(context, "1.6.2_begin_protection", System.currentTimeMillis());
                }
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public static synchronized void b(Context context) {
        synchronized (f.class) {
            if (a(context, "1.6.2_end__protection") == a) {
                a(context, "1.6.2_end__protection", System.currentTimeMillis());
            }
        }
    }
}
