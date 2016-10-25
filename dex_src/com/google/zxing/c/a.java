package com.google.zxing.c;

import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;

// compiled from: CodaBarReader.java
public final class a extends q {
    static final char[] a;
    static final int[] b;
    private static final char[] c;
    private final StringBuilder d;
    private int[] e;
    private int f;

    static {
        a = "0123456789-$:/.+ABCD".toCharArray();
        b = new int[]{3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};
        c = new char[]{'A', 'B', 'C', 'D'};
    }

    public a() {
        this.d = new StringBuilder(20);
        this.e = new int[80];
        this.f = 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.zxing.n a(int r16, com.google.zxing.common.a r17, java.util.Map<com.google.zxing.DecodeHintType, ?> r18) throws com.google.zxing.j {
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.c.a.a(int, com.google.zxing.common.a, java.util.Map):com.google.zxing.n");
        /*
        this = this;
        r1 = r15.e;
        r2 = 0;
        java.util.Arrays.fill(r1, r2);
        r1 = 0;
        r15.f = r1;
        r1 = 0;
        r0 = r17;
        r2 = r0.d(r1);
        r0 = r17;
        r5 = r0.b;
        if (r2 < r5) goto L_0x001b;
    L_0x0016:
        r1 = com.google.zxing.j.a();
        throw r1;
    L_0x001b:
        r3 = 1;
        r1 = 0;
        r4 = r2;
    L_0x001e:
        if (r4 >= r5) goto L_0x003e;
    L_0x0020:
        r0 = r17;
        r2 = r0.a(r4);
        r2 = r2 ^ r3;
        if (r2 == 0) goto L_0x0031;
    L_0x0029:
        r1 = r1 + 1;
        r2 = r3;
    L_0x002c:
        r3 = r4 + 1;
        r4 = r3;
        r3 = r2;
        goto L_0x001e;
    L_0x0031:
        r15.a(r1);
        r2 = 1;
        if (r3 != 0) goto L_0x003c;
    L_0x0037:
        r1 = 1;
    L_0x0038:
        r14 = r2;
        r2 = r1;
        r1 = r14;
        goto L_0x002c;
    L_0x003c:
        r1 = 0;
        goto L_0x0038;
    L_0x003e:
        r15.a(r1);
        r1 = 1;
    L_0x0042:
        r2 = r15.f;
        if (r1 >= r2) goto L_0x008b;
    L_0x0046:
        r2 = r15.b(r1);
        r3 = -1;
        if (r2 == r3) goto L_0x0088;
    L_0x004d:
        r3 = c;
        r4 = a;
        r2 = r4[r2];
        r2 = a(r3, r2);
        if (r2 == 0) goto L_0x0088;
    L_0x0059:
        r2 = 0;
        r3 = r2;
        r2 = r1;
    L_0x005c:
        r4 = r1 + 7;
        if (r2 >= r4) goto L_0x0068;
    L_0x0060:
        r4 = r15.e;
        r4 = r4[r2];
        r3 = r3 + r4;
        r2 = r2 + 1;
        goto L_0x005c;
    L_0x0068:
        r2 = 1;
        if (r1 == r2) goto L_0x0075;
    L_0x006b:
        r2 = r15.e;
        r4 = r1 + -1;
        r2 = r2[r4];
        r3 = r3 / 2;
        if (r2 < r3) goto L_0x0088;
    L_0x0075:
        r2 = r15.d;
        r3 = 0;
        r2.setLength(r3);
        r2 = r1;
    L_0x007c:
        r3 = r15.b(r2);
        r4 = -1;
        if (r3 != r4) goto L_0x0090;
    L_0x0083:
        r1 = com.google.zxing.j.a();
        throw r1;
    L_0x0088:
        r1 = r1 + 2;
        goto L_0x0042;
    L_0x008b:
        r1 = com.google.zxing.j.a();
        throw r1;
    L_0x0090:
        r4 = r15.d;
        r5 = (char) r3;
        r4.append(r5);
        r2 = r2 + 8;
        r4 = r15.d;
        r4 = r4.length();
        r5 = 1;
        if (r4 <= r5) goto L_0x00ad;
    L_0x00a1:
        r4 = c;
        r5 = a;
        r3 = r5[r3];
        r3 = a(r4, r3);
        if (r3 != 0) goto L_0x00b1;
    L_0x00ad:
        r3 = r15.f;
        if (r2 < r3) goto L_0x007c;
    L_0x00b1:
        r3 = r15.e;
        r4 = r2 + -1;
        r5 = r3[r4];
        r4 = 0;
        r3 = -8;
    L_0x00b9:
        r6 = -1;
        if (r3 >= r6) goto L_0x00c6;
    L_0x00bc:
        r6 = r15.e;
        r7 = r2 + r3;
        r6 = r6[r7];
        r4 = r4 + r6;
        r3 = r3 + 1;
        goto L_0x00b9;
    L_0x00c6:
        r3 = r15.f;
        if (r2 >= r3) goto L_0x00d3;
    L_0x00ca:
        r3 = r4 / 2;
        if (r5 >= r3) goto L_0x00d3;
    L_0x00ce:
        r1 = com.google.zxing.j.a();
        throw r1;
    L_0x00d3:
        r3 = 4;
        r7 = new int[r3];
        r7 = new int[]{0, 0, 0, 0};
        r3 = 4;
        r8 = new int[r3];
        r8 = new int[]{0, 0, 0, 0};
        r3 = r15.d;
        r3 = r3.length();
        r9 = r3 + -1;
        r3 = 0;
        r4 = r1;
    L_0x00e9:
        r5 = b;
        r6 = r15.d;
        r6 = r6.charAt(r3);
        r6 = r5[r6];
        r5 = 6;
    L_0x00f4:
        if (r5 < 0) goto L_0x0113;
    L_0x00f6:
        r10 = r5 & 1;
        r11 = r6 & 1;
        r11 = r11 * 2;
        r10 = r10 + r11;
        r11 = r7[r10];
        r12 = r15.e;
        r13 = r4 + r5;
        r12 = r12[r13];
        r11 = r11 + r12;
        r7[r10] = r11;
        r11 = r8[r10];
        r11 = r11 + 1;
        r8[r10] = r11;
        r6 = r6 >> 1;
        r5 = r5 + -1;
        goto L_0x00f4;
    L_0x0113:
        if (r3 >= r9) goto L_0x011a;
    L_0x0115:
        r4 = r4 + 8;
        r3 = r3 + 1;
        goto L_0x00e9;
    L_0x011a:
        r3 = 4;
        r10 = new float[r3];
        r3 = 4;
        r11 = new float[r3];
        r3 = 0;
    L_0x0121:
        r4 = 2;
        if (r3 >= r4) goto L_0x015f;
    L_0x0124:
        r4 = 0;
        r11[r3] = r4;
        r4 = r3 + 2;
        r5 = r7[r3];
        r5 = (float) r5;
        r6 = r8[r3];
        r6 = (float) r6;
        r5 = r5 / r6;
        r6 = r3 + 2;
        r6 = r7[r6];
        r6 = (float) r6;
        r12 = r3 + 2;
        r12 = r8[r12];
        r12 = (float) r12;
        r6 = r6 / r12;
        r5 = r5 + r6;
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r5 = r5 / r6;
        r11[r4] = r5;
        r4 = r3 + 2;
        r4 = r11[r4];
        r10[r3] = r4;
        r4 = r3 + 2;
        r5 = r3 + 2;
        r5 = r7[r5];
        r5 = (float) r5;
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r5 = r5 * r6;
        r6 = 1069547520; // 0x3fc00000 float:1.5 double:5.28426686E-315;
        r5 = r5 + r6;
        r6 = r3 + 2;
        r6 = r8[r6];
        r6 = (float) r6;
        r5 = r5 / r6;
        r10[r4] = r5;
        r3 = r3 + 1;
        goto L_0x0121;
    L_0x015f:
        r3 = 0;
        r4 = r1;
    L_0x0161:
        r5 = b;
        r6 = r15.d;
        r6 = r6.charAt(r3);
        r5 = r5[r6];
        r6 = 6;
    L_0x016c:
        if (r6 < 0) goto L_0x0193;
    L_0x016e:
        r7 = r6 & 1;
        r8 = r5 & 1;
        r8 = r8 * 2;
        r7 = r7 + r8;
        r8 = r15.e;
        r12 = r4 + r6;
        r8 = r8[r12];
        r12 = (float) r8;
        r13 = r11[r7];
        r12 = (r12 > r13 ? 1 : (r12 == r13 ? 0 : -1));
        if (r12 < 0) goto L_0x0189;
    L_0x0182:
        r8 = (float) r8;
        r7 = r10[r7];
        r7 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1));
        if (r7 <= 0) goto L_0x018e;
    L_0x0189:
        r1 = com.google.zxing.j.a();
        throw r1;
    L_0x018e:
        r5 = r5 >> 1;
        r6 = r6 + -1;
        goto L_0x016c;
    L_0x0193:
        if (r3 >= r9) goto L_0x019a;
    L_0x0195:
        r4 = r4 + 8;
        r3 = r3 + 1;
        goto L_0x0161;
    L_0x019a:
        r3 = 0;
    L_0x019b:
        r4 = r15.d;
        r4 = r4.length();
        if (r3 >= r4) goto L_0x01b5;
    L_0x01a3:
        r4 = r15.d;
        r5 = a;
        r6 = r15.d;
        r6 = r6.charAt(r3);
        r5 = r5[r6];
        r4.setCharAt(r3, r5);
        r3 = r3 + 1;
        goto L_0x019b;
    L_0x01b5:
        r3 = r15.d;
        r4 = 0;
        r3 = r3.charAt(r4);
        r4 = c;
        r3 = a(r4, r3);
        if (r3 != 0) goto L_0x01c9;
    L_0x01c4:
        r1 = com.google.zxing.j.a();
        throw r1;
    L_0x01c9:
        r3 = r15.d;
        r4 = r15.d;
        r4 = r4.length();
        r4 = r4 + -1;
        r3 = r3.charAt(r4);
        r4 = c;
        r3 = a(r4, r3);
        if (r3 != 0) goto L_0x01e4;
    L_0x01df:
        r1 = com.google.zxing.j.a();
        throw r1;
    L_0x01e4:
        r3 = r15.d;
        r3 = r3.length();
        r4 = 3;
        if (r3 > r4) goto L_0x01f2;
    L_0x01ed:
        r1 = com.google.zxing.j.a();
        throw r1;
    L_0x01f2:
        if (r18 == 0) goto L_0x01fe;
    L_0x01f4:
        r3 = com.google.zxing.DecodeHintType.RETURN_CODABAR_START_END;
        r0 = r18;
        r3 = r0.containsKey(r3);
        if (r3 != 0) goto L_0x0211;
    L_0x01fe:
        r3 = r15.d;
        r4 = r15.d;
        r4 = r4.length();
        r4 = r4 + -1;
        r3.deleteCharAt(r4);
        r3 = r15.d;
        r4 = 0;
        r3.deleteCharAt(r4);
    L_0x0211:
        r4 = 0;
        r3 = 0;
        r14 = r3;
        r3 = r4;
        r4 = r14;
    L_0x0216:
        if (r4 >= r1) goto L_0x0222;
    L_0x0218:
        r5 = r15.e;
        r5 = r5[r4];
        r5 = r5 + r3;
        r3 = r4 + 1;
        r4 = r3;
        r3 = r5;
        goto L_0x0216;
    L_0x0222:
        r4 = (float) r3;
        r14 = r3;
        r3 = r1;
        r1 = r14;
    L_0x0226:
        r5 = r2 + -1;
        if (r3 >= r5) goto L_0x0232;
    L_0x022a:
        r5 = r15.e;
        r5 = r5[r3];
        r1 = r1 + r5;
        r3 = r3 + 1;
        goto L_0x0226;
    L_0x0232:
        r1 = (float) r1;
        r2 = new com.google.zxing.n;
        r3 = r15.d;
        r3 = r3.toString();
        r5 = 0;
        r6 = 2;
        r6 = new com.google.zxing.o[r6];
        r7 = 0;
        r8 = new com.google.zxing.o;
        r0 = r16;
        r9 = (float) r0;
        r8.<init>(r4, r9);
        r6[r7] = r8;
        r4 = 1;
        r7 = new com.google.zxing.o;
        r0 = r16;
        r8 = (float) r0;
        r7.<init>(r1, r8);
        r6[r4] = r7;
        r1 = com.google.zxing.BarcodeFormat.CODABAR;
        r2.<init>(r3, r5, r6, r1);
        return r2;
        */
    }

    private void a(int i) {
        this.e[this.f] = i;
        this.f++;
        if (this.f >= this.e.length) {
            Object obj = new Object[(this.f * 2)];
            System.arraycopy(this.e, 0, obj, 0, this.f);
            this.e = obj;
        }
    }

    static boolean a(char[] cArr, char c) {
        if (cArr == null) {
            return false;
        }
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            if (cArr[i] == c) {
                return true;
            }
        }
        return false;
    }

