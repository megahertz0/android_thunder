package com.xiaomi.network;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.network.c;
import com.xiaomi.channel.commonutils.network.d;
import com.xunlei.common.encrypt.CharsetConvert;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;

public abstract class HttpUtils {

    public static class DefaultHttpGetProcessor extends HttpProcessor {
        public DefaultHttpGetProcessor() {
            super(1);
        }

        public String b(Context context, String str, List<c> list) {
            if (list == null) {
                return d.a(context, new URL(str));
            }
            Builder buildUpon = Uri.parse(str).buildUpon();
            for (c cVar : list) {
                buildUpon.appendQueryParameter(cVar.a(), cVar.b());
            }
            return d.a(context, new URL(buildUpon.toString()));
        }
    }

    static int a(int i, int i2) {
        return (((((i2 + 243) / 1448) * 132) + 1080) + i) + i2;
    }

    static int a(int i, int i2, int i3) {
        return ((((((i2 + 200) / 1448) * 132) + 1011) + i2) + i) + i3;
    }

    private static int a(HttpProcessor httpProcessor, String str, List<c> list, String str2) {
        if (httpProcessor.a() == 1) {
            return a(str.length(), a(str2));
        }
        if (httpProcessor.a() != 2) {
            return -1;
        }
        return a(str.length(), a((List) list), a(str2));
    }

    static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return str.getBytes(CharsetConvert.UTF_8).length;
        } catch (UnsupportedEncodingException e) {
            return 0;
        }
    }

    static int a(List<c> list) {
        int i = 0;
        for (c cVar : list) {
            if (!TextUtils.isEmpty(cVar.a())) {
                i += cVar.a().length();
            }
            i = !TextUtils.isEmpty(cVar.b()) ? cVar.b().length() + i : i;
        }
        return i * 2;
    }

    public static String a(Context context, String str, List<c> list) {
        return a(context, str, list, new DefaultHttpGetProcessor(), true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r12, java.lang.String r13, java.util.List<com.xiaomi.channel.commonutils.network.c> r14, com.xiaomi.network.HttpProcessor r15, boolean r16) {
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.network.HttpUtils.a(android.content.Context, java.lang.String, java.util.List, com.xiaomi.network.HttpProcessor, boolean):java.lang.String");
        /*
        r0 = com.xiaomi.channel.commonutils.network.d.d(r12);
        if (r0 == 0) goto L_0x0094;
    L_0x0006:
        r1 = new java.util.ArrayList;	 Catch:{ MalformedURLException -> 0x0090 }
        r1.<init>();	 Catch:{ MalformedURLException -> 0x0090 }
        r0 = 0;
        if (r16 == 0) goto L_0x001c;
    L_0x000e:
        r0 = com.xiaomi.network.HostManager.getInstance();	 Catch:{ MalformedURLException -> 0x0090 }
        r0 = r0.getFallbacksByURL(r13);	 Catch:{ MalformedURLException -> 0x0090 }
        if (r0 == 0) goto L_0x001c;
    L_0x0018:
        r1 = r0.a(r13);	 Catch:{ MalformedURLException -> 0x0090 }
    L_0x001c:
        r2 = r1.contains(r13);	 Catch:{ MalformedURLException -> 0x0090 }
        if (r2 != 0) goto L_0x0025;
    L_0x0022:
        r1.add(r13);	 Catch:{ MalformedURLException -> 0x0090 }
    L_0x0025:
        r7 = 0;
        r9 = r1.iterator();	 Catch:{ MalformedURLException -> 0x0090 }
        r2 = r7;
    L_0x002b:
        r1 = r9.hasNext();	 Catch:{ MalformedURLException -> 0x0090 }
        if (r1 == 0) goto L_0x0099;
    L_0x0031:
        r1 = r9.next();	 Catch:{ MalformedURLException -> 0x0090 }
        r1 = (java.lang.String) r1;	 Catch:{ MalformedURLException -> 0x0090 }
        if (r14 == 0) goto L_0x004b;
    L_0x0039:
        r3 = new java.util.ArrayList;	 Catch:{ MalformedURLException -> 0x0090 }
        r3.<init>(r14);	 Catch:{ MalformedURLException -> 0x0090 }
        r8 = r3;
    L_0x003f:
        r10 = java.lang.System.currentTimeMillis();	 Catch:{ MalformedURLException -> 0x0090 }
        r3 = r15.a(r12, r1, r8);	 Catch:{ IOException -> 0x0096 }
        if (r3 != 0) goto L_0x004e;
    L_0x0049:
        r0 = r2;
    L_0x004a:
        return r0;
    L_0x004b:
        r3 = 0;
        r8 = r3;
        goto L_0x003f;
    L_0x004e:
        r7 = r15.b(r12, r1, r8);	 Catch:{ IOException -> 0x0096 }
        r2 = android.text.TextUtils.isEmpty(r7);	 Catch:{ IOException -> 0x007b }
        if (r2 != 0) goto L_0x0069;
    L_0x0058:
        if (r0 == 0) goto L_0x0067;
    L_0x005a:
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ IOException -> 0x007b }
        r2 = r2 - r10;
        r4 = a(r15, r1, r8, r7);	 Catch:{ IOException -> 0x007b }
        r4 = (long) r4;	 Catch:{ IOException -> 0x007b }
        r0.a(r1, r2, r4);	 Catch:{ IOException -> 0x007b }
    L_0x0067:
        r0 = r7;
        goto L_0x004a;
    L_0x0069:
        if (r0 == 0) goto L_0x0079;
    L_0x006b:
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ IOException -> 0x007b }
        r2 = r2 - r10;
        r4 = a(r15, r1, r8, r7);	 Catch:{ IOException -> 0x007b }
        r4 = (long) r4;	 Catch:{ IOException -> 0x007b }
        r6 = 0;
        r0.a(r1, r2, r4, r6);	 Catch:{ IOException -> 0x007b }
    L_0x0079:
        r2 = r7;
        goto L_0x002b;
    L_0x007b:
        r6 = move-exception;
    L_0x007c:
        if (r0 == 0) goto L_0x008b;
    L_0x007e:
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ MalformedURLException -> 0x0090 }
        r2 = r2 - r10;
        r4 = a(r15, r1, r8, r7);	 Catch:{ MalformedURLException -> 0x0090 }
        r4 = (long) r4;	 Catch:{ MalformedURLException -> 0x0090 }
        r0.a(r1, r2, r4, r6);	 Catch:{ MalformedURLException -> 0x0090 }
    L_0x008b:
        r6.printStackTrace();	 Catch:{ MalformedURLException -> 0x0090 }
        r2 = r7;
        goto L_0x002b;
    L_0x0090:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x0094:
        r0 = 0;
        goto L_0x004a;
    L_0x0096:
        r6 = move-exception;
        r7 = r2;
        goto L_0x007c;
    L_0x0099:
        r0 = r2;
        goto L_0x004a;
        */
    }
}
