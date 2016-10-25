package com.google.zxing.a;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.a.c.a;
import com.google.zxing.common.b;
import com.google.zxing.q;
import java.nio.charset.Charset;
import java.util.Map;

// compiled from: AztecWriter.java
public final class c implements q {
    private static final Charset a;

    static {
        a = Charset.forName("ISO-8859-1");
    }

    public final b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        Charset charset;
        int i3;
        int i4;
        String str2 = map == null ? null : (String) map.get(EncodeHintType.CHARACTER_SET);
        Number number = map == null ? null : (Number) map.get(EncodeHintType.ERROR_CORRECTION);
        Number number2 = map == null ? null : (Number) map.get(EncodeHintType.AZTEC_LAYERS);
        if (str2 == null) {
            charset = a;
        } else {
            charset = Charset.forName(str2);
        }
        if (number == null) {
            i3 = 33;
        } else {
            i3 = number.intValue();
        }
        if (number2 == null) {
            i4 = 0;
        } else {
            i4 = number2.intValue();
        }
        if (barcodeFormat == BarcodeFormat.AZTEC) {
            return a(com.google.zxing.a.c.c.a(str.getBytes(charset), i3, i4), i, i2);
        }
        throw new IllegalArgumentException(new StringBuilder("Can only encode AZTEC, but got ").append(barcodeFormat).toString());
    }

    private static b a(a aVar, int i, int i2) {
        b bVar = aVar.e;
        if (bVar == null) {
            throw new IllegalStateException();
        }
        int i3 = bVar.a;
        int i4 = bVar.b;
        int max = Math.max(i, i3);
        int max2 = Math.max(i2, i4);
        int min = Math.min(max / i3, max2 / i4);
        int i5 = (max - (i3 * min)) / 2;
        int i6 = (max2 - (i4 * min)) / 2;
        b bVar2 = new b(max, max2);
        max2 = i6;
        for (int i7 = 0; i7 < i4; i7++) {
            i6 = i5;
            max = 0;
            while (max < i3) {
                if (bVar.a(max, i7)) {
                    bVar2.a(i6, max2, min, min);
                }
                max++;
                i6 += min;
            }
            max2 += min;
        }
        return bVar2;
    }
}
