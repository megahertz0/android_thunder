package com.google.protobuf.micro;

import com.sina.weibo.sdk.component.GameManager;
import com.taobao.accs.data.Message;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public final class b {
    private final byte[] a;
    private final int b;
    private int c;
    private final OutputStream d;

    public static class a extends IOException {
        a() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

    private b(OutputStream outputStream, byte[] bArr) {
        this.d = outputStream;
        this.a = bArr;
        this.c = 0;
        this.b = bArr.length;
    }

    private b(byte[] bArr, int i, int i2) {
        this.d = null;
        this.a = bArr;
        this.c = i;
        this.b = i + i2;
    }

    public static b a(OutputStream outputStream) {
        return a(outputStream, (int) Message.FLAG_ERR);
    }

    public static b a(OutputStream outputStream, int i) {
        return new b(outputStream, new byte[i]);
    }

    public static b a(byte[] bArr, int i, int i2) {
        return new b(bArr, i, i2);
    }

    public static int b(int i, boolean z) {
        return f(i) + b(z);
    }

    public static int b(String str) {
        try {
            byte[] bytes = str.getBytes(GameManager.DEFAULT_CHARSET);
            return bytes.length + h(bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }

    public static int b(boolean z) {
        return 1;
    }

    public static int c(int i) {
        return i >= 0 ? h(i) : XZBDevice.Stop;
    }

    public static int c(int i, int i2) {
        return f(i) + c(i2);
    }

    public static int d(int i) {
        return h(i);
    }

    public static int d(int i, int i2) {
        return f(i) + d(i2);
    }

    private void d() {
        if (this.d == null) {
            throw new a();
        }
        this.d.write(this.a, 0, this.c);
        this.c = 0;
    }

    public static int f(int i) {
        return h(e.a(i, 0));
    }

    public static int h(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? XZBDevice.DOWNLOAD_LIST_RECYCLE : (-2097152 & i) == 0 ? XZBDevice.DOWNLOAD_LIST_FAILED : (-268435456 & i) == 0 ? XZBDevice.DOWNLOAD_LIST_ALL : XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
    }

    public final void a() {
        if (this.d != null) {
            d();
        }
    }

    public final void a(byte b) {
        if (this.c == this.b) {
            d();
        }
        byte[] bArr = this.a;
        int i = this.c;
        this.c = i + 1;
        bArr[i] = b;
    }

    public final void a(int i) {
        if (i >= 0) {
            g(i);
        } else {
            a((long) i);
        }
    }

    public final void a(int i, int i2) {
        e(i, 0);
        a(i2);
    }

    public final void a(int i, String str) {
        e(i, XZBDevice.DOWNLOAD_LIST_RECYCLE);
        a(str);
    }

    public final void a(int i, boolean z) {
        e(i, 0);
        a(z);
    }

    public final void a(long j) {
        while ((-128 & j) != 0) {
            e((((int) j) & 127) | 128);
            j >>>= 7;
        }
        e((int) j);
    }

    public final void a(String str) {
        byte[] bytes = str.getBytes(GameManager.DEFAULT_CHARSET);
        g(bytes.length);
        a(bytes);
    }

    public final void a(boolean z) {
        e(z ? 1 : 0);
    }

    public final void a(byte[] bArr) {
        b(bArr, 0, bArr.length);
    }

    public final int b() {
        if (this.d == null) {
            return this.b - this.c;
        }
        throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array.");
    }

    public final void b(int i) {
        g(i);
    }

    public final void b(int i, int i2) {
        e(i, 0);
        b(i2);
    }

    public final void b(byte[] bArr, int i, int i2) {
        if (this.b - this.c >= i2) {
            System.arraycopy(bArr, i, this.a, this.c, i2);
            this.c += i2;
            return;
        }
        int i3 = this.b - this.c;
        System.arraycopy(bArr, i, this.a, this.c, i3);
        int i4 = i + i3;
        i3 = i2 - i3;
        this.c = this.b;
        d();
        if (i3 <= this.b) {
            System.arraycopy(bArr, i4, this.a, 0, i3);
            this.c = i3;
            return;
        }
        this.d.write(bArr, i4, i3);
    }

    public final void c() {
        if (b() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public final void e(int i) {
        a((byte) i);
    }

    public final void e(int i, int i2) {
        g(e.a(i, i2));
    }

    public final void g(int i) {
        while ((i & -128) != 0) {
            e((i & 127) | 128);
            i >>>= 7;
        }
        e(i);
    }
}
