package com.xunlei.XLStat.b;

public class a {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r8) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.XLStat.b.a.a(android.content.Context):java.lang.String");
        /*
        r1 = 0;
        r0 = "connectivity";
        r0 = r8.getSystemService(r0);	 Catch:{ Exception -> 0x00d5 }
        r0 = (android.net.ConnectivityManager) r0;	 Catch:{ Exception -> 0x00d5 }
        r2 = "NetworkInfo";
        r3 = "networkType";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d5 }
        r5 = "conMan: ";
        r4.<init>(r5);	 Catch:{ Exception -> 0x00d5 }
        r4 = r4.append(r0);	 Catch:{ Exception -> 0x00d5 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x00d5 }
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r2, r3, r4);	 Catch:{ Exception -> 0x00d5 }
        if (r0 != 0) goto L_0x0029;
    L_0x0025:
        r0 = "unknown";
    L_0x0028:
        return r0;
    L_0x0029:
        r2 = 0;
        r2 = r0.getNetworkInfo(r2);	 Catch:{ Exception -> 0x00d5 }
        r3 = 1;
        r0 = r0.getNetworkInfo(r3);	 Catch:{ Exception -> 0x00d5 }
        r3 = "NetworkInfo";
        r4 = "networkType";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d5 }
        r6 = "mobileInfo: ";
        r5.<init>(r6);	 Catch:{ Exception -> 0x00d5 }
        r5 = r5.append(r2);	 Catch:{ Exception -> 0x00d5 }
        r6 = ", wifiInfo: ";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x00d5 }
        r5 = r5.append(r0);	 Catch:{ Exception -> 0x00d5 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x00d5 }
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r3, r4, r5);	 Catch:{ Exception -> 0x00d5 }
        if (r2 != 0) goto L_0x005f;
    L_0x0059:
        if (r0 != 0) goto L_0x005f;
    L_0x005b:
        r0 = "unknown";
        goto L_0x0028;
    L_0x005f:
        if (r2 == 0) goto L_0x00f9;
    L_0x0061:
        r2 = r2.getState();	 Catch:{ Exception -> 0x00d5 }
        r3 = "NetworkInfo";
        r4 = "networkType";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ec }
        r6 = "mobile to string: ";
        r5.<init>(r6);	 Catch:{ Exception -> 0x00ec }
        r6 = r2.toString();	 Catch:{ Exception -> 0x00ec }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x00ec }
        r5 = r5.toString();	 Catch:{ Exception -> 0x00ec }
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r3, r4, r5);	 Catch:{ Exception -> 0x00ec }
    L_0x0082:
        if (r0 == 0) goto L_0x00f7;
    L_0x0084:
        r0 = r0.getState();	 Catch:{ Exception -> 0x00ec }
        r1 = "NetworkInfo";
        r3 = "networkType";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f2 }
        r5 = "wifi to string: ";
        r4.<init>(r5);	 Catch:{ Exception -> 0x00f2 }
        r5 = r0.toString();	 Catch:{ Exception -> 0x00f2 }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00f2 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x00f2 }
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r1, r3, r4);	 Catch:{ Exception -> 0x00f2 }
    L_0x00a5:
        r1 = r2;
    L_0x00a6:
        r2 = "NetworkInfo";
        r3 = "networkType";
        r4 = new java.lang.StringBuilder;
        r5 = "mobile: ";
        r4.<init>(r5);
        r4 = r4.append(r1);
        r5 = ", wifi: ";
        r4 = r4.append(r5);
        r4 = r4.append(r0);
        r4 = r4.toString();
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r2, r3, r4);
        if (r1 == 0) goto L_0x00dc;
    L_0x00cc:
        r2 = android.net.NetworkInfo.State.CONNECTED;
        if (r1 != r2) goto L_0x00dc;
    L_0x00d0:
        r0 = "3G";
        goto L_0x0028;
    L_0x00d5:
        r0 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x00d8:
        r2.printStackTrace();
        goto L_0x00a6;
    L_0x00dc:
        if (r0 == 0) goto L_0x00e7;
    L_0x00de:
        r1 = android.net.NetworkInfo.State.CONNECTED;
        if (r0 != r1) goto L_0x00e7;
    L_0x00e2:
        r0 = "WIFI";
        goto L_0x0028;
    L_0x00e7:
        r0 = "unknown";
        goto L_0x0028;
    L_0x00ec:
        r0 = move-exception;
        r7 = r0;
        r0 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x00d8;
    L_0x00f2:
        r1 = move-exception;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x00d8;
    L_0x00f7:
        r0 = r1;
        goto L_0x00a5;
    L_0x00f9:
        r2 = r1;
        goto L_0x0082;
        */
    }
}
