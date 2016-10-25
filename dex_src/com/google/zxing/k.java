package com.google.zxing;

// compiled from: RGBLuminanceSource.java
public final class k extends f {
    private final byte[] c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;

    public k(int i, int i2, int[] iArr) {
        super(i, i2);
        this.d = i;
        this.e = i2;
        this.f = 0;
        this.g = 0;
        this.c = new byte[(i * i2)];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 * i;
            for (int i5 = 0; i5 < i; i5++) {
                int i6 = iArr[i4 + i5];
                int i7 = (i6 >> 16) & 255;
                int i8 = (i6 >> 8) & 255;
                i6 &= 255;
                if (i7 == i8 && i8 == i6) {
                    this.c[i4 + i5] = (byte) i7;
                } else {
                    this.c[i4 + i5] = (byte) ((i6 + (i7 + (i8 * 2))) / 4);
                }
            }
        }
    }

    public final byte[] a(int i, byte[] bArr) {
        if (i < 0 || i >= this.b) {
            throw new IllegalArgumentException(new StringBuilder("Requested row is outside the image: ").append(i).toString());
        }
        int i2 = this.a;
        if (bArr == null || bArr.length < i2) {
            bArr = new byte[i2];
        }
        System.arraycopy(this.c, ((this.g + i) * this.d) + this.f, bArr, 0, i2);
        return bArr;
    }

    public final byte[] a() {
        int i = 0;
        int i2 = this.a;
        int i3 = this.b;
        if (i2 == this.d && i3 == this.e) {
            return this.c;
        }
        int i4 = i2 * i3;
        byte[] bArr = new byte[i4];
        int i5 = (this.g * this.d) + this.f;
        if (i2 == this.d) {
            System.arraycopy(this.c, i5, bArr, 0, i4);
            return bArr;
        }
        Object obj = this.c;
        while (i < i3) {
            System.arraycopy(obj, i5, bArr, i * i2, i2);
            i5 += this.d;
            i++;
        }
        return bArr;
    }
}
