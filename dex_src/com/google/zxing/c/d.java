package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.b;
import com.google.zxing.r;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// compiled from: Code128Writer.java
public final class d extends r {
    public final b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws r {
        if (barcodeFormat == BarcodeFormat.CODE_128) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException(new StringBuilder("Can only encode CODE_128, but got ").append(barcodeFormat).toString());
    }

    public final boolean[] a(String str) {
        int i = 0;
        int length = str.length();
        if (length <= 0 || length > 80) {
            throw new IllegalArgumentException(new StringBuilder("Contents length should be between 1 and 80 characters, but got ").append(length).toString());
        }
        int i2;
        for (i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt < ' ' || charAt > '~') {
                switch (charAt) {
                    case '\u00f1':
                    case '\u00f2':
                    case '\u00f3':
                    case '\u00f4':
                        break;
                    default:
                        throw new IllegalArgumentException(new StringBuilder("Bad character in input: ").append(charAt).toString());
                }
            }
        }
        Collection<int[]> arrayList = new ArrayList();
        int i3 = 0;
        int i4 = 0;
        int i5 = 1;
        int i6 = 0;
        while (i3 < length) {
            int i7;
            if (a(str, i3, i4 == 99 ? XZBDevice.DOWNLOAD_LIST_RECYCLE : XZBDevice.DOWNLOAD_LIST_ALL)) {
                i7 = 99;
            } else {
                i7 = 100;
            }
            if (i7 == i4) {
                switch (str.charAt(i3)) {
                    case '\u00f1':
                        i2 = R.styleable.AppCompatTheme_checkboxStyle;
                        break;
                    case '\u00f2':
                        i2 = R.styleable.AppCompatTheme_buttonBarNegativeButtonStyle;
                        break;
                    case '\u00f3':
                        i2 = R.styleable.AppCompatTheme_buttonBarPositiveButtonStyle;
                        break;
                    case '\u00f4':
                        i2 = 100;
                        break;
                    default:
                        if (i4 == 100) {
                            i2 = str.charAt(i3) - 32;
                        } else {
                            i2 = Integer.parseInt(str.substring(i3, i3 + 2));
                            i3++;
                        }
                        break;
                }
                i3++;
                i7 = i4;
            } else if (i4 == 0) {
                i2 = i7 == 100 ? R.styleable.AppCompatTheme_editTextStyle : R.styleable.AppCompatTheme_radioButtonStyle;
            } else {
                i2 = i7;
            }
            arrayList.add(c.a[i2]);
            i4 = i6 + (i2 * i5);
            if (i3 != 0) {
                i2 = i5 + 1;
            } else {
                i2 = i5;
            }
            i5 = i2;
            i6 = i4;
            i4 = i7;
        }
        arrayList.add(c.a[i6 % 103]);
        arrayList.add(c.a[106]);
        int i8 = 0;
        for (int[] iArr : arrayList) {
            for (i3 = 0; i3 < iArr.length; i3++) {
                i8 += iArr[i3];
            }
        }
        boolean[] zArr = new boolean[i8];
        for (int[] iArr2 : arrayList) {
            i += a(zArr, i, iArr2, true);
        }
        return zArr;
    }

    private static boolean a(CharSequence charSequence, int i, int i2) {
        int i3 = i + i2;
        int length = charSequence.length();
        while (i < i3 && i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < '0' || charAt > '9') {
                if (charAt != '\u00f1') {
                    return false;
                }
                i3++;
            }
            i++;
        }
        return i3 <= length;
    }
}
