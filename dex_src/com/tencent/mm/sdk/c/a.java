package com.tencent.mm.sdk.c;

import android.net.Uri;
import android.provider.BaseColumns;

public final class a {

    public static final class a {
        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.lang.Object a(int r2, java.lang.String r3) {
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.mm.sdk.c.a.a.a(int, java.lang.String):java.lang.Object");
            /*
            switch(r2) {
                case 1: goto L_0x000e;
                case 2: goto L_0x0013;
                case 3: goto L_0x000d;
                case 4: goto L_0x0018;
                case 5: goto L_0x001d;
                case 6: goto L_0x0022;
                default: goto L_0x0003;
            };
        L_0x0003:
            r0 = "MicroMsg.SDK.PluginProvider.Resolver";
            r1 = "unknown type";
            com.tencent.mm.sdk.b.b.b(r0, r1);	 Catch:{ Exception -> 0x0027 }
        L_0x000c:
            r3 = 0;
        L_0x000d:
            return r3;
        L_0x000e:
            r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Exception -> 0x0027 }
            goto L_0x000d;
        L_0x0013:
            r3 = java.lang.Long.valueOf(r3);	 Catch:{ Exception -> 0x0027 }
            goto L_0x000d;
        L_0x0018:
            r3 = java.lang.Boolean.valueOf(r3);	 Catch:{ Exception -> 0x0027 }
            goto L_0x000d;
        L_0x001d:
            r3 = java.lang.Float.valueOf(r3);	 Catch:{ Exception -> 0x0027 }
            goto L_0x000d;
        L_0x0022:
            r3 = java.lang.Double.valueOf(r3);	 Catch:{ Exception -> 0x0027 }
            goto L_0x000d;
        L_0x0027:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x000c;
            */
        }
    }

    public static final class b implements BaseColumns {
        public static final Uri CONTENT_URI;

        static {
            CONTENT_URI = Uri.parse("content://com.tencent.mm.sdk.plugin.provider/sharedpref");
        }
    }
}
