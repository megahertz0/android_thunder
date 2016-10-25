package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.q;
import com.google.zxing.qrcode.b.c;
import com.google.zxing.qrcode.b.g;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.r;
import java.util.Map;

// compiled from: QRCodeWriter.java
public final class b implements q {
    public final com.google.zxing.common.b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws r {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (barcodeFormat != BarcodeFormat.QR_CODE) {
            throw new IllegalArgumentException(new StringBuilder("Can only encode QR_CODE, but got ").append(barcodeFormat).toString());
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException(new StringBuilder("Requested dimensions are too small: ").append(i).append('x').append(i2).toString());
        } else {
            int intValue;
            ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
            if (map != null) {
                ErrorCorrectionLevel errorCorrectionLevel2 = (ErrorCorrectionLevel) map.get(EncodeHintType.ERROR_CORRECTION);
                if (errorCorrectionLevel2 != null) {
                    errorCorrectionLevel = errorCorrectionLevel2;
                }
                Integer num = (Integer) map.get(EncodeHintType.MARGIN);
                if (num != null) {
                    intValue = num.intValue();
                    return a(c.a(str, errorCorrectionLevel, map), i, i2, intValue);
                }
            }
            intValue = 4;
            return a(c.a(str, errorCorrectionLevel, map), i, i2, intValue);
        }
    }

    private static com.google.zxing.common.b a(g gVar, int i, int i2, int i3) {
        com.google.zxing.qrcode.b.b bVar = gVar.e;
        if (bVar == null) {
            throw new IllegalStateException();
        }
        int i4 = bVar.b;
        int i5 = bVar.c;
        int i6 = (i3 * 2) + i4;
        int i7 = (i3 * 2) + i5;
        int max = Math.max(i, i6);
        int max2 = Math.max(i2, i7);
        int min = Math.min(max / i6, max2 / i7);
        i7 = (max - (i4 * min)) / 2;
        i6 = (max2 - (i5 * min)) / 2;
        com.google.zxing.common.b bVar2 = new com.google.zxing.common.b(max, max2);
        max2 = i6;
        for (int i8 = 0; i8 < i5; i8++) {
            max = 0;
            i6 = i7;
            while (max < i4) {
                if (bVar.a(max, i8) == 1) {
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
