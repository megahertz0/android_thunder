package com.nostra13.universalimageloader.core.assist;

import java.io.IOException;
import java.io.InputStream;

// compiled from: ContentLengthInputStream.java
public final class a extends InputStream {
    private final InputStream a;
    private final int b;

    public a(InputStream inputStream, int i) {
        this.a = inputStream;
        this.b = i;
    }

    public final int available() {
        return this.b;
    }

    public final void close() throws IOException {
        this.a.close();
    }

    public final void mark(int i) {
        this.a.mark(i);
    }

    public final int read() throws IOException {
        return this.a.read();
    }

    public final int read(byte[] bArr) throws IOException {
        return this.a.read(bArr);
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        return this.a.read(bArr, i, i2);
    }

    public final void reset() throws IOException {
        this.a.reset();
    }

    public final long skip(long j) throws IOException {
        return this.a.skip(j);
    }

    public final boolean markSupported() {
        return this.a.markSupported();
    }
}
