package com.android.volley.toolbox;

import com.android.volley.Request;
import com.android.volley.i;
import com.android.volley.t;
import com.android.volley.u;
import com.android.volley.w;
import com.android.volley.x;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

// compiled from: BasicNetwork.java
public final class a implements i {
    protected static final boolean a;
    private static int d;
    private static int e;
    protected final g b;
    protected final b c;

    static {
        a = x.b;
        d = 3000;
        e = 4096;
    }

    public a(g gVar) {
        this(gVar, new b(e));
    }

    private a(g gVar, b bVar) {
        this.b = gVar;
        this.c = bVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.android.volley.l a(com.android.volley.Request<?> r19) throws com.android.volley.w {
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.a.a(com.android.volley.Request):com.android.volley.l");
        /*
        this = this;
        r16 = android.os.SystemClock.elapsedRealtime();
    L_0x0004:
        r3 = 0;
        r14 = 0;
        r6 = java.util.Collections.emptyMap();
        r2 = new java.util.HashMap;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x013e }
        r2.<init>();	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x013e }
        r4 = r19.getCacheEntry();	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x013e }
        if (r4 == 0) goto L_0x003a;
    L_0x0015:
        r5 = r4.b;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x013e }
        if (r5 == 0) goto L_0x0021;
    L_0x0019:
        r5 = "If-None-Match";
        r7 = r4.b;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x013e }
        r2.put(r5, r7);	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x013e }
    L_0x0021:
        r8 = r4.d;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x013e }
        r10 = 0;
        r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r5 <= 0) goto L_0x003a;
    L_0x0029:
        r5 = new java.util.Date;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x013e }
        r8 = r4.d;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x013e }
        r5.<init>(r8);	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x013e }
        r4 = "If-Modified-Since";
        r5 = org.apache.http.impl.cookie.DateUtils.formatDate(r5);	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x013e }
        r2.put(r4, r5);	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x013e }
    L_0x003a:
        r0 = r18;
        r4 = r0.b;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x013e }
        r0 = r19;
        r15 = r4.a(r0, r2);	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x013e }
        r3 = r15.getStatusLine();	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        r4 = r3.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        r2 = r15.getAllHeaders();	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        r6 = a(r2);	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        r2 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r4 != r2) goto L_0x0087;
    L_0x0058:
        r2 = r19.getCacheEntry();	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        if (r2 != 0) goto L_0x006e;
    L_0x005e:
        r3 = new com.android.volley.l;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        r4 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r5 = 0;
        r7 = 1;
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        r8 = r8 - r16;
        r3.<init>(r4, r5, r6, r7, r8);	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
    L_0x006d:
        return r3;
    L_0x006e:
        r3 = r2.g;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        r3.putAll(r6);	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        r7 = new com.android.volley.l;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        r8 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r9 = r2.a;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        r10 = r2.g;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        r11 = 1;
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        r12 = r2 - r16;
        r7.<init>(r8, r9, r10, r11, r12);	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        r3 = r7;
        goto L_0x006d;
    L_0x0087:
        r2 = r15.getEntity();	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        if (r2 == 0) goto L_0x00fd;
    L_0x008d:
        r2 = r15.getEntity();	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        r0 = r18;
        r5 = r0.a(r2);	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
    L_0x0097:
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r8 = r8 - r16;
        r2 = a;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        if (r2 != 0) goto L_0x00a8;
    L_0x00a1:
        r2 = d;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r10 = (long) r2;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r2 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r2 <= 0) goto L_0x00df;
    L_0x00a8:
        r7 = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]";
        r2 = 5;
        r10 = new java.lang.Object[r2];	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r2 = 0;
        r10[r2] = r19;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r2 = 1;
        r8 = java.lang.Long.valueOf(r8);	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r10[r2] = r8;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r8 = 2;
        if (r5 == 0) goto L_0x0101;
    L_0x00bb:
        r2 = r5.length;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
    L_0x00c0:
        r10[r8] = r2;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r2 = 3;
        r3 = r3.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r10[r2] = r3;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r2 = 4;
        r3 = r19.getRetryPolicy();	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r3 = r3.b();	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r10[r2] = r3;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        com.android.volley.x.b(r7, r10);	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
    L_0x00df:
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r4 < r2) goto L_0x00e7;
    L_0x00e3:
        r2 = 299; // 0x12b float:4.19E-43 double:1.477E-321;
        if (r4 <= r2) goto L_0x0105;
    L_0x00e7:
        r2 = new java.io.IOException;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r2.<init>();	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        throw r2;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
    L_0x00ed:
        r2 = move-exception;
        r2 = "socket";
        r3 = new com.android.volley.v;
        r3.<init>();
        r0 = r19;
        a(r2, r0, r3);
        goto L_0x0004;
    L_0x00fd:
        r2 = 0;
        r5 = new byte[r2];	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d2 }
        goto L_0x0097;
    L_0x0101:
        r2 = "null";
        goto L_0x00c0;
    L_0x0105:
        r3 = new com.android.volley.l;	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r7 = 0;
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        r8 = r8 - r16;
        r3.<init>(r4, r5, r6, r7, r8);	 Catch:{ SocketTimeoutException -> 0x00ed, ConnectTimeoutException -> 0x0113, MalformedURLException -> 0x0123, IOException -> 0x01d7 }
        goto L_0x006d;
    L_0x0113:
        r2 = move-exception;
        r2 = "connection";
        r3 = new com.android.volley.v;
        r3.<init>();
        r0 = r19;
        a(r2, r0, r3);
        goto L_0x0004;
    L_0x0123:
        r2 = move-exception;
        r3 = new java.lang.RuntimeException;
        r4 = new java.lang.StringBuilder;
        r5 = "Bad URL ";
        r4.<init>(r5);
        r5 = r19.getUrl();
        r4 = r4.append(r5);
        r4 = r4.toString();
        r3.<init>(r4, r2);
        throw r3;
    L_0x013e:
        r2 = move-exception;
        r5 = r14;
    L_0x0140:
        if (r3 == 0) goto L_0x0186;
    L_0x0142:
        r2 = r3.getStatusLine();
        r4 = r2.getStatusCode();
        r2 = "Unexpected response code %d for %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];
        r7 = 0;
        r8 = java.lang.Integer.valueOf(r4);
        r3[r7] = r8;
        r7 = 1;
        r8 = r19.getUrl();
        r3[r7] = r8;
        com.android.volley.x.c(r2, r3);
        if (r5 == 0) goto L_0x01c3;
    L_0x0163:
        r3 = new com.android.volley.l;
        r7 = 0;
        r8 = android.os.SystemClock.elapsedRealtime();
        r8 = r8 - r16;
        r3.<init>(r4, r5, r6, r7, r8);
        r2 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r4 == r2) goto L_0x0177;
    L_0x0173:
        r2 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r4 != r2) goto L_0x018c;
    L_0x0177:
        r2 = "auth";
        r4 = new com.android.volley.a;
        r4.<init>(r3);
        r0 = r19;
        a(r2, r0, r4);
        goto L_0x0004;
    L_0x0186:
        r3 = new com.android.volley.m;
        r3.<init>(r2);
        throw r3;
    L_0x018c:
        r2 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r4 < r2) goto L_0x019a;
    L_0x0190:
        r2 = 499; // 0x1f3 float:6.99E-43 double:2.465E-321;
        if (r4 > r2) goto L_0x019a;
    L_0x0194:
        r2 = new com.android.volley.e;
        r2.<init>(r3);
        throw r2;
    L_0x019a:
        r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r4 < r2) goto L_0x01bd;
    L_0x019e:
        r2 = 599; // 0x257 float:8.4E-43 double:2.96E-321;
        if (r4 > r2) goto L_0x01bd;
    L_0x01a2:
        r2 = r19.shouldRetryServerErrors();
        if (r2 == 0) goto L_0x01b7;
    L_0x01a8:
        r2 = "server";
        r4 = new com.android.volley.u;
        r4.<init>(r3);
        r0 = r19;
        a(r2, r0, r4);
        goto L_0x0004;
    L_0x01b7:
        r2 = new com.android.volley.u;
        r2.<init>(r3);
        throw r2;
    L_0x01bd:
        r2 = new com.android.volley.u;
        r2.<init>(r3);
        throw r2;
    L_0x01c3:
        r2 = "network";
        r3 = new com.android.volley.k;
        r3.<init>();
        r0 = r19;
        a(r2, r0, r3);
        goto L_0x0004;
    L_0x01d2:
        r2 = move-exception;
        r5 = r14;
        r3 = r15;
        goto L_0x0140;
    L_0x01d7:
        r2 = move-exception;
        r3 = r15;
        goto L_0x0140;
        */
    }

    private static void a(String str, Request<?> request, w wVar) throws w {
        t retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.a(wVar);
            Object[] objArr = new Object[2];
            objArr[0] = str;
            timeoutMs = Integer.valueOf(timeoutMs);
            objArr[1] = timeoutMs;
            request.addMarker(String.format("%s-retry [timeout=%s]", objArr));
        } catch (w e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
            throw e;
        }
    }

    private byte[] a(HttpEntity httpEntity) throws IOException, u {
        s sVar = new s(this.c, (int) httpEntity.getContentLength());
        try {
            InputStream content = httpEntity.getContent();
            if (content == null) {
                throw new u();
            }
            byte[] a = this.c.a((int) JsInterface.MSG_JS_COLLECT_WEBSITE);
            while (true) {
                int read = content.read(a);
                if (read == -1) {
                    break;
                }
                sVar.write(a, 0, read);
            }
            byte[] toByteArray = sVar.toByteArray();
            try {
                httpEntity.consumeContent();
            } catch (IOException e) {
                x.a("Error occured when calling consumingContent", new Object[0]);
            }
            this.c.a(a);
            sVar.close();
            return toByteArray;
        } catch (Throwable th) {
            try {
                httpEntity.consumeContent();
            } catch (IOException e2) {
                x.a("Error occured when calling consumingContent", new Object[0]);
            }
        }
    }

    private static Map<String, String> a(Header[] headerArr) {
        Map<String, String> treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return treeMap;
    }
}
