package org.apache.thrift.transport;

public abstract class d {
    public abstract int a(byte[] bArr, int i, int i2);

    public void a(int i) {
    }

    public byte[] a() {
        return null;
    }

    public int b() {
        return 0;
    }

    public void b(byte[] bArr) {
        b(bArr, 0, bArr.length);
    }

    public abstract void b(byte[] bArr, int i, int i2);

    public int c() {
        return -1;
    }

    public int d(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            int a = a(bArr, i + i3, i2 - i3);
            if (a <= 0) {
                throw new e(new StringBuilder("Cannot read. Remote side has closed. Tried to read ").append(i2).append(" bytes, but only got ").append(i3).append(" bytes.").toString());
            }
            i3 += a;
        }
        return i3;
    }
}
