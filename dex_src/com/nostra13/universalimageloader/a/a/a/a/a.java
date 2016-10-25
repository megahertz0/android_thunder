package com.nostra13.universalimageloader.a.a.a.a;

import com.alipay.sdk.util.h;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import java.util.regex.Pattern;
import org.android.agoo.message.MessageService;

// compiled from: DiskLruCache.java
final class a implements Closeable {
    static final Pattern a;
    private static final OutputStream r;
    final File b;
    final ThreadPoolExecutor c;
    private final File d;
    private final File e;
    private final File f;
    private final int g;
    private long h;
    private int i;
    private final int j;
    private long k;
    private int l;
    private Writer m;
    private final LinkedHashMap<String, b> n;
    private int o;
    private long p;
    private final Callable<Void> q;

    // compiled from: DiskLruCache.java
    public final class a {
        final b a;
        final boolean[] b;
        boolean c;
        private boolean e;

        // compiled from: DiskLruCache.java
        private class a extends FilterOutputStream {
            private a(OutputStream outputStream) {
                super(outputStream);
            }

            public final void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    com.nostra13.universalimageloader.a.a.a.a.a.a.this.c = true;
                }
            }

            public final void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    com.nostra13.universalimageloader.a.a.a.a.a.a.this.c = true;
                }
            }

            public final void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    com.nostra13.universalimageloader.a.a.a.a.a.a.this.c = true;
                }
            }

            public final void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    com.nostra13.universalimageloader.a.a.a.a.a.a.this.c = true;
                }
            }
        }

        private a(b bVar) {
            this.a = bVar;
            this.b = bVar.c ? null : new boolean[a.this.j];
        }

        public final OutputStream a() throws IOException {
            OutputStream d;
            synchronized (a.this) {
                if (a.this != this) {
                    throw new IllegalStateException();
                }
                OutputStream fileOutputStream;
                if (!this.a.c) {
                    this.b[0] = true;
                }
                File b = this.a.b(0);
                try {
                    fileOutputStream = new FileOutputStream(b);
                } catch (FileNotFoundException e) {
                    a.this.b.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(b);
                    } catch (FileNotFoundException e2) {
                        d = r;
                    }
                }
                d = new a(fileOutputStream, (byte) 0);
            }
            return d;
        }

        public final void b() throws IOException {
            if (this.c) {
                a.this.a(this, false);
                a.this.c(this.a.a);
            } else {
                a.this.a(this, true);
            }
            this.e = true;
        }

        public final void c() throws IOException {
            a.this.a(this, false);
        }
    }

    // compiled from: DiskLruCache.java
    private final class b {
        final String a;
        final long[] b;
        boolean c;
        com.nostra13.universalimageloader.a.a.a.a.a.a d;
        long e;

        private b(String str) {
            this.a = str;
            this.b = new long[a.this.j];
        }

        public final String a() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long j : this.b) {
                stringBuilder.append(' ').append(j);
            }
            return stringBuilder.toString();
        }

        final void a(String[] strArr) throws IOException {
            if (strArr.length != a.this.j) {
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

        public final File a(int i) {
            return new File(a.this.b, this.a + i);
        }

        public final File b(int i) {
            return new File(a.this.b, this.a + i + ".tmp");
        }
    }

    // compiled from: DiskLruCache.java
    public final class c implements Closeable {
        File[] a;
        private final String c;
        private final long d;
        private final InputStream[] e;
        private final long[] f;

        private c(String str, long j, File[] fileArr, InputStream[] inputStreamArr, long[] jArr) {
            this.c = str;
            this.d = j;
            this.a = fileArr;
            this.e = inputStreamArr;
            this.f = jArr;
        }

        public final void close() {
            for (Closeable closeable : this.e) {
                g.a(closeable);
            }
        }
    }

    static {
        a = Pattern.compile("[a-z0-9_-]{1,64}");
        r = new c();
    }

    private a(File file, long j, int i) {
        this.k = 0;
        this.l = 0;
        this.n = new LinkedHashMap(0, 0.75f, true);
        this.p = 0;
        this.c = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.q = new b(this);
        this.b = file;
        this.g = 1;
        this.d = new File(file, "journal");
        this.e = new File(file, "journal.tmp");
        this.f = new File(file, "journal.bkp");
        this.j = 1;
        this.h = j;
        this.i = i;
    }

    public static a a(File file, long j, int i) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i <= 0) {
            throw new IllegalArgumentException("maxFileCount <= 0");
        } else {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    a(file2, file3, false);
                }
            }
            a aVar = new a(file, j, i);
            if (aVar.d.exists()) {
                try {
                    aVar.e();
                    aVar.f();
                    aVar.m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(aVar.d, true), g.a));
                    return aVar;
                } catch (IOException e) {
                    System.out.println(new StringBuilder("DiskLruCache ").append(file).append(" is corrupt: ").append(e.getMessage()).append(", removing").toString());
                    aVar.c();
                }
            }
            file.mkdirs();
            aVar = new a(file, j, i);
            aVar.g();
            return aVar;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void e() throws java.io.IOException {
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.a.a.a.a.a.e():void");
        /*
        this = this;
        r9 = 5;
        r0 = 0;
        r8 = -1;
        r3 = new com.nostra13.universalimageloader.a.a.a.a.e;
        r1 = new java.io.FileInputStream;
        r2 = r10.d;
        r1.<init>(r2);
        r2 = com.nostra13.universalimageloader.a.a.a.a.g.a;
        r3.<init>(r1, r2);
        r1 = r3.a();	 Catch:{ all -> 0x0096 }
        r2 = r3.a();	 Catch:{ all -> 0x0096 }
        r4 = r3.a();	 Catch:{ all -> 0x0096 }
        r5 = r3.a();	 Catch:{ all -> 0x0096 }
        r6 = r3.a();	 Catch:{ all -> 0x0096 }
        r7 = "libcore.io.DiskLruCache";
        r7 = r7.equals(r1);	 Catch:{ all -> 0x0096 }
        if (r7 == 0) goto L_0x0058;
    L_0x002e:
        r7 = "1";
        r7 = r7.equals(r2);	 Catch:{ all -> 0x0096 }
        if (r7 == 0) goto L_0x0058;
    L_0x0037:
        r7 = r10.g;	 Catch:{ all -> 0x0096 }
        r7 = java.lang.Integer.toString(r7);	 Catch:{ all -> 0x0096 }
        r4 = r7.equals(r4);	 Catch:{ all -> 0x0096 }
        if (r4 == 0) goto L_0x0058;
    L_0x0043:
        r4 = r10.j;	 Catch:{ all -> 0x0096 }
        r4 = java.lang.Integer.toString(r4);	 Catch:{ all -> 0x0096 }
        r4 = r4.equals(r5);	 Catch:{ all -> 0x0096 }
        if (r4 == 0) goto L_0x0058;
    L_0x004f:
        r4 = "";
        r4 = r4.equals(r6);	 Catch:{ all -> 0x0096 }
        if (r4 != 0) goto L_0x009b;
    L_0x0058:
        r0 = new java.io.IOException;	 Catch:{ all -> 0x0096 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0096 }
        r7 = "unexpected journal header: [";
        r4.<init>(r7);	 Catch:{ all -> 0x0096 }
        r1 = r4.append(r1);	 Catch:{ all -> 0x0096 }
        r4 = ", ";
        r1 = r1.append(r4);	 Catch:{ all -> 0x0096 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0096 }
        r2 = ", ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0096 }
        r1 = r1.append(r5);	 Catch:{ all -> 0x0096 }
        r2 = ", ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0096 }
        r1 = r1.append(r6);	 Catch:{ all -> 0x0096 }
        r2 = "]";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0096 }
        r1 = r1.toString();	 Catch:{ all -> 0x0096 }
        r0.<init>(r1);	 Catch:{ all -> 0x0096 }
        throw r0;	 Catch:{ all -> 0x0096 }
    L_0x0096:
        r0 = move-exception;
        com.nostra13.universalimageloader.a.a.a.a.g.a(r3);
        throw r0;
    L_0x009b:
        r1 = r0;
    L_0x009c:
        r4 = r3.a();	 Catch:{ EOFException -> 0x00be }
        r0 = 32;
        r5 = r4.indexOf(r0);	 Catch:{ EOFException -> 0x00be }
        if (r5 != r8) goto L_0x00cd;
    L_0x00a8:
        r0 = new java.io.IOException;	 Catch:{ EOFException -> 0x00be }
        r2 = new java.lang.StringBuilder;	 Catch:{ EOFException -> 0x00be }
        r5 = "unexpected journal line: ";
        r2.<init>(r5);	 Catch:{ EOFException -> 0x00be }
        r2 = r2.append(r4);	 Catch:{ EOFException -> 0x00be }
        r2 = r2.toString();	 Catch:{ EOFException -> 0x00be }
        r0.<init>(r2);	 Catch:{ EOFException -> 0x00be }
        throw r0;	 Catch:{ EOFException -> 0x00be }
    L_0x00be:
        r0 = move-exception;
        r0 = r10.n;	 Catch:{ all -> 0x0096 }
        r0 = r0.size();	 Catch:{ all -> 0x0096 }
        r0 = r1 - r0;
        r10.o = r0;	 Catch:{ all -> 0x0096 }
        com.nostra13.universalimageloader.a.a.a.a.g.a(r3);
        return;
    L_0x00cd:
        r0 = r5 + 1;
        r2 = 32;
        r6 = r4.indexOf(r2, r0);	 Catch:{ EOFException -> 0x00be }
        if (r6 != r8) goto L_0x00f0;
    L_0x00d7:
        r0 = r4.substring(r0);	 Catch:{ EOFException -> 0x00be }
        r2 = 6;
        if (r5 != r2) goto L_0x0168;
    L_0x00de:
        r2 = "REMOVE";
        r2 = r4.startsWith(r2);	 Catch:{ EOFException -> 0x00be }
        if (r2 == 0) goto L_0x0168;
    L_0x00e7:
        r2 = r10.n;	 Catch:{ EOFException -> 0x00be }
        r2.remove(r0);	 Catch:{ EOFException -> 0x00be }
    L_0x00ec:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x009c;
    L_0x00f0:
        r0 = r4.substring(r0, r6);	 Catch:{ EOFException -> 0x00be }
        r2 = r0;
    L_0x00f5:
        r0 = r10.n;	 Catch:{ EOFException -> 0x00be }
        r0 = r0.get(r2);	 Catch:{ EOFException -> 0x00be }
        r0 = (com.nostra13.universalimageloader.a.a.a.a.a.b) r0;	 Catch:{ EOFException -> 0x00be }
        if (r0 != 0) goto L_0x010a;
    L_0x00ff:
        r0 = new com.nostra13.universalimageloader.a.a.a.a.a$b;	 Catch:{ EOFException -> 0x00be }
        r7 = 0;
        r0.<init>(r2, r7);	 Catch:{ EOFException -> 0x00be }
        r7 = r10.n;	 Catch:{ EOFException -> 0x00be }
        r7.put(r2, r0);	 Catch:{ EOFException -> 0x00be }
    L_0x010a:
        if (r6 == r8) goto L_0x012e;
    L_0x010c:
        if (r5 != r9) goto L_0x012e;
    L_0x010e:
        r2 = "CLEAN";
        r2 = r4.startsWith(r2);	 Catch:{ EOFException -> 0x00be }
        if (r2 == 0) goto L_0x012e;
    L_0x0117:
        r2 = r6 + 1;
        r2 = r4.substring(r2);	 Catch:{ EOFException -> 0x00be }
        r4 = " ";
        r2 = r2.split(r4);	 Catch:{ EOFException -> 0x00be }
        r4 = 1;
        r0.c = r4;	 Catch:{ EOFException -> 0x00be }
        r4 = 0;
        r0.d = r4;	 Catch:{ EOFException -> 0x00be }
        r0.a(r2);	 Catch:{ EOFException -> 0x00be }
        goto L_0x00ec;
    L_0x012e:
        if (r6 != r8) goto L_0x0144;
    L_0x0130:
        if (r5 != r9) goto L_0x0144;
    L_0x0132:
        r2 = "DIRTY";
        r2 = r4.startsWith(r2);	 Catch:{ EOFException -> 0x00be }
        if (r2 == 0) goto L_0x0144;
    L_0x013b:
        r2 = new com.nostra13.universalimageloader.a.a.a.a.a$a;	 Catch:{ EOFException -> 0x00be }
        r4 = 0;
        r2.<init>(r0, r4);	 Catch:{ EOFException -> 0x00be }
        r0.d = r2;	 Catch:{ EOFException -> 0x00be }
        goto L_0x00ec;
    L_0x0144:
        if (r6 != r8) goto L_0x0152;
    L_0x0146:
        r0 = 4;
        if (r5 != r0) goto L_0x0152;
    L_0x0149:
        r0 = "READ";
        r0 = r4.startsWith(r0);	 Catch:{ EOFException -> 0x00be }
        if (r0 != 0) goto L_0x00ec;
    L_0x0152:
        r0 = new java.io.IOException;	 Catch:{ EOFException -> 0x00be }
        r2 = new java.lang.StringBuilder;	 Catch:{ EOFException -> 0x00be }
        r5 = "unexpected journal line: ";
        r2.<init>(r5);	 Catch:{ EOFException -> 0x00be }
        r2 = r2.append(r4);	 Catch:{ EOFException -> 0x00be }
        r2 = r2.toString();	 Catch:{ EOFException -> 0x00be }
        r0.<init>(r2);	 Catch:{ EOFException -> 0x00be }
        throw r0;	 Catch:{ EOFException -> 0x00be }
    L_0x0168:
        r2 = r0;
        goto L_0x00f5;
        */
    }

    private void f() throws IOException {
        a(this.e);
        Iterator it = this.n.values().iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            int i;
            if (bVar.d == null) {
                for (i = 0; i < this.j; i++) {
                    this.k += bVar.b[i];
                    this.l++;
                }
            } else {
                bVar.d = null;
                for (i = 0; i < this.j; i++) {
                    a(bVar.a(i));
                    a(bVar.b(i));
                }
                it.remove();
            }
        }
    }

    private synchronized void g() throws IOException {
        try {
            if (this.m != null) {
                this.m.close();
            }
            Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e), g.a));
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write(MessageService.MSG_DB_NOTIFY_REACHED);
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.j));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (b bVar : this.n.values()) {
                if (bVar.d != null) {
                    bufferedWriter.write(new StringBuilder("DIRTY ").append(bVar.a).append('\n').toString());
                } else {
                    bufferedWriter.write(new StringBuilder("CLEAN ").append(bVar.a).append(bVar.a()).append('\n').toString());
                }
            }
            bufferedWriter.close();
            if (this.d.exists()) {
                a(this.d, this.f, true);
            }
            a(this.e, this.d, false);
            this.f.delete();
            this.m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), g.a));
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
            i();
            d(str);
            b bVar = (b) this.n.get(str);
            if (bVar != null) {
                if (bVar.c) {
                    File[] fileArr = new File[this.j];
                    InputStream[] inputStreamArr = new InputStream[this.j];
                    int i = 0;
                    while (i < this.j) {
                        try {
                            File a = bVar.a(i);
                            fileArr[i] = a;
                            inputStreamArr[i] = new FileInputStream(a);
                            i++;
                        } catch (FileNotFoundException e) {
                            int i2 = 0;
                            while (i2 < this.j && inputStreamArr[i2] != null) {
                                g.a(inputStreamArr[i2]);
                                i2++;
                            }
                        }
                    }
                    this.o++;
                    this.m.append(new StringBuilder("READ ").append(str).append('\n').toString());
                    if (h()) {
                        this.c.submit(this.q);
                    }
                    cVar = new c(str, bVar.e, fileArr, inputStreamArr, bVar.b, (byte) 0);
                }
            }
        }
        return cVar;
    }

    final synchronized a b(String str) throws IOException {
        a aVar;
        i();
        d(str);
        b bVar = (b) this.n.get(str);
        if (-1 == -1 || (bVar != null && bVar.e == -1)) {
            b bVar2;
            if (bVar == null) {
                bVar = new b(str, (byte) 0);
                this.n.put(str, bVar);
                bVar2 = bVar;
            } else if (bVar.d != null) {
                aVar = null;
            } else {
                bVar2 = bVar;
            }
            aVar = new a(bVar2, (byte) 0);
            bVar2.d = aVar;
            this.m.write(new StringBuilder("DIRTY ").append(str).append('\n').toString());
            this.m.flush();
        } else {
            aVar = null;
        }
        return aVar;
    }

    public final synchronized long a() {
        return this.h;
    }

    public final synchronized int b() {
        return this.i;
    }

    private synchronized void a(a aVar, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            b bVar = aVar.a;
            if (bVar.d != aVar) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!bVar.c) {
                    int i2 = 0;
                    while (i2 < this.j) {
                        if (!aVar.b[i2]) {
                            aVar.c();
                            throw new IllegalStateException(new StringBuilder("Newly created entry didn't create value for index ").append(i2).toString());
                        } else if (!bVar.b(i2).exists()) {
                            aVar.c();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.j) {
                File b = bVar.b(i);
                if (!z) {
                    a(b);
                } else if (b.exists()) {
                    File a = bVar.a(i);
                    b.renameTo(a);
                    long j = bVar.b[i];
                    long length = a.length();
                    bVar.b[i] = length;
                    this.k = (this.k - j) + length;
                    this.l++;
                }
                i++;
            }
            this.o++;
            bVar.d = null;
            if ((bVar.c | z) != 0) {
                bVar.c = true;
                this.m.write(new StringBuilder("CLEAN ").append(bVar.a).append(bVar.a()).append('\n').toString());
                if (z) {
                    long j2 = this.p;
                    this.p = 1 + j2;
                    bVar.e = j2;
                }
            } else {
                this.n.remove(bVar.a);
                this.m.write(new StringBuilder("REMOVE ").append(bVar.a).append('\n').toString());
            }
            this.m.flush();
            if (this.k > this.h || this.l > this.i || h()) {
                this.c.submit(this.q);
            }
        }
    }

    private boolean h() {
        return this.o >= 2000 && this.o >= this.n.size();
    }

    public final synchronized boolean c(String str) throws IOException {
        boolean z;
        int i = 0;
        synchronized (this) {
            i();
            d(str);
            b bVar = (b) this.n.get(str);
            if (bVar == null || bVar.d != null) {
                z = false;
            } else {
                while (i < this.j) {
                    File a = bVar.a(i);
                    if (!a.exists() || a.delete()) {
                        this.k -= bVar.b[i];
                        this.l--;
                        bVar.b[i] = 0;
                        i++;
                    } else {
                        throw new IOException(new StringBuilder("failed to delete ").append(a).toString());
                    }
                }
                this.o++;
                this.m.append(new StringBuilder("REMOVE ").append(str).append('\n').toString());
                this.n.remove(str);
                if (h()) {
                    this.c.submit(this.q);
                }
                z = true;
            }
        }
        return z;
    }

    private void i() {
        if (this.m == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public final synchronized void close() throws IOException {
        if (this.m != null) {
            Iterator it = new ArrayList(this.n.values()).iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (bVar.d != null) {
                    bVar.d.c();
                }
            }
            j();
            k();
            this.m.close();
            this.m = null;
        }
    }

    private void j() throws IOException {
        while (this.k > this.h) {
            c((String) ((Entry) this.n.entrySet().iterator().next()).getKey());
        }
    }

    private void k() throws IOException {
        while (this.l > this.i) {
            c((String) ((Entry) this.n.entrySet().iterator().next()).getKey());
        }
    }

    public final void c() throws IOException {
        close();
        g.a(this.b);
    }

    private static void d(String str) {
        if (!a.matcher(str).matches()) {
            throw new IllegalArgumentException(new StringBuilder("keys must match regex [a-z0-9_-]{1,64}: \"").append(str).append(h.f).toString());
        }
    }
}
