package com.bumptech.glide.h;

import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

// compiled from: ExceptionCatchingInputStream.java
public final class c extends InputStream {
    private static final Queue<c> b;
    public IOException a;
    private InputStream c;

    static {
        b = h.a(0);
    }

    public static c a(InputStream inputStream) {
        c cVar;
        synchronized (b) {
            cVar = (c) b.poll();
        }
        if (cVar == null) {
            cVar = new c();
        }
        cVar.c = inputStream;
        return cVar;
    }

    c() {
    }

    public final int available() throws IOException {
        return this.c.available();
    }

    public final void close() throws IOException {
        this.c.close();
    }

    public final void mark(int i) {
        this.c.mark(i);
    }

    public final boolean markSupported() {
        return this.c.markSupported();
    }

    public final int read(byte[] bArr) throws IOException {
        try {
            return this.c.read(bArr);
        } catch (IOException e) {
            this.a = e;
            return -1;
        }
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        try {
            return this.c.read(bArr, i, i2);
        } catch (IOException e) {
            this.a = e;
            return -1;
        }
    }

    public final synchronized void reset() throws IOException {
        this.c.reset();
    }

    public final long skip(long j) throws IOException {
        try {
            return this.c.skip(j);
        } catch (IOException e) {
            this.a = e;
            return 0;
        }
    }

    public final int read() throws IOException {
        try {
            return this.c.read();
        } catch (IOException e) {
            this.a = e;
            return -1;
        }
    }

    public final void a() {
        this.a = null;
        this.c = null;
        synchronized (b) {
            b.offer(this);
        }
    }
}
