package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.a;
import com.google.zxing.j;

// compiled from: EAN13Reader.java
public final class h extends x {
    static final int[] a;
    private final int[] f;

    static {
        a = new int[]{0, 11, 13, 14, 19, 25, 28, 21, 22, 26};
    }

    public h() {
        this.f = new int[4];
    }

    protected final int a(a aVar, int[] iArr, StringBuilder stringBuilder) throws j {
        int i;
        int i2;
        int[] iArr2 = this.f;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i3 = aVar.b;
        int i4 = iArr[1];
        int i5 = 0;
        for (i = 0; i < 6 && i4 < i3; i++) {
            int a = a(aVar, iArr2, i4, e);
            stringBuilder.append((char) ((a % 10) + 48));
            for (int i6 : iArr2) {
                i4 += i6;
            }
            if (a >= 10) {
                i5 |= 1 << (5 - i);
            }
        }
        for (i2 = 0; i2 < 10; i2++) {
            if (i5 == a[i2]) {
                stringBuilder.insert(0, (char) (i2 + 48));
                i2 = a(aVar, i4, true, c)[1];
                for (i5 = 0; i5 < 6 && i2 < i3; i5++) {
                    stringBuilder.append((char) (a(aVar, iArr2, i2, d) + 48));
                    i4 = 0;
                    while (i4 < iArr2.length) {
                        i = iArr2[i4] + i2;
                        i4++;
                        i2 = i;
                    }
                }
                return i2;
            }
        }
        throw j.a();
    }

    final BarcodeFormat b() {
        return BarcodeFormat.EAN_13;
    }
}
