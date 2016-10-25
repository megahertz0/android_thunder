package anet.channel.a;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// compiled from: Taobao
public class a implements Comparable<a> {
    final byte[] a;
    int b;
    int c;

    public /* synthetic */ int compareTo(Object obj) {
        return a((a) obj);
    }

    private a(byte[] bArr, int i) {
        if (bArr == null) {
            bArr = new byte[i];
        }
        this.a = bArr;
        this.b = this.a.length;
        this.c = i;
    }

    public static a a(int i) {
        return new a(null, i);
    }

    public static a a(byte[] bArr, int i) {
        return (bArr == null || i <= 0) ? null : new a(bArr, i);
    }

    public static a a(byte[] bArr) {
        return bArr == null ? null : a(bArr, bArr.length);
    }

    public byte[] a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public void d() {
        if (this.b != 0) {
            a.a.a(this);
        }
    }

    public int a(InputStream inputStream) throws IOException {
        int i = 0;
        int read = inputStream.read(this.a, 0, this.b);
        if (read != -1) {
            i = read;
        }
        this.c = i;
        return read;
    }

    public void a(OutputStream outputStream) throws IOException {
        outputStream.write(this.a, 0, this.c);
    }

    public int a(a aVar) {
        if (this.b != aVar.b) {
            return this.b - aVar.b;
        }
        if (this.a == null) {
            return -1;
        }
        return aVar.a == null ? 1 : hashCode() - aVar.hashCode();
    }
}
