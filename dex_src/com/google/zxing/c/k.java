package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.b;
import com.google.zxing.r;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Map;

// compiled from: EAN8Writer.java
public final class k extends y {
    public final b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws r {
        if (barcodeFormat == BarcodeFormat.EAN_8) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException(new StringBuilder("Can only encode EAN_8, but got ").append(barcodeFormat).toString());
    }

    public final boolean[] a(String str) {
        if (str.length() != 8) {
            throw new IllegalArgumentException(new StringBuilder("Requested contents should be 8 digits long, but got ").append(str.length()).toString());
        }
        int i;
        boolean[] zArr = new boolean[67];
        int a = a(zArr, 0, x.b, true) + 0;
        for (i = 0; i <= 3; i++) {
            a += a(zArr, a, x.d[Integer.parseInt(str.substring(i, i + 1))], false);
        }
        int a2 = a + a(zArr, a, x.c, false);
        for (i = XZBDevice.DOWNLOAD_LIST_ALL; i <= 7; i++) {
            a2 += a(zArr, a2, x.d[Integer.parseInt(str.substring(i, i + 1))], true);
        }
        a(zArr, a2, x.b, true);
        return zArr;
    }
}
