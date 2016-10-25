package com.google.zxing.qrcode.a;

import com.google.zxing.common.a.a;
import com.google.zxing.common.b;
import com.google.zxing.common.f;
import com.google.zxing.e;
import com.google.zxing.j;
import com.google.zxing.o;
import com.google.zxing.p;
import com.google.zxing.qrcode.decoder.h;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: Detector.java
public final class c {
    public final b a;
    public p b;

    public c(b bVar) {
        this.a = bVar;
    }

    private float a(o oVar, o oVar2) {
        float a = a((int) oVar.a, (int) oVar.b, (int) oVar2.a, (int) oVar2.b);
        float a2 = a((int) oVar2.a, (int) oVar2.b, (int) oVar.a, (int) oVar.b);
        if (Float.isNaN(a)) {
            return a2 / 7.0f;
        }
        return Float.isNaN(a2) ? a / 7.0f : (a + a2) / 14.0f;
    }

    private float a(int i, int i2, int i3, int i4) {
        float f;
        int i5;
        int i6 = 0;
        float b = b(i, i2, i3, i4);
        int i7 = i - (i3 - i);
        if (i7 < 0) {
            f = ((float) i) / ((float) (i - i7));
            i5 = 0;
        } else if (i7 >= this.a.a) {
            f = ((float) ((this.a.a - 1) - i)) / ((float) (i7 - i));
            i5 = this.a.a - 1;
        } else {
            i5 = i7;
            f = 1.0f;
        }
        i7 = (int) (((float) i2) - (f * ((float) (i4 - i2))));
        if (i7 < 0) {
            f = ((float) i2) / ((float) (i2 - i7));
        } else if (i7 >= this.a.b) {
            f = ((float) ((this.a.b - 1) - i2)) / ((float) (i7 - i2));
            i6 = this.a.b - 1;
        } else {
            i6 = i7;
            f = 1.0f;
        }
        return (b(i, i2, (int) ((f * ((float) (i5 - i))) + ((float) i)), i6) + b) - 1.0f;
    }

    private float b(int i, int i2, int i3, int i4) {
        if (Math.abs(i4 - i2) > Math.abs(i3 - i)) {
            int i5 = 1;
        } else {
            Object obj = null;
        }
        if (obj == null) {
            int i6 = i4;
            i4 = i3;
            i3 = i6;
            int i7 = i2;
            i2 = i;
            i = i7;
        }
        int abs = Math.abs(i4 - i2);
        int abs2 = Math.abs(i3 - i);
        int i8 = (-abs) / 2;
        int i9 = i2 < i4 ? 1 : -1;
        int i10 = i < i3 ? 1 : -1;
        int i11 = 0;
        int i12 = i4 + i9;
        int i13 = i2;
        int i14 = i8;
        i8 = i;
        while (i13 != i12) {
            int i15;
            int i16;
            int i17;
            if (obj != null) {
                i15 = i8;
            } else {
                i15 = i13;
            }
            if (obj != null) {
                i16 = i13;
            } else {
                i16 = i8;
            }
            if ((i11 == 1 ? 1 : null) != this.a.a(i15, i16)) {
                i16 = i11;
            } else if (i11 == 2) {
                return a.a(i13, i8, i2, i);
            } else {
                i16 = i11 + 1;
            }
            i11 = i14 + abs2;
            if (i11 > 0) {
                if (i8 == i3) {
                    i10 = i16;
                    break;
                }
                i17 = i8 + i10;
                i8 = i11 - abs;
            } else {
                i17 = i8;
                i8 = i11;
            }
            i13 += i9;
            i11 = i16;
            i14 = i8;
            i8 = i17;
        }
        i10 = i11;
        return i10 == 2 ? a.a(i4 + i9, i3, i2, i) : Float.NaN;
    }

