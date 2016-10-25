package com.google.zxing.datamatrix.encoder;

// compiled from: ErrorCorrection.java
public final class i {
    private static final int[] a;
    private static final int[][] b;
    private static final int[] c;
    private static final int[] d;

    static {
        a = new int[]{5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
        b = new int[][]{new int[]{228, 48, 15, 111, 62}, new int[]{23, 68, 144, 134, 240, 92, 254}, new int[]{28, 24, 185, 166, 223, 248, 116, 255, 110, 61}, new int[]{175, 138, 205, 12, 194, 168, 39, 245, 60, 97, 120}, new int[]{41, 153, 158, 91, 61, 42, 142, 213, 97, 178, 100, 242}, new int[]{156, 97, 192, 252, 95, 9, 157, 119, 138, 45, 18, 186, 83, 185}, new int[]{83, 195, 100, 39, 188, 75, 66, 61, 241, 213, 109, 129, 94, 254, 225, 48, 90, 188}, new int[]{15, 195, 244, 9, 233, 71, 168, 2, 188, 160, 153, 145, 253, 79, 108, 82, 27, 174, 186, 172}, new int[]{52, 190, 88, 205, 109, 39, 176, 21, 155, 197, 251, 223, 155, 21, 5, 172, 254, 124, 12, 181, 184, 96, 50, 193}, new int[]{211, 231, 43, 97, 71, 96, 103, 174, 37, 151, 170, 53, 75, 34, 249, 121, 17, 138, 110, 213, 141, 136, 120, 151, 233, 168, 93, 255}, new int[]{245, 127, 242, 218, 130, 250, 162, 181, 102, 120, 84, 179, 220, 251, 80, 182, 229, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, 175, 184, 59, 25, 225, 98, 81, 112}, new int[]{77, 193, 137, 31, 19, 38, 22, 153, 247, 105, 122, 2, 245, 133, 242, 8, 175, 95, 100, 9, 167, 105, 214, 111, 57, 121, 21, 1, 253, 57, 54, 101, 248, 202, 69, 50, 150, 177, 226, 5, 9, 5}, new int[]{245, 132, 172, 223, 96, 32, 117, 22, 238, 133, 238, 231, 205, 188, 237, 87, 191, 106, 16, 147, 118, 23, 37, 90, 170, 205, 131, 88, 120, 100, 66, 138, 186, 240, 82, 44, 176, 87, 187, 147, 160, 175, 69, 213, 92, 253, 225, 19}, new int[]{175, 9, 223, 238, 12, 17, 220, 208, 100, 29, 175, 170, 230, 192, 215, 235, 150, 159, 36, 223, 38, 200, 132, 54, 228, 146, 218, 234, 117, 203, 29, 232, 144, 238, 22, 150, 201, 117, 62, 207, 164, 13, 137, 245, 127, 67, 247, 28, 155, 43, 203, 107, 233, 53, 143, 46}, new int[]{242, 93, 169, 50, 144, 210, 39, 118, 202, 188, 201, 189, 143, 108, 196, 37, 185, 112, 134, 230, 245, 63, 197, 190, 250, 106, 185, 221, 175, 64, 114, 71, 161, 44, 147, 6, 27, 218, 51, 63, 87, 10, 40, 130, 188, 17, 163, 31, 176, 170, 4, 107, 232, 7, 94, 166, 224, 124, 86, 47, 11, 204}, new int[]{220, 228, 173, 89, 251, 149, 159, 56, 89, 33, 147, 244, 154, 36, 73, 127, 213, 136, 248, 180, 234, 197, 158, 177, 68, 122, 93, 213, 15, 160, 227, 236, 66, 139, 153, 185, 202, 167, 179, 25, 220, 232, 96, 210, 231, 136, 223, 239, 181, 241, 59, 52, 172, 25, 49, 232, 211, 189, 64, 54, 108, 153, 132, 63, 96, 103, 82, 186}};
        c = new int[256];
        d = new int[255];
        int i = 1;
        for (int i2 = 0; i2 < 255; i2++) {
            d[i2] = i;
            c[i] = i2;
            i *= 2;
            if (i >= 256) {
                i ^= 301;
            }
        }
    }

    public static String a(String str, k kVar) {
        if (str.length() != kVar.b) {
            throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
        }
        StringBuilder stringBuilder = new StringBuilder(kVar.b + kVar.c);
        stringBuilder.append(str);
        int a = kVar.a();
        if (a == 1) {
            stringBuilder.append(a((CharSequence) str, kVar.c));
        } else {
            int i;
            stringBuilder.setLength(stringBuilder.capacity());
            int[] iArr = new int[a];
            int[] iArr2 = new int[a];
            int[] iArr3 = new int[a];
            for (i = 0; i < a; i++) {
                iArr[i] = kVar.a(i + 1);
                iArr2[i] = kVar.f;
                iArr3[i] = 0;
                if (i > 0) {
                    iArr3[i] = iArr3[i - 1] + iArr[i];
                }
            }
            for (int i2 = 0; i2 < a; i2++) {
                StringBuilder stringBuilder2 = new StringBuilder(iArr[i2]);
                for (i = i2; i < kVar.b; i += a) {
                    stringBuilder2.append(str.charAt(i));
                }
                String a2 = a(stringBuilder2.toString(), iArr2[i2]);
                i = i2;
                int i3 = 0;
                while (i < iArr2[i2] * a) {
                    int i4 = i3 + 1;
                    stringBuilder.setCharAt(kVar.b + i, a2.charAt(i3));
                    i += a;
                    i3 = i4;
                }
            }
        }
        return stringBuilder.toString();
    }

    private static String a(CharSequence charSequence, int i) {
        int i2 = 0;
        int length = charSequence.length();
        int i3 = 0;
        while (i3 < a.length) {
            if (a[i3] == i) {
                break;
            }
            i3++;
        }
        i3 = -1;
        if (i3 < 0) {
            throw new IllegalArgumentException(new StringBuilder("Illegal number of error correction codewords specified: ").append(i).toString());
        }
        int[] iArr = b[i3];
        char[] cArr = new char[i];
        for (i3 = 0; i3 < i; i3++) {
            cArr[i3] = '\u0000';
        }
        for (int i4 = 0; i4 < length + 0; i4++) {
            int charAt = charSequence.charAt(i4) ^ cArr[i - 1];
            i3 = i - 1;
            while (i3 > 0) {
                if (charAt == 0 || iArr[i3] == 0) {
                    cArr[i3] = cArr[i3 - 1];
                } else {
                    cArr[i3] = (char) (cArr[i3 - 1] ^ d[(c[charAt] + c[iArr[i3]]) % 255]);
                }
                i3--;
            }
            if (charAt == 0 || iArr[0] == 0) {
                cArr[0] = '\u0000';
            } else {
                cArr[0] = (char) d[(c[charAt] + c[iArr[0]]) % 255];
            }
        }
        char[] cArr2 = new char[i];
        while (i2 < i) {
            cArr2[i2] = cArr[(i - i2) - 1];
            i2++;
        }
        return String.valueOf(cArr2);
    }
}
