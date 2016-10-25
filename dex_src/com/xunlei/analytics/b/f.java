package com.xunlei.analytics.b;

import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;

public class f {
    private static String a;
    private static long b;
    private static long c;

    static {
        a = BuildConfig.VERSION_NAME;
        b = 0;
        c = 5000;
    }

    public static String a() {
        if (TextUtils.isEmpty(a)) {
            a = String.valueOf(System.currentTimeMillis());
        }
        return a;
    }

    public static void a(long j) {
        c = j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b() {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.analytics.b.f.b():void");
        /*
        r6 = 0;
        r0 = java.lang.System.currentTimeMillis();
        r2 = a;
        r2 = android.text.TextUtils.isEmpty(r2);
        if (r2 != 0) goto L_0x0024;
    L_0x000e:
        r2 = b;
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 == 0) goto L_0x002a;
    L_0x0014:
        r2 = b;
        r2 = r0 - r2;
        r4 = c;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 >= 0) goto L_0x0024;
    L_0x001e:
        r2 = b;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x002a;
    L_0x0024:
        r0 = java.lang.String.valueOf(r0);
        a = r0;
    L_0x002a:
        b = r6;
        return;
        */
    }

    public static void c() {
        b = System.currentTimeMillis();
    }
}
