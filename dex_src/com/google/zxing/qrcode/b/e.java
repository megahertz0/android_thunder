package com.google.zxing.qrcode.b;

// compiled from: MaskUtil.java
final class e {
    static boolean a(byte[] bArr, int i, int i2) {
        while (i < i2) {
            if (i >= 0 && i < bArr.length && bArr[i] == (byte) 1) {
                return false;
            }
            i++;
        }
        return true;
    }

    static boolean a(byte[][] bArr, int i, int i2, int i3) {
        while (i2 < i3) {
            if (i2 >= 0 && i2 < bArr.length && bArr[i2][i] == (byte) 1) {
                return false;
            }
            i2++;
        }
        return true;
    }

    static int a(b bVar, boolean z) {
        int i;
        int i2;
        if (z) {
            i = bVar.c;
        } else {
            i = bVar.b;
        }
        if (z) {
            i2 = bVar.b;
        } else {
            i2 = bVar.c;
        }
        byte[][] bArr = bVar.a;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            Object obj = -1;
            int i5 = 0;
            int i6 = 0;
            while (i5 < i2) {
                int i7;
                int i8;
                byte b;
                byte b2 = z ? bArr[i3][i5] : bArr[i5][i3];
                if (b2 == obj) {
                    Object obj2 = obj;
                    i7 = i6 + 1;
                    i8 = i4;
                    Object obj3 = obj2;
                } else {
                    if (i6 >= 5) {
                        i4 += (i6 - 5) + 3;
                    }
                    obj = 1;
                    byte b3 = b2;
                    i8 = i4;
                    b = b3;
                }
                i5++;
                i6 = i7;
                obj = b;
                i4 = i8;
            }
            if (i6 >= 5) {
                i4 += (i6 - 5) + 3;
            }
            i3++;
        }
        return i4;
    }
}
