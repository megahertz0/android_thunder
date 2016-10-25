package com.google.zxing.datamatrix;

import com.google.zxing.datamatrix.a.e;
import com.google.zxing.l;
import com.google.zxing.o;

// compiled from: DataMatrixReader.java
public final class a implements l {
    private static final o[] a;
    private final e b;

    public a() {
        this.b = new e();
    }

    static {
        a = new o[0];
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.zxing.n a(com.google.zxing.b r14, java.util.Map<com.google.zxing.DecodeHintType, ?> r15) throws com.google.zxing.j, com.google.zxing.c, com.google.zxing.e {
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.a.a(com.google.zxing.b, java.util.Map):com.google.zxing.n");
        /*
        this = this;
        if (r15 == 0) goto L_0x00b0;
    L_0x0002:
        r0 = com.google.zxing.DecodeHintType.PURE_BARCODE;
        r0 = r15.containsKey(r0);
        if (r0 == 0) goto L_0x00b0;
    L_0x000a:
        r2 = r14.a();
        r1 = r2.b();
        r3 = r2.c();
        if (r1 == 0) goto L_0x001a;
    L_0x0018:
        if (r3 != 0) goto L_0x001f;
    L_0x001a:
        r0 = com.google.zxing.j.a();
        throw r0;
    L_0x001f:
        r4 = r2.a;
        r0 = 0;
        r0 = r1[r0];
        r5 = 1;
        r5 = r1[r5];
    L_0x0027:
        if (r0 >= r4) goto L_0x0032;
    L_0x0029:
        r6 = r2.a(r0, r5);
        if (r6 == 0) goto L_0x0032;
    L_0x002f:
        r0 = r0 + 1;
        goto L_0x0027;
    L_0x0032:
        if (r0 != r4) goto L_0x0039;
    L_0x0034:
        r0 = com.google.zxing.j.a();
        throw r0;
    L_0x0039:
        r4 = 0;
        r4 = r1[r4];
        r4 = r0 - r4;
        if (r4 != 0) goto L_0x0045;
    L_0x0040:
        r0 = com.google.zxing.j.a();
        throw r0;
    L_0x0045:
        r0 = 1;
        r0 = r1[r0];
        r5 = 1;
        r5 = r3[r5];
        r6 = 0;
        r1 = r1[r6];
        r6 = 0;
        r3 = r3[r6];
        r3 = r3 - r1;
        r3 = r3 + 1;
        r3 = r3 / r4;
        r5 = r5 - r0;
        r5 = r5 + 1;
        r5 = r5 / r4;
        if (r3 <= 0) goto L_0x005d;
    L_0x005b:
        if (r5 > 0) goto L_0x0062;
    L_0x005d:
        r0 = com.google.zxing.j.a();
        throw r0;
    L_0x0062:
        r6 = r4 / 2;
        r7 = r0 + r6;
        r6 = r6 + r1;
        r8 = new com.google.zxing.common.b;
        r8.<init>(r3, r5);
        r0 = 0;
        r1 = r0;
    L_0x006e:
        if (r1 >= r5) goto L_0x008a;
    L_0x0070:
        r0 = r1 * r4;
        r9 = r7 + r0;
        r0 = 0;
    L_0x0075:
        if (r0 >= r3) goto L_0x0086;
    L_0x0077:
        r10 = r0 * r4;
        r10 = r10 + r6;
        r10 = r2.a(r10, r9);
        if (r10 == 0) goto L_0x0083;
    L_0x0080:
        r8.b(r0, r1);
    L_0x0083:
        r0 = r0 + 1;
        goto L_0x0075;
    L_0x0086:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x006e;
    L_0x008a:
        r0 = r13.b;
        r1 = r0.a(r8);
        r0 = a;
    L_0x0092:
        r2 = new com.google.zxing.n;
        r3 = r1.b;
        r4 = r1.a;
        r5 = com.google.zxing.BarcodeFormat.DATA_MATRIX;
        r2.<init>(r3, r4, r0, r5);
        r0 = r1.c;
        if (r0 == 0) goto L_0x00a6;
    L_0x00a1:
        r3 = com.google.zxing.ResultMetadataType.BYTE_SEGMENTS;
        r2.a(r3, r0);
    L_0x00a6:
        r0 = r1.d;
        if (r0 == 0) goto L_0x00af;
    L_0x00aa:
        r1 = com.google.zxing.ResultMetadataType.ERROR_CORRECTION_LEVEL;
        r2.a(r1, r0);
    L_0x00af:
        return r2;
    L_0x00b0:
        r9 = new com.google.zxing.datamatrix.b.a;
        r0 = r14.a();
        r9.<init>(r0);
        r0 = r9.b;
        r0 = r0.a();
        r1 = 0;
        r5 = r0[r1];
        r1 = 1;
        r6 = r0[r1];
        r1 = 2;
        r7 = r0[r1];
        r1 = 3;
        r8 = r0[r1];
        r1 = new java.util.ArrayList;
        r0 = 4;
        r1.<init>(r0);
        r0 = r9.b(r5, r6);
        r1.add(r0);
        r0 = r9.b(r5, r7);
        r1.add(r0);
        r0 = r9.b(r6, r8);
        r1.add(r0);
        r0 = r9.b(r7, r8);
        r1.add(r0);
        r0 = new com.google.zxing.datamatrix.b.a$b;
        r2 = 0;
        r0.<init>();
        java.util.Collections.sort(r1, r0);
        r0 = 0;
        r0 = r1.get(r0);
        r0 = (com.google.zxing.datamatrix.b.a.a) r0;
        r2 = 1;
        r1 = r1.get(r2);
        r1 = (com.google.zxing.datamatrix.b.a.a) r1;
        r10 = new java.util.HashMap;
        r10.<init>();
        r2 = r0.a;
        com.google.zxing.datamatrix.b.a.a(r10, r2);
        r0 = r0.b;
        com.google.zxing.datamatrix.b.a.a(r10, r0);
        r0 = r1.a;
        com.google.zxing.datamatrix.b.a.a(r10, r0);
        r0 = r1.b;
        com.google.zxing.datamatrix.b.a.a(r10, r0);
        r2 = 0;
        r0 = 0;
        r1 = 0;
        r3 = r10.entrySet();
        r11 = r3.iterator();
        r3 = r0;
        r4 = r2;
        r2 = r1;
    L_0x012b:
        r0 = r11.hasNext();
        if (r0 == 0) goto L_0x0152;
    L_0x0131:
        r0 = r11.next();
        r0 = (java.util.Map.Entry) r0;
        r1 = r0.getKey();
        r1 = (com.google.zxing.o) r1;
        r0 = r0.getValue();
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r12 = 2;
        if (r0 != r12) goto L_0x014c;
    L_0x014a:
        r3 = r1;
        goto L_0x012b;
    L_0x014c:
        if (r4 != 0) goto L_0x0150;
    L_0x014e:
        r4 = r1;
        goto L_0x012b;
    L_0x0150:
        r2 = r1;
        goto L_0x012b;
    L_0x0152:
        if (r4 == 0) goto L_0x0158;
    L_0x0154:
        if (r3 == 0) goto L_0x0158;
    L_0x0156:
        if (r2 != 0) goto L_0x015d;
    L_0x0158:
        r0 = com.google.zxing.j.a();
        throw r0;
    L_0x015d:
        r0 = 3;
        r0 = new com.google.zxing.o[r0];
        r1 = 0;
        r0[r1] = r4;
        r1 = 1;
        r0[r1] = r3;
        r1 = 2;
        r0[r1] = r2;
        com.google.zxing.o.a(r0);
        r1 = 0;
        r3 = r0[r1];
        r1 = 1;
        r2 = r0[r1];
        r1 = 2;
        r1 = r0[r1];
        r0 = r10.containsKey(r5);
        if (r0 != 0) goto L_0x0244;
    L_0x017b:
        r0 = r5;
    L_0x017c:
        r4 = r9.b(r1, r0);
        r5 = r4.c;
        r4 = r9.b(r3, r0);
        r4 = r4.c;
        r6 = r5 & 1;
        r7 = 1;
        if (r6 != r7) goto L_0x018f;
    L_0x018d:
        r5 = r5 + 1;
    L_0x018f:
        r6 = r5 + 2;
        r5 = r4 & 1;
        r7 = 1;
        if (r5 != r7) goto L_0x0198;
    L_0x0196:
        r4 = r4 + 1;
    L_0x0198:
        r7 = r4 + 2;
        r4 = r6 * 4;
        r5 = r7 * 7;
        if (r4 >= r5) goto L_0x01a6;
    L_0x01a0:
        r4 = r7 * 4;
        r5 = r6 * 7;
        if (r4 < r5) goto L_0x0294;
    L_0x01a6:
        r4 = com.google.zxing.datamatrix.b.a.a(r2, r3);
        r4 = (float) r4;
        r5 = (float) r6;
        r5 = r4 / r5;
        r4 = com.google.zxing.datamatrix.b.a.a(r1, r0);
        r8 = r0.a;
        r10 = r1.a;
        r8 = r8 - r10;
        r10 = (float) r4;
        r8 = r8 / r10;
        r10 = r0.b;
        r11 = r1.b;
        r10 = r10 - r11;
        r4 = (float) r4;
        r10 = r10 / r4;
        r4 = new com.google.zxing.o;
        r11 = r0.a;
        r8 = r8 * r5;
        r8 = r8 + r11;
        r11 = r0.b;
        r5 = r5 * r10;
        r5 = r5 + r11;
        r4.<init>(r8, r5);
        r5 = com.google.zxing.datamatrix.b.a.a(r2, r1);
        r5 = (float) r5;
        r8 = (float) r7;
        r8 = r5 / r8;
        r5 = com.google.zxing.datamatrix.b.a.a(r3, r0);
        r10 = r0.a;
        r11 = r3.a;
        r10 = r10 - r11;
        r11 = (float) r5;
        r10 = r10 / r11;
        r11 = r0.b;
        r12 = r3.b;
        r11 = r11 - r12;
        r5 = (float) r5;
        r11 = r11 / r5;
        r5 = new com.google.zxing.o;
        r12 = r0.a;
        r10 = r10 * r8;
        r10 = r10 + r12;
        r12 = r0.b;
        r8 = r8 * r11;
        r8 = r8 + r12;
        r5.<init>(r10, r8);
        r8 = r9.a(r4);
        if (r8 != 0) goto L_0x0259;
    L_0x01fa:
        r4 = r9.a(r5);
        if (r4 != 0) goto L_0x0291;
    L_0x0200:
        r4 = 0;
    L_0x0201:
        if (r4 != 0) goto L_0x0204;
    L_0x0203:
        r4 = r0;
    L_0x0204:
        r0 = r9.b(r1, r4);
        r5 = r0.c;
        r0 = r9.b(r3, r4);
        r6 = r0.c;
        r0 = r5 & 1;
        r7 = 1;
        if (r0 != r7) goto L_0x0217;
    L_0x0215:
        r5 = r5 + 1;
    L_0x0217:
        r0 = r6 & 1;
        r7 = 1;
        if (r0 != r7) goto L_0x021e;
    L_0x021c:
        r6 = r6 + 1;
    L_0x021e:
        r0 = r9.a;
        r0 = com.google.zxing.datamatrix.b.a.a(r0, r1, r2, r3, r4, r5, r6);
    L_0x0224:
        r5 = new com.google.zxing.common.f;
        r6 = 4;
        r6 = new com.google.zxing.o[r6];
        r7 = 0;
        r6[r7] = r1;
        r1 = 1;
        r6[r1] = r2;
        r1 = 2;
        r6[r1] = r3;
        r1 = 3;
        r6[r1] = r4;
        r5.<init>(r0, r6);
        r0 = r13.b;
        r1 = r5.d;
        r1 = r0.a(r1);
        r0 = r5.e;
        goto L_0x0092;
    L_0x0244:
        r0 = r10.containsKey(r6);
        if (r0 != 0) goto L_0x024d;
    L_0x024a:
        r0 = r6;
        goto L_0x017c;
    L_0x024d:
        r0 = r10.containsKey(r7);
        if (r0 != 0) goto L_0x0256;
    L_0x0253:
        r0 = r7;
        goto L_0x017c;
    L_0x0256:
        r0 = r8;
        goto L_0x017c;
    L_0x0259:
        r8 = r9.a(r5);
        if (r8 == 0) goto L_0x0201;
    L_0x025f:
        r8 = r9.b(r1, r4);
        r8 = r8.c;
        r8 = r6 - r8;
        r8 = java.lang.Math.abs(r8);
        r10 = r9.b(r3, r4);
        r10 = r10.c;
        r10 = r7 - r10;
        r10 = java.lang.Math.abs(r10);
        r8 = r8 + r10;
        r10 = r9.b(r1, r5);
        r10 = r10.c;
        r6 = r6 - r10;
        r6 = java.lang.Math.abs(r6);
        r10 = r9.b(r3, r5);
        r10 = r10.c;
        r7 = r7 - r10;
        r7 = java.lang.Math.abs(r7);
        r6 = r6 + r7;
        if (r8 <= r6) goto L_0x0201;
    L_0x0291:
        r4 = r5;
        goto L_0x0201;
    L_0x0294:
        r5 = java.lang.Math.min(r7, r6);
        r4 = com.google.zxing.datamatrix.b.a.a(r2, r3);
        r4 = (float) r4;
        r6 = (float) r5;
        r6 = r4 / r6;
        r4 = com.google.zxing.datamatrix.b.a.a(r1, r0);
        r7 = r0.a;
        r8 = r1.a;
        r7 = r7 - r8;
        r8 = (float) r4;
        r7 = r7 / r8;
        r8 = r0.b;
        r10 = r1.b;
        r8 = r8 - r10;
        r4 = (float) r4;
        r8 = r8 / r4;
        r4 = new com.google.zxing.o;
        r10 = r0.a;
        r7 = r7 * r6;
        r7 = r7 + r10;
        r10 = r0.b;
        r6 = r6 * r8;
        r6 = r6 + r10;
        r4.<init>(r7, r6);
        r6 = com.google.zxing.datamatrix.b.a.a(r2, r1);
        r6 = (float) r6;
        r5 = (float) r5;
        r6 = r6 / r5;
        r5 = com.google.zxing.datamatrix.b.a.a(r3, r0);
        r7 = r0.a;
        r8 = r3.a;
        r7 = r7 - r8;
        r8 = (float) r5;
        r7 = r7 / r8;
        r8 = r0.b;
        r10 = r3.b;
        r8 = r8 - r10;
        r5 = (float) r5;
        r8 = r8 / r5;
        r5 = new com.google.zxing.o;
        r10 = r0.a;
        r7 = r7 * r6;
        r7 = r7 + r10;
        r10 = r0.b;
        r6 = r6 * r8;
        r6 = r6 + r10;
        r5.<init>(r7, r6);
        r6 = r9.a(r4);
        if (r6 != 0) goto L_0x0317;
    L_0x02eb:
        r4 = r9.a(r5);
        if (r4 != 0) goto L_0x0341;
    L_0x02f1:
        r4 = 0;
    L_0x02f2:
        if (r4 != 0) goto L_0x02f5;
    L_0x02f4:
        r4 = r0;
    L_0x02f5:
        r0 = r9.b(r1, r4);
        r0 = r0.c;
        r5 = r9.b(r3, r4);
        r5 = r5.c;
        r0 = java.lang.Math.max(r0, r5);
        r5 = r0 + 1;
        r0 = r5 & 1;
        r6 = 1;
        if (r0 != r6) goto L_0x030e;
    L_0x030c:
        r5 = r5 + 1;
    L_0x030e:
        r0 = r9.a;
        r6 = r5;
        r0 = com.google.zxing.datamatrix.b.a.a(r0, r1, r2, r3, r4, r5, r6);
        goto L_0x0224;
    L_0x0317:
        r6 = r9.a(r5);
        if (r6 == 0) goto L_0x02f2;
    L_0x031d:
        r6 = r9.b(r1, r4);
        r6 = r6.c;
        r7 = r9.b(r3, r4);
        r7 = r7.c;
        r6 = r6 - r7;
        r6 = java.lang.Math.abs(r6);
        r7 = r9.b(r1, r5);
        r7 = r7.c;
        r8 = r9.b(r3, r5);
        r8 = r8.c;
        r7 = r7 - r8;
        r7 = java.lang.Math.abs(r7);
        if (r6 <= r7) goto L_0x02f2;
    L_0x0341:
        r4 = r5;
        goto L_0x02f2;
        */
    }

    public final void a() {
    }
}
