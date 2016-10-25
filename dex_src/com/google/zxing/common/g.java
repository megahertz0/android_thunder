package com.google.zxing.common;

import com.google.zxing.a;
import com.google.zxing.f;
import com.google.zxing.j;

// compiled from: GlobalHistogramBinarizer.java
public class g extends a {
    private static final byte[] b;
    private byte[] c;
    private final int[] d;

    static {
        b = new byte[0];
    }

    public g(f fVar) {
        super(fVar);
        this.c = b;
        this.d = new int[32];
    }

    public final a a(int i, a aVar) throws j {
        int i2;
        int i3;
        int i4 = 1;
        f fVar = this.a;
        int i5 = fVar.a;
        if (aVar == null || aVar.b < i5) {
            aVar = new a(i5);
        } else {
            aVar.b();
        }
        a(i5);
        byte[] a = fVar.a(i, this.c);
        int[] iArr = this.d;
        for (i2 = 0; i2 < i5; i2++) {
            i3 = (a[i2] & 255) >> 3;
            iArr[i3] = iArr[i3] + 1;
        }
        i3 = a(iArr);
        i2 = a[0] & 255;
        int i6 = a[1] & 255;
        while (i4 < i5 - 1) {
            int i7 = a[i4 + 1] & 255;
            if ((((i6 * 4) - i2) - i7) / 2 < i3) {
                aVar.b(i4);
            }
            i4++;
            i2 = i6;
            i6 = i7;
        }
        return aVar;
    }

    public b a() throws j {
        int i;
        int i2;
        f fVar = this.a;
        int i3 = fVar.a;
        int i4 = fVar.b;
        b bVar = new b(i3, i4);
        a(i3);
        int[] iArr = this.d;
        for (i = 1; i < 5; i++) {
            byte[] a = fVar.a((i4 * i) / 5, this.c);
            int i5 = (i3 * 4) / 5;
            for (i2 = i3 / 5; i2 < i5; i2++) {
                int i6 = (a[i2] & 255) >> 3;
                iArr[i6] = iArr[i6] + 1;
            }
        }
        int a2 = a(iArr);
        byte[] a3 = fVar.a();
        for (i = 0; i < i4; i++) {
            int i7 = i * i3;
            for (i2 = 0; i2 < i3; i2++) {
                if ((a3[i7 + i2] & 255) < a2) {
                    bVar.b(i2, i);
                }
            }
        }
        return bVar;
    }

    private void a(int i) {
        if (this.c.length < i) {
            this.c = new byte[i];
        }
        for (int i2 = 0; i2 < 32; i2++) {
            this.d[i2] = 0;
        }
    }

    private static int a(int[] iArr) throws j {
        int i;
        int i2;
        int length = iArr.length;
        Object obj = null;
        int i3 = 0;
        int i4 = 0;
        for (i = 0; i < length; i++) {
            if (iArr[i] > i2) {
                i2 = iArr[i];
                i3 = i;
            }
            if (iArr[i] > i4) {
                i4 = iArr[i];
            }
        }
        i = 0;
        i2 = 0;
        Object obj2 = null;
        while (i < length) {
            int i5 = i - i3;
            i5 *= iArr[i] * i5;
            if (i5 > r5) {
                i2 = i;
            } else {
                i5 = r5;
            }
            i++;
            int i6 = i5;
        }
        if (i3 <= i2) {
            int i7 = i2;
            i2 = i3;
            i3 = i7;
        }
        if (i3 - i2 <= length / 16) {
            throw j.a();
        }
        length = i3 - 1;
        Object obj3 = -1;
        i6 = i3 - 1;
        while (i6 > i2) {
            i = i6 - i2;
            i = ((i * i) * (i3 - i6)) * (i4 - iArr[i6]);
            if (i > i5) {
                i5 = i6;
            } else {
                i = i5;
                i5 = length;
            }
            i6--;
            length = i5;
            i5 = i;
        }
        return length << 3;
    }
}
