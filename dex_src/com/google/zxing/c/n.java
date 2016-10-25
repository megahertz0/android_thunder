package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.b;
import com.google.zxing.r;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Map;

// compiled from: ITFWriter.java
public final class n extends r {
    private static final int[] a;
    private static final int[] b;

    static {
        a = new int[]{1, 1, 1, 1};
        b = new int[]{3, 1, 1};
    }

    public final b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws r {
        if (barcodeFormat == BarcodeFormat.ITF) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException(new StringBuilder("Can only encode ITF, but got ").append(barcodeFormat).toString());
    }

    public final boolean[] a(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("The lenght of the input should be even");
        } else if (length > 80) {
            throw new IllegalArgumentException(new StringBuilder("Requested contents should be less than 80 digits long, but got ").append(length).toString());
        } else {
            boolean[] zArr = new boolean[((length * 9) + 9)];
            int a = a(zArr, 0, a, true);
            for (int i = 0; i < length; i += 2) {
                int digit = Character.digit(str.charAt(i), XZBDevice.Stop);
                int digit2 = Character.digit(str.charAt(i + 1), XZBDevice.Stop);
                int[] iArr = new int[18];
                for (int i2 = 0; i2 < 5; i2++) {
                    iArr[i2 * 2] = m.a[digit][i2];
                    iArr[(i2 * 2) + 1] = m.a[digit2][i2];
                }
                a += a(zArr, a, iArr, true);
            }
            a(zArr, a, b, true);
            return zArr;
        }
    }
}
