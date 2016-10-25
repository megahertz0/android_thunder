package com.google.zxing.pdf417.a.a;

import com.google.zxing.c;

// compiled from: ErrorCorrection.java
public final class a {
    public final b a;

    public a() {
        this.a = b.a;
    }

    public final int[] a(c cVar, c cVar2, int[] iArr) {
        int i;
        int length = cVar2.b.length - 1;
        int[] iArr2 = new int[length];
        for (i = 1; i <= length; i++) {
            iArr2[length - i] = this.a.d(i, cVar2.a(i));
        }
        c cVar3 = new c(this.a, iArr2);
        int length2 = iArr.length;
        int[] iArr3 = new int[length2];
        for (i = 0; i < length2; i++) {
            int a = this.a.a(iArr[i]);
            iArr3[i] = this.a.d(this.a.c(0, cVar.b(a)), this.a.a(cVar3.b(a)));
        }
        return iArr3;
    }

    public final int[] a(c cVar) throws c {
        int length = cVar.b.length - 1;
        int[] iArr = new int[length];
        int i = 0;
        for (int i2 = 1; i2 < this.a.f && i < length; i2++) {
            if (cVar.b(i2) == 0) {
                iArr[i] = this.a.a(i2);
                i++;
            }
        }
        if (i == length) {
            return iArr;
        }
        throw c.a();
    }
}
