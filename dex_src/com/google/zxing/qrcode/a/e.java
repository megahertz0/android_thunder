package com.google.zxing.qrcode.a;

import android.support.v4.widget.AutoScrollHelper;
import com.google.zxing.p;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// compiled from: FinderPatternFinder.java
public final class e {
    public final com.google.zxing.common.b a;
    public final List<d> b;
    public boolean c;
    private final int[] d;
    private final p e;

    // compiled from: FinderPatternFinder.java
    private static final class a implements Serializable, Comparator<d> {
        private final float a;

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            d dVar = (d) obj;
            d dVar2 = (d) obj2;
            if (dVar2.d != dVar.d) {
                return dVar2.d - dVar.d;
            }
            float abs = Math.abs(dVar2.c - this.a);
            float abs2 = Math.abs(dVar.c - this.a);
            if (abs < abs2) {
                return 1;
            }
            return abs == abs2 ? 0 : -1;
        }

        private a(float f) {
            this.a = f;
        }
    }

    // compiled from: FinderPatternFinder.java
    private static final class b implements Serializable, Comparator<d> {
        private final float a;

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            d dVar = (d) obj;
            float abs = Math.abs(((d) obj2).c - this.a);
            float abs2 = Math.abs(dVar.c - this.a);
            if (abs < abs2) {
                return -1;
            }
            return abs == abs2 ? 0 : 1;
        }

        private b(float f) {
            this.a = f;
        }
    }

    public e(com.google.zxing.common.b bVar, p pVar) {
        this.a = bVar;
        this.b = new ArrayList();
        this.d = new int[5];
        this.e = pVar;
    }

    private static float a(int[] iArr, int i) {
        return ((float) ((i - iArr[4]) - iArr[3])) - (((float) iArr[2]) / 2.0f);
    }

    public static boolean a(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 7) {
            return false;
        }
        float f = ((float) i) / 7.0f;
        float f2 = f / 2.0f;
        return Math.abs(f - ((float) iArr[0])) < f2 && Math.abs(f - ((float) iArr[1])) < f2 && Math.abs((3.0f * f) - ((float) iArr[2])) < 3.0f * f2 && Math.abs(f - ((float) iArr[3])) < f2 && Math.abs(f - ((float) iArr[4])) < f2;
    }

    private int[] b() {
        this.d[0] = 0;
        this.d[1] = 0;
        this.d[2] = 0;
        this.d[3] = 0;
        this.d[4] = 0;
        return this.d;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(int[] r15, int r16, int r17, boolean r18) {
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.a.e.a(int[], int, int, boolean):boolean");
        /*
        this = this;
        r1 = 0;
        r1 = r15[r1];
        r2 = 1;
        r2 = r15[r2];
        r1 = r1 + r2;
        r2 = 2;
        r2 = r15[r2];
        r1 = r1 + r2;
        r2 = 3;
        r2 = r15[r2];
        r1 = r1 + r2;
        r2 = 4;
        r2 = r15[r2];
        r4 = r1 + r2;
        r0 = r17;
        r3 = a(r15, r0);
        r2 = (int) r3;
        r1 = 2;
        r5 = r15[r1];
        r6 = r14.a;
        r7 = r6.b;
        r8 = r14.b();
        r1 = r16;
    L_0x0028:
        if (r1 < 0) goto L_0x003a;
    L_0x002a:
        r9 = r6.a(r2, r1);
        if (r9 == 0) goto L_0x003a;
    L_0x0030:
        r9 = 2;
        r10 = r8[r9];
        r10 = r10 + 1;
        r8[r9] = r10;
        r1 = r1 + -1;
        goto L_0x0028;
    L_0x003a:
        if (r1 >= 0) goto L_0x0065;
    L_0x003c:
        r1 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
        r2 = r1;
    L_0x003f:
        r1 = java.lang.Float.isNaN(r2);
        if (r1 != 0) goto L_0x03d5;
    L_0x0045:
        r3 = (int) r3;
        r5 = (int) r2;
        r1 = 2;
        r6 = r15[r1];
        r7 = r14.a;
        r8 = r7.a;
        r9 = r14.b();
        r1 = r3;
    L_0x0053:
        if (r1 < 0) goto L_0x013b;
    L_0x0055:
        r10 = r7.a(r1, r5);
        if (r10 == 0) goto L_0x013b;
    L_0x005b:
        r10 = 2;
        r11 = r9[r10];
        r11 = r11 + 1;
        r9[r10] = r11;
        r1 = r1 + -1;
        goto L_0x0053;
    L_0x0065:
        if (r1 < 0) goto L_0x007c;
    L_0x0067:
        r9 = r6.a(r2, r1);
        if (r9 != 0) goto L_0x007c;
    L_0x006d:
        r9 = 1;
        r9 = r8[r9];
        if (r9 > r5) goto L_0x007c;
    L_0x0072:
        r9 = 1;
        r10 = r8[r9];
        r10 = r10 + 1;
        r8[r9] = r10;
        r1 = r1 + -1;
        goto L_0x0065;
    L_0x007c:
        if (r1 < 0) goto L_0x0083;
    L_0x007e:
        r9 = 1;
        r9 = r8[r9];
        if (r9 <= r5) goto L_0x0087;
    L_0x0083:
        r1 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
        r2 = r1;
        goto L_0x003f;
    L_0x0087:
        if (r1 < 0) goto L_0x009e;
    L_0x0089:
        r9 = r6.a(r2, r1);
        if (r9 == 0) goto L_0x009e;
    L_0x008f:
        r9 = 0;
        r9 = r8[r9];
        if (r9 > r5) goto L_0x009e;
    L_0x0094:
        r9 = 0;
        r10 = r8[r9];
        r10 = r10 + 1;
        r8[r9] = r10;
        r1 = r1 + -1;
        goto L_0x0087;
    L_0x009e:
        r1 = 0;
        r1 = r8[r1];
        if (r1 <= r5) goto L_0x00a7;
    L_0x00a3:
        r1 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
        r2 = r1;
        goto L_0x003f;
    L_0x00a7:
        r1 = r16 + 1;
    L_0x00a9:
        if (r1 >= r7) goto L_0x00bb;
    L_0x00ab:
        r9 = r6.a(r2, r1);
        if (r9 == 0) goto L_0x00bb;
    L_0x00b1:
        r9 = 2;
        r10 = r8[r9];
        r10 = r10 + 1;
        r8[r9] = r10;
        r1 = r1 + 1;
        goto L_0x00a9;
    L_0x00bb:
        if (r1 != r7) goto L_0x00c2;
    L_0x00bd:
        r1 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
        r2 = r1;
        goto L_0x003f;
    L_0x00c2:
        if (r1 >= r7) goto L_0x00d9;
    L_0x00c4:
        r9 = r6.a(r2, r1);
        if (r9 != 0) goto L_0x00d9;
    L_0x00ca:
        r9 = 3;
        r9 = r8[r9];
        if (r9 >= r5) goto L_0x00d9;
    L_0x00cf:
        r9 = 3;
        r10 = r8[r9];
        r10 = r10 + 1;
        r8[r9] = r10;
        r1 = r1 + 1;
        goto L_0x00c2;
    L_0x00d9:
        if (r1 == r7) goto L_0x00e0;
    L_0x00db:
        r9 = 3;
        r9 = r8[r9];
        if (r9 < r5) goto L_0x00e5;
    L_0x00e0:
        r1 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
        r2 = r1;
        goto L_0x003f;
    L_0x00e5:
        if (r1 >= r7) goto L_0x00fc;
    L_0x00e7:
        r9 = r6.a(r2, r1);
        if (r9 == 0) goto L_0x00fc;
    L_0x00ed:
        r9 = 4;
        r9 = r8[r9];
        if (r9 >= r5) goto L_0x00fc;
    L_0x00f2:
        r9 = 4;
        r10 = r8[r9];
        r10 = r10 + 1;
        r8[r9] = r10;
        r1 = r1 + 1;
        goto L_0x00e5;
    L_0x00fc:
        r2 = 4;
        r2 = r8[r2];
        if (r2 < r5) goto L_0x0106;
    L_0x0101:
        r1 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
        r2 = r1;
        goto L_0x003f;
    L_0x0106:
        r2 = 0;
        r2 = r8[r2];
        r5 = 1;
        r5 = r8[r5];
        r2 = r2 + r5;
        r5 = 2;
        r5 = r8[r5];
        r2 = r2 + r5;
        r5 = 3;
        r5 = r8[r5];
        r2 = r2 + r5;
        r5 = 4;
        r5 = r8[r5];
        r2 = r2 + r5;
        r2 = r2 - r4;
        r2 = java.lang.Math.abs(r2);
        r2 = r2 * 5;
        r5 = r4 * 2;
        if (r2 < r5) goto L_0x0129;
    L_0x0124:
        r1 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
        r2 = r1;
        goto L_0x003f;
    L_0x0129:
        r2 = a(r8);
        if (r2 == 0) goto L_0x0136;
    L_0x012f:
        r1 = a(r8, r1);
        r2 = r1;
        goto L_0x003f;
    L_0x0136:
        r1 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
        r2 = r1;
        goto L_0x003f;
    L_0x013b:
        if (r1 >= 0) goto L_0x016c;
    L_0x013d:
        r1 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
        r3 = r1;
    L_0x0140:
        r1 = java.lang.Float.isNaN(r3);
        if (r1 != 0) goto L_0x03d5;
    L_0x0146:
        if (r18 == 0) goto L_0x0247;
    L_0x0148:
        r5 = (int) r2;
        r6 = (int) r3;
        r1 = 2;
        r7 = r15[r1];
        r8 = r14.b();
        r1 = 0;
    L_0x0152:
        if (r5 < r1) goto L_0x0240;
    L_0x0154:
        if (r6 < r1) goto L_0x0240;
    L_0x0156:
        r9 = r14.a;
        r10 = r6 - r1;
        r11 = r5 - r1;
        r9 = r9.a(r10, r11);
        if (r9 == 0) goto L_0x0240;
    L_0x0162:
        r9 = 2;
        r10 = r8[r9];
        r10 = r10 + 1;
        r8[r9] = r10;
        r1 = r1 + 1;
        goto L_0x0152;
    L_0x016c:
        if (r1 < 0) goto L_0x0183;
    L_0x016e:
        r10 = r7.a(r1, r5);
        if (r10 != 0) goto L_0x0183;
    L_0x0174:
        r10 = 1;
        r10 = r9[r10];
        if (r10 > r6) goto L_0x0183;
    L_0x0179:
        r10 = 1;
        r11 = r9[r10];
        r11 = r11 + 1;
        r9[r10] = r11;
        r1 = r1 + -1;
        goto L_0x016c;
    L_0x0183:
        if (r1 < 0) goto L_0x018a;
    L_0x0185:
        r10 = 1;
        r10 = r9[r10];
        if (r10 <= r6) goto L_0x018e;
    L_0x018a:
        r1 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
        r3 = r1;
        goto L_0x0140;
    L_0x018e:
        if (r1 < 0) goto L_0x01a5;
    L_0x0190:
        r10 = r7.a(r1, r5);
        if (r10 == 0) goto L_0x01a5;
    L_0x0196:
        r10 = 0;
        r10 = r9[r10];
        if (r10 > r6) goto L_0x01a5;
    L_0x019b:
        r10 = 0;
        r11 = r9[r10];
        r11 = r11 + 1;
        r9[r10] = r11;
        r1 = r1 + -1;
        goto L_0x018e;
    L_0x01a5:
        r1 = 0;
        r1 = r9[r1];
        if (r1 <= r6) goto L_0x01ae;
    L_0x01aa:
        r1 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
        r3 = r1;
        goto L_0x0140;
    L_0x01ae:
        r1 = r3 + 1;
    L_0x01b0:
        if (r1 >= r8) goto L_0x01c2;
    L_0x01b2:
        r3 = r7.a(r1, r5);
        if (r3 == 0) goto L_0x01c2;
    L_0x01b8:
        r3 = 2;
        r10 = r9[r3];
        r10 = r10 + 1;
        r9[r3] = r10;
        r1 = r1 + 1;
        goto L_0x01b0;
    L_0x01c2:
        if (r1 != r8) goto L_0x01c9;
    L_0x01c4:
        r1 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
        r3 = r1;
        goto L_0x0140;
    L_0x01c9:
        if (r1 >= r8) goto L_0x01e0;
    L_0x01cb:
        r3 = r7.a(r1, r5);
        if (r3 != 0) goto L_0x01e0;
    L_0x01d1:
        r3 = 3;
        r3 = r9[r3];
        if (r3 >= r6) goto L_0x01e0;
    L_0x01d6:
        r3 = 3;
        r10 = r9[r3];
        r10 = r10 + 1;
        r9[r3] = r10;
        r1 = r1 + 1;
        goto L_0x01c9;
    L_0x01e0:
        if (r1 == r8) goto L_0x01e7;
    L_0x01e2:
        r3 = 3;
        r3 = r9[r3];
        if (r3 < r6) goto L_0x01ec;
    L_0x01e7:
        r1 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
        r3 = r1;
        goto L_0x0140;
    L_0x01ec:
        if (r1 >= r8) goto L_0x0203;
    L_0x01ee:
        r3 = r7.a(r1, r5);
        if (r3 == 0) goto L_0x0203;
    L_0x01f4:
        r3 = 4;
        r3 = r9[r3];
        if (r3 >= r6) goto L_0x0203;
    L_0x01f9:
        r3 = 4;
        r10 = r9[r3];
        r10 = r10 + 1;
        r9[r3] = r10;
        r1 = r1 + 1;
        goto L_0x01ec;
    L_0x0203:
        r3 = 4;
        r3 = r9[r3];
        if (r3 < r6) goto L_0x020d;
    L_0x0208:
        r1 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
        r3 = r1;
        goto L_0x0140;
    L_0x020d:
        r3 = 0;
        r3 = r9[r3];
        r5 = 1;
        r5 = r9[r5];
        r3 = r3 + r5;
        r5 = 2;
        r5 = r9[r5];
        r3 = r3 + r5;
        r5 = 3;
        r5 = r9[r5];
        r3 = r3 + r5;
        r5 = 4;
        r5 = r9[r5];
        r3 = r3 + r5;
        r3 = r3 - r4;
        r3 = java.lang.Math.abs(r3);
        r3 = r3 * 5;
        if (r3 < r4) goto L_0x022e;
    L_0x0229:
        r1 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
        r3 = r1;
        goto L_0x0140;
    L_0x022e:
        r3 = a(r9);
        if (r3 == 0) goto L_0x023b;
    L_0x0234:
        r1 = a(r9, r1);
        r3 = r1;
        goto L_0x0140;
    L_0x023b:
        r1 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
        r3 = r1;
        goto L_0x0140;
    L_0x0240:
        if (r5 < r1) goto L_0x0244;
    L_0x0242:
        if (r6 >= r1) goto L_0x02c6;
    L_0x0244:
        r1 = 0;
    L_0x0245:
        if (r1 == 0) goto L_0x03d5;
    L_0x0247:
        r1 = (float) r4;
        r4 = 1088421888; // 0x40e00000 float:7.0 double:5.37751863E-315;
        r7 = r1 / r4;
        r5 = 0;
        r1 = 0;
        r4 = r1;
    L_0x024f:
        r1 = r14.b;
        r1 = r1.size();
        if (r4 >= r1) goto L_0x03d8;
    L_0x0257:
        r1 = r14.b;
        r1 = r1.get(r4);
        r1 = (com.google.zxing.qrcode.a.d) r1;
        r6 = r1.b;
        r6 = r2 - r6;
        r6 = java.lang.Math.abs(r6);
        r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1));
        if (r6 > 0) goto L_0x03cd;
    L_0x026b:
        r6 = r1.a;
        r6 = r3 - r6;
        r6 = java.lang.Math.abs(r6);
        r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1));
        if (r6 > 0) goto L_0x03cd;
    L_0x0277:
        r6 = r1.c;
        r6 = r7 - r6;
        r6 = java.lang.Math.abs(r6);
        r8 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r8 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r8 <= 0) goto L_0x028b;
    L_0x0285:
        r8 = r1.c;
        r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r6 > 0) goto L_0x03ca;
    L_0x028b:
        r6 = 1;
    L_0x028c:
        if (r6 == 0) goto L_0x03d0;
    L_0x028e:
        r5 = r14.b;
        r6 = r1.d;
        r6 = r6 + 1;
        r8 = r1.d;
        r8 = (float) r8;
        r9 = r1.a;
        r8 = r8 * r9;
        r8 = r8 + r3;
        r9 = (float) r6;
        r8 = r8 / r9;
        r9 = r1.d;
        r9 = (float) r9;
        r10 = r1.b;
        r9 = r9 * r10;
        r9 = r9 + r2;
        r10 = (float) r6;
        r9 = r9 / r10;
        r10 = r1.d;
        r10 = (float) r10;
        r1 = r1.c;
        r1 = r1 * r10;
        r1 = r1 + r7;
        r10 = (float) r6;
        r1 = r1 / r10;
        r10 = new com.google.zxing.qrcode.a.d;
        r10.<init>(r8, r9, r1, r6);
        r5.set(r4, r10);
        r1 = 1;
    L_0x02b8:
        if (r1 != 0) goto L_0x02c4;
    L_0x02ba:
        r1 = new com.google.zxing.qrcode.a.d;
        r1.<init>(r3, r2, r7);
        r2 = r14.b;
        r2.add(r1);
    L_0x02c4:
        r1 = 1;
    L_0x02c5:
        return r1;
    L_0x02c6:
        if (r5 < r1) goto L_0x02e5;
    L_0x02c8:
        if (r6 < r1) goto L_0x02e5;
    L_0x02ca:
        r9 = r14.a;
        r10 = r6 - r1;
        r11 = r5 - r1;
        r9 = r9.a(r10, r11);
        if (r9 != 0) goto L_0x02e5;
    L_0x02d6:
        r9 = 1;
        r9 = r8[r9];
        if (r9 > r7) goto L_0x02e5;
    L_0x02db:
        r9 = 1;
        r10 = r8[r9];
        r10 = r10 + 1;
        r8[r9] = r10;
        r1 = r1 + 1;
        goto L_0x02c6;
    L_0x02e5:
        if (r5 < r1) goto L_0x02ee;
    L_0x02e7:
        if (r6 < r1) goto L_0x02ee;
    L_0x02e9:
        r9 = 1;
        r9 = r8[r9];
        if (r9 <= r7) goto L_0x02f1;
    L_0x02ee:
        r1 = 0;
        goto L_0x0245;
    L_0x02f1:
        if (r5 < r1) goto L_0x0310;
    L_0x02f3:
        if (r6 < r1) goto L_0x0310;
    L_0x02f5:
        r9 = r14.a;
        r10 = r6 - r1;
        r11 = r5 - r1;
        r9 = r9.a(r10, r11);
        if (r9 == 0) goto L_0x0310;
    L_0x0301:
        r9 = 0;
        r9 = r8[r9];
        if (r9 > r7) goto L_0x0310;
    L_0x0306:
        r9 = 0;
        r10 = r8[r9];
        r10 = r10 + 1;
        r8[r9] = r10;
        r1 = r1 + 1;
        goto L_0x02f1;
    L_0x0310:
        r1 = 0;
        r1 = r8[r1];
        if (r1 > r7) goto L_0x03c7;
    L_0x0315:
        r1 = r14.a;
        r9 = r1.b;
        r1 = r14.a;
        r10 = r1.a;
        r1 = 1;
    L_0x031e:
        r11 = r5 + r1;
        if (r11 >= r9) goto L_0x033c;
    L_0x0322:
        r11 = r6 + r1;
        if (r11 >= r10) goto L_0x033c;
    L_0x0326:
        r11 = r14.a;
        r12 = r6 + r1;
        r13 = r5 + r1;
        r11 = r11.a(r12, r13);
        if (r11 == 0) goto L_0x033c;
    L_0x0332:
        r11 = 2;
        r12 = r8[r11];
        r12 = r12 + 1;
        r8[r11] = r12;
        r1 = r1 + 1;
        goto L_0x031e;
    L_0x033c:
        r11 = r5 + r1;
        if (r11 >= r9) goto L_0x0344;
    L_0x0340:
        r11 = r6 + r1;
        if (r11 < r10) goto L_0x0347;
    L_0x0344:
        r1 = 0;
        goto L_0x0245;
    L_0x0347:
        r11 = r5 + r1;
        if (r11 >= r9) goto L_0x036a;
    L_0x034b:
        r11 = r6 + r1;
        if (r11 >= r10) goto L_0x036a;
    L_0x034f:
        r11 = r14.a;
        r12 = r6 + r1;
        r13 = r5 + r1;
        r11 = r11.a(r12, r13);
        if (r11 != 0) goto L_0x036a;
    L_0x035b:
        r11 = 3;
        r11 = r8[r11];
        if (r11 >= r7) goto L_0x036a;
    L_0x0360:
        r11 = 3;
        r12 = r8[r11];
        r12 = r12 + 1;
        r8[r11] = r12;
        r1 = r1 + 1;
        goto L_0x0347;
    L_0x036a:
        r11 = r5 + r1;
        if (r11 >= r9) goto L_0x0377;
    L_0x036e:
        r11 = r6 + r1;
        if (r11 >= r10) goto L_0x0377;
    L_0x0372:
        r11 = 3;
        r11 = r8[r11];
        if (r11 < r7) goto L_0x037a;
    L_0x0377:
        r1 = 0;
        goto L_0x0245;
    L_0x037a:
        r11 = r5 + r1;
        if (r11 >= r9) goto L_0x039d;
    L_0x037e:
        r11 = r6 + r1;
        if (r11 >= r10) goto L_0x039d;
    L_0x0382:
        r11 = r14.a;
        r12 = r6 + r1;
        r13 = r5 + r1;
        r11 = r11.a(r12, r13);
        if (r11 == 0) goto L_0x039d;
    L_0x038e:
        r11 = 4;
        r11 = r8[r11];
        if (r11 >= r7) goto L_0x039d;
    L_0x0393:
        r11 = 4;
        r12 = r8[r11];
        r12 = r12 + 1;
        r8[r11] = r12;
        r1 = r1 + 1;
        goto L_0x037a;
    L_0x039d:
        r1 = 4;
        r1 = r8[r1];
        if (r1 >= r7) goto L_0x03c7;
    L_0x03a2:
        r1 = 0;
        r1 = r8[r1];
        r5 = 1;
        r5 = r8[r5];
        r1 = r1 + r5;
        r5 = 2;
        r5 = r8[r5];
        r1 = r1 + r5;
        r5 = 3;
        r5 = r8[r5];
        r1 = r1 + r5;
        r5 = 4;
        r5 = r8[r5];
        r1 = r1 + r5;
        r1 = r1 - r4;
        r1 = java.lang.Math.abs(r1);
        r5 = r4 * 2;
        if (r1 >= r5) goto L_0x03c7;
    L_0x03be:
        r1 = a(r8);
        if (r1 == 0) goto L_0x03c7;
    L_0x03c4:
        r1 = 1;
        goto L_0x0245;
    L_0x03c7:
        r1 = 0;
        goto L_0x0245;
    L_0x03ca:
        r6 = 0;
        goto L_0x028c;
    L_0x03cd:
        r6 = 0;
        goto L_0x028c;
    L_0x03d0:
        r1 = r4 + 1;
        r4 = r1;
        goto L_0x024f;
    L_0x03d5:
        r1 = 0;
        goto L_0x02c5;
    L_0x03d8:
        r1 = r5;
        goto L_0x02b8;
        */
    }

    public final boolean a() {
        float f = AutoScrollHelper.RELATIVE_UNSPECIFIED;
        int size = this.b.size();
        float f2 = 0.0f;
        int i = 0;
        for (d dVar : this.b) {
            float f3;
            int i2;
            if (dVar.d >= 2) {
                f3 = dVar.c + f2;
                i2 = i + 1;
            } else {
                f3 = f2;
                i2 = i;
            }
            i = i2;
            f2 = f3;
        }
        if (i < 3) {
            return false;
        }
        float f4 = f2 / ((float) size);
        for (d dVar2 : this.b) {
            f += Math.abs(dVar2.c - f4);
        }
        return f <= 0.05f * f2;
    }
}
