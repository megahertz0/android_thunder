package com.xunlei.downloadprovider.member;

import android.graphics.Bitmap;

// compiled from: VerifyProtocol.java
public final class a {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final int h;

    // compiled from: VerifyProtocol.java
    public class a {
        public Bitmap a;
        public String b;
    }

    public a() {
        this.a = "DownloadVerifyProcotol";
        this.b = "http://verify2.xunlei.com/";
        this.c = "http://verify.xunlei.com/";
        this.d = "http://verify3.xunlei.com/";
        this.e = "%simage?cachetime=%s";
        this.f = "%simage?t=MEA&cachetime=%s";
        this.g = "%simage?t=SEA&cachetime=%s";
        this.h = 30000;
    }

    public final com.xunlei.downloadprovider.member.a.a a() {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.member.a.a():com.xunlei.downloadprovider.member.a$a");
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Try/catch wrap count limit reached in com.xunlei.downloadprovider.member.a.a():com.xunlei.downloadprovider.member.a$a
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:54)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:40)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:16)
	at jadx.core.ProcessClass.process(ProcessClass.java:22)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:209)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:133)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
	at java.lang.Thread.run(Unknown Source)
*/
        /*
        this = this;
        r14 = 1;
        r13 = 0;
        r4 = 0;
        r11 = "%simage?t=SEA&cachetime=%s";
        r2 = r4;
        r6 = r4;
        r7 = r4;
    L_0x0009:
        r3 = "http://verify3.xunlei.com/";
        r3 = r3.equals(r2);
        if (r3 != 0) goto L_0x0150;
    L_0x0012:
        if (r6 != 0) goto L_0x0150;
    L_0x0014:
        if (r2 != 0) goto L_0x00be;
    L_0x0016:
        r5 = "http://verify2.xunlei.com/";
    L_0x0019:
        r2 = 2;
        r2 = new java.lang.Object[r2];
        r2[r13] = r5;
        r8 = java.lang.System.currentTimeMillis();
        r3 = java.lang.String.valueOf(r8);
        r2[r14] = r3;
        r2 = java.lang.String.format(r11, r2);
        r9 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x015e, all -> 0x0151 }
        r9.<init>();	 Catch:{ Exception -> 0x015e, all -> 0x0151 }
        r3 = new java.net.URL;	 Catch:{ Exception -> 0x0168, all -> 0x0155 }
        r3.<init>(r2);	 Catch:{ Exception -> 0x0168, all -> 0x0155 }
        r2 = r3.openConnection();	 Catch:{ Exception -> 0x0168, all -> 0x0155 }
        r0 = r2;
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x0168, all -> 0x0155 }
        r3 = r0;
        r2 = "User-Agent";
        r8 = "Mozilla/4.0";
        r3.setRequestProperty(r2, r8);	 Catch:{ Exception -> 0x0171, all -> 0x0158 }
        r2 = "Connection";
        r8 = "Keep-Alive";
        r3.setRequestProperty(r2, r8);	 Catch:{ Exception -> 0x0171, all -> 0x0158 }
        r2 = "Accept";
        r8 = "*/*";
        r3.setRequestProperty(r2, r8);	 Catch:{ Exception -> 0x0171, all -> 0x0158 }
        r2 = "Accept-Encoding";
        r8 = "gzip, deflate";
        r3.setRequestProperty(r2, r8);	 Catch:{ Exception -> 0x0171, all -> 0x0158 }
        r2 = 0;
        r3.setUseCaches(r2);	 Catch:{ Exception -> 0x0171, all -> 0x0158 }
        r2 = 1;
        r3.setDoInput(r2);	 Catch:{ Exception -> 0x0171, all -> 0x0158 }
        r2 = 0;
        r3.setDoOutput(r2);	 Catch:{ Exception -> 0x0171, all -> 0x0158 }
        r2 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r3.setConnectTimeout(r2);	 Catch:{ Exception -> 0x0171, all -> 0x0158 }
        r2 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r3.setReadTimeout(r2);	 Catch:{ Exception -> 0x0171, all -> 0x0158 }
        r2 = "GET";
        r3.setRequestMethod(r2);	 Catch:{ Exception -> 0x0171, all -> 0x0158 }
        r8 = r3.getInputStream();	 Catch:{ Exception -> 0x0171, all -> 0x0158 }
        r2 = r3.getResponseCode();	 Catch:{ Exception -> 0x017d, all -> 0x012e }
        r10 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r10 != r2) goto L_0x0188;
    L_0x008a:
        r10 = new com.xunlei.downloadprovider.member.a$a;	 Catch:{ Exception -> 0x017d, all -> 0x012e }
        r0 = r17;
        r10.<init>();	 Catch:{ Exception -> 0x017d, all -> 0x012e }
        r2 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r2 = new byte[r2];	 Catch:{ Exception -> 0x00a1, all -> 0x012e }
    L_0x0095:
        r7 = -1;
        r12 = r8.read(r2);	 Catch:{ Exception -> 0x00a1, all -> 0x012e }
        if (r7 == r12) goto L_0x00df;
    L_0x009c:
        r7 = 0;
        r9.write(r2, r7, r12);	 Catch:{ Exception -> 0x00a1, all -> 0x012e }
        goto L_0x0095;
    L_0x00a1:
        r2 = move-exception;
        r7 = r3;
        r3 = r10;
        r15 = r6;
        r6 = r2;
        r2 = r15;
    L_0x00a7:
        r6.printStackTrace();	 Catch:{ all -> 0x015a }
        if (r9 == 0) goto L_0x00af;
    L_0x00ac:
        r9.close();	 Catch:{ Exception -> 0x0124 }
    L_0x00af:
        if (r8 == 0) goto L_0x00b4;
    L_0x00b1:
        r8.close();	 Catch:{ Exception -> 0x0129 }
    L_0x00b4:
        if (r7 == 0) goto L_0x014b;
    L_0x00b6:
        r7.disconnect();
        r6 = r2;
        r7 = r3;
        r2 = r5;
        goto L_0x0009;
    L_0x00be:
        r3 = "http://verify2.xunlei.com/";
        r3 = r2.equals(r3);
        if (r3 == 0) goto L_0x00cc;
    L_0x00c7:
        r5 = "http://verify.xunlei.com/";
        goto L_0x0019;
    L_0x00cc:
        r3 = "http://verify.xunlei.com/";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x00da;
    L_0x00d5:
        r5 = "http://verify3.xunlei.com/";
        goto L_0x0019;
    L_0x00da:
        r5 = "none";
        goto L_0x0019;
    L_0x00df:
        r2 = r9.toByteArray();	 Catch:{ Exception -> 0x00a1, all -> 0x012e }
        r7 = 0;
        r12 = r2.length;	 Catch:{ Exception -> 0x00a1, all -> 0x012e }
        r6 = android.graphics.BitmapFactory.decodeByteArray(r2, r7, r12);	 Catch:{ Exception -> 0x00a1, all -> 0x012e }
        r10.a = r6;	 Catch:{ Exception -> 0x00a1, all -> 0x012e }
        r2 = r3.getHeaderFields();	 Catch:{ Exception -> 0x00a1, all -> 0x012e }
        r7 = new com.xunlei.downloadprovider.member.register.a.a;	 Catch:{ Exception -> 0x00a1, all -> 0x012e }
        r7.<init>();	 Catch:{ Exception -> 0x00a1, all -> 0x012e }
        r7.a = r2;	 Catch:{ Exception -> 0x00a1, all -> 0x012e }
        r7.a();	 Catch:{ Exception -> 0x00a1, all -> 0x012e }
        r2 = "VERIFY_KEY";
        r7 = r7.b;	 Catch:{ Exception -> 0x00a1, all -> 0x012e }
        r2 = r7.get(r2);	 Catch:{ Exception -> 0x00a1, all -> 0x012e }
        r2 = (java.lang.String) r2;	 Catch:{ Exception -> 0x00a1, all -> 0x012e }
        r10.b = r2;	 Catch:{ Exception -> 0x00a1, all -> 0x012e }
        r2 = r6;
        r6 = r10;
    L_0x0108:
        r9.close();	 Catch:{ Exception -> 0x011a }
    L_0x010b:
        if (r8 == 0) goto L_0x0110;
    L_0x010d:
        r8.close();	 Catch:{ Exception -> 0x011f }
    L_0x0110:
        if (r3 == 0) goto L_0x014a;
    L_0x0112:
        r3.disconnect();
        r7 = r6;
        r6 = r2;
        r2 = r5;
        goto L_0x0009;
    L_0x011a:
        r7 = move-exception;
        r7.printStackTrace();
        goto L_0x010b;
    L_0x011f:
        r7 = move-exception;
        r7.printStackTrace();
        goto L_0x0110;
    L_0x0124:
        r6 = move-exception;
        r6.printStackTrace();
        goto L_0x00af;
    L_0x0129:
        r6 = move-exception;
        r6.printStackTrace();
        goto L_0x00b4;
    L_0x012e:
        r2 = move-exception;
        r4 = r8;
    L_0x0130:
        if (r9 == 0) goto L_0x0135;
    L_0x0132:
        r9.close();	 Catch:{ Exception -> 0x0140 }
    L_0x0135:
        if (r4 == 0) goto L_0x013a;
    L_0x0137:
        r4.close();	 Catch:{ Exception -> 0x0145 }
    L_0x013a:
        if (r3 == 0) goto L_0x013f;
    L_0x013c:
        r3.disconnect();
    L_0x013f:
        throw r2;
    L_0x0140:
        r5 = move-exception;
        r5.printStackTrace();
        goto L_0x0135;
    L_0x0145:
        r4 = move-exception;
        r4.printStackTrace();
        goto L_0x013a;
    L_0x014a:
        r3 = r6;
    L_0x014b:
        r6 = r2;
        r7 = r3;
        r2 = r5;
        goto L_0x0009;
    L_0x0150:
        return r7;
    L_0x0151:
        r2 = move-exception;
        r3 = r4;
        r9 = r4;
        goto L_0x0130;
    L_0x0155:
        r2 = move-exception;
        r3 = r4;
        goto L_0x0130;
    L_0x0158:
        r2 = move-exception;
        goto L_0x0130;
    L_0x015a:
        r2 = move-exception;
        r3 = r7;
        r4 = r8;
        goto L_0x0130;
    L_0x015e:
        r2 = move-exception;
        r8 = r4;
        r9 = r4;
        r3 = r7;
        r7 = r4;
        r15 = r6;
        r6 = r2;
        r2 = r15;
        goto L_0x00a7;
    L_0x0168:
        r2 = move-exception;
        r8 = r4;
        r3 = r7;
        r7 = r4;
        r15 = r6;
        r6 = r2;
        r2 = r15;
        goto L_0x00a7;
    L_0x0171:
        r2 = move-exception;
        r8 = r4;
        r15 = r3;
        r3 = r7;
        r7 = r15;
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x00a7;
    L_0x017d:
        r2 = move-exception;
        r15 = r2;
        r2 = r6;
        r6 = r15;
        r16 = r3;
        r3 = r7;
        r7 = r16;
        goto L_0x00a7;
    L_0x0188:
        r2 = r6;
        r6 = r7;
        goto L_0x0108;
        */
    }
}
