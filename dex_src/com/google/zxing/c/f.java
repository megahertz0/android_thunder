package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.b;
import com.google.zxing.r;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Map;

// compiled from: Code39Writer.java
public final class f extends r {
    public final b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws r {
        if (barcodeFormat == BarcodeFormat.CODE_39) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException(new StringBuilder("Can only encode CODE_39, but got ").append(barcodeFormat).toString());
    }

    public final boolean[] a(String str) {
        int length = str.length();
        if (length > 80) {
            throw new IllegalArgumentException(new StringBuilder("Requested contents should be less than 80 digits long, but got ").append(length).toString());
        }
        int[] iArr = new int[9];
        int i = length + 25;
        int i2 = 0;
        while (i2 < length) {
            int indexOf = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(str.charAt(i2));
            if (indexOf < 0) {
                throw new IllegalArgumentException(new StringBuilder("Bad contents: ").append(str).toString());
            }
            a(e.a[indexOf], iArr);
            indexOf = i;
            for (i = 0; i < 9; i++) {
                indexOf += iArr[i];
            }
            i2++;
            i = indexOf;
        }
        boolean[] zArr = new boolean[i];
        a(e.a[39], iArr);
        i = a(zArr, 0, iArr, true);
        int[] iArr2 = new int[]{1};
        i += a(zArr, i, iArr2, false);
        for (indexOf = 0; indexOf < length; indexOf++) {
            a(e.a["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(str.charAt(indexOf))], iArr);
            i += a(zArr, i, iArr, true);
            i += a(zArr, i, iArr2, false);
        }
        a(e.a[39], iArr);
        a(zArr, i, iArr, true);
        return zArr;
    }

    private static void a(int i, int[] iArr) {
        for (int i2 = 0; i2 < 9; i2++) {
            int i3;
            if (((1 << (8 - i2)) & i) == 0) {
                i3 = 1;
            } else {
                Object obj = XZBDevice.DOWNLOAD_LIST_RECYCLE;
            }
            iArr[i2] = i3;
        }
    }
}
