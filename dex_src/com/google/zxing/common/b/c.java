package com.google.zxing.common.b;

// compiled from: ReedSolomonDecoder.java
public final class c {
    private final a a;

    public c(a aVar) {
        this.a = aVar;
    }

    public final void a(int[] iArr, int i) throws e {
        int i2;
        int i3 = 0;
        b bVar = new b(this.a, iArr);
        int[] iArr2 = new int[i];
        int i4 = 1;
        for (i2 = 0; i2 < i; i2++) {
            int i5 = this.a.m + i2;
            int b = bVar.b(this.a.i[i5]);
            iArr2[(iArr2.length - 1) - i2] = b;
            if (b != 0) {
                i4 = 0;
            }
        }
        if (i4 == 0) {
            b bVar2;
            b bVar3 = new b(this.a, iArr2);
            b a = this.a.a(i, 1);
            if (a.a.length - 1 >= bVar3.a.length - 1) {
                bVar2 = bVar3;
                bVar3 = a;
                a = bVar2;
            }
            b bVar4 = this.a.j;
            bVar = a;
            a = this.a.k;
            while (bVar.a.length - 1 >= i / 2) {
                if (bVar.a()) {
                    throw new e("r_{i-1} was zero");
                }
                b bVar5 = this.a.j;
                i5 = this.a.b(bVar.a(bVar.a.length - 1));
                bVar2 = bVar5;
                bVar5 = bVar3;
                bVar3 = bVar2;
                while (bVar5.a.length - 1 >= bVar.a.length - 1 && !bVar5.a()) {
                    int length = (bVar5.a.length - 1) - (bVar.a.length - 1);
                    int c = this.a.c(bVar5.a(bVar5.a.length - 1), i5);
                    bVar3 = bVar3.a(this.a.a(length, c));
                    bVar5 = bVar5.a(bVar.a(length, c));
                }
                bVar3 = bVar3.b(a).a(bVar4);
                if (bVar5.a.length - 1 >= bVar.a.length - 1) {
                    throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
                }
                bVar4 = a;
                a = bVar3;
                bVar3 = bVar;
                bVar = bVar5;
            }
            i2 = a.a(0);
            if (i2 == 0) {
                throw new e("sigmaTilde(0) was zero");
            }
            i2 = this.a.b(i2);
            a = a.c(i2);
            bVar3 = bVar.c(i2);
            b[] bVarArr = new b[]{a, bVar3};
            a = bVarArr[0];
            bVar3 = bVarArr[1];
            int[] a2 = a(a);
            int[] a3 = a(bVar3, a2);
            while (i3 < a2.length) {
                int length2 = (iArr.length - 1) - this.a.a(a2[i3]);
                if (length2 < 0) {
                    throw new e("Bad error location");
                }
                iArr[length2] = a.b(iArr[length2], a3[i3]);
                i3++;
            }
        }
    }

    private int[] a(b bVar) throws e {
        int i = 0;
        int i2 = 1;
        int length = bVar.a.length - 1;
        if (length == 1) {
            return new int[]{bVar.a(1)};
        }
        int[] iArr = new int[length];
        while (i2 < this.a.l && i < length) {
            if (bVar.b(i2) == 0) {
                iArr[i] = this.a.b(i2);
                i++;
            }
            i2++;
        }
        if (i == length) {
            return iArr;
        }
        throw new e("Error locator degree does not match number of roots");
    }

    private int[] a(b bVar, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            int b = this.a.b(iArr[i]);
            int i2 = 1;
            int i3 = 0;
            while (i3 < length) {
                int c;
                if (i != i3) {
                    c = this.a.c(iArr[i3], b);
                    c = this.a.c(i2, (c & 1) == 0 ? c | 1 : c & -2);
                } else {
                    c = i2;
                }
                i3++;
                i2 = c;
            }
            iArr2[i] = this.a.c(bVar.b(b), this.a.b(i2));
            if (this.a.m != 0) {
                iArr2[i] = this.a.c(iArr2[i], b);
            }
        }
        return iArr2;
    }
}
