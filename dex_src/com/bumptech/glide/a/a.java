package com.bumptech.glide.a;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.android.agoo.message.MessageService;

// compiled from: DiskLruCache.java
public final class a implements Closeable {
    final ThreadPoolExecutor a;
    private final File b;
    private final File c;
    private final File d;
    private final File e;
    private final int f;
    private long g;
    private final int h;
    private long i;
    private Writer j;
    private final LinkedHashMap<String, b> k;
    private int l;
    private long m;
    private final Callable<Void> n;

    // compiled from: DiskLruCache.java
    public final class a {
        final b a;
        final boolean[] b;
        public boolean c;

        private a(b bVar) {
            this.a = bVar;
            this.b = bVar.e ? null : new boolean[a.this.h];
        }

        public final File a() throws IOException {
            File file;
            synchronized (a.this) {
                if (this.a.f != this) {
                    throw new IllegalStateException();
                }
                if (!this.a.e) {
                    this.b[0] = true;
                }
                file = a.this[0];
                if (!a.this.b.exists()) {
                    a.this.b.mkdirs();
                }
            }
            return file;
        }

        public final void b() throws IOException {
            a.this.a(this, false);
        }

        public final void c() {
            if (!this.c) {
                try {
                    b();
                } catch (IOException e) {
                }
            }
        }
    }

    // compiled from: DiskLruCache.java
    private final class b {
        final String a;
        final long[] b;
        File[] c;
        File[] d;
        boolean e;
        com.bumptech.glide.a.a.a f;
        long g;

        private b(String str) {
            this.a = str;
            this.b = new long[a.this];
            this.c = new File[a.this];
            this.d = new File[a.this];
            StringBuilder append = new StringBuilder(str).append('.');
            int length = append.length();
            for (int i = 0; i < a.this; i++) {
                append.append(i);
                this.c[i] = new File(a.this.b, append.toString());
                append.append(".tmp");
                this.d[i] = new File(a.this.b, append.toString());
                append.setLength(length);
            }
        }

