package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.a;

// compiled from: EAN8Reader.java
public final class j extends x {
    private final int[] a;

    public j() {
        this.a = new int[4];
    }

    protected final int a(a aVar, int[] iArr, StringBuilder stringBuilder) throws com.google.zxing.j {
        int[] iArr2 = this.a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i = aVar.b;
        int i2 = iArr[1];
        int i3 = 0;
        while (i3 < 4 && i2 < i) {
            stringBuilder.append((char) (a(aVar, iArr2, i2, d) + 48));
            int i4 = i2;
            for (i2 = 0; i2 < iArr2.length; i2++) {
                i4 += iArr2[i2];
            }
            i3++;
            i2 = i4;
        }
        i2 = a(aVar, i2, true, c)[1];
        i3 = 0;
        while (i3 < 4 && i2 < i) {
            stringBuilder.append((char) (a(aVar, iArr2, i2, d) + 48));
            i4 = i2;
            for (i2 = 0; i2 < iArr2.length; i2++) {
                i4 += iArr2[i2];
            }
            i3++;
            i2 = i4;
        }
        return i2;
    }

    final BarcodeFormat b() {
        return BarcodeFormat.EAN_8;
    }
}