    public final f a(f fVar) throws j, e {
        o oVar = fVar.b;
        o oVar2 = fVar.c;
        o oVar3 = fVar.a;
        float a = (a(oVar, oVar2) + a(oVar, oVar3)) / 2.0f;
        if (a < 1.0f) {
            throw j.a();
        }
        int i;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        int a2 = ((a.a(o.a(oVar, oVar2) / a) + a.a(o.a(oVar, oVar3) / a)) / 2) + 7;
        switch (a2 & 3) {
            case SpdyAgent.ACCS_TEST_SERVER:
                i = a2 + 1;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                i = a2 - 1;
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                throw j.a();
            default:
                i = a2;
                break;
        }
        h a3 = h.a(i);
        int a4 = a3.a() - 7;
        if (a3.b.length > 0) {
            float f6 = 1.0f - (3.0f / ((float) a4));
            int i2 = (int) (((((oVar2.a - oVar.a) + oVar3.a) - oVar.a) * f6) + oVar.a);
            int i3 = (int) (oVar.b + (f6 * (((oVar2.b - oVar.b) + oVar3.b) - oVar.b)));
            int i4 = 4;
            while (i4 <= 16) {
                int max;
                int min;
                a2 = (int) (((float) i4) * a);
                try {
                    max = Math.max(0, i2 - a2);
                    min = Math.min(this.a.a - 1, i2 + a2);
                } catch (j e) {
                    i4 <<= 1;
                }
                if (((float) (min - max)) < 3.0f * a) {
                    throw j.a();
                }
                continue;
                int max2 = Math.max(0, i3 - a2);
                int min2 = Math.min(this.a.b - 1, a2 + i3);
                if (((float) (min2 - max2)) < 3.0f * a) {
                    throw j.a();
                }
                a aVar;
                a aVar2;
                b bVar = new b(this.a, max, max2, min - max, min2 - max2, a, this.b);
                max2 = bVar.c;
                min2 = bVar.f;
                int i5 = max2 + bVar.e;
                int i6 = bVar.d + (min2 / 2);
                int[] iArr = new int[3];
                for (min = 0; min < min2; min++) {
                    a a5;
                    if ((min & 1) == 0) {
                        a4 = (min + 1) / 2;
                    } else {
                        a4 = -((min + 1) / 2);
                    }
                    int i7 = i6 + a4;
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    max = max2;
                    while (max < i5 && !bVar.a.a(max, i7)) {
                        max++;
                    }
                    a4 = 0;
                    while (max < i5) {
                        if (bVar.a.a(max, i7)) {
                            if (a4 != 1) {
                                if (a4 == 2) {
                                    if (bVar.a(iArr)) {
                                        a5 = bVar.a(iArr, i7, max);
                                        if (a5 != null) {
                                            aVar = a5;
                                            aVar2 = aVar;
                                            f = ((float) i) - 3.5f;
                                            if (r20 == null) {
                                                f2 = r20.a;
                                                f3 = r20.b;
                                                f4 = f - 3.0f;
                                                f5 = f4;
                                            } else {
                                                f2 = (oVar2.a - oVar.a) + oVar3.a;
                                                f3 = (oVar2.b - oVar.b) + oVar3.b;
                                                f4 = f;
                                                f5 = f;
                                            }
                                            return new f(com.google.zxing.common.h.a().a(this.a, i, i, com.google.zxing.common.j.a(3.5f, 3.5f, f, 3.5f, f4, f5, 3.5f, f, oVar.a, oVar.b, oVar2.a, oVar2.b, f2, f3, oVar3.a, oVar3.b)), r20 != null ? new o[]{oVar3, oVar, oVar2} : new o[]{oVar3, oVar, oVar2, r20});
                                        }
                                    }
                                    iArr[0] = iArr[2];
                                    iArr[1] = 1;
                                    iArr[2] = 0;
                                    a4 = 1;
                                } else {
                                    a4++;
                                    iArr[a4] = iArr[a4] + 1;
                                }
                            }
                            iArr[a4] = iArr[a4] + 1;
                        } else {
                            if (a4 == 1) {
                                a4++;
                            }
                            iArr[a4] = iArr[a4] + 1;
                        }
                        max++;
                    }
                    if (bVar.a(iArr)) {
                        a5 = bVar.a(iArr, i7, i5);
                        if (a5 != null) {
                            aVar = a5;
                            aVar2 = aVar;
                            f = ((float) i) - 3.5f;
                            if (r20 == null) {
                                f2 = (oVar2.a - oVar.a) + oVar3.a;
                                f3 = (oVar2.b - oVar.b) + oVar3.b;
                                f4 = f;
                                f5 = f;
                            } else {
                                f2 = r20.a;
                                f3 = r20.b;
                                f4 = f - 3.0f;
                                f5 = f4;
                            }
                            if (r20 != null) {
                            }
                            return new f(com.google.zxing.common.h.a().a(this.a, i, i, com.google.zxing.common.j.a(3.5f, 3.5f, f, 3.5f, f4, f5, 3.5f, f, oVar.a, oVar.b, oVar2.a, oVar2.b, f2, f3, oVar3.a, oVar3.b)), r20 != null ? new o[]{oVar3, oVar, oVar2} : new o[]{oVar3, oVar, oVar2, r20});
                        }
                    }
                }
                if (bVar.b.isEmpty()) {
                    throw j.a();
                }
                aVar = (a) bVar.b.get(0);
                aVar2 = aVar;
                f = ((float) i) - 3.5f;
                if (r20 == null) {
                    f2 = r20.a;
                    f3 = r20.b;
                    f4 = f - 3.0f;
                    f5 = f4;
                } else {
                    f2 = (oVar2.a - oVar.a) + oVar3.a;
                    f3 = (oVar2.b - oVar.b) + oVar3.b;
                    f4 = f;
                    f5 = f;
                }
                if (r20 != null) {
                }
                return new f(com.google.zxing.common.h.a().a(this.a, i, i, com.google.zxing.common.j.a(3.5f, 3.5f, f, 3.5f, f4, f5, 3.5f, f, oVar.a, oVar.b, oVar2.a, oVar2.b, f2, f3, oVar3.a, oVar3.b)), r20 != null ? new o[]{oVar3, oVar, oVar2} : new o[]{oVar3, oVar, oVar2, r20});
            }
        }
        Object obj = null;
        f = ((float) i) - 3.5f;
        if (r20 == null) {
            f2 = (oVar2.a - oVar.a) + oVar3.a;
            f3 = (oVar2.b - oVar.b) + oVar3.b;
            f4 = f;
            f5 = f;
        } else {
            f2 = r20.a;
            f3 = r20.b;
            f4 = f - 3.0f;
            f5 = f4;
        }
        if (r20 != null) {
        }
        return new f(com.google.zxing.common.h.a().a(this.a, i, i, com.google.zxing.common.j.a(3.5f, 3.5f, f, 3.5f, f4, f5, 3.5f, f, oVar.a, oVar.b, oVar2.a, oVar2.b, f2, f3, oVar3.a, oVar3.b)), r20 != null ? new o[]{oVar3, oVar, oVar2} : new o[]{oVar3, oVar, oVar2, r20});
    }
}