    private int b(int i) {
        int i2 = InMobiClientPositioning.NO_REPEAT;
        int i3 = i + 7;
        if (i3 >= this.f) {
            return -1;
        }
        int[] iArr = this.e;
        int i4 = i;
        int i5 = Integer.MAX_VALUE;
        int i6 = 0;
        while (i4 < i3) {
            int i7 = iArr[i4];
            if (i7 < i5) {
                i5 = i7;
            }
            if (i7 <= i6) {
                i7 = i6;
            }
            i4 += 2;
            i6 = i7;
        }
        i5 = (i5 + i6) / 2;
        i6 = 0;
        i4 = i + 1;
        while (i4 < i3) {
            i7 = iArr[i4];
            if (i7 < i2) {
                i2 = i7;
            }
            if (i7 <= i6) {
                i7 = i6;
            }
            i4 += 2;
            i6 = i7;
        }
        i6 = (i2 + i6) / 2;
        i4 = 128;
        i3 = 0;
        i2 = 0;
        while (i3 < 7) {
            if ((i3 & 1) == 0) {
                i7 = i5;
            } else {
                i7 = i6;
            }
            i4 >>= 1;
            if (iArr[i + i3] > i7) {
                i7 = i2 | i4;
            } else {
                i7 = i2;
            }
            i3++;
            i2 = i7;
        }
        for (i7 = 0; i7 < b.length; i7++) {
            if (b[i7] == i2) {
                return i7;
            }
        }
        return -1;
    }
}
