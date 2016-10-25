package com.bumptech.glide.load.resource.bitmap;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

// compiled from: RecyclableBufferedInputStream.java
public final class q extends FilterInputStream {
    private volatile byte[] a;
    private int b;
    private int c;
    private int d;
    private int e;

    // compiled from: RecyclableBufferedInputStream.java
    public static class a extends RuntimeException {
        public a(String str) {
            super(str);
        }
    }

    public q(InputStream inputStream, byte[] bArr) {
        super(inputStream);
        this.d = -1;
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("buffer is null or empty");
        }
        this.a = bArr;
    }

    public final synchronized int available() throws IOException {
        InputStream inputStream;
        inputStream = this.in;
        if (this.a == null || inputStream == null) {
            throw b();
        }
        return inputStream.available() + (this.b - this.e);
    }

    private static IOException b() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    public final synchronized void a() {
        this.c = this.a.length;
    }

    public final void close() throws IOException {
        this.a = null;
        InputStream inputStream = this.in;
        this.in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    private int a(InputStream inputStream, byte[] bArr) throws IOException {
        int read;
        if (this.d == -1 || this.e - this.d >= this.c) {
            read = inputStream.read(bArr);
            if (read <= 0) {
                return read;
            }
            this.d = -1;
            this.e = 0;
            this.b = read;
            return read;
        }
        if (this.d == 0 && this.c > bArr.length && this.b == bArr.length) {
            read = bArr.length * 2;
            if (read > this.c) {
                read = this.c;
            }
            Object obj = new Object[read];
            System.arraycopy(bArr, 0, obj, 0, bArr.length);
            this.a = obj;
            Object obj2 = obj;
        } else if (this.d > 0) {
            System.arraycopy(bArr, this.d, bArr, 0, bArr.length - this.d);
        }
        this.e -= this.d;
        this.d = 0;
        this.b = 0;
        int read2 = inputStream.read(bArr, this.e, bArr.length - this.e);
        this.b = read2 <= 0 ? this.e : this.e + read2;
        return read2;
    }

    public final synchronized void mark(int i) {
        this.c = Math.max(this.c, i);
        this.d = this.e;
    }

    public final boolean markSupported() {
        return true;
    }

    public final synchronized int read() throws IOException {
        int i = -1;
        synchronized (this) {
            byte[] bArr = this.a;
            InputStream inputStream = this.in;
            if (bArr == null || inputStream == null) {
                throw b();
            }
            if (this.e < this.b || a(inputStream, bArr) != -1) {
                if (bArr != this.a) {
                    bArr = this.a;
                    if (bArr == null) {
                        throw b();
                    }
                }
                if (this.b - this.e > 0) {
                    i = this.e;
                    this.e = i + 1;
                    i = bArr[i] & 255;
                }
            }
        }
        return i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized int read(byte[] r7, int r8, int r9) throws java.io.IOException {
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.q.read(byte[], int, int):int");
        /*
        this = this;
        r1 = -1;
        monitor-enter(r6);
        r2 = r6.a;	 Catch:{ all -> 0x000b }
        if (r2 != 0) goto L_0x000e;
    L_0x0006:
        r0 = b();	 Catch:{ all -> 0x000b }
        throw r0;	 Catch:{ all -> 0x000b }
    L_0x000b:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x000e:
        if (r9 != 0) goto L_0x0013;
    L_0x0010:
        r9 = 0;
    L_0x0011:
        monitor-exit(r6);
        return r9;
    L_0x0013:
        r4 = r6.in;	 Catch:{ all -> 0x000b }
        if (r4 != 0) goto L_0x001c;
    L_0x0017:
        r0 = b();	 Catch:{ all -> 0x000b }
        throw r0;	 Catch:{ all -> 0x000b }
    L_0x001c:
        r0 = r6.e;	 Catch:{ all -> 0x000b }
        r3 = r6.b;	 Catch:{ all -> 0x000b }
        if (r0 >= r3) goto L_0x0058;
    L_0x0022:
        r0 = r6.b;	 Catch:{ all -> 0x000b }
        r3 = r6.e;	 Catch:{ all -> 0x000b }
        r0 = r0 - r3;
        if (r0 < r9) goto L_0x003e;
    L_0x0029:
        r0 = r9;
    L_0x002a:
        r3 = r6.e;	 Catch:{ all -> 0x000b }
        java.lang.System.arraycopy(r2, r3, r7, r8, r0);	 Catch:{ all -> 0x000b }
        r3 = r6.e;	 Catch:{ all -> 0x000b }
        r3 = r3 + r0;
        r6.e = r3;	 Catch:{ all -> 0x000b }
        if (r0 == r9) goto L_0x003c;
    L_0x0036:
        r3 = r4.available();	 Catch:{ all -> 0x000b }
        if (r3 != 0) goto L_0x0044;
    L_0x003c:
        r9 = r0;
        goto L_0x0011;
    L_0x003e:
        r0 = r6.b;	 Catch:{ all -> 0x000b }
        r3 = r6.e;	 Catch:{ all -> 0x000b }
        r0 = r0 - r3;
        goto L_0x002a;
    L_0x0044:
        r8 = r8 + r0;
        r3 = r9 - r0;
    L_0x0047:
        r0 = r6.d;	 Catch:{ all -> 0x000b }
        if (r0 != r1) goto L_0x005c;
    L_0x004b:
        r0 = r2.length;	 Catch:{ all -> 0x000b }
        if (r3 < r0) goto L_0x005c;
    L_0x004e:
        r0 = r4.read(r7, r8, r3);	 Catch:{ all -> 0x000b }
        if (r0 != r1) goto L_0x0087;
    L_0x0054:
        if (r3 != r9) goto L_0x005a;
    L_0x0056:
        r9 = r1;
        goto L_0x0011;
    L_0x0058:
        r3 = r9;
        goto L_0x0047;
    L_0x005a:
        r9 = r9 - r3;
        goto L_0x0011;
    L_0x005c:
        r0 = r6.a(r4, r2);	 Catch:{ all -> 0x000b }
        if (r0 != r1) goto L_0x0068;
    L_0x0062:
        if (r3 != r9) goto L_0x0066;
    L_0x0064:
        r9 = r1;
        goto L_0x0011;
    L_0x0066:
        r9 = r9 - r3;
        goto L_0x0011;
    L_0x0068:
        r0 = r6.a;	 Catch:{ all -> 0x000b }
        if (r2 == r0) goto L_0x0075;
    L_0x006c:
        r2 = r6.a;	 Catch:{ all -> 0x000b }
        if (r2 != 0) goto L_0x0075;
    L_0x0070:
        r0 = b();	 Catch:{ all -> 0x000b }
        throw r0;	 Catch:{ all -> 0x000b }
    L_0x0075:
        r0 = r6.b;	 Catch:{ all -> 0x000b }
        r5 = r6.e;	 Catch:{ all -> 0x000b }
        r0 = r0 - r5;
        if (r0 < r3) goto L_0x0092;
    L_0x007c:
        r0 = r3;
    L_0x007d:
        r5 = r6.e;	 Catch:{ all -> 0x000b }
        java.lang.System.arraycopy(r2, r5, r7, r8, r0);	 Catch:{ all -> 0x000b }
        r5 = r6.e;	 Catch:{ all -> 0x000b }
        r5 = r5 + r0;
        r6.e = r5;	 Catch:{ all -> 0x000b }
    L_0x0087:
        r3 = r3 - r0;
        if (r3 == 0) goto L_0x0011;
    L_0x008a:
        r5 = r4.available();	 Catch:{ all -> 0x000b }
        if (r5 != 0) goto L_0x0098;
    L_0x0090:
        r9 = r9 - r3;
        goto L_0x0011;
    L_0x0092:
        r0 = r6.b;	 Catch:{ all -> 0x000b }
        r5 = r6.e;	 Catch:{ all -> 0x000b }
        r0 = r0 - r5;
        goto L_0x007d;
    L_0x0098:
        r8 = r8 + r0;
        goto L_0x0047;
        */
    }

    public final synchronized void reset() throws IOException {
        if (this.a == null) {
            throw new IOException("Stream is closed");
        } else if (-1 == this.d) {
            throw new a("Mark has been invalidated");
        } else {
            this.e = this.d;
        }
    }

    public final synchronized long skip(long j) throws IOException {
        byte[] bArr = this.a;
        InputStream inputStream = this.in;
        if (bArr == null) {
            throw b();
        } else if (j < 1) {
            j = 0;
        } else if (inputStream == null) {
            throw b();
        } else if (((long) (this.b - this.e)) >= j) {
            this.e = (int) (((long) this.e) + j);
        } else {
            long j2 = (long) (this.b - this.e);
            this.e = this.b;
            if (this.d == -1 || j > ((long) this.c)) {
                j = j2 + inputStream.skip(j - j2);
            } else if (a(inputStream, bArr) == -1) {
                j = j2;
            } else if (((long) (this.b - this.e)) >= j - j2) {
                this.e = (int) ((j - j2) + ((long) this.e));
            } else {
                j = (j2 + ((long) this.b)) - ((long) this.e);
                this.e = this.b;
            }
        }
        return j;
    }
}
