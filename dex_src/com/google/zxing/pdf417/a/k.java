package com.google.zxing.pdf417.a;

import com.google.zxing.c;
import com.google.zxing.common.b;
import com.google.zxing.common.d;
import com.google.zxing.e;
import com.google.zxing.j;
import com.google.zxing.o;
import com.google.zxing.pdf417.a.a.a;
import com.xunlei.tdlive.R;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// compiled from: PDF417ScanningDecoder.java
public final class k {
    private static final a a;

    static {
        a = new a();
    }

    public static d a(b bVar, o oVar, o oVar2, o oVar3, o oVar4, int i, int i2) throws j, e, c {
        i iVar;
        i iVar2;
        g gVar;
        i iVar3;
        int i3;
        int i4;
        int i5;
        Object obj = null;
        int i6 = 0;
        c cVar = new c(bVar, oVar, oVar2, oVar3, oVar4);
        g gVar2 = null;
        Object obj2 = null;
        while (i6 < 2) {
            i a;
            i a2;
            if (oVar != null) {
                a = a(bVar, cVar, oVar, true, i, i2);
            } else {
                a = iVar;
            }
            if (oVar3 != null) {
                a2 = a(bVar, cVar, oVar3, false, i, i2);
            } else {
                a2 = iVar2;
            }
            if (a == null && a2 == null) {
                gVar2 = null;
            } else {
                a aVar;
                c a3;
                c a4;
                c cVar2;
                if (a != null) {
                    a a5 = a.a();
                    if (a5 != null) {
                        if (a2 != null) {
                            a a6 = a2.a();
                            if (a6 != null) {
                                aVar = (a5.a == a6.a || a5.b == a6.b || a5.e == a6.e) ? a5 : null;
                                if (aVar == null) {
                                    gVar2 = null;
                                } else {
                                    a3 = a(a);
                                    a4 = a(a2);
                                    if (a3 != null) {
                                        cVar2 = a4;
                                    } else if (a4 == null) {
                                        cVar2 = a3;
                                    } else {
                                        cVar2 = new c(a3.a, a3.b, a3.c, a4.d, a4.e);
                                    }
                                    gVar2 = new g(aVar, cVar2);
                                }
                            }
                        }
                        aVar = a5;
                        if (aVar == null) {
                            a3 = a(a);
                            a4 = a(a2);
                            if (a3 != null) {
                                cVar2 = a4;
                            } else if (a4 == null) {
                                cVar2 = new c(a3.a, a3.b, a3.c, a4.d, a4.e);
                            } else {
                                cVar2 = a3;
                            }
                            gVar2 = new g(aVar, cVar2);
                        } else {
                            gVar2 = null;
                        }
                    }
                }
                aVar = a2 == null ? null : a2.a();
                if (aVar == null) {
                    gVar2 = null;
                } else {
                    a3 = a(a);
                    a4 = a(a2);
                    if (a3 != null) {
                        cVar2 = a4;
                    } else if (a4 == null) {
                        cVar2 = a3;
                    } else {
                        cVar2 = new c(a3.a, a3.b, a3.c, a4.d, a4.e);
                    }
                    gVar2 = new g(aVar, cVar2);
                }
            }
            if (gVar2 != null) {
                if (i6 == 0 && gVar2.c != null) {
                    if (gVar2.c.h < cVar.h || gVar2.c.i > cVar.i) {
                    }
                    i6++;
                    iVar2 = a2;
                    cVar = gVar2.c;
                    iVar = a;
                }
                gVar2.c = cVar;
                gVar = gVar2;
                iVar = a;
                iVar3 = a2;
                break;
            }
            throw j.a();
        }
        gVar = gVar2;
        iVar3 = iVar2;
        int i7 = gVar.d + 1;
        gVar.b[0] = iVar;
        gVar.b[i7] = iVar3;
        boolean z = iVar != null;
        int i8 = i2;
        int i9 = i;
        for (int i10 = 1; i10 <= i7; i10++) {
            int i11;
            if (z) {
                i6 = i10;
            } else {
                i6 = i7 - i10;
            }
            if (gVar.b[i6] == null) {
                h iVar4;
                if (i6 == 0 || i6 == i7) {
                    boolean z2;
                    if (i6 == 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    iVar4 = new i(cVar, z2);
                } else {
                    iVar4 = new h(cVar);
                }
                gVar.b[i6] = iVar4;
                Object obj3 = -1;
                for (i11 = cVar.h; i11 <= cVar.i; i11++) {
                    int i12;
                    int i13;
                    if (z) {
                        i3 = 1;
                    } else {
                        i3 = -1;
                    }
                    d dVar = null;
                    if (a(gVar, i6 - i3)) {
                        dVar = gVar.b[i6 - i3].c(i11);
                    }
                    if (dVar == null) {
                        dVar = gVar.b[i6].a(i11);
                        if (dVar == null) {
                            if (a(gVar, i6 - i3)) {
                                dVar = gVar.b[i6 - i3].a(i11);
                            }
                            if (dVar == null) {
                                i4 = 0;
                                int i14 = i6;
                                while (a(gVar, i14 - i3)) {
                                    int i15 = i14 - i3;
                                    d[] dVarArr = gVar.b[i15].b;
                                    int length = dVarArr.length;
                                    i14 = 0;
                                    while (i14 < length) {
                                        d dVar2 = dVarArr[i14];
                                        if (dVar2 != null) {
                                            if (z) {
                                                i14 = dVar2.b;
                                            } else {
                                                i14 = dVar2.a;
                                            }
                                            i12 = i14 + ((i4 * i3) * (dVar2.b - dVar2.a));
                                        } else {
                                            i14++;
                                        }
                                    }
                                    i4++;
                                    i14 = i15;
                                }
                                if (z) {
                                    i12 = gVar.c.f;
                                } else {
                                    i12 = gVar.c.g;
                                }
                            } else if (z) {
                                i12 = dVar.b;
                            } else {
                                i12 = dVar.a;
                            }
                        } else if (z) {
                            i12 = dVar.a;
                        } else {
                            i12 = dVar.b;
                        }
                    } else if (z) {
                        i12 = dVar.b;
                    } else {
                        i12 = dVar.a;
                    }
                    if (i12 < 0 || i12 > cVar.g) {
                        if (i13 != -1) {
                            i12 = i13;
                        } else {
                        }
                    }
                    dVar = a(bVar, cVar.f, cVar.g, z, i12, i11, i9, i8);
                    if (dVar != null) {
                        iVar4.a(i11, dVar);
                        i9 = Math.min(i9, dVar.c());
                        i8 = Math.max(i8, dVar.c());
                        i13 = i12;
                    }
                }
            }
        }
        b[][] bVarArr = (b[][]) Array.newInstance(b.class, new int[]{gVar.a.e, gVar.d + 2});
        for (i4 = 0; i4 < bVarArr.length; i4++) {
            for (i5 = 0; i5 < bVarArr[i4].length; i5++) {
                bVarArr[i4][i5] = new b();
            }
        }
        gVar.a(gVar.b[0]);
        gVar.a(gVar.b[gVar.d + 1]);
        int i16 = 928;
        while (true) {
            d dVar3;
            d[] dVarArr2;
            if (!(gVar.b[0] == null || gVar.b[gVar.d + 1] == null)) {
                d[] dVarArr3 = gVar.b[0].b;
                d[] dVarArr4 = gVar.b[gVar.d + 1].b;
                i5 = 0;
                while (i5 < dVarArr3.length) {
                    if (dVarArr3[i5] != null && dVarArr4[i5] != null && dVarArr3[i5].e == dVarArr4[i5].e) {
                        for (i4 = 1; i4 <= gVar.d; i4++) {
                            d dVar4 = gVar.b[i4].b[i5];
                            if (dVar4 != null) {
                                dVar4.e = dVarArr3[i5].e;
                                if (!dVar4.a()) {
                                    gVar.b[i4].b[i5] = null;
                                }
                            }
                        }
                    }
                    i5++;
                }
            }
            if (gVar.b[0] == null) {
                i4 = 0;
            } else {
                i4 = 0;
                d[] dVarArr5 = gVar.b[0].b;
                for (i5 = 0; i5 < dVarArr5.length; i5++) {
                    if (dVarArr5[i5] != null) {
                        i9 = dVarArr5[i5].e;
                        i3 = 0;
                        i15 = i4;
                        for (i4 = 1; i4 < gVar.d + 1 && i3 < 2; i4++) {
                            dVar3 = gVar.b[i4].b[i5];
                            if (dVar3 != null) {
                                i3 = g.a(i9, i3, dVar3);
                                if (!dVar3.a()) {
                                    i15++;
                                }
                            }
                        }
                        i4 = i15;
                    }
                }
            }
            if (gVar.b[gVar.d + 1] == null) {
                i15 = 0;
            } else {
                i15 = 0;
                dVarArr2 = gVar.b[gVar.d + 1].b;
                for (i5 = 0; i5 < dVarArr2.length; i5++) {
                    if (dVarArr2[i5] != null) {
                        i8 = dVarArr2[i5].e;
                        i3 = 0;
                        for (i11 = gVar.d + 1; i11 > 0 && i3 < 2; i11--) {
                            d dVar5 = gVar.b[i11].b[i5];
                            if (dVar5 != null) {
                                i3 = g.a(i8, i3, dVar5);
                                if (!dVar5.a()) {
                                    i15++;
                                }
                            }
                        }
                    }
                }
            }
            i11 = i4 + i15;
            if (i11 == 0) {
                Object obj4 = null;
            } else {
                for (i3 = 1; i3 < gVar.d + 1; i3++) {
                    dVarArr2 = gVar.b[i3].b;
                    i15 = 0;
                    while (i15 < dVarArr2.length) {
                        if (dVarArr2[i15] != null && !dVarArr2[i15].a()) {
                            d[] dVarArr6;
                            dVar3 = dVarArr2[i15];
                            d[] dVarArr7 = gVar.b[i3 - 1].b;
                            if (gVar.b[i3 + 1] != null) {
                                dVarArr6 = gVar.b[i3 + 1].b;
                            } else {
                                dVarArr6 = dVarArr7;
                            }
                            d[] dVarArr8 = new d[14];
                            dVarArr8[2] = dVarArr7[i15];
                            dVarArr8[3] = dVarArr6[i15];
                            if (i15 > 0) {
                                dVarArr8[0] = dVarArr2[i15 - 1];
                                dVarArr8[4] = dVarArr7[i15 - 1];
                                dVarArr8[5] = dVarArr6[i15 - 1];
                            }
                            if (i15 > 1) {
                                dVarArr8[8] = dVarArr2[i15 - 2];
                                dVarArr8[10] = dVarArr7[i15 - 2];
                                dVarArr8[11] = dVarArr6[i15 - 2];
                            }
                            if (i15 < dVarArr2.length - 1) {
                                dVarArr8[1] = dVarArr2[i15 + 1];
                                dVarArr8[6] = dVarArr7[i15 + 1];
                                dVarArr8[7] = dVarArr6[i15 + 1];
                            }
                            if (i15 < dVarArr2.length - 2) {
                                dVarArr8[9] = dVarArr2[i15 + 2];
                                dVarArr8[12] = dVarArr7[i15 + 2];
                                dVarArr8[13] = dVarArr6[i15 + 2];
                            }
                            for (i5 = 0; i5 < 14; i5++) {
                                d dVar6 = dVarArr8[i5];
                                if (dVar6 != null && dVar6.a() && dVar6.c == dVar3.c) {
                                    dVar3.e = dVar6.e;
                                    obj2 = 1;
                                } else {
                                    obj2 = null;
                                }
                                if (obj2 != null) {
                                    break;
                                }
                            }
                        }
                        i15++;
                    }
                }
                i5 = i11;
            }
            if (i5 <= 0 || i5 >= r6) {
                break;
            }
            i16 = i5;
        }
        i4 = 0;
        for (h hVar : gVar.b) {
            if (hVar != null) {
                for (d dVar7 : hVar.b) {
                    if (dVar7 != null) {
                        i8 = dVar7.e;
                        if (i8 < 0) {
                            continue;
                        } else if (i8 >= bVarArr.length) {
                            throw e.a();
                        } else {
                            bVarArr[i8][i4].a(dVar7.d);
                        }
                    }
                }
                continue;
            }
            i4++;
        }
        int[] a7 = bVarArr[0][1].a();
        i4 = (gVar.d * gVar.a.e) - (2 << gVar.a.b);
        if (a7.length == 0) {
            if (i4 <= 0 || i4 > 928) {
                throw j.a();
            }
            bVarArr[0][1].a(i4);
        } else if (a7[0] != i4) {
            bVarArr[0][1].a(i4);
        }
        Collection arrayList = new ArrayList();
        int[] iArr = new int[(gVar.a.e * gVar.d)];
        List arrayList2 = new ArrayList();
        Collection arrayList3 = new ArrayList();
        for (i5 = 0; i5 < gVar.a.e; i5++) {
            for (i4 = 0; i4 < gVar.d; i4++) {
                Object a8 = bVarArr[i5][i4 + 1].a();
                i9 = (gVar.d * i5) + i4;
                if (a8.length == 0) {
                    arrayList.add(Integer.valueOf(i9));
                } else if (a8.length == 1) {
                    iArr[i9] = a8[0];
                } else {
                    arrayList3.add(Integer.valueOf(i9));
                    arrayList2.add(a8);
                }
            }
        }
        int[][] iArr2 = new int[arrayList2.size()][];
        for (i5 = 0; i5 < iArr2.length; i5++) {
            iArr2[i5] = (int[]) arrayList2.get(i5);
        }
        return a(gVar.a.b, iArr, com.google.zxing.pdf417.a.a(arrayList), com.google.zxing.pdf417.a.a(arrayList3), iArr2);
    }

    private static c a(i iVar) throws j, e {
        if (iVar == null) {
            return null;
        }
        o oVar;
        int b;
        int i;
        int i2;
        a a = iVar.a();
        if (a == null) {
            Object[] objArr = null;
        } else {
            o oVar2;
            int i3;
            c cVar = iVar.a;
            if (iVar.c) {
                oVar = cVar.b;
            } else {
                oVar = cVar.d;
            }
            if (iVar.c) {
                oVar2 = cVar.c;
            } else {
                oVar2 = cVar.e;
            }
            b = iVar.b((int) oVar.b);
            int b2 = iVar.b((int) oVar2.b);
            d[] dVarArr = iVar.b;
            i = 1;
            i2 = -1;
            b = 0;
            for (i3 = b; i3 < b2; i3++) {
                if (dVarArr[i3] != null) {
                    d dVar = dVarArr[i3];
                    dVar.b();
                    int i4 = dVar.e - i2;
                    if (i4 == 0) {
                        b++;
                    } else if (i4 == 1) {
                        i2 = dVar.e;
                        i = Math.max(i, b);
                        b = 1;
                    } else if (dVar.e >= a.e) {
                        dVarArr[i3] = null;
                    } else {
                        i2 = dVar.e;
                        b = 1;
                    }
                }
            }
            int[] iArr = new int[a.e];
            d[] dVarArr2 = iVar.b;
            i2 = dVarArr2.length;
            for (i = 0; i < i2; i++) {
                d dVar2 = dVarArr2[i];
                if (dVar2 != null) {
                    i3 = dVar2.e;
                    if (i3 >= iArr.length) {
                        throw e.a();
                    }
                    iArr[i3] = iArr[i3] + 1;
                }
            }
            int[] iArr2 = iArr;
        }
        if (iArr2 == null) {
            return null;
        }
        o oVar3;
        o oVar4;
        b = 0;
        i2 = -1;
        while (b < iArr2.length) {
            b++;
            i2 = Math.max(i2, iArr2[b]);
        }
        int length = iArr2.length;
        b = 0;
        for (i = 0; i < length; i++) {
            int i5 = iArr2[i];
            b += i2 - i5;
            if (i5 > 0) {
                break;
            }
        }
        d[] dVarArr3 = iVar.b;
        length = b;
        b = 0;
        while (length > 0 && dVarArr3[b] == null) {
            b++;
            length--;
        }
        b = 0;
        for (i = iArr2.length - 1; i >= 0; i--) {
            b += i2 - iArr2[i];
            if (iArr2[i] > 0) {
                break;
            }
        }
        int i6 = b;
        b = dVarArr3.length - 1;
        while (i6 > 0 && dVarArr3[b] == null) {
            b--;
            i6--;
        }
        c cVar2 = iVar.a;
        boolean z = iVar.c;
        o oVar5 = cVar2.b;
        o oVar6 = cVar2.c;
        o oVar7 = cVar2.d;
        o oVar8 = cVar2.e;
        if (length > 0) {
            if (z) {
                oVar = cVar2.b;
            } else {
                oVar = cVar2.d;
            }
            i = ((int) oVar.b) - length;
            if (i < 0) {
                i = 0;
            }
            oVar3 = new o(oVar.a, (float) i);
            if (z) {
                oVar4 = oVar7;
            } else {
                oVar4 = oVar3;
                oVar3 = oVar5;
            }
        } else {
            oVar3 = oVar5;
            oVar4 = oVar7;
        }
        if (i6 > 0) {
            if (z) {
                oVar = cVar2.c;
            } else {
                oVar = cVar2.e;
            }
            i = ((int) oVar.b) + i6;
            if (i >= cVar2.a.b) {
                i = cVar2.a.b - 1;
            }
            oVar5 = new o(oVar.a, (float) i);
            if (!z) {
                oVar8 = oVar5;
                oVar5 = oVar6;
            }
        } else {
            oVar5 = oVar6;
        }
        cVar2.a();
        return new c(cVar2.a, oVar3, oVar5, oVar4, oVar8);
    }

    private static i a(b bVar, c cVar, o oVar, boolean z, int i, int i2) {
        i iVar = new i(cVar, z);
        for (int i3 = 0; i3 < 2; i3++) {
            int i4;
            if (i3 == 0) {
                i4 = 1;
            } else {
                i4 = -1;
            }
            int i5 = (int) oVar.a;
            int i6 = (int) oVar.b;
            while (i6 <= cVar.i && i6 >= cVar.h) {
                d a = a(bVar, 0, bVar.a, z, i5, i6, i, i2);
                if (a != null) {
                    iVar.a(i6, a);
                    if (z) {
                        i5 = a.a;
                    } else {
                        i5 = a.b;
                    }
                }
                i6 += i4;
            }
        }
        return iVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.zxing.common.d a(int r18, int[] r19, int[] r20, int[] r21, int[][] r22) throws com.google.zxing.e, com.google.zxing.c {
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.a.k.a(int, int[], int[], int[], int[][]):com.google.zxing.common.d");
        /*
        r0 = r21;
        r1 = r0.length;
        r9 = new int[r1];
        r1 = 100;
    L_0x0007:
        r8 = r1 + -1;
        if (r1 <= 0) goto L_0x0254;
    L_0x000b:
        r1 = 0;
    L_0x000c:
        r2 = r9.length;
        if (r1 >= r2) goto L_0x001c;
    L_0x000f:
        r2 = r21[r1];
        r3 = r22[r1];
        r4 = r9[r1];
        r3 = r3[r4];
        r19[r2] = r3;
        r1 = r1 + 1;
        goto L_0x000c;
    L_0x001c:
        r0 = r19;
        r1 = r0.length;	 Catch:{ c -> 0x0026 }
        if (r1 != 0) goto L_0x002f;
    L_0x0021:
        r1 = com.google.zxing.e.a();	 Catch:{ c -> 0x0026 }
        throw r1;	 Catch:{ c -> 0x0026 }
    L_0x0026:
        r1 = move-exception;
        r1 = r9.length;
        if (r1 != 0) goto L_0x022b;
    L_0x002a:
        r1 = com.google.zxing.c.a();
        throw r1;
    L_0x002f:
        r1 = 1;
        r2 = r18 + 1;
        r7 = r1 << r2;
        if (r20 == 0) goto L_0x003f;
    L_0x0036:
        r0 = r20;
        r1 = r0.length;	 Catch:{ c -> 0x0026 }
        r2 = r7 / 2;
        r2 = r2 + 3;
        if (r1 > r2) goto L_0x0045;
    L_0x003f:
        if (r7 < 0) goto L_0x0045;
    L_0x0041:
        r1 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        if (r7 <= r1) goto L_0x004a;
    L_0x0045:
        r1 = com.google.zxing.c.a();	 Catch:{ c -> 0x0026 }
        throw r1;	 Catch:{ c -> 0x0026 }
    L_0x004a:
        r10 = a;	 Catch:{ c -> 0x0026 }
        r3 = new com.google.zxing.pdf417.a.a.c;	 Catch:{ c -> 0x0026 }
        r1 = r10.a;	 Catch:{ c -> 0x0026 }
        r0 = r19;
        r3.<init>(r1, r0);	 Catch:{ c -> 0x0026 }
        r4 = new int[r7];	 Catch:{ c -> 0x0026 }
        r1 = 0;
        r2 = r7;
    L_0x0059:
        if (r2 <= 0) goto L_0x006f;
    L_0x005b:
        r5 = r10.a;	 Catch:{ c -> 0x0026 }
        r5 = r5.b;	 Catch:{ c -> 0x0026 }
        r5 = r5[r2];	 Catch:{ c -> 0x0026 }
        r5 = r3.b(r5);	 Catch:{ c -> 0x0026 }
        r6 = r7 - r2;
        r4[r6] = r5;	 Catch:{ c -> 0x0026 }
        if (r5 == 0) goto L_0x006c;
    L_0x006b:
        r1 = 1;
    L_0x006c:
        r2 = r2 + -1;
        goto L_0x0059;
    L_0x006f:
        if (r1 != 0) goto L_0x007d;
    L_0x0071:
        r1 = 0;
    L_0x0072:
        r0 = r19;
        r2 = r0.length;	 Catch:{ c -> 0x0026 }
        r3 = 4;
        if (r2 >= r3) goto L_0x01f1;
    L_0x0078:
        r1 = com.google.zxing.e.a();	 Catch:{ c -> 0x0026 }
        throw r1;	 Catch:{ c -> 0x0026 }
    L_0x007d:
        r1 = r10.a;	 Catch:{ c -> 0x0026 }
        r1 = r1.e;	 Catch:{ c -> 0x0026 }
        if (r20 == 0) goto L_0x00b7;
    L_0x0083:
        r0 = r20;
        r3 = r0.length;	 Catch:{ c -> 0x0026 }
        r2 = 0;
    L_0x0087:
        if (r2 >= r3) goto L_0x00b7;
    L_0x0089:
        r5 = r20[r2];	 Catch:{ c -> 0x0026 }
        r6 = r10.a;	 Catch:{ c -> 0x0026 }
        r0 = r19;
        r11 = r0.length;	 Catch:{ c -> 0x0026 }
        r11 = r11 + -1;
        r5 = r11 - r5;
        r6 = r6.b;	 Catch:{ c -> 0x0026 }
        r5 = r6[r5];	 Catch:{ c -> 0x0026 }
        r6 = new com.google.zxing.pdf417.a.a.c;	 Catch:{ c -> 0x0026 }
        r11 = r10.a;	 Catch:{ c -> 0x0026 }
        r12 = 2;
        r12 = new int[r12];	 Catch:{ c -> 0x0026 }
        r13 = 0;
        r14 = r10.a;	 Catch:{ c -> 0x0026 }
        r15 = 0;
        r5 = r14.c(r15, r5);	 Catch:{ c -> 0x0026 }
        r12[r13] = r5;	 Catch:{ c -> 0x0026 }
        r5 = 1;
        r13 = 1;
        r12[r5] = r13;	 Catch:{ c -> 0x0026 }
        r6.<init>(r11, r12);	 Catch:{ c -> 0x0026 }
        r1 = r1.c(r6);	 Catch:{ c -> 0x0026 }
        r2 = r2 + 1;
        goto L_0x0087;
    L_0x00b7:
        r2 = new com.google.zxing.pdf417.a.a.c;	 Catch:{ c -> 0x0026 }
        r1 = r10.a;	 Catch:{ c -> 0x0026 }
        r2.<init>(r1, r4);	 Catch:{ c -> 0x0026 }
        r1 = r10.a;	 Catch:{ c -> 0x0026 }
        r3 = 1;
        r1 = r1.a(r7, r3);	 Catch:{ c -> 0x0026 }
        r3 = r1.b;	 Catch:{ c -> 0x0026 }
        r3 = r3.length;	 Catch:{ c -> 0x0026 }
        r3 = r3 + -1;
        r4 = r2.b;	 Catch:{ c -> 0x0026 }
        r4 = r4.length;	 Catch:{ c -> 0x0026 }
        r4 = r4 + -1;
        if (r3 >= r4) goto L_0x0259;
    L_0x00d1:
        r4 = r1;
    L_0x00d2:
        r1 = r10.a;	 Catch:{ c -> 0x0026 }
        r3 = r1.d;	 Catch:{ c -> 0x0026 }
        r1 = r10.a;	 Catch:{ c -> 0x0026 }
        r1 = r1.e;	 Catch:{ c -> 0x0026 }
        r17 = r3;
        r3 = r4;
        r4 = r17;
    L_0x00df:
        r5 = r3.b;	 Catch:{ c -> 0x0026 }
        r5 = r5.length;	 Catch:{ c -> 0x0026 }
        r5 = r5 + -1;
        r6 = r7 / 2;
        if (r5 < r6) goto L_0x018d;
    L_0x00e8:
        r5 = r3.a();	 Catch:{ c -> 0x0026 }
        if (r5 == 0) goto L_0x00f3;
    L_0x00ee:
        r1 = com.google.zxing.c.a();	 Catch:{ c -> 0x0026 }
        throw r1;	 Catch:{ c -> 0x0026 }
    L_0x00f3:
        r5 = r10.a;	 Catch:{ c -> 0x0026 }
        r5 = r5.d;	 Catch:{ c -> 0x0026 }
        r6 = r3.b;	 Catch:{ c -> 0x0026 }
        r6 = r6.length;	 Catch:{ c -> 0x0026 }
        r6 = r6 + -1;
        r6 = r3.a(r6);	 Catch:{ c -> 0x0026 }
        r11 = r10.a;	 Catch:{ c -> 0x0026 }
        r11 = r11.a(r6);	 Catch:{ c -> 0x0026 }
        r17 = r5;
        r5 = r2;
        r2 = r17;
    L_0x010b:
        r6 = r5.b;	 Catch:{ c -> 0x0026 }
        r6 = r6.length;	 Catch:{ c -> 0x0026 }
        r6 = r6 + -1;
        r12 = r3.b;	 Catch:{ c -> 0x0026 }
        r12 = r12.length;	 Catch:{ c -> 0x0026 }
        r12 = r12 + -1;
        if (r6 < r12) goto L_0x017b;
    L_0x0117:
        r6 = r5.a();	 Catch:{ c -> 0x0026 }
        if (r6 != 0) goto L_0x017b;
    L_0x011d:
        r6 = r5.b;	 Catch:{ c -> 0x0026 }
        r6 = r6.length;	 Catch:{ c -> 0x0026 }
        r6 = r6 + -1;
        r12 = r3.b;	 Catch:{ c -> 0x0026 }
        r12 = r12.length;	 Catch:{ c -> 0x0026 }
        r12 = r12 + -1;
        r12 = r6 - r12;
        r6 = r10.a;	 Catch:{ c -> 0x0026 }
        r13 = r5.b;	 Catch:{ c -> 0x0026 }
        r13 = r13.length;	 Catch:{ c -> 0x0026 }
        r13 = r13 + -1;
        r13 = r5.a(r13);	 Catch:{ c -> 0x0026 }
        r13 = r6.d(r13, r11);	 Catch:{ c -> 0x0026 }
        r6 = r10.a;	 Catch:{ c -> 0x0026 }
        r6 = r6.a(r12, r13);	 Catch:{ c -> 0x0026 }
        r6 = r2.a(r6);	 Catch:{ c -> 0x0026 }
        if (r12 >= 0) goto L_0x014a;
    L_0x0144:
        r1 = new java.lang.IllegalArgumentException;	 Catch:{ c -> 0x0026 }
        r1.<init>();	 Catch:{ c -> 0x0026 }
        throw r1;	 Catch:{ c -> 0x0026 }
    L_0x014a:
        if (r13 != 0) goto L_0x0156;
    L_0x014c:
        r2 = r3.a;	 Catch:{ c -> 0x0026 }
        r2 = r2.d;	 Catch:{ c -> 0x0026 }
    L_0x0150:
        r5 = r5.b(r2);	 Catch:{ c -> 0x0026 }
        r2 = r6;
        goto L_0x010b;
    L_0x0156:
        r2 = r3.b;	 Catch:{ c -> 0x0026 }
        r14 = r2.length;	 Catch:{ c -> 0x0026 }
        r2 = r14 + r12;
        r12 = new int[r2];	 Catch:{ c -> 0x0026 }
        r2 = 0;
    L_0x015e:
        if (r2 >= r14) goto L_0x0173;
    L_0x0160:
        r15 = r3.a;	 Catch:{ c -> 0x0026 }
        r0 = r3.b;	 Catch:{ c -> 0x0026 }
        r16 = r0;
        r16 = r16[r2];	 Catch:{ c -> 0x0026 }
        r0 = r16;
        r15 = r15.d(r0, r13);	 Catch:{ c -> 0x0026 }
        r12[r2] = r15;	 Catch:{ c -> 0x0026 }
        r2 = r2 + 1;
        goto L_0x015e;
    L_0x0173:
        r2 = new com.google.zxing.pdf417.a.a.c;	 Catch:{ c -> 0x0026 }
        r13 = r3.a;	 Catch:{ c -> 0x0026 }
        r2.<init>(r13, r12);	 Catch:{ c -> 0x0026 }
        goto L_0x0150;
    L_0x017b:
        r2 = r2.c(r1);	 Catch:{ c -> 0x0026 }
        r2 = r2.b(r4);	 Catch:{ c -> 0x0026 }
        r2 = r2.b();	 Catch:{ c -> 0x0026 }
        r4 = r1;
        r1 = r2;
        r2 = r3;
        r3 = r5;
        goto L_0x00df;
    L_0x018d:
        r2 = 0;
        r2 = r1.a(r2);	 Catch:{ c -> 0x0026 }
        if (r2 != 0) goto L_0x0199;
    L_0x0194:
        r1 = com.google.zxing.c.a();	 Catch:{ c -> 0x0026 }
        throw r1;	 Catch:{ c -> 0x0026 }
    L_0x0199:
        r4 = r10.a;	 Catch:{ c -> 0x0026 }
        r2 = r4.a(r2);	 Catch:{ c -> 0x0026 }
        r1 = r1.c(r2);	 Catch:{ c -> 0x0026 }
        r2 = r3.c(r2);	 Catch:{ c -> 0x0026 }
        r3 = 2;
        r3 = new com.google.zxing.pdf417.a.a.c[r3];	 Catch:{ c -> 0x0026 }
        r4 = 0;
        r3[r4] = r1;	 Catch:{ c -> 0x0026 }
        r1 = 1;
        r3[r1] = r2;	 Catch:{ c -> 0x0026 }
        r1 = 0;
        r1 = r3[r1];	 Catch:{ c -> 0x0026 }
        r2 = 1;
        r2 = r3[r2];	 Catch:{ c -> 0x0026 }
        r3 = r10.a(r1);	 Catch:{ c -> 0x0026 }
        r2 = r10.a(r2, r1, r3);	 Catch:{ c -> 0x0026 }
        r1 = 0;
    L_0x01bf:
        r4 = r3.length;	 Catch:{ c -> 0x0026 }
        if (r1 >= r4) goto L_0x01ee;
    L_0x01c2:
        r0 = r19;
        r4 = r0.length;	 Catch:{ c -> 0x0026 }
        r4 = r4 + -1;
        r5 = r10.a;	 Catch:{ c -> 0x0026 }
        r6 = r3[r1];	 Catch:{ c -> 0x0026 }
        if (r6 != 0) goto L_0x01d3;
    L_0x01cd:
        r1 = new java.lang.IllegalArgumentException;	 Catch:{ c -> 0x0026 }
        r1.<init>();	 Catch:{ c -> 0x0026 }
        throw r1;	 Catch:{ c -> 0x0026 }
    L_0x01d3:
        r5 = r5.c;	 Catch:{ c -> 0x0026 }
        r5 = r5[r6];	 Catch:{ c -> 0x0026 }
        r4 = r4 - r5;
        if (r4 >= 0) goto L_0x01df;
    L_0x01da:
        r1 = com.google.zxing.c.a();	 Catch:{ c -> 0x0026 }
        throw r1;	 Catch:{ c -> 0x0026 }
    L_0x01df:
        r5 = r10.a;	 Catch:{ c -> 0x0026 }
        r6 = r19[r4];	 Catch:{ c -> 0x0026 }
        r11 = r2[r1];	 Catch:{ c -> 0x0026 }
        r5 = r5.c(r6, r11);	 Catch:{ c -> 0x0026 }
        r19[r4] = r5;	 Catch:{ c -> 0x0026 }
        r1 = r1 + 1;
        goto L_0x01bf;
    L_0x01ee:
        r1 = r3.length;	 Catch:{ c -> 0x0026 }
        goto L_0x0072;
    L_0x01f1:
        r2 = 0;
        r2 = r19[r2];	 Catch:{ c -> 0x0026 }
        r0 = r19;
        r3 = r0.length;	 Catch:{ c -> 0x0026 }
        if (r2 <= r3) goto L_0x01fe;
    L_0x01f9:
        r1 = com.google.zxing.e.a();	 Catch:{ c -> 0x0026 }
        throw r1;	 Catch:{ c -> 0x0026 }
    L_0x01fe:
        if (r2 != 0) goto L_0x020c;
    L_0x0200:
        r0 = r19;
        r2 = r0.length;	 Catch:{ c -> 0x0026 }
        if (r7 >= r2) goto L_0x0226;
    L_0x0205:
        r2 = 0;
        r0 = r19;
        r3 = r0.length;	 Catch:{ c -> 0x0026 }
        r3 = r3 - r7;
        r19[r2] = r3;	 Catch:{ c -> 0x0026 }
    L_0x020c:
        r2 = java.lang.String.valueOf(r18);	 Catch:{ c -> 0x0026 }
        r0 = r19;
        r2 = com.google.zxing.pdf417.a.e.a(r0, r2);	 Catch:{ c -> 0x0026 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ c -> 0x0026 }
        r2.e = r1;	 Catch:{ c -> 0x0026 }
        r0 = r20;
        r1 = r0.length;	 Catch:{ c -> 0x0026 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ c -> 0x0026 }
        r2.f = r1;	 Catch:{ c -> 0x0026 }
        return r2;
    L_0x0226:
        r1 = com.google.zxing.e.a();	 Catch:{ c -> 0x0026 }
        throw r1;	 Catch:{ c -> 0x0026 }
    L_0x022b:
        r1 = 0;
    L_0x022c:
        r2 = r9.length;
        if (r1 >= r2) goto L_0x0251;
    L_0x022f:
        r2 = r9[r1];
        r3 = r22[r1];
        r3 = r3.length;
        r3 = r3 + -1;
        if (r2 >= r3) goto L_0x0241;
    L_0x0238:
        r2 = r9[r1];
        r2 = r2 + 1;
        r9[r1] = r2;
        r1 = r8;
        goto L_0x0007;
    L_0x0241:
        r2 = 0;
        r9[r1] = r2;
        r2 = r9.length;
        r2 = r2 + -1;
        if (r1 != r2) goto L_0x024e;
    L_0x0249:
        r1 = com.google.zxing.c.a();
        throw r1;
    L_0x024e:
        r1 = r1 + 1;
        goto L_0x022c;
    L_0x0251:
        r1 = r8;
        goto L_0x0007;
    L_0x0254:
        r1 = com.google.zxing.c.a();
        throw r1;
    L_0x0259:
        r4 = r2;
        r2 = r1;
        goto L_0x00d2;
        */
    }

    private static boolean a(g gVar, int i) {
        return i >= 0 && i <= gVar.d + 1;
    }

    private static int[] a(int i) {
        int[] iArr = new int[8];
        int i2 = 0;
        int i3 = R.styleable.Toolbar_contentInsetLeft;
        while (true) {
            if ((i & 1) != i2) {
                i2 = i & 1;
                i3--;
                if (i3 < 0) {
                    return iArr;
                }
            }
            iArr[i3] = iArr[i3] + 1;
            i >>= 1;
        }
    }

    private static d a(b bVar, int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
        int i7;
        Object obj;
        int i8 = 0;
        int i9 = z ? -1 : 1;
        boolean z2 = z;
        int i10 = i3;
        loop0:
        while (i8 < 2) {
            i7 = i10;
            while (true) {
                if (((!z2 || i7 < i) && (z2 || i7 >= i2)) || z2 != bVar.a(i7, i4)) {
                    break;
                } else if (Math.abs(i3 - i7) > 2) {
                    break loop0;
                } else {
                    i7 += i9;
                }
            }
            i9 = -i9;
            if (z2) {
                obj = null;
            } else {
                obj = 1;
            }
            i8++;
            Object obj2 = obj;
            i10 = i7;
        }
        i3 = i10;
        int[] iArr = new int[8];
        i7 = 0;
        i10 = z ? 1 : -1;
        int i11 = i3;
        boolean z3 = z;
        while (true) {
            if (((!z || i11 >= i2) && (z || i11 < i)) || i7 >= 8) {
                break;
            } else if (bVar.a(i11, i4) == z3) {
                iArr[i7] = iArr[i7] + 1;
                i11 += i10;
            } else {
                Object obj3 = !z3 ? 1 : null;
                i7++;
            }
        }
        if (i7 != 8) {
            if ((!(z && i11 == i2) && (z || i11 != i)) || i7 != 7) {
                iArr = null;
            }
        }
        if (iArr == null) {
            return null;
        }
        i9 = com.google.zxing.pdf417.a.a(iArr);
        if (z) {
            i7 = i3 + i9;
        } else {
            for (i10 = 0; i10 < iArr.length / 2; i10++) {
                i7 = iArr[i10];
                iArr[i10] = iArr[(iArr.length - 1) - i10];
                iArr[(iArr.length - 1) - i10] = i7;
            }
            i7 = i3;
            i3 -= i9;
        }
        if (i5 - 2 > i9 || i9 > i6 + 2) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj == null) {
            return null;
        }
        i8 = j.a(iArr);
        i9 = com.google.zxing.pdf417.a.a(i8);
        if (i9 == -1) {
            return null;
        }
        iArr = a(i8);
        return new d(i3, i7, ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9, i9);
    }
}
