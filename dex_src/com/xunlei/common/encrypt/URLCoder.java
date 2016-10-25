package com.xunlei.common.encrypt;

public class URLCoder {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String decode(java.lang.String r2, java.lang.String r3) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.encrypt.URLCoder.decode(java.lang.String, java.lang.String):java.lang.String");
        /*
        if (r3 == 0) goto L_0x0007;
    L_0x0002:
        r0 = java.net.URLDecoder.decode(r2, r3);	 Catch:{ UnsupportedEncodingException -> 0x000b }
        return r0;
    L_0x0007:
        r3 = "UTF-8";
        goto L_0x0002;
    L_0x000b:
        r0 = move-exception;
        r1 = new java.lang.IllegalArgumentException;
        r1.<init>(r0);
        throw r1;
        */
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String encode(java.lang.String r2, java.lang.String r3) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.encrypt.URLCoder.encode(java.lang.String, java.lang.String):java.lang.String");
        /*
        if (r3 == 0) goto L_0x0007;
    L_0x0002:
        r0 = java.net.URLEncoder.encode(r2, r3);	 Catch:{ UnsupportedEncodingException -> 0x000b }
        return r0;
    L_0x0007:
        r3 = "UTF-8";
        goto L_0x0002;
    L_0x000b:
        r0 = move-exception;
        r1 = new java.lang.IllegalArgumentException;
        r1.<init>(r0);
        throw r1;
        */
    }
}
