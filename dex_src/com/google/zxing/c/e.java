package com.google.zxing.c;

import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;

// compiled from: Code39Reader.java
public final class e extends q {
    static final int[] a;
    private static final char[] b;
    private static final int c;
    private final boolean d;
    private final boolean e;
    private final StringBuilder f;
    private final int[] g;

    static {
        b = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".toCharArray();
        int[] iArr = new int[]{52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, 193, 448, 145, 400, 208, 133, 388, 196, 148, 168, 162, 138, 42};
        a = iArr;
        c = iArr[39];
    }

    public e() {
        this(false);
    }

    public e(boolean z) {
        this(z, (byte) 0);
    }

    private e(boolean z, byte b) {
        this.d = z;
        this.e = false;
        this.f = new StringBuilder(20);
        this.g = new int[9];
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.zxing.n a(int r12, com.google.zxing.common.a r13, java.util.Map<com.google.zxing.DecodeHintType, ?> r14) throws com.google.zxing.j, com.google.zxing.c, com.google.zxing.e {
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.c.e.a(int, com.google.zxing.common.a, java.util.Map):com.google.zxing.n");
        /*
        this = this;
        r4 = r11.g;
        r0 = 0;
        java.util.Arrays.fill(r4, r0);
        r5 = r11.f;
        r0 = 0;
        r5.setLength(r0);
        r6 = r13.b;
        r0 = 0;
        r0 = r13.c(r0);
        r1 = 0;
        r2 = 0;
        r7 = r4.length;
        r3 = r0;
    L_0x0017:
        if (r3 >= r6) goto L_0x008e;
    L_0x0019:
        r8 = r13.a(r3);
        r8 = r8 ^ r2;
        if (r8 == 0) goto L_0x0029;
    L_0x0020:
        r8 = r4[r1];
        r8 = r8 + 1;
        r4[r1] = r8;
    L_0x0026:
        r3 = r3 + 1;
        goto L_0x0017;
    L_0x0029:
        r8 = r7 + -1;
        if (r1 != r8) goto L_0x0089;
    L_0x002d:
        r8 = a(r4);
        r9 = c;
        if (r8 != r9) goto L_0x0067;
    L_0x0035:
        r8 = 0;
        r9 = r3 - r0;
        r9 = r9 / 2;
        r9 = r0 - r9;
        r8 = java.lang.Math.max(r8, r9);
        r8 = r13.a(r8, r0);
        if (r8 == 0) goto L_0x0067;
    L_0x0046:
        r1 = 2;
        r6 = new int[r1];
        r1 = 0;
        r6[r1] = r0;
        r0 = 1;
        r6[r0] = r3;
        r0 = 1;
        r0 = r6[r0];
        r0 = r13.c(r0);
        r7 = r13.b;
        r1 = r0;
    L_0x0059:
        a(r13, r1, r4);
        r2 = a(r4);
        if (r2 >= 0) goto L_0x0093;
    L_0x0062:
        r0 = com.google.zxing.j.a();
        throw r0;
    L_0x0067:
        r8 = 0;
        r8 = r4[r8];
        r9 = 1;
        r9 = r4[r9];
        r8 = r8 + r9;
        r0 = r0 + r8;
        r8 = 2;
        r9 = 0;
        r10 = r7 + -2;
        java.lang.System.arraycopy(r4, r8, r4, r9, r10);
        r8 = r7 + -2;
        r9 = 0;
        r4[r8] = r9;
        r8 = r7 + -1;
        r9 = 0;
        r4[r8] = r9;
        r1 = r1 + -1;
    L_0x0082:
        r8 = 1;
        r4[r1] = r8;
        if (r2 != 0) goto L_0x008c;
    L_0x0087:
        r2 = 1;
        goto L_0x0026;
    L_0x0089:
        r1 = r1 + 1;
        goto L_0x0082;
    L_0x008c:
        r2 = 0;
        goto L_0x0026;
    L_0x008e:
        r0 = com.google.zxing.j.a();
        throw r0;
    L_0x0093:
        r0 = 0;
    L_0x0094:
        r3 = a;
        r3 = r3.length;
        if (r0 >= r3) goto L_0x00b4;
    L_0x0099:
        r3 = a;
        r3 = r3[r0];
        if (r3 != r2) goto L_0x00b1;
    L_0x009f:
        r2 = b;
        r3 = r2[r0];
        r5.append(r3);
        r8 = r4.length;
        r0 = 0;
        r2 = r1;
    L_0x00a9:
        if (r0 >= r8) goto L_0x00b9;
    L_0x00ab:
        r9 = r4[r0];
        r2 = r2 + r9;
        r0 = r0 + 1;
        goto L_0x00a9;
    L_0x00b1:
        r0 = r0 + 1;
        goto L_0x0094;
    L_0x00b4:
        r0 = com.google.zxing.j.a();
        throw r0;
    L_0x00b9:
        r0 = r13.c(r2);
        r2 = 42;
        if (r3 != r2) goto L_0x01eb;
    L_0x00c1:
        r2 = r5.length();
        r2 = r2 + -1;
        r5.setLength(r2);
        r3 = 0;
        r8 = r4.length;
        r2 = 0;
    L_0x00cd:
        if (r2 >= r8) goto L_0x00d5;
    L_0x00cf:
        r9 = r4[r2];
        r3 = r3 + r9;
        r2 = r2 + 1;
        goto L_0x00cd;
    L_0x00d5:
        r2 = r0 - r1;
        r2 = r2 - r3;
        if (r0 == r7) goto L_0x00e3;
    L_0x00da:
        r0 = r2 * 2;
        if (r0 >= r3) goto L_0x00e3;
    L_0x00de:
        r0 = com.google.zxing.j.a();
        throw r0;
    L_0x00e3:
        r0 = r11.d;
        if (r0 == 0) goto L_0x0116;
    L_0x00e7:
        r0 = r5.length();
        r4 = r0 + -1;
        r2 = 0;
        r0 = 0;
    L_0x00ef:
        if (r0 >= r4) goto L_0x0102;
    L_0x00f1:
        r7 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
        r8 = r11.f;
        r8 = r8.charAt(r0);
        r7 = r7.indexOf(r8);
        r2 = r2 + r7;
        r0 = r0 + 1;
        goto L_0x00ef;
    L_0x0102:
        r0 = r5.charAt(r4);
        r7 = b;
        r2 = r2 % 43;
        r2 = r7[r2];
        if (r0 == r2) goto L_0x0113;
    L_0x010e:
        r0 = com.google.zxing.c.a();
        throw r0;
    L_0x0113:
        r5.setLength(r4);
    L_0x0116:
        r0 = r5.length();
        if (r0 != 0) goto L_0x0121;
    L_0x011c:
        r0 = com.google.zxing.j.a();
        throw r0;
    L_0x0121:
        r0 = r11.e;
        if (r0 == 0) goto L_0x01e6;
    L_0x0125:
        r4 = r5.length();
        r7 = new java.lang.StringBuilder;
        r7.<init>(r4);
        r2 = 0;
    L_0x012f:
        if (r2 >= r4) goto L_0x01b3;
    L_0x0131:
        r8 = r5.charAt(r2);
        r0 = 43;
        if (r8 == r0) goto L_0x0145;
    L_0x0139:
        r0 = 36;
        if (r8 == r0) goto L_0x0145;
    L_0x013d:
        r0 = 37;
        if (r8 == r0) goto L_0x0145;
    L_0x0141:
        r0 = 47;
        if (r8 != r0) goto L_0x01ae;
    L_0x0145:
        r0 = r2 + 1;
        r9 = r5.charAt(r0);
        r0 = 0;
        switch(r8) {
            case 36: goto L_0x0168;
            case 37: goto L_0x0179;
            case 43: goto L_0x0157;
            case 47: goto L_0x0196;
            default: goto L_0x014f;
        };
    L_0x014f:
        r7.append(r0);
        r0 = r2 + 1;
    L_0x0154:
        r2 = r0 + 1;
        goto L_0x012f;
    L_0x0157:
        r0 = 65;
        if (r9 < r0) goto L_0x0163;
    L_0x015b:
        r0 = 90;
        if (r9 > r0) goto L_0x0163;
    L_0x015f:
        r0 = r9 + 32;
        r0 = (char) r0;
        goto L_0x014f;
    L_0x0163:
        r0 = com.google.zxing.e.a();
        throw r0;
    L_0x0168:
        r0 = 65;
        if (r9 < r0) goto L_0x0174;
    L_0x016c:
        r0 = 90;
        if (r9 > r0) goto L_0x0174;
    L_0x0170:
        r0 = r9 + -64;
        r0 = (char) r0;
        goto L_0x014f;
    L_0x0174:
        r0 = com.google.zxing.e.a();
        throw r0;
    L_0x0179:
        r0 = 65;
        if (r9 < r0) goto L_0x0185;
    L_0x017d:
        r0 = 69;
        if (r9 > r0) goto L_0x0185;
    L_0x0181:
        r0 = r9 + -38;
        r0 = (char) r0;
        goto L_0x014f;
    L_0x0185:
        r0 = 70;
        if (r9 < r0) goto L_0x0191;
    L_0x0189:
        r0 = 87;
        if (r9 > r0) goto L_0x0191;
    L_0x018d:
        r0 = r9 + -11;
        r0 = (char) r0;
        goto L_0x014f;
    L_0x0191:
        r0 = com.google.zxing.e.a();
        throw r0;
    L_0x0196:
        r0 = 65;
        if (r9 < r0) goto L_0x01a2;
    L_0x019a:
        r0 = 79;
        if (r9 > r0) goto L_0x01a2;
    L_0x019e:
        r0 = r9 + -32;
        r0 = (char) r0;
        goto L_0x014f;
    L_0x01a2:
        r0 = 90;
        if (r9 != r0) goto L_0x01a9;
    L_0x01a6:
        r0 = 58;
        goto L_0x014f;
    L_0x01a9:
        r0 = com.google.zxing.e.a();
        throw r0;
    L_0x01ae:
        r7.append(r8);
        r0 = r2;
        goto L_0x0154;
    L_0x01b3:
        r0 = r7.toString();
    L_0x01b7:
        r2 = 1;
        r2 = r6[r2];
        r4 = 0;
        r4 = r6[r4];
        r2 = r2 + r4;
        r2 = (float) r2;
        r4 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 / r4;
        r1 = (float) r1;
        r3 = (float) r3;
        r4 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = r3 / r4;
        r1 = r1 + r3;
        r3 = new com.google.zxing.n;
        r4 = 0;
        r5 = 2;
        r5 = new com.google.zxing.o[r5];
        r6 = 0;
        r7 = new com.google.zxing.o;
        r8 = (float) r12;
        r7.<init>(r2, r8);
        r5[r6] = r7;
        r2 = 1;
        r6 = new com.google.zxing.o;
        r7 = (float) r12;
        r6.<init>(r1, r7);
        r5[r2] = r6;
        r1 = com.google.zxing.BarcodeFormat.CODE_39;
        r3.<init>(r0, r4, r5, r1);
        return r3;
    L_0x01e6:
        r0 = r5.toString();
        goto L_0x01b7;
    L_0x01eb:
        r1 = r0;
        goto L_0x0059;
        */
    }

    private static int a(int[] iArr) {
        int i;
        int i2;
        int length = iArr.length;
        Object obj = null;
        while (true) {
            int i3;
            Object obj2 = InMobiClientPositioning.NO_REPEAT;
            int length2 = iArr.length;
            for (i = 0; i < length2; i++) {
                i3 = iArr[i];
                int i4;
                if (i3 < i4 && i3 > r0) {
                    i4 = i3;
                }
            }
            i2 = 0;
            i3 = 0;
            i = 0;
            for (length2 = 0; length2 < length; length2++) {
                int i5 = iArr[length2];
                if (i5 > i4) {
                    i2 |= 1 << ((length - 1) - length2);
                    i++;
                    i3 += i5;
                }
            }
            if (i == 3) {
                break;
            } else if (i <= 3) {
                return -1;
            } else {
                i2 = i4;
            }
        }
        int i6 = i;
        for (i = 0; i < length && i6 > 0; i++) {
            length2 = iArr[i];
            if (length2 > i4) {
                i6--;
                if (length2 * 2 >= i3) {
                    return -1;
                }
            }
        }
        return i2;
    }
}