        public final String a() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long j : this.b) {
                stringBuilder.append(' ').append(j);
            }
            return stringBuilder.toString();
        }

        final void a(String[] strArr) throws IOException {
            if (strArr.length != a.this) {
                throw b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.b[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw b(strArr);
                }
            }
        }

        private static IOException b(String[] strArr) throws IOException {
            throw new IOException(new StringBuilder("unexpected journal line: ").append(Arrays.toString(strArr)).toString());
        }
    }

    // compiled from: DiskLruCache.java
    public final class c {
        public final File[] a;
        private final String c;
        private final long d;
        private final long[] e;

        private c(String str, long j, File[] fileArr, long[] jArr) {
            this.c = str;
            this.d = j;
            this.a = fileArr;
            this.e = jArr;
        }
    }

    private a(File file, long j) {
        this.i = 0;
        this.k = new LinkedHashMap(0, 0.75f, true);
        this.m = 0;
        this.a = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.n = new b(this);
        this.b = file;
        this.f = 1;
        this.c = new File(file, "journal");
        this.d = new File(file, "journal.tmp");
        this.e = new File(file, "journal.bkp");
        this.h = 1;
        this.g = j;
    }

    public static a a(File file, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                a(file2, file3, false);
            }
        }
        a aVar = new a(file, j);
        if (aVar.c.exists()) {
            try {
                aVar.a();
                aVar.b();
                return aVar;
            } catch (IOException e) {
                System.out.println(new StringBuilder("DiskLruCache ").append(file).append(" is corrupt: ").append(e.getMessage()).append(", removing").toString());
                aVar.close();
                e.a(aVar.b);
            }
        }
        file.mkdirs();
        aVar = new a(file, j);
        aVar.c();
        return aVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a() throws java.io.IOException {
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.a.a.a():void");
        /*
        this = this;
        r11 = 5;
        r1 = 1;
        r2 = 0;
        r10 = -1;
        r5 = new com.bumptech.glide.a.c;
        r0 = new java.io.FileInputStream;
        r3 = r12.c;
        r0.<init>(r3);
        r3 = com.bumptech.glide.a.e.a;
        r5.<init>(r0, r3);
        r0 = r5.a();	 Catch:{ all -> 0x0097 }
        r3 = r5.a();	 Catch:{ all -> 0x0097 }
        r4 = r5.a();	 Catch:{ all -> 0x0097 }
        r6 = r5.a();	 Catch:{ all -> 0x0097 }
        r7 = r5.a();	 Catch:{ all -> 0x0097 }
        r8 = "libcore.io.DiskLruCache";
        r8 = r8.equals(r0);	 Catch:{ all -> 0x0097 }
        if (r8 == 0) goto L_0x0059;
    L_0x002f:
        r8 = "1";
        r8 = r8.equals(r3);	 Catch:{ all -> 0x0097 }
        if (r8 == 0) goto L_0x0059;
    L_0x0038:
        r8 = r12.f;	 Catch:{ all -> 0x0097 }
        r8 = java.lang.Integer.toString(r8);	 Catch:{ all -> 0x0097 }
        r4 = r8.equals(r4);	 Catch:{ all -> 0x0097 }
        if (r4 == 0) goto L_0x0059;
    L_0x0044:
        r4 = r12.h;	 Catch:{ all -> 0x0097 }
        r4 = java.lang.Integer.toString(r4);	 Catch:{ all -> 0x0097 }
        r4 = r4.equals(r6);	 Catch:{ all -> 0x0097 }
        if (r4 == 0) goto L_0x0059;
    L_0x0050:
        r4 = "";
        r4 = r4.equals(r7);	 Catch:{ all -> 0x0097 }
        if (r4 != 0) goto L_0x009c;
    L_0x0059:
        r1 = new java.io.IOException;	 Catch:{ all -> 0x0097 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0097 }
        r4 = "unexpected journal header: [";
        r2.<init>(r4);	 Catch:{ all -> 0x0097 }
        r0 = r2.append(r0);	 Catch:{ all -> 0x0097 }
        r2 = ", ";
        r0 = r0.append(r2);	 Catch:{ all -> 0x0097 }
        r0 = r0.append(r3);	 Catch:{ all -> 0x0097 }
        r2 = ", ";
        r0 = r0.append(r2);	 Catch:{ all -> 0x0097 }
        r0 = r0.append(r6);	 Catch:{ all -> 0x0097 }
        r2 = ", ";
        r0 = r0.append(r2);	 Catch:{ all -> 0x0097 }
        r0 = r0.append(r7);	 Catch:{ all -> 0x0097 }
        r2 = "]";
        r0 = r0.append(r2);	 Catch:{ all -> 0x0097 }
        r0 = r0.toString();	 Catch:{ all -> 0x0097 }
        r1.<init>(r0);	 Catch:{ all -> 0x0097 }
        throw r1;	 Catch:{ all -> 0x0097 }
    L_0x0097:
        r0 = move-exception;
        com.bumptech.glide.a.e.a(r5);
        throw r0;
    L_0x009c:
        r3 = r2;
    L_0x009d:
        r6 = r5.a();	 Catch:{ EOFException -> 0x00bf }
        r0 = 32;
        r7 = r6.indexOf(r0);	 Catch:{ EOFException -> 0x00bf }
        if (r7 != r10) goto L_0x00d8;
    L_0x00a9:
        r0 = new java.io.IOException;	 Catch:{ EOFException -> 0x00bf }
        r4 = new java.lang.StringBuilder;	 Catch:{ EOFException -> 0x00bf }
        r7 = "unexpected journal line: ";
        r4.<init>(r7);	 Catch:{ EOFException -> 0x00bf }
        r4 = r4.append(r6);	 Catch:{ EOFException -> 0x00bf }
        r4 = r4.toString();	 Catch:{ EOFException -> 0x00bf }
        r0.<init>(r4);	 Catch:{ EOFException -> 0x00bf }
        throw r0;	 Catch:{ EOFException -> 0x00bf }
    L_0x00bf:
        r0 = move-exception;
        r0 = r12.k;	 Catch:{ all -> 0x0097 }
        r0 = r0.size();	 Catch:{ all -> 0x0097 }
        r0 = r3 - r0;
        r12.l = r0;	 Catch:{ all -> 0x0097 }
        r0 = r5.b;	 Catch:{ all -> 0x0097 }
        if (r0 != r10) goto L_0x0173;
    L_0x00ce:
        r0 = r1;
    L_0x00cf:
        if (r0 == 0) goto L_0x0176;
    L_0x00d1:
        r12.c();	 Catch:{ all -> 0x0097 }
    L_0x00d4:
        com.bumptech.glide.a.e.a(r5);
        return;
    L_0x00d8:
        r0 = r7 + 1;
        r4 = 32;
        r8 = r6.indexOf(r4, r0);	 Catch:{ EOFException -> 0x00bf }
        if (r8 != r10) goto L_0x00fb;
    L_0x00e2:
        r0 = r6.substring(r0);	 Catch:{ EOFException -> 0x00bf }
        r4 = 6;
        if (r7 != r4) goto L_0x018e;
    L_0x00e9:
        r4 = "REMOVE";
        r4 = r6.startsWith(r4);	 Catch:{ EOFException -> 0x00bf }
        if (r4 == 0) goto L_0x018e;
    L_0x00f2:
        r4 = r12.k;	 Catch:{ EOFException -> 0x00bf }
        r4.remove(r0);	 Catch:{ EOFException -> 0x00bf }
    L_0x00f7:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x009d;
    L_0x00fb:
        r0 = r6.substring(r0, r8);	 Catch:{ EOFException -> 0x00bf }
        r4 = r0;
    L_0x0100:
        r0 = r12.k;	 Catch:{ EOFException -> 0x00bf }
        r0 = r0.get(r4);	 Catch:{ EOFException -> 0x00bf }
        r0 = (com.bumptech.glide.a.a.b) r0;	 Catch:{ EOFException -> 0x00bf }
        if (r0 != 0) goto L_0x0115;
    L_0x010a:
        r0 = new com.bumptech.glide.a.a$b;	 Catch:{ EOFException -> 0x00bf }
        r9 = 0;
        r0.<init>(r4, r9);	 Catch:{ EOFException -> 0x00bf }
        r9 = r12.k;	 Catch:{ EOFException -> 0x00bf }
        r9.put(r4, r0);	 Catch:{ EOFException -> 0x00bf }
    L_0x0115:
        if (r8 == r10) goto L_0x0139;
    L_0x0117:
        if (r7 != r11) goto L_0x0139;
    L_0x0119:
        r4 = "CLEAN";
        r4 = r6.startsWith(r4);	 Catch:{ EOFException -> 0x00bf }
        if (r4 == 0) goto L_0x0139;
    L_0x0122:
        r4 = r8 + 1;
        r4 = r6.substring(r4);	 Catch:{ EOFException -> 0x00bf }
        r6 = " ";
        r4 = r4.split(r6);	 Catch:{ EOFException -> 0x00bf }
        r6 = 1;
        r0.e = r6;	 Catch:{ EOFException -> 0x00bf }
        r6 = 0;
        r0.f = r6;	 Catch:{ EOFException -> 0x00bf }
        r0.a(r4);	 Catch:{ EOFException -> 0x00bf }
        goto L_0x00f7;
    L_0x0139:
        if (r8 != r10) goto L_0x014f;
    L_0x013b:
        if (r7 != r11) goto L_0x014f;
    L_0x013d:
        r4 = "DIRTY";
        r4 = r6.startsWith(r4);	 Catch:{ EOFException -> 0x00bf }
        if (r4 == 0) goto L_0x014f;
    L_0x0146:
        r4 = new com.bumptech.glide.a.a$a;	 Catch:{ EOFException -> 0x00bf }
        r6 = 0;
        r4.<init>(r0, r6);	 Catch:{ EOFException -> 0x00bf }
        r0.f = r4;	 Catch:{ EOFException -> 0x00bf }
        goto L_0x00f7;
    L_0x014f:
        if (r8 != r10) goto L_0x015d;
    L_0x0151:
        r0 = 4;
        if (r7 != r0) goto L_0x015d;
    L_0x0154:
        r0 = "READ";
        r0 = r6.startsWith(r0);	 Catch:{ EOFException -> 0x00bf }
        if (r0 != 0) goto L_0x00f7;
    L_0x015d:
        r0 = new java.io.IOException;	 Catch:{ EOFException -> 0x00bf }
        r4 = new java.lang.StringBuilder;	 Catch:{ EOFException -> 0x00bf }
        r7 = "unexpected journal line: ";
        r4.<init>(r7);	 Catch:{ EOFException -> 0x00bf }
        r4 = r4.append(r6);	 Catch:{ EOFException -> 0x00bf }
        r4 = r4.toString();	 Catch:{ EOFException -> 0x00bf }
        r0.<init>(r4);	 Catch:{ EOFException -> 0x00bf }
        throw r0;	 Catch:{ EOFException -> 0x00bf }
    L_0x0173:
        r0 = r2;
        goto L_0x00cf;
    L_0x0176:
        r0 = new java.io.BufferedWriter;	 Catch:{ all -> 0x0097 }
        r1 = new java.io.OutputStreamWriter;	 Catch:{ all -> 0x0097 }
        r2 = new java.io.FileOutputStream;	 Catch:{ all -> 0x0097 }
        r3 = r12.c;	 Catch:{ all -> 0x0097 }
        r4 = 1;
        r2.<init>(r3, r4);	 Catch:{ all -> 0x0097 }
        r3 = com.bumptech.glide.a.e.a;	 Catch:{ all -> 0x0097 }
        r1.<init>(r2, r3);	 Catch:{ all -> 0x0097 }
        r0.<init>(r1);	 Catch:{ all -> 0x0097 }
        r12.j = r0;	 Catch:{ all -> 0x0097 }
        goto L_0x00d4;
    L_0x018e:
        r4 = r0;
        goto L_0x0100;
        */
    }

    private void b() throws IOException {
        a(this.d);
        Iterator it = this.k.values().iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            int i;
            if (bVar.f == null) {
                for (i = 0; i < this.h; i++) {
                    this.i += bVar.b[i];
                }
            } else {
                bVar.f = null;
                for (i = 0; i < this.h; i++) {
                    a(bVar.c[i]);
                    a(bVar.d[i]);
                }
                it.remove();
            }
        }
    }

    private synchronized void c() throws IOException {
        try {
            if (this.j != null) {
                this.j.close();
            }
            Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d), e.a));
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write(MessageService.MSG_DB_NOTIFY_REACHED);
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.h));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (b bVar : this.k.values()) {
                if (bVar.f != null) {
                    bufferedWriter.write(new StringBuilder("DIRTY ").append(bVar.a).append('\n').toString());
                } else {
                    bufferedWriter.write(new StringBuilder("CLEAN ").append(bVar.a).append(bVar.a()).append('\n').toString());
                }
            }
            bufferedWriter.close();
            if (this.c.exists()) {
                a(this.c, this.e, true);
            }
            a(this.d, this.c, false);
            this.e.delete();
            this.j = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.c, true), e.a));
        } catch (Throwable th) {
        }
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    public final synchronized c a(String str) throws IOException {
        c cVar = null;
        synchronized (this) {
            e();
            b bVar = (b) this.k.get(str);
            if (bVar != null) {
                if (bVar.e) {
                    for (File file : bVar.c) {
                        if (!file.exists()) {
                            break;
                        }
                    }
                    this.l++;
                    this.j.append("READ");
                    this.j.append(' ');
                    this.j.append(str);
                    this.j.append('\n');
                    if (d()) {
                        this.a.submit(this.n);
                    }
                    cVar = new c(str, bVar.g, bVar.c, bVar.b, (byte) 0);
                }
            }
        }
        return cVar;
    }

    public final synchronized a b(String str) throws IOException {
        a aVar;
        e();
        b bVar = (b) this.k.get(str);
        if (-1 == -1 || (bVar != null && bVar.g == -1)) {
            b bVar2;
            if (bVar == null) {
                bVar = new b(str, (byte) 0);
                this.k.put(str, bVar);
                bVar2 = bVar;
            } else if (bVar.f != null) {
                aVar = null;
            } else {
                bVar2 = bVar;
            }
            aVar = new a(bVar2, (byte) 0);
            bVar2.f = aVar;
            this.j.append("DIRTY");
            this.j.append(' ');
            this.j.append(str);
            this.j.append('\n');
            this.j.flush();
        } else {
            aVar = null;
        }
        return aVar;
    }

    private synchronized void a(a aVar, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            b bVar = aVar.a;
            if (bVar.f != aVar) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!bVar.e) {
                    int i2 = 0;
                    while (i2 < this.h) {
                        if (!aVar.b[i2]) {
                            aVar.b();
                            throw new IllegalStateException(new StringBuilder("Newly created entry didn't create value for index ").append(i2).toString());
                        } else if (!bVar.d[i2].exists()) {
                            aVar.b();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.h) {
                File file = bVar.d[i];
                if (!z) {
                    a(file);
                } else if (file.exists()) {
                    File file2 = bVar.c[i];
                    file.renameTo(file2);
                    long j = bVar.b[i];
                    long length = file2.length();
                    bVar.b[i] = length;
                    this.i = (this.i - j) + length;
                }
                i++;
            }
            this.l++;
            bVar.f = null;
            if ((bVar.e | z) != 0) {
                bVar.e = true;
                this.j.append("CLEAN");
                this.j.append(' ');
                this.j.append(bVar.a);
                this.j.append(bVar.a());
                this.j.append('\n');
                if (z) {
                    long j2 = this.m;
                    this.m = 1 + j2;
                    bVar.g = j2;
                }
            } else {
                this.k.remove(bVar.a);
                this.j.append("REMOVE");
                this.j.append(' ');
                this.j.append(bVar.a);
                this.j.append('\n');
            }
            this.j.flush();
            if (this.i > this.g || d()) {
                this.a.submit(this.n);
            }
        }
    }

    private boolean d() {
        return this.l >= 2000 && this.l >= this.k.size();
    }

    public final synchronized boolean c(String str) throws IOException {
        boolean z;
        int i = 0;
        synchronized (this) {
            e();
            b bVar = (b) this.k.get(str);
            if (bVar == null || bVar.f != null) {
                z = false;
            } else {
                while (i < this.h) {
                    File file = bVar.c[i];
                    if (!file.exists() || file.delete()) {
                        this.i -= bVar.b[i];
                        bVar.b[i] = 0;
                        i++;
                    } else {
                        throw new IOException(new StringBuilder("failed to delete ").append(file).toString());
                    }
                }
                this.l++;
                this.j.append("REMOVE");
                this.j.append(' ');
                this.j.append(str);
                this.j.append('\n');
                this.k.remove(str);
                if (d()) {
                    this.a.submit(this.n);
                }
                z = true;
            }
        }
        return z;
    }

    private void e() {
        if (this.j == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public final synchronized void close() throws IOException {
        if (this.j != null) {
            Iterator it = new ArrayList(this.k.values()).iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (bVar.f != null) {
                    bVar.f.b();
                }
            }
            f();
            this.j.close();
            this.j = null;
        }
    }

    private void f() throws IOException {
        while (this.i > this.g) {
            c((String) ((Entry) this.k.entrySet().iterator().next()).getKey());
        }
    }
}
