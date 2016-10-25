package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.x;
import com.sina.weibo.sdk.component.GameManager;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: DiskBasedCache.java
public final class d implements com.android.volley.b {
    private final Map<String, a> a;
    private long b;
    private final File c;
    private final int d;

    // compiled from: DiskBasedCache.java
    static class a {
        public long a;
        public String b;
        public String c;
        public long d;
        public long e;
        public long f;
        public long g;
        public Map<String, String> h;

        private a() {
        }

        public a(String str, com.android.volley.b.a aVar) {
            this.b = str;
            this.a = (long) aVar.a.length;
            this.c = aVar.b;
            this.d = aVar.c;
            this.e = aVar.d;
            this.f = aVar.e;
            this.g = aVar.f;
            this.h = aVar.g;
        }

        public static a a(InputStream inputStream) throws IOException {
            a aVar = new a();
            if (d.a(inputStream) != 538247942) {
                throw new IOException();
            }
            aVar.b = d.c(inputStream);
            aVar.c = d.c(inputStream);
            if (aVar.c.equals(com.umeng.a.d)) {
                aVar.c = null;
            }
            aVar.d = d.b(inputStream);
            aVar.e = d.b(inputStream);
            aVar.f = d.b(inputStream);
            aVar.g = d.b(inputStream);
            aVar.h = d.d(inputStream);
            return aVar;
        }

        public final boolean a(OutputStream outputStream) {
            try {
                d.a(outputStream, 538247942);
                d.a(outputStream, this.b);
                d.a(outputStream, this.c == null ? com.umeng.a.d : this.c);
                d.a(outputStream, this.d);
                d.a(outputStream, this.e);
                d.a(outputStream, this.f);
                d.a(outputStream, this.g);
                Map map = this.h;
                if (map != null) {
                    d.a(outputStream, map.size());
                    for (Entry entry : map.entrySet()) {
                        d.a(outputStream, (String) entry.getKey());
                        d.a(outputStream, (String) entry.getValue());
                    }
                } else {
                    d.a(outputStream, 0);
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                x.b("%s", e.toString());
                return false;
            }
        }
    }

    // compiled from: DiskBasedCache.java
    private static class b extends FilterInputStream {
        private int a;

        private b(InputStream inputStream) {
            super(inputStream);
            this.a = 0;
        }

        public final int read() throws IOException {
            int read = super.read();
            if (read != -1) {
                this.a++;
            }
            return read;
        }

        public final int read(byte[] bArr, int i, int i2) throws IOException {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.a += read;
            }
            return read;
        }
    }

    public d(File file, int i) {
        this.a = new LinkedHashMap(16, 0.75f, true);
        this.b = 0;
        this.c = file;
        this.d = i;
    }

    public d(File file) {
        this(file, 5242880);
    }

    public final synchronized com.android.volley.b.a a(String str) {
        com.android.volley.b.a aVar;
        IOException e;
        b bVar;
        NegativeArraySizeException e2;
        b bVar2;
        Throwable th;
        a aVar2 = (a) this.a.get(str);
        if (aVar2 == null) {
            aVar = null;
        } else {
            File d = d(str);
            try {
                InputStream bVar3 = new b((byte) 0);
                try {
                    a.a(bVar3);
                    byte[] a = a(bVar3, (int) (d.length() - ((long) bVar3.a)));
                    com.android.volley.b.a aVar3 = new com.android.volley.b.a();
                    aVar3.a = a;
                    aVar3.b = aVar2.c;
                    aVar3.c = aVar2.d;
                    aVar3.d = aVar2.e;
                    aVar3.e = aVar2.f;
                    aVar3.f = aVar2.g;
                    aVar3.g = aVar2.h;
                    try {
                        bVar3.close();
                        aVar = aVar3;
                    } catch (IOException e3) {
                        aVar = null;
                    }
                } catch (IOException e4) {
                    e = e4;
                    InputStream inputStream = bVar3;
                    x.b("%s: %s", d.getAbsolutePath(), e.toString());
                    b(str);
                    if (bVar != null) {
                        bVar.close();
                    }
                    aVar = null;
                    return aVar;
                } catch (NegativeArraySizeException e5) {
                    e2 = e5;
                    x.b("%s: %s", d.getAbsolutePath(), e2.toString());
                    b(str);
                    if (bVar2 != null) {
                        bVar2.close();
                    }
                    aVar = null;
                    return aVar;
                }
            } catch (IOException e6) {
                e = e6;
                bVar = null;
                try {
                    x.b("%s: %s", d.getAbsolutePath(), e.toString());
                    b(str);
                    if (bVar != null) {
                        try {
                            bVar.close();
                        } catch (IOException e7) {
                            aVar = null;
                        }
                    }
                    aVar = null;
                } catch (Throwable th2) {
                    th = th2;
                    bVar2 = bVar;
                    if (bVar2 != null) {
                        bVar2.close();
                    }
                    throw th;
                }
                return aVar;
            } catch (NegativeArraySizeException e8) {
                e2 = e8;
                bVar2 = null;
                try {
                    x.b("%s: %s", d.getAbsolutePath(), e2.toString());
                    b(str);
                    if (bVar2 != null) {
                        try {
                            bVar2.close();
                        } catch (IOException e9) {
                            aVar = null;
                        }
                    }
                    aVar = null;
                } catch (Throwable th3) {
                    th = th3;
                    if (bVar2 != null) {
                        bVar2.close();
                    }
                    throw th;
                }
                return aVar;
            } catch (Throwable th4) {
                th = th4;
                bVar2 = null;
                if (bVar2 != null) {
                    try {
                        bVar2.close();
                    } catch (IOException e10) {
                        aVar = null;
                    }
                }
                throw th;
            }
        }
        return aVar;
    }

