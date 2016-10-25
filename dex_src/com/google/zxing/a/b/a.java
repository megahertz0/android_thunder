package com.google.zxing.a.b;

import com.google.zxing.common.b;
import com.google.zxing.common.b.c;
import com.google.zxing.common.b.e;
import com.google.zxing.common.h;
import com.google.zxing.j;
import com.google.zxing.o;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: Detector.java
public final class a {
    private static final int[] g;
    private final b a;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private int f;

    // compiled from: Detector.java
    static final class a {
        final int a;
        final int b;

        final o a() {
            return new o((float) this.a, (float) this.b);
        }

        a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public final String toString() {
            return new StringBuilder("<").append(this.a).append(' ').append(this.b).append('>').toString();
        }
    }

    public a(b bVar) {
        this.a = bVar;
    }

    public final com.google.zxing.a.a a(boolean z) throws j {
        a a = a();
        this.e = 1;
        a aVar = a;
        boolean z2 = true;
        a aVar2 = a;
        a aVar3 = a;
        while (this.e < 9) {
            a a2 = a(aVar3, z2, 1, -1);
            a a3 = a(aVar2, z2, 1, 1);
            a a4 = a(aVar, z2, -1, 1);
            a a5 = a(a, z2, -1, -1);
            if (this.e > 2) {
                float b = (b(a5, a2) * ((float) this.e)) / (b(a, aVar3) * ((float) (this.e + 2)));
                if (((double) b) >= 0.75d && ((double) b) <= 1.25d) {
                    Object obj;
                    a aVar4 = new a(a2.a - 3, a2.b + 3);
                    a aVar5 = new a(a3.a - 3, a3.b - 3);
                    a aVar6 = new a(a4.a + 3, a4.b - 3);
                    a aVar7 = new a(a5.a + 3, a5.b + 3);
                    int a6 = a(aVar7, aVar4);
                    if (a6 != 0 && a(aVar4, aVar5) == a6 && a(aVar5, aVar6) == a6 && a(aVar6, aVar7) == a6) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj == null) {
                        break;
                    }
                }
                break;
            }
            Object obj2;
            if (z2) {
                obj2 = null;
            } else {
                obj2 = 1;
            }
            this.e++;
            aVar = a4;
            z2 = r2;
            aVar2 = a3;
            aVar3 = a2;
            a = a5;
        }
        if (this.e == 5 || this.e == 7) {
            this.b = this.e == 5;
            o oVar = new o(((float) aVar3.a) + 0.5f, ((float) aVar3.b) - 0.5f);
            o oVar2 = new o(((float) aVar2.a) + 0.5f, ((float) aVar2.b) + 0.5f);
            o oVar3 = new o(((float) aVar.a) - 0.5f, ((float) aVar.b) + 0.5f);
            o oVar4 = new o(((float) a.a) - 0.5f, ((float) a.b) - 0.5f);
            o[] a7 = a(new o[]{oVar, oVar2, oVar3, oVar4}, (float) ((this.e * 2) - 3), (float) (this.e * 2));
            if (z) {
                o oVar5 = a7[0];
                a7[0] = a7[2];
                a7[2] = oVar5;
            }
            if (a(a7[0]) && a(a7[1]) && a(a7[2]) && a(a7[3])) {
                int i;
                int i2;
                int i3;
                int[] iArr = new int[]{a(a7[0], a7[1], this.e * 2), a(a7[1], a7[2], this.e * 2), a(a7[2], a7[3], this.e * 2), a(a7[3], a7[0], this.e * 2)};
                int i4 = 0;
                for (i = 0; i < 4; i++) {
                    i2 = iArr[i];
                    i4 = (i4 << 3) + ((i2 & 1) + ((i2 >> (i3 - 2)) << 1));
                }
                i4 = (i4 >> 1) + ((i4 & 1) << 11);
                for (i = 0; i < 4; i++) {
                    if (Integer.bitCount(g[i] ^ i4) <= 2) {
                        this.f = i;
                        long j = 0;
                        for (i3 = 0; i3 < 4; i3++) {
                            i2 = iArr[(this.f + i3) % 4];
                            if (this.b) {
                                j = (j << 7) + ((long) ((i2 >> 1) & 127));
                            } else {
                                j = (j << 10) + ((long) (((i2 >> 1) & 31) + ((i2 >> 2) & 992)));
                            }
                        }
                        i = a(j, this.b);
                        if (this.b) {
                            this.c = (i >> 6) + 1;
                            this.d = (i & 63) + 1;
                        } else {
                            this.c = (i >> 11) + 1;
                            this.d = (i & 2047) + 1;
                        }
                        b bVar = this.a;
                        oVar2 = a7[this.f % 4];
                        o oVar6 = a7[(this.f + 1) % 4];
                        o oVar7 = a7[(this.f + 2) % 4];
                        o oVar8 = a7[(this.f + 3) % 4];
                        h a8 = h.a();
                        i3 = b();
                        float f = (((float) i3) / 2.0f) - ((float) this.e);
                        float f2 = (((float) i3) / 2.0f) + ((float) this.e);
                        return new com.google.zxing.a.a(a8.a(bVar, i3, i3, f, f, f2, f, f2, f2, f, f2, oVar2.a, oVar2.b, oVar6.a, oVar6.b, oVar7.a, oVar7.b, oVar8.a, oVar8.b), a(a7, (float) (this.e * 2), (float) b()), this.b, this.d, this.c);
                    }
                }
                throw j.a();
            }
            throw j.a();
        }
        throw j.a();
    }

    static {
        g = new int[]{3808, 476, 2107, 1799};
    }

    private static int a(long j, boolean z) throws j {
        int i;
        int i2;
        int i3 = 0;
        if (z) {
            i = R.styleable.Toolbar_contentInsetLeft;
            i2 = XZBDevice.DOWNLOAD_LIST_RECYCLE;
        } else {
            i = 10;
            i2 = 4;
        }
        int i4 = i - i2;
        int[] iArr = new int[i];
        for (i--; i >= 0; i--) {
            iArr[i] = ((int) j) & 15;
            j >>= 4;
        }
        try {
            new c(com.google.zxing.common.b.a.d).a(iArr, i4);
            int i5 = 0;
            while (i3 < i2) {
                i5 = (i5 << 4) + iArr[i3];
                i3++;
            }
            return i5;
        } catch (e e) {
            throw j.a();
        }
    }

    private a a() {
        o oVar;
        o oVar2;
        o oVar3;
        o oVar4;
        int i;
        int i2;
        try {
            o[] a = new com.google.zxing.common.a.b(this.a).a();
            oVar = a[0];
            oVar2 = a[1];
            oVar3 = a[2];
            oVar4 = a[3];
        } catch (j e) {
            i = this.a.a / 2;
            i2 = this.a.b / 2;
            oVar = a(new a(i + 7, i2 - 7), false, 1, -1).a();
            oVar2 = a(new a(i + 7, i2 + 7), false, 1, 1).a();
            oVar3 = a(new a(i - 7, i2 + 7), false, -1, 1).a();
            oVar4 = a(new a(i - 7, i2 - 7), false, -1, -1).a();
        }
        i2 = com.google.zxing.common.a.a.a((((oVar.a + oVar4.a) + oVar2.a) + oVar3.a) / 4.0f);
        i = com.google.zxing.common.a.a.a((((oVar4.b + oVar.b) + oVar2.b) + oVar3.b) / 4.0f);
        try {
            o[] a2 = new com.google.zxing.common.a.b(this.a, 15, i2, i).a();
            oVar = a2[0];
            oVar2 = a2[1];
            oVar3 = a2[2];
            oVar4 = a2[3];
        } catch (j e2) {
            oVar = a(new a(i2 + 7, i - 7), false, 1, -1).a();
            oVar2 = a(new a(i2 + 7, i + 7), false, 1, 1).a();
            oVar3 = a(new a(i2 - 7, i + 7), false, -1, 1).a();
            oVar4 = a(new a(i2 - 7, i - 7), false, -1, -1).a();
        }
        return new a(com.google.zxing.common.a.a.a((((oVar.a + oVar4.a) + oVar2.a) + oVar3.a) / 4.0f), com.google.zxing.common.a.a.a((((oVar4.b + oVar.b) + oVar2.b) + oVar3.b) / 4.0f));
    }

    private int a(o oVar, o oVar2, int i) {
        int i2 = 0;
        float a = com.google.zxing.common.a.a.a(oVar.a, oVar.b, oVar2.a, oVar2.b);
        float f = a / ((float) i);
        float f2 = oVar.a;
        float f3 = oVar.b;
        float f4 = ((oVar2.a - oVar.a) * f) / a;
        f = (f * (oVar2.b - oVar.b)) / a;
        for (int i3 = 0; i3 < i; i3++) {
            if (this.a.a(com.google.zxing.common.a.a.a((((float) i3) * f4) + f2), com.google.zxing.common.a.a.a((((float) i3) * f) + f3))) {
                i2 |= 1 << ((i - i3) - 1);
            }
        }
        return i2;
    }

    private int a(a aVar, a aVar2) {
        float b = b(aVar, aVar2);
        float f = ((float) (aVar2.a - aVar.a)) / b;
        float f2 = ((float) (aVar2.b - aVar.b)) / b;
        float f3 = (float) aVar.a;
        float f4 = (float) aVar.b;
        boolean a = this.a.a(aVar.a, aVar.b);
        int i = 0;
        float f5 = f3;
        f3 = f4;
        for (int i2 = 0; ((float) i2) < b; i2++) {
            f5 += f;
            f3 += f2;
            if (this.a.a(com.google.zxing.common.a.a.a(f5), com.google.zxing.common.a.a.a(f3)) != a) {
                i++;
            }
        }
        float f6 = ((float) i) / b;
        if (f6 > 0.1f && f6 < 0.9f) {
            return 0;
        }
        return ((f6 > 0.1f ? 1 : (f6 == 0.1f ? 0 : -1)) <= 0 ? 1 : null) == a ? 1 : -1;
    }

    private static o[] a(o[] oVarArr, float f, float f2) {
        float f3 = f2 / (2.0f * f);
        float f4 = oVarArr[0].a - oVarArr[2].a;
        float f5 = oVarArr[0].b - oVarArr[2].b;
        float f6 = (oVarArr[0].a + oVarArr[2].a) / 2.0f;
        float f7 = (oVarArr[0].b + oVarArr[2].b) / 2.0f;
        o oVar = new o((f3 * f4) + f6, (f3 * f5) + f7);
        o oVar2 = new o(f6 - (f4 * f3), f7 - (f5 * f3));
        f4 = oVarArr[1].a - oVarArr[3].a;
        f5 = oVarArr[1].b - oVarArr[3].b;
        f6 = (oVarArr[1].a + oVarArr[3].a) / 2.0f;
        f7 = (oVarArr[1].b + oVarArr[3].b) / 2.0f;
        o oVar3 = new o((f3 * f4) + f6, (f3 * f5) + f7);
        o oVar4 = new o(f6 - (f4 * f3), f7 - (f3 * f5));
        return new o[]{oVar, oVar3, oVar2, oVar4};
    }

    private boolean a(int i, int i2) {
        return i >= 0 && i < this.a.a && i2 > 0 && i2 < this.a.b;
    }

    private int b() {
        if (this.b) {
            return (this.c * 4) + 11;
        }
        return this.c <= 4 ? (this.c * 4) + 15 : ((this.c * 4) + ((((this.c - 4) / 8) + 1) * 2)) + 15;
    }

    private a a(a aVar, boolean z, int i, int i2) {
        int i3 = aVar.a + i;
        int i4 = aVar.b + i2;
        while (a(i3, i4) && this.a.a(i3, i4) == z) {
            i3 += i;
            i4 += i2;
        }
        i3 -= i;
        i4 -= i2;
        while (a(i3, i4) && this.a.a(i3, i4) == z) {
            i3 += i;
        }
        int i5 = i3 - i;
        i3 = i4;
        while (a(i5, i3) && this.a.a(i5, i3) == z) {
            i3 += i2;
        }
        return new a(i5, i3 - i2);
    }

    private boolean a(o oVar) {
        return a(com.google.zxing.common.a.a.a(oVar.a), com.google.zxing.common.a.a.a(oVar.b));
    }

    private static float b(a aVar, a aVar2) {
        return com.google.zxing.common.a.a.a(aVar.a, aVar.b, aVar2.a, aVar2.b);
    }
}
