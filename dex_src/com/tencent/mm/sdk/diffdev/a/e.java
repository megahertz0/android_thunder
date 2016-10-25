package com.tencent.mm.sdk.diffdev.a;

public final class e {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] b(java.lang.String r4, int r5) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mm.sdk.diffdev.a.e.b(java.lang.String, int):byte[]");
        /*
        r0 = 0;
        if (r4 == 0) goto L_0x0009;
    L_0x0003:
        r1 = r4.length();
        if (r1 != 0) goto L_0x000a;
    L_0x0009:
        return r0;
    L_0x000a:
        r1 = new org.apache.http.impl.client.DefaultHttpClient;
        r1.<init>();
        r2 = new org.apache.http.client.methods.HttpGet;
        r2.<init>(r4);
        if (r5 < 0) goto L_0x001d;
    L_0x0016:
        r3 = r1.getParams();	 Catch:{ Exception -> 0x0041, IncompatibleClassChangeError -> 0x005b, Throwable -> 0x006c }
        org.apache.http.params.HttpConnectionParams.setSoTimeout(r3, r5);	 Catch:{ Exception -> 0x0041, IncompatibleClassChangeError -> 0x005b, Throwable -> 0x006c }
    L_0x001d:
        r1 = r1.execute(r2);	 Catch:{ Exception -> 0x0041, IncompatibleClassChangeError -> 0x005b, Throwable -> 0x006c }
        r2 = r1.getStatusLine();	 Catch:{ Exception -> 0x0041, IncompatibleClassChangeError -> 0x005b, Throwable -> 0x006c }
        r2 = r2.getStatusCode();	 Catch:{ Exception -> 0x0041, IncompatibleClassChangeError -> 0x005b, Throwable -> 0x006c }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 == r3) goto L_0x0052;
    L_0x002d:
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0041, IncompatibleClassChangeError -> 0x005b, Throwable -> 0x006c }
        r3 = "httpGet fail, status code = ";
        r2.<init>(r3);	 Catch:{ Exception -> 0x0041, IncompatibleClassChangeError -> 0x005b, Throwable -> 0x006c }
        r1 = r1.getStatusLine();	 Catch:{ Exception -> 0x0041, IncompatibleClassChangeError -> 0x005b, Throwable -> 0x006c }
        r1 = r1.getStatusCode();	 Catch:{ Exception -> 0x0041, IncompatibleClassChangeError -> 0x005b, Throwable -> 0x006c }
        r2.append(r1);	 Catch:{ Exception -> 0x0041, IncompatibleClassChangeError -> 0x005b, Throwable -> 0x006c }
        goto L_0x0009;
    L_0x0041:
        r1 = move-exception;
        r2 = new java.lang.StringBuilder;
        r3 = "httpGet, Exception ex = ";
        r2.<init>(r3);
        r1 = r1.getMessage();
        r2.append(r1);
        goto L_0x0009;
    L_0x0052:
        r1 = r1.getEntity();	 Catch:{ Exception -> 0x0041, IncompatibleClassChangeError -> 0x005b, Throwable -> 0x006c }
        r0 = org.apache.http.util.EntityUtils.toByteArray(r1);	 Catch:{ Exception -> 0x0041, IncompatibleClassChangeError -> 0x005b, Throwable -> 0x006c }
        goto L_0x0009;
    L_0x005b:
        r1 = move-exception;
        r2 = new java.lang.StringBuilder;
        r3 = "httpGet, IncompatibleClassChangeError ex = ";
        r2.<init>(r3);
        r1 = r1.getMessage();
        r2.append(r1);
        goto L_0x0009;
    L_0x006c:
        r1 = move-exception;
        r2 = new java.lang.StringBuilder;
        r3 = "httpGet, Throwable ex = ";
        r2.<init>(r3);
        r1 = r1.getMessage();
        r2.append(r1);
        goto L_0x0009;
        */
    }
}
