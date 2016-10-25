package org.apache.thrift.transport;

public final class c extends d {
    private byte[] a;
    private int b;
    private int c;

    public final int a(byte[] bArr, int i, int i2) {
        int c = c();
        if (i2 > c) {
            i2 = c;
        }
        if (i2 > 0) {
            System.arraycopy(this.a, this.b, bArr, i, i2);
            a(i2);
        }
        return i2;
    }

    public final void a(int i) {
        this.b += i;
    }

    public final void a(byte[] bArr) {
        c(bArr, 0, bArr.length);
    }

    public final byte[] a() {
        return this.a;
    }

    public final int b() {
        return this.b;
    }

    public final void b(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    public final int c() {
        return this.c - this.b;
    }

    public final void c(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.b = i;
        this.c = i + i2;
    }
}
