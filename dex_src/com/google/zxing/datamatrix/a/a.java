package com.google.zxing.datamatrix.a;

import com.google.zxing.common.b;
import com.google.zxing.e;

// compiled from: BitMatrixParser.java
final class a {
    final b a;
    final b b;
    final f c;

    a(b bVar) throws e {
        int i = bVar.b;
        if (i < 8 || i > 144 || (i & 1) != 0) {
            throw e.a();
        }
        this.c = f.a(bVar.b, bVar.a);
        this.a = a(bVar);
        this.b = new b(this.a.a, this.a.b);
    }

    final boolean a(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        if (i < 0) {
            i5 = i + i3;
            i6 = (4 - ((i3 + 4) & 7)) + i2;
        } else {
            i6 = i2;
            i5 = i;
        }
        if (i6 < 0) {
            i6 += i4;
            i5 += 4 - ((i4 + 4) & 7);
        }
        this.b.b(i6, i5);
        return this.a.a(i6, i5);
    }

    final int b(int i, int i2, int i3, int i4) {
        int i5 = 0;
        if (a(i - 2, i2 - 2, i3, i4)) {
            i5 = 1;
        }
        i5 <<= 1;
        if (a(i - 2, i2 - 1, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        if (a(i - 1, i2 - 2, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        if (a(i - 1, i2 - 1, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        if (a(i - 1, i2, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        if (a(i, i2 - 2, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        if (a(i, i2 - 1, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        return a(i, i2, i3, i4) ? i5 | 1 : i5;
    }

    private b a(b bVar) {
        int i = this.c.b;
        int i2 = this.c.c;
        if (bVar.b != i) {
            throw new IllegalArgumentException("Dimension of bitMarix must match the version size");
        }
        int i3 = this.c.d;
        int i4 = this.c.e;
        int i5 = i / i3;
        int i6 = i2 / i4;
        b bVar2 = new b(i6 * i4, i5 * i3);
        for (int i7 = 0; i7 < i5; i7++) {
            int i8 = i7 * i3;
            for (int i9 = 0; i9 < i6; i9++) {
                int i10 = i9 * i4;
                for (i2 = 0; i2 < i3; i2++) {
                    int i11 = (((i3 + 2) * i7) + 1) + i2;
                    int i12 = i8 + i2;
                    for (i = 0; i < i4; i++) {
                        if (bVar.a((((i4 + 2) * i9) + 1) + i, i11)) {
                            bVar2.b(i10 + i, i12);
                        }
                    }
                }
            }
        }
        return bVar2;
    }
}
