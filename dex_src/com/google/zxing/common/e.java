package com.google.zxing.common;

import com.google.zxing.j;

// compiled from: DefaultGridSampler.java
public final class e extends h {
    public final b a(b bVar, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws j {
        return a(bVar, i, i2, j.a(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16));
    }

    public final b a(b bVar, int i, int i2, j jVar) throws j {
        if (i <= 0 || i2 <= 0) {
            throw j.a();
        }
        b bVar2 = new b(i, i2);
        float[] fArr = new float[(i * 2)];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4;
            Object obj;
            int length = fArr.length;
            float f = 0.5f + ((float) i3);
            for (i4 = 0; i4 < length; i4 += 2) {
                fArr[i4] = ((float) (i4 / 2)) + 0.5f;
                fArr[i4 + 1] = f;
            }
            int length2 = fArr.length;
            float f2 = jVar.a;
            float f3 = jVar.b;
            float f4 = jVar.c;
            float f5 = jVar.d;
            float f6 = jVar.e;
            float f7 = jVar.f;
            float f8 = jVar.g;
            float f9 = jVar.h;
            float f10 = jVar.i;
            for (i4 = 0; i4 < length2; i4 += 2) {
                float f11 = fArr[i4];
                float f12 = fArr[i4 + 1];
                float f13 = ((f4 * f11) + (f7 * f12)) + f10;
                fArr[i4] = (((f2 * f11) + (f5 * f12)) + f8) / f13;
                fArr[i4 + 1] = (((f11 * f3) + (f12 * f6)) + f9) / f13;
            }
            int i5 = bVar.a;
            int i6 = bVar.b;
            i4 = 1;
            length2 = 0;
            while (length2 < fArr.length && r2 != null) {
                int i7 = (int) fArr[length2];
                int i8 = (int) fArr[length2 + 1];
                if (i7 >= -1 && i7 <= i5 && i8 >= -1 && i8 <= i6) {
                    obj = null;
                    if (i7 == -1) {
                        fArr[length2] = 0.0f;
                        obj = 1;
                    } else if (i7 == i5) {
                        fArr[length2] = (float) (i5 - 1);
                        obj = 1;
                    }
                    if (i8 == -1) {
                        fArr[length2 + 1] = 0.0f;
                        obj = 1;
                    } else if (i8 == i6) {
                        fArr[length2 + 1] = (float) (i6 - 1);
                        obj = 1;
                    }
                    length2 += 2;
                }
                throw j.a();
            }
            i4 = 1;
            length2 = fArr.length - 2;
            while (length2 >= 0 && r2 != null) {
                i7 = (int) fArr[length2];
                i8 = (int) fArr[length2 + 1];
                if (i7 >= -1 && i7 <= i5 && i8 >= -1 && i8 <= i6) {
                    obj = null;
                    if (i7 == -1) {
                        fArr[length2] = 0.0f;
                        obj = 1;
                    } else if (i7 == i5) {
                        fArr[length2] = (float) (i5 - 1);
                        obj = 1;
                    }
                    if (i8 == -1) {
                        fArr[length2 + 1] = 0.0f;
                        obj = 1;
                    } else if (i8 == i6) {
                        fArr[length2 + 1] = (float) (i6 - 1);
                        obj = 1;
                    }
                    length2 -= 2;
                }
                throw j.a();
            }
            i4 = 0;
            while (i4 < length) {
                try {
                    if (bVar.a((int) fArr[i4], (int) fArr[i4 + 1])) {
                        bVar2.b(i4 / 2, i3);
                    }
                    i4 += 2;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw j.a();
                }
            }
        }
        return bVar2;
    }
}
