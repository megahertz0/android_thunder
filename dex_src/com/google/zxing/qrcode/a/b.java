package com.google.zxing.qrcode.a;

import com.google.zxing.p;
import java.util.ArrayList;
import java.util.List;

// compiled from: AlignmentPatternFinder.java
final class b {
    final com.google.zxing.common.b a;
    final List<a> b;
    final int c;
    final int d;
    final int e;
    final int f;
    private final float g;
    private final int[] h;
    private final p i;

    b(com.google.zxing.common.b bVar, int i, int i2, int i3, int i4, float f, p pVar) {
        this.a = bVar;
        this.b = new ArrayList(5);
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        this.g = f;
        this.h = new int[3];
        this.i = pVar;
    }

    private static float a(int[] iArr, int i) {
        return ((float) (i - iArr[2])) - (((float) iArr[1]) / 2.0f);
    }

    final boolean a(int[] iArr) {
        float f = this.g;
        float f2 = f / 2.0f;
        for (int i = 0; i < 3; i++) {
            if (Math.abs(f - ((float) iArr[i])) >= f2) {
                return false;
            }
        }
        return true;
    }

    final a a(int[] iArr, int i, int i2) {
        float f;
        int i3 = iArr[2] + (iArr[0] + iArr[1]);
        float a = a(iArr, i2);
        int i4 = (int) a;
        int i5 = iArr[1] * 2;
        com.google.zxing.common.b bVar = this.a;
        int i6 = bVar.b;
        int[] iArr2 = this.h;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        int i7 = i;
        while (i7 >= 0 && bVar.a(i4, i7) && iArr2[1] <= i5) {
            iArr2[1] = iArr2[1] + 1;
            i7--;
        }
        if (i7 < 0 || iArr2[1] > i5) {
            f = Float.NaN;
        } else {
            while (i7 >= 0 && !bVar.a(i4, i7) && iArr2[0] <= i5) {
                iArr2[0] = iArr2[0] + 1;
                i7--;
            }
            if (iArr2[0] > i5) {
                f = Float.NaN;
            } else {
                i7 = i + 1;
                while (i7 < i6 && bVar.a(i4, i7) && iArr2[1] <= i5) {
                    iArr2[1] = iArr2[1] + 1;
                    i7++;
                }
                if (i7 == i6 || iArr2[1] > i5) {
                    f = Float.NaN;
                } else {
                    while (i7 < i6 && !bVar.a(i4, i7) && iArr2[2] <= i5) {
                        iArr2[2] = iArr2[2] + 1;
                        i7++;
                    }
                    f = iArr2[2] > i5 ? Float.NaN : Math.abs(((iArr2[0] + iArr2[1]) + iArr2[2]) - i3) * 5 >= i3 * 2 ? Float.NaN : a(iArr2) ? a(iArr2, i7) : Float.NaN;
                }
            }
        }
        if (!Float.isNaN(f)) {
            float f2 = ((float) ((iArr[0] + iArr[1]) + iArr[2])) / 3.0f;
            for (a aVar : this.b) {
                Object obj;
                if (Math.abs(f - aVar.b) > f2 || Math.abs(a - aVar.a) > f2) {
                    obj = null;
                    continue;
                } else {
                    float abs = Math.abs(f2 - aVar.c);
                    if (abs <= 1.0f || abs <= aVar.c) {
                        obj = 1;
                        continue;
                    } else {
                        obj = null;
                        continue;
                    }
                }
                if (obj != null) {
                    return new a((aVar.a + a) / 2.0f, (f + aVar.b) / 2.0f, (aVar.c + f2) / 2.0f);
                }
            }
            this.b.add(new a(a, f, f2));
        }
        return null;
    }
}
