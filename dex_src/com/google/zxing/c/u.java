package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.common.a;
import com.google.zxing.j;
import com.google.zxing.n;
import com.google.zxing.o;
import java.util.EnumMap;
import java.util.Map;

// compiled from: UPCEANExtension2Support.java
final class u {
    private final int[] a;
    private final StringBuilder b;

    u() {
        this.a = new int[4];
        this.b = new StringBuilder();
    }

    final n a(int i, a aVar, int[] iArr) throws j {
        StringBuilder stringBuilder = this.b;
        stringBuilder.setLength(0);
        int[] iArr2 = this.a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i2 = aVar.b;
        int i3 = iArr[1];
        int i4 = 0;
        int i5 = 0;
        while (i5 < 2 && i3 < i2) {
            int a = x.a(aVar, iArr2, i3, x.e);
            stringBuilder.append((char) ((a % 10) + 48));
            int i6 = i3;
            i3 = 0;
            while (i3 < iArr2.length) {
                int i7 = iArr2[i3] + i6;
                i3++;
                i6 = i7;
            }
            if (a >= 10) {
                i4 |= 1 << (1 - i5);
            }
            if (i5 != 1) {
                i6 = aVar.d(aVar.c(i6));
            }
            i5++;
            i3 = i6;
        }
        if (stringBuilder.length() != 2) {
            throw j.a();
        } else if (Integer.parseInt(stringBuilder.toString()) % 4 != i4) {
            throw j.a();
        } else {
            Map map;
            String toString = stringBuilder.toString();
            if (toString.length() != 2) {
                map = null;
            } else {
                map = new EnumMap(ResultMetadataType.class);
                map.put(ResultMetadataType.ISSUE_NUMBER, Integer.valueOf(toString));
            }
            n nVar = new n(toString, null, new o[]{new o(((float) (iArr[0] + iArr[1])) / 2.0f, (float) i), new o((float) i3, (float) i)}, BarcodeFormat.UPC_EAN_EXTENSION);
            if (map != null) {
                nVar.a(map);
            }
            return nVar;
        }
    }
}
