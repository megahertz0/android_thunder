package com.google.zxing.common;

// compiled from: BitSource.java
public final class c {
    public int a;
    public int b;
    private final byte[] c;

    public c(byte[] bArr) {
        this.c = bArr;
    }

    public final int a(int i) {
        if (i <= 0 || i > 32 || i > a()) {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        int i2;
        int i3;
        if (this.b > 0) {
            i2 = 8 - this.b;
            i3 = i < i2 ? i : i2;
            i2 -= i3;
            i2 = (((255 >> (8 - i3)) << i2) & this.c[this.a]) >> i2;
            i -= i3;
            this.b = i3 + this.b;
            if (this.b == 8) {
                this.b = 0;
                this.a++;
            }
            i3 = i2;
            i2 = i;
        } else {
            i3 = 0;
            i2 = i;
        }
        if (i2 <= 0) {
            return i3;
        }
        while (i2 >= 8) {
            i3 = (i3 << 8) | (this.c[this.a] & 255);
            this.a++;
            i2 -= 8;
        }
        if (i2 <= 0) {
            return i3;
        }
        int i4 = 8 - i2;
        i3 = (i3 << i2) | ((((255 >> i4) << i4) & this.c[this.a]) >> i4);
        this.b = i2 + this.b;
        return i3;
    }

    public final int a() {
        return ((this.c.length - this.a) * 8) - this.b;
    }
}
