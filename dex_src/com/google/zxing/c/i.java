package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.b;
import com.google.zxing.e;
import com.google.zxing.r;
import com.xunlei.tdlive.R;
import java.util.Map;

// compiled from: EAN13Writer.java
public final class i extends y {
    public final b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws r {
        if (barcodeFormat == BarcodeFormat.EAN_13) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException(new StringBuilder("Can only encode EAN_13, but got ").append(barcodeFormat).toString());
    }

    public final boolean[] a(String str) {
        if (str.length() != 13) {
            throw new IllegalArgumentException(new StringBuilder("Requested contents should be 13 digits long, but got ").append(str.length()).toString());
        }
        try {
            if (x.a((CharSequence) str)) {
                int i;
                int parseInt;
                int i2 = h.a[Integer.parseInt(str.substring(0, 1))];
                boolean[] zArr = new boolean[95];
                int a = a(zArr, 0, x.b, true) + 0;
                for (i = 1; i <= 6; i++) {
                    parseInt = Integer.parseInt(str.substring(i, i + 1));
                    if (((i2 >> (6 - i)) & 1) == 1) {
                        parseInt += 10;
                    }
                    a += a(zArr, a, x.e[parseInt], false);
                }
                i = a + a(zArr, a, x.c, false);
                for (parseInt = R.styleable.Toolbar_contentInsetLeft; parseInt <= 12; parseInt++) {
                    i += a(zArr, i, x.d[Integer.parseInt(str.substring(parseInt, parseInt + 1))], true);
                }
                a(zArr, i, x.b, true);
                return zArr;
            }
            throw new IllegalArgumentException("Contents do not pass checksum");
        } catch (e e) {
            throw new IllegalArgumentException("Illegal contents");
        }
    }
}