    public final synchronized void a() {
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.d.a():void");
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Try/catch wrap count limit reached in com.android.volley.toolbox.d.a():void
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
        r0 = 0;
        monitor-enter(r9);
        r1 = r9.c;	 Catch:{ all -> 0x007a }
        r1 = r1.exists();	 Catch:{ all -> 0x007a }
        if (r1 != 0) goto L_0x0026;
    L_0x000a:
        r0 = r9.c;	 Catch:{ all -> 0x007a }
        r0 = r0.mkdirs();	 Catch:{ all -> 0x007a }
        if (r0 != 0) goto L_0x0024;
    L_0x0012:
        r0 = "Unable to create cache dir %s";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x007a }
        r2 = 0;
        r3 = r9.c;	 Catch:{ all -> 0x007a }
        r3 = r3.getAbsolutePath();	 Catch:{ all -> 0x007a }
        r1[r2] = r3;	 Catch:{ all -> 0x007a }
        com.android.volley.x.c(r0, r1);	 Catch:{ all -> 0x007a }
    L_0x0024:
        monitor-exit(r9);
        return;
    L_0x0026:
        r1 = r9.c;	 Catch:{ all -> 0x007a }
        r3 = r1.listFiles();	 Catch:{ all -> 0x007a }
        if (r3 == 0) goto L_0x0024;
    L_0x002e:
        r4 = r3.length;	 Catch:{ all -> 0x007a }
        r2 = r0;
    L_0x0030:
        if (r2 >= r4) goto L_0x0024;
    L_0x0032:
        r5 = r3[r2];	 Catch:{ all -> 0x007a }
        r1 = 0;
        r0 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x0055, NegativeArraySizeException -> 0x0064, all -> 0x0073 }
        r6 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x0055, NegativeArraySizeException -> 0x0064, all -> 0x0073 }
        r6.<init>(r5);	 Catch:{ IOException -> 0x0055, NegativeArraySizeException -> 0x0064, all -> 0x0073 }
        r0.<init>(r6);	 Catch:{ IOException -> 0x0055, NegativeArraySizeException -> 0x0064, all -> 0x0073 }
        r1 = com.android.volley.toolbox.d.a.a(r0);	 Catch:{ IOException -> 0x0088, NegativeArraySizeException -> 0x0086 }
        r6 = r5.length();	 Catch:{ IOException -> 0x0088, NegativeArraySizeException -> 0x0086 }
        r1.a = r6;	 Catch:{ IOException -> 0x0088, NegativeArraySizeException -> 0x0086 }
        r6 = r1.b;	 Catch:{ IOException -> 0x0088, NegativeArraySizeException -> 0x0086 }
        r9.a(r6, r1);	 Catch:{ IOException -> 0x0088, NegativeArraySizeException -> 0x0086 }
        r0.close();	 Catch:{ IOException -> 0x007d }
    L_0x0051:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0030;
    L_0x0055:
        r0 = move-exception;
        r0 = r1;
    L_0x0057:
        if (r5 == 0) goto L_0x005c;
    L_0x0059:
        r5.delete();	 Catch:{ all -> 0x0081 }
    L_0x005c:
        if (r0 == 0) goto L_0x0051;
    L_0x005e:
        r0.close();	 Catch:{ IOException -> 0x0062 }
        goto L_0x0051;
    L_0x0062:
        r0 = move-exception;
        goto L_0x0051;
    L_0x0064:
        r0 = move-exception;
        r0 = r1;
    L_0x0066:
        if (r5 == 0) goto L_0x006b;
    L_0x0068:
        r5.delete();	 Catch:{ all -> 0x0081 }
    L_0x006b:
        if (r0 == 0) goto L_0x0051;
    L_0x006d:
        r0.close();	 Catch:{ IOException -> 0x0071 }
        goto L_0x0051;
    L_0x0071:
        r0 = move-exception;
        goto L_0x0051;
    L_0x0073:
        r0 = move-exception;
    L_0x0074:
        if (r1 == 0) goto L_0x0079;
    L_0x0076:
        r1.close();	 Catch:{ IOException -> 0x007f }
    L_0x0079:
        throw r0;	 Catch:{ all -> 0x007a }
    L_0x007a:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
    L_0x007d:
        r0 = move-exception;
        goto L_0x0051;
    L_0x007f:
        r1 = move-exception;
        goto L_0x0079;
    L_0x0081:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x0074;
    L_0x0086:
        r1 = move-exception;
        goto L_0x0066;
    L_0x0088:
        r1 = move-exception;
        goto L_0x0057;
        */
    }

    public final synchronized void a(String str, com.android.volley.b.a aVar) {
        int i = 0;
        synchronized (this) {
            int length = aVar.a.length;
            if (this.b + ((long) length) >= ((long) this.d)) {
                int i2;
                if (x.b) {
                    x.a("Pruning old cache entries.", new Object[0]);
                }
                long j = this.b;
                long elapsedRealtime = SystemClock.elapsedRealtime();
                Iterator it = this.a.entrySet().iterator();
                while (it.hasNext()) {
                    a aVar2 = (a) ((Entry) it.next()).getValue();
                    if (d(aVar2.b).delete()) {
                        this.b -= aVar2.a;
                    } else {
                        x.b("Could not delete cache entry for key=%s, filename=%s", aVar2.b, c(aVar2.b));
                    }
                    it.remove();
                    i2 = i + 1;
                    if (((float) (this.b + ((long) length))) < ((float) this.d) * 0.9f) {
                        break;
                    }
                    i = i2;
                }
                i2 = i;
                if (x.b) {
                    x.a("pruned %d files, %d bytes, %d ms", Integer.valueOf(i2), Long.valueOf(this.b - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                }
            }
            File d = d(str);
            try {
                OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(d));
                a aVar3 = new a(str, aVar);
                if (aVar3.a(bufferedOutputStream)) {
                    bufferedOutputStream.write(aVar.a);
                    bufferedOutputStream.close();
                    a(str, aVar3);
                } else {
                    bufferedOutputStream.close();
                    x.b("Failed to write header for %s", d.getAbsolutePath());
                    throw new IOException();
                }
            } catch (IOException e) {
                if (!d.delete()) {
                    x.b("Could not clean up file %s", d.getAbsolutePath());
                }
            }
        }
    }

    private synchronized void b(String str) {
        boolean delete = d(str).delete();
        a aVar = (a) this.a.get(str);
        if (aVar != null) {
            this.b -= aVar.a;
            this.a.remove(str);
        }
        if (!delete) {
            x.b("Could not delete cache entry for key=%s, filename=%s", str, c(str));
        }
    }

    private static String c(String str) {
        int length = str.length() / 2;
        return String.valueOf(str.substring(0, length).hashCode()) + String.valueOf(str.substring(length).hashCode());
    }

    private File d(String str) {
        return new File(this.c, c(str));
    }

    private void a(String str, a aVar) {
        if (this.a.containsKey(str)) {
            a aVar2 = (a) this.a.get(str);
            this.b = (aVar.a - aVar2.a) + this.b;
        } else {
            this.b += aVar.a;
        }
        this.a.put(str, aVar);
    }

    private static byte[] a(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read == -1) {
                break;
            }
            i2 += read;
        }
        if (i2 == i) {
            return bArr;
        }
        throw new IOException(new StringBuilder("Expected ").append(i).append(" bytes, read ").append(i2).append(" bytes").toString());
    }

    private static int e(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    static void a(OutputStream outputStream, int i) throws IOException {
        outputStream.write((i >> 0) & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write((i >> 24) & 255);
    }

    static int a(InputStream inputStream) throws IOException {
        return ((((e(inputStream) << 0) | 0) | (e(inputStream) << 8)) | (e(inputStream) << 16)) | (e(inputStream) << 24);
    }

    static void a(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) ((int) (j >>> null)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static long b(InputStream inputStream) throws IOException {
        return (((((((0 | ((((long) e(inputStream)) & 255) << null)) | ((((long) e(inputStream)) & 255) << 8)) | ((((long) e(inputStream)) & 255) << 16)) | ((((long) e(inputStream)) & 255) << 24)) | ((((long) e(inputStream)) & 255) << 32)) | ((((long) e(inputStream)) & 255) << 40)) | ((((long) e(inputStream)) & 255) << 48)) | ((((long) e(inputStream)) & 255) << 56);
    }

    static void a(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes(GameManager.DEFAULT_CHARSET);
        a(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    static String c(InputStream inputStream) throws IOException {
        return new String(a(inputStream, (int) b(inputStream)), GameManager.DEFAULT_CHARSET);
    }

    static Map<String, String> d(InputStream inputStream) throws IOException {
        int a = a(inputStream);
        Map<String, String> emptyMap = a == 0 ? Collections.emptyMap() : new HashMap(a);
        for (int i = 0; i < a; i++) {
            emptyMap.put(c(inputStream).intern(), c(inputStream).intern());
        }
        return emptyMap;
    }
}
