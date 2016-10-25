package com.xiaomi.channel.commonutils.string;

public class a {
    private static final String a;
    private static char[] b;
    private static byte[] c;

    static {
        int i;
        int i2 = 0;
        a = System.getProperty("line.separator");
        b = new char[64];
        char c = 'A';
        int i3 = 0;
        while (c <= 'Z') {
            i = i3 + 1;
            b[i3] = c;
            c = (char) (c + 1);
            i3 = i;
        }
        c = 'a';
        while (c <= 'z') {
            i = i3 + 1;
            b[i3] = c;
            c = (char) (c + 1);
            i3 = i;
        }
        c = '0';
        while (c <= '9') {
            i = i3 + 1;
            b[i3] = c;
            c = (char) (c + 1);
            i3 = i;
        }
        i = i3 + 1;
        b[i3] = '+';
        b[i] = '/';
        c = new byte[128];
        for (int i4 = 0; i4 < c.length; i4++) {
            c[i4] = (byte) -1;
        }
        while (i2 < 64) {
            c[b[i2]] = (byte) i2;
            i2++;
        }
    }

    public static byte[] a(String str) {
        return a(str.toCharArray());
    }

    public static byte[] a(char[] cArr) {
        return a(cArr, 0, cArr.length);
    }

    public static byte[] a(char[] cArr, int i, int i2) {
        if (i2 % 4 != 0) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
        }
        while (i2 > 0 && cArr[(i + i2) - 1] == '=') {
            i2--;
        }
        int i3 = (i2 * 3) / 4;
        byte[] bArr = new byte[i3];
        int i4 = i + i2;
        int i5 = 0;
        while (i < i4) {
            int i6;
            int i7 = i + 1;
            char c = cArr[i];
            int i8 = i7 + 1;
            char c2 = cArr[i7];
            if (i8 < i4) {
                char c3 = cArr[i8];
                i8++;
            } else {
                int i9 = 65;
            }
            if (i8 < i4) {
                i7 = i8 + 1;
                i8 = cArr[i8];
                i6 = i7;
            } else {
                i6 = i8;
                i8 = 65;
            }
            if (c <= '\u007f' && c2 <= '\u007f' && i9 <= 127 && i8 <= 127) {
                byte b = c[c];
                byte b2 = c[c2];
                byte b3 = c[i9];
                byte b4 = c[i8];
                if (b >= null && b2 >= null && b3 >= null && b4 >= null) {
                    i7 = (b << 2) | (b2 >>> 4);
                    int i10 = ((b2 & 15) << 4) | (b3 >>> 2);
                    i9 = ((b3 & 3) << 6) | b4;
                    i8 = i5 + 1;
                    bArr[i5] = (byte) i7;
                    if (i8 < i3) {
                        i7 = i8 + 1;
                        bArr[i8] = (byte) i10;
                    } else {
                        i7 = i8;
                    }
                    if (i7 < i3) {
                        i8 = i7 + 1;
                        bArr[i7] = (byte) i9;
                    } else {
                        i8 = i7;
                    }
                    i = i6;
                    i5 = i8;
                }
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
        }
        return bArr;
    }

    public static char[] a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static char[] a(byte[] bArr, int i, int i2) {
        int i3 = ((i2 * 4) + 2) / 3;
        char[] cArr = new char[(((i2 + 2) / 3) * 4)];
        int i4 = i + i2;
        int i5 = 0;
        while (i < i4) {
            int i6;
            int i7;
            int i8 = i + 1;
            int i9 = bArr[i] & 255;
            if (i8 < i4) {
                i6 = bArr[i8] & 255;
                i8++;
            } else {
                i6 = 0;
            }
            if (i8 < i4) {
                i7 = i8 + 1;
                i8 = bArr[i8] & 255;
            } else {
                i7 = i8;
                i8 = 0;
            }
            int i10 = i9 >>> 2;
            i9 = ((i9 & 3) << 4) | (i6 >>> 4);
            i6 = ((i6 & 15) << 2) | (i8 >>> 6);
            int i11 = i8 & 63;
            i8 = i5 + 1;
            cArr[i5] = b[i10];
            i5 = i8 + 1;
            cArr[i8] = b[i9];
            cArr[i5] = i5 < i3 ? b[i6] : '=';
            i6 = i5 + 1;
            cArr[i6] = i6 < i3 ? b[i11] : '=';
            i = i7;
            i5 = i6 + 1;
        }
        return cArr;
    }
}
