package com.google.zxing.client.a;

import com.google.zxing.f;

// compiled from: PlanarYUVLuminanceSource.java
public final class b extends f {
    final byte[] c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;

    public b(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6) {
        super(i5, i6);
        if (i3 + i5 > i || i4 + i6 > i2) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.c = bArr;
        this.d = i;
        this.e = i2;
        this.f = i3;
        this.g = i4;
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
