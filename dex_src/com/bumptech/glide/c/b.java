package com.bumptech.glide.c;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.io.OutputStream;

// compiled from: LZWEncoder.java
final class b {
    int a;
    int b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int[] j;
    int[] k;
    int l;
    int m;
    boolean n;
    int o;
    int p;
    int q;
    int r;
    int s;
    int[] t;
    int u;
    byte[] v;
    private byte[] w;

    b(int i, int i2, byte[] bArr, int i3) {
        this.g = 12;
        this.i = 4096;
        this.j = new int[5003];
        this.k = new int[5003];
        this.l = 5003;
        this.m = 0;
        this.n = false;
        this.r = 0;
        this.s = 0;
        this.t = new int[]{0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535};
        this.v = new byte[256];
        this.a = i;
        this.b = i2;
        this.w = bArr;
        this.c = Math.max(XZBDevice.DOWNLOAD_LIST_RECYCLE, i3);
    }

    private void a(byte b, OutputStream outputStream) throws IOException {
        byte[] bArr = this.v;
        int i = this.u;
        this.u = i + 1;
        bArr[i] = b;
        if (this.u >= 254) {
            a(outputStream);
        }
    }

    private void a(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.j[i2] = -1;
        }
    }

    final void a(int i, OutputStream outputStream) throws IOException {
        int i2;
        int i3 = 0;
        this.o = i;
        this.n = false;
        this.f = this.o;
        this.h = (1 << this.f) - 1;
        this.p = 1 << (i - 1);
        this.q = this.p + 1;
        this.m = this.p + 2;
        this.u = 0;
        int a = a();
        for (i2 = this.l; i2 < 65536; i2 *= 2) {
            i3++;
        }
        int i4 = 8 - i3;
        int i5 = this.l;
        a(i5);
        b(this.p, outputStream);
        i3 = a;
        while (true) {
            i2 = a();
            if (i2 != -1) {
                int i6 = (i2 << this.g) + i3;
                a = (i2 << i4) ^ i3;
                if (this.j[a] == i6) {
                    i3 = this.k[a];
                } else {
                    int i7;
                    if (this.j[a] >= 0) {
                        i7 = i5 - a;
                        if (a == 0) {
                            i7 = 1;
                        }
                        do {
                            a -= i7;
                            if (a < 0) {
                                a += i5;
                            }
                            if (this.j[a] == i6) {
                                i3 = this.k[a];
                                break;
                            }
                        } while (this.j[a] >= 0);
                    }
                    b(i3, outputStream);
                    if (this.m < this.i) {
                        int[] iArr = this.k;
                        i7 = this.m;
                        this.m = i7 + 1;
                        iArr[a] = i7;
                        this.j[a] = i6;
                        i3 = i2;
                    } else {
                        a(this.l);
                        this.m = this.p + 2;
                        this.n = true;
                        b(this.p, outputStream);
                        i3 = i2;
                    }
                }
            } else {
                b(i3, outputStream);
                b(this.q, outputStream);
                return;
            }
        }
    }

    private void a(OutputStream outputStream) throws IOException {
        if (this.u > 0) {
            outputStream.write(this.u);
            outputStream.write(this.v, 0, this.u);
            this.u = 0;
        }
    }

    private int a() {
        if (this.d == 0) {
            return -1;
        }
        this.d--;
        byte[] bArr = this.w;
        int i = this.e;
        this.e = i + 1;
        return bArr[i] & 255;
    }

    private void b(int i, OutputStream outputStream) throws IOException {
        this.r &= this.t[this.s];
        if (this.s > 0) {
            this.r |= i << this.s;
        } else {
            this.r = i;
        }
        this.s += this.f;
        while (this.s >= 8) {
            a((byte) (this.r & 255), outputStream);
            this.r >>= 8;
            this.s -= 8;
        }
        if (this.m > this.h || this.n) {
            if (this.n) {
                int i2 = this.o;
                this.f = i2;
                this.h = (1 << i2) - 1;
                this.n = false;
            } else {
                this.f++;
                if (this.f == this.g) {
                    this.h = this.i;
                } else {
                    this.h = (1 << this.f) - 1;
                }
            }
        }
        if (i == this.q) {
            while (this.s > 0) {
                a((byte) (this.r & 255), outputStream);
                this.r >>= 8;
                this.s -= 8;
            }
            a(outputStream);
        }
    }
}
