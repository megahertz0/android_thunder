package com.xunlei.analytics.a;

import android.text.TextUtils;
import android.util.Log;
import com.xunlei.analytics.c.f;
import com.xunlei.analytics.config.a;
import com.xunlei.analytics.dbstore.AnalyticsConstant;
import com.xunlei.analytics.dbstore.d;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class c {
    private static final int a = 20000;
    private static final int b = 20000;

    private static String a() {
        int f = a.f();
        return f == 1 ? AnalyticsConstant.API_TEST_URL : f == 2 ? AnalyticsConstant.API_PRE_URL : AnalyticsConstant.API_RELEASE_URL;
    }

    private static String a(String str, int i, String str2, String str3) {
        return a() + "?appId=" + a.a() + "&discardCount=" + i + "&sig=" + str + "&callId=" + str3 + "&octet=" + str2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.lang.String r7, byte[] r8) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.analytics.a.c.a(java.lang.String, byte[]):java.lang.String");
        /*
        r3 = 0;
        r1 = "";
        r0 = new java.net.URL;	 Catch:{ Exception -> 0x0090, all -> 0x00a8 }
        r0.<init>(r7);	 Catch:{ Exception -> 0x0090, all -> 0x00a8 }
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x0090, all -> 0x00a8 }
        r2 = "accept";
        r4 = "*/*";
        r0.setRequestProperty(r2, r4);	 Catch:{ Exception -> 0x0090, all -> 0x00a8 }
        r2 = "connection";
        r4 = "Keep-Alive";
        r0.setRequestProperty(r2, r4);	 Catch:{ Exception -> 0x0090, all -> 0x00a8 }
        r2 = "user-agent";
        r4 = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)";
        r0.setRequestProperty(r2, r4);	 Catch:{ Exception -> 0x0090, all -> 0x00a8 }
        r2 = 20000; // 0x4e20 float:2.8026E-41 double:9.8813E-320;
        r0.setReadTimeout(r2);	 Catch:{ Exception -> 0x0090, all -> 0x00a8 }
        r2 = 20000; // 0x4e20 float:2.8026E-41 double:9.8813E-320;
        r0.setConnectTimeout(r2);	 Catch:{ Exception -> 0x0090, all -> 0x00a8 }
        r2 = "Content-Type";
        r4 = "application/octet-stream";
        r0.setRequestProperty(r2, r4);	 Catch:{ Exception -> 0x0090, all -> 0x00a8 }
        r2 = r8.length;	 Catch:{ Exception -> 0x0090, all -> 0x00a8 }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ Exception -> 0x0090, all -> 0x00a8 }
        r4 = "Content-Length";
        r0.setRequestProperty(r4, r2);	 Catch:{ Exception -> 0x0090, all -> 0x00a8 }
        b(r2);	 Catch:{ Exception -> 0x0090, all -> 0x00a8 }
        r2 = 1;
        r0.setDoOutput(r2);	 Catch:{ Exception -> 0x0090, all -> 0x00a8 }
        r2 = 1;
        r0.setDoInput(r2);	 Catch:{ Exception -> 0x0090, all -> 0x00a8 }
        r2 = r0.getOutputStream();	 Catch:{ Exception -> 0x0090, all -> 0x00a8 }
        r2.write(r8);	 Catch:{ Exception -> 0x00c5, all -> 0x00ba }
        r2.flush();	 Catch:{ Exception -> 0x00c5, all -> 0x00ba }
        r4 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x00c5, all -> 0x00ba }
        r5 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x00c5, all -> 0x00ba }
        r0 = r0.getInputStream();	 Catch:{ Exception -> 0x00c5, all -> 0x00ba }
        r5.<init>(r0);	 Catch:{ Exception -> 0x00c5, all -> 0x00ba }
        r4.<init>(r5);	 Catch:{ Exception -> 0x00c5, all -> 0x00ba }
        r0 = r1;
    L_0x006a:
        r1 = r4.readLine();	 Catch:{ Exception -> 0x00ca, all -> 0x00be }
        if (r1 == 0) goto L_0x0082;
    L_0x0070:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ca, all -> 0x00be }
        r3.<init>();	 Catch:{ Exception -> 0x00ca, all -> 0x00be }
        r3 = r3.append(r0);	 Catch:{ Exception -> 0x00ca, all -> 0x00be }
        r1 = r3.append(r1);	 Catch:{ Exception -> 0x00ca, all -> 0x00be }
        r0 = r1.toString();	 Catch:{ Exception -> 0x00ca, all -> 0x00be }
        goto L_0x006a;
    L_0x0082:
        if (r2 == 0) goto L_0x0087;
    L_0x0084:
        r2.close();	 Catch:{ IOException -> 0x008b }
    L_0x0087:
        r4.close();	 Catch:{ IOException -> 0x008b }
    L_0x008a:
        return r0;
    L_0x008b:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x008a;
    L_0x0090:
        r0 = move-exception;
        r2 = r3;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x0095:
        a(r1);	 Catch:{ all -> 0x00c1 }
        if (r2 == 0) goto L_0x009d;
    L_0x009a:
        r2.close();	 Catch:{ IOException -> 0x00a3 }
    L_0x009d:
        if (r3 == 0) goto L_0x008a;
    L_0x009f:
        r3.close();	 Catch:{ IOException -> 0x00a3 }
        goto L_0x008a;
    L_0x00a3:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x008a;
    L_0x00a8:
        r0 = move-exception;
        r4 = r3;
    L_0x00aa:
        if (r3 == 0) goto L_0x00af;
    L_0x00ac:
        r3.close();	 Catch:{ IOException -> 0x00b5 }
    L_0x00af:
        if (r4 == 0) goto L_0x00b4;
    L_0x00b1:
        r4.close();	 Catch:{ IOException -> 0x00b5 }
    L_0x00b4:
        throw r0;
    L_0x00b5:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00b4;
    L_0x00ba:
        r0 = move-exception;
        r4 = r3;
        r3 = r2;
        goto L_0x00aa;
    L_0x00be:
        r0 = move-exception;
        r3 = r2;
        goto L_0x00aa;
    L_0x00c1:
        r0 = move-exception;
        r4 = r3;
        r3 = r2;
        goto L_0x00aa;
    L_0x00c5:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
        goto L_0x0095;
    L_0x00ca:
        r1 = move-exception;
        r3 = r4;
        goto L_0x0095;
        */
    }

    private static String a(List<d> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (d dVar : list) {
            stringBuffer.append(dVar.c).append("\n");
            a(dVar);
        }
        return stringBuffer.toString();
    }

    private static void a(d dVar) {
        if (f.a()) {
            f.a(new StringBuilder("upload event content = ").append(dVar.c).toString());
        }
    }

    private static void a(Exception exception) {
        if (f.a()) {
            f.a(new StringBuilder("upload error =").append(Log.getStackTraceString(exception)).toString());
        }
    }

    private static void a(String str) {
        if (f.a()) {
            f.a(new StringBuilder("event data request url  = ").append(str).toString());
        }
    }

    public static boolean a(List<d> list, int i) {
        try {
            byte[] a = com.xunlei.analytics.c.a.a(com.xunlei.analytics.c.a.a(a((List) list)), a.b());
            String a2 = com.xunlei.analytics.c.a.a(a);
            Map hashMap = new HashMap();
            hashMap.put("appId", a.a());
            hashMap.put("discardCount", String.valueOf(i));
            hashMap.put("octet", a2);
            String valueOf = String.valueOf(System.currentTimeMillis());
            hashMap.put("callId", valueOf);
            a2 = a(com.xunlei.analytics.c.a.a(hashMap, a.b()), i, a2, valueOf);
            a(a2);
            return c(a(a2, a));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void b(String str) {
        if (f.a()) {
            f.a(new StringBuilder("total upoload data length :").append(str).toString());
        }
    }

    private static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            d(str);
            JSONObject jSONObject = new JSONObject(str);
            return jSONObject.has("result") && jSONObject.getInt("result") == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void d(String str) {
        if (f.a()) {
            f.a(new StringBuilder("mResult = ").append(str).toString());
        }
    }
}
