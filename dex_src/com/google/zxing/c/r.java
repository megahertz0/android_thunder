package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.b;
import com.google.zxing.q;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Map;

// compiled from: OneDimensionalCodeWriter.java
public abstract class r implements q {
    public abstract boolean[] a(String str);

    public b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws com.google.zxing.r {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException(new StringBuilder("Negative size is not allowed. Input: ").append(i).append('x').append(i2).toString());
        } else {
            int intValue;
            int a = a();
            if (map != null) {
                Integer num = (Integer) map.get(EncodeHintType.MARGIN);
                if (num != null) {
                    intValue = num.intValue();
                    return a(a(str), i, i2, intValue);
                }
            }
            intValue = a;
            return a(a(str), i, i2, intValue);
        }
    }

    private static b a(boolean[] zArr, int i, int i2, int i3) {
        int length = zArr.length;
        int i4 = length + i3;
        int max = Math.max(i, i4);
        int max2 = Math.max(1, i2);
        int i5 = max / i4;
        i4 = (max - (length * i5)) / 2;
        b bVar = new b(max, max2);
        max = i4;
        i4 = 0;
        while (i4 < length) {
            if (zArr[i4]) {
                bVar.a(max, 0, i5, max2);
            }
            i4++;
            max += i5;
        }
        return bVar;
    }

    protected static int a(boolean[] zArr, int i, int[] iArr, boolean z) {
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = i;
        while (i2 < length) {
            int i5 = iArr[i2];
            int i6 = i4;
            i4 = 0;
            while (i4 < i5) {
                int i7 = i6 + 1;
                zArr[i6] = z;
                i4++;
                i6 = i7;
            }
            i3 += i5;
            i2++;
            z = !z ? 1 : null;
            i4 = i6;
        }
        return i3;
    }

    public int a() {
        return XZBDevice.Stop;
    }
}
