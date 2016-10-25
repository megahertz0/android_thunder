package com.bumptech.glide.h;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

// compiled from: ContentLengthInputStream.java
public final class b extends FilterInputStream {
    private final long a;
    private int b;

    public static InputStream a(InputStream inputStream, long j) {
        return new b(inputStream, j);
    }

    private b(InputStream inputStream, long j) {
        super(inputStream);
        this.a = j;
    }

    public final synchronized int available() throws IOException {
        return (int) Math.max(this.a - ((long) this.b), (long) this.in.available());
    }

    public final synchronized int read() throws IOException {
        return a(super.read());
    }

    public final int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public final synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        return a(super.read(bArr, i, i2));
    }

    private int a(int i) throws IOException {
        if (i >= 0) {
            this.b += i;
        } else if (this.a - ((long) this.b) > 0) {
            throw new IOException(new StringBuilder("Failed to read all expected data, expected: ").append(this.a).append(", but read: ").append(this.b).toString());
        }
        return i;
    }
}
