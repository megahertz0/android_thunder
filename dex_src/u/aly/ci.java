package u.aly;

// compiled from: TTransport.java
public abstract class ci {
    public abstract int a(byte[] bArr, int i, int i2) throws cj;

    public abstract void b(byte[] bArr, int i, int i2) throws cj;

    public final int a(byte[] bArr, int i) throws cj {
        int i2 = 0;
        while (i2 < i) {
            int a = a(bArr, i2 + 0, i - i2);
            if (a <= 0) {
                throw new cj(new StringBuilder("Cannot read. Remote side has closed. Tried to read ").append(i).append(" bytes, but only got ").append(i2).append(" bytes. (This is often indicative of an internal error on the server side. Please check your server logs.)").toString(), (byte) 0);
            }
            i2 += a;
        }
        return i2;
    }

    public final void a(byte[] bArr) throws cj {
        b(bArr, 0, bArr.length);
    }

    public byte[] a() {
        return null;
    }

    public int b() {
        return 0;
    }

    public int c() {
        return -1;
    }

    public void a(int i) {
    }
}
