package u.aly;

// compiled from: TMemoryInputTransport.java
public final class ch extends ci {
    byte[] a;
    int b;
    int c;

    public final int a(byte[] bArr, int i, int i2) throws cj {
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

    public final void b(byte[] bArr, int i, int i2) throws cj {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    public final byte[] a() {
        return this.a;
    }

    public final int b() {
        return this.b;
    }

    public final int c() {
        return this.c - this.b;
    }

    public final void a(int i) {
        this.b += i;
    }
}
