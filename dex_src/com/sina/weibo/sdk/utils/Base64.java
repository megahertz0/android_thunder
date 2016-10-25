package com.sina.weibo.sdk.utils;

import com.xunlei.tdlive.R;

public final class Base64 {
    private static char[] alphabet;
    private static byte[] codes;

    static {
        int i;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
        codes = new byte[256];
        for (i = 0; i < 256; i++) {
            codes[i] = (byte) -1;
        }
        for (i = R.styleable.AppCompatTheme_textAppearanceSearchResultTitle; i <= 90; i++) {
            codes[i] = (byte) (i - 65);
        }
        for (i = R.styleable.AppCompatTheme_buttonBarNegativeButtonStyle; i <= 122; i++) {
            codes[i] = (byte) ((i + 26) - 97);
        }
        for (i = R.styleable.AppCompatTheme_homeAsUpIndicator; i <= 57; i++) {
            codes[i] = (byte) ((i + 52) - 48);
        }
        codes[43] = (byte) 62;
        codes[47] = (byte) 63;
    }

    public static byte[] decode(byte[] bArr) {
        int i = 0;
        int length = ((bArr.length + 3) / 4) * 3;
        if (bArr.length > 0 && bArr[bArr.length - 1] == (byte) 61) {
            length--;
        }
        if (bArr.length > 1 && bArr[bArr.length - 2] == (byte) 61) {
            length--;
        }
        byte[] bArr2 = new byte[length];
        int i2 = 0;
        int i3 = 0;
        for (length = 0; length < bArr.length; length++) {
            byte b = codes[bArr[length] & 255];
            if (b >= null) {
                int i4 = i2 << 6;
                i2 = i3 + 6;
                i3 = i4 | b;
                if (i2 >= 8) {
                    int i5 = i2 - 8;
                    i2 = i + 1;
                    bArr2[i] = (byte) ((i3 >> i5) & 255);
                    i = i2;
                    i2 = i3;
                    i3 = i5;
                } else {
                    int i6 = i3;
                    i3 = i2;
                    i2 = i6;
                }
            }
        }
        if (i == bArr2.length) {
            return bArr2;
        }
        throw new RuntimeException("miscalculated data length!");
    }

    public static char[] encode(byte[] bArr) {
        char[] cArr = new char[(((bArr.length + 2) / 3) * 4)];
        int i = 0;
        int i2 = 0;
        while (i2 < bArr.length) {
            int i3;
            Object obj;
            int i4 = (bArr[i2] & 255) << 8;
            if (i2 + 1 < bArr.length) {
                i4 |= bArr[i2 + 1] & 255;
                int i5 = 1;
            } else {
                Object obj2 = null;
            }
            i4 <<= 8;
            if (i2 + 2 < bArr.length) {
                i4 |= bArr[i2 + 2] & 255;
                i3 = 1;
            } else {
                obj = null;
            }
            cArr[i + 3] = alphabet[obj != null ? i4 & 63 : 64];
            i3 = i4 >> 6;
            int i6 = i + 2;
            char[] cArr2 = alphabet;
            if (obj2 != null) {
                i4 = i3 & 63;
            } else {
                i4 = 64;
            }
            cArr[i6] = cArr2[i4];
            i4 = i3 >> 6;
            cArr[i + 1] = alphabet[i4 & 63];
            cArr[i + 0] = alphabet[(i4 >> 6) & 63];
            i2 += 3;
            i += 4;
        }
        return cArr;
    }

    public static byte[] encodebyte(byte[] bArr) {
        byte[] bArr2 = new byte[(((bArr.length + 2) / 3) * 4)];
        int i = 0;
        int i2 = 0;
        while (i2 < bArr.length) {
            int i3;
            Object obj;
            int i4 = (bArr[i2] & 255) << 8;
            if (i2 + 1 < bArr.length) {
                i4 |= bArr[i2 + 1] & 255;
                int i5 = 1;
            } else {
                Object obj2 = null;
            }
            i4 <<= 8;
            if (i2 + 2 < bArr.length) {
                i4 |= bArr[i2 + 2] & 255;
                i3 = 1;
            } else {
                obj = null;
            }
            bArr2[i + 3] = (byte) alphabet[obj != null ? i4 & 63 : 64];
            i3 = i4 >> 6;
            int i6 = i + 2;
            char[] cArr = alphabet;
            if (obj2 != null) {
                i4 = i3 & 63;
            } else {
                i4 = 64;
            }
            bArr2[i6] = (byte) cArr[i4];
            i4 = i3 >> 6;
            bArr2[i + 1] = (byte) alphabet[i4 & 63];
            bArr2[i + 0] = (byte) alphabet[(i4 >> 6) & 63];
            i2 += 3;
            i += 4;
        }
        return bArr2;
    }
}
