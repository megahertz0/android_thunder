package com.inmobi.ads;

import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.xunlei.download.Downloads.Impl;
import java.util.Arrays;
import java.util.List;

// compiled from: NativeStrandPositioningData.java
class ae {
    private static final String a;
    private final int[] b;
    private final int[] c;
    private int d;
    private final int[] e;
    private final int[] f;
    private int g;
    private final q[] h;

    static {
        a = ae.class.getSimpleName();
    }

    private ae(int[] iArr) {
        this.b = new int[200];
        this.c = new int[200];
        this.d = 0;
        this.e = new int[200];
        this.f = new int[200];
        this.g = 0;
        this.h = new q[200];
        this.d = Math.min(iArr.length, Impl.STATUS_SUCCESS);
        System.arraycopy(iArr, 0, this.c, 0, this.d);
        System.arraycopy(iArr, 0, this.b, 0, this.d);
    }

    static ae a(InMobiClientPositioning inMobiClientPositioning) {
        int size;
        int i = 0;
        List<Integer> fixedPositions = inMobiClientPositioning.getFixedPositions();
        int repeatingInterval = inMobiClientPositioning.getRepeatingInterval();
        if (Integer.MAX_VALUE == inMobiClientPositioning.getRepeatingInterval()) {
            size = fixedPositions.size();
        } else {
            size = 200;
        }
        int[] iArr = new int[size];
        int i2 = 0;
        for (Integer num : fixedPositions) {
            i2 = num.intValue() - i;
            int i3 = i + 1;
            iArr[i] = i2;
            i = i3;
        }
        while (i < size) {
            i2 = (i2 + repeatingInterval) - 1;
            i3 = i + 1;
            iArr[i] = i2;
            i = i3;
        }
        return new ae(iArr);
    }

    q a(int i) {
        int binarySearch = Arrays.binarySearch(this.f, 0, this.g, i);
        return binarySearch < 0 ? null : this.h[binarySearch];
    }

    boolean b(int i) {
        return Arrays.binarySearch(this.c, 0, this.d, i) >= 0;
    }

    int c(int i) {
        int b = b(this.c, this.d, i);
        return b == this.d ? -1 : this.c[b];
    }

    void a(q qVar, int i) {
        int a = a(this.c, this.d, i);
        if (a != this.d && this.c[a] == i) {
            int[] iArr;
            int i2 = this.b[a];
            int b = b(this.e, this.g, i2);
            if (b < this.g) {
                int i3 = this.g - b;
                System.arraycopy(this.e, b, this.e, b + 1, i3);
                System.arraycopy(this.f, b, this.f, b + 1, i3);
                System.arraycopy(this.h, b, this.h, b + 1, i3);
            }
            this.e[b] = i2;
            this.f[b] = i;
            this.h[b] = qVar;
            this.g++;
            i2 = (this.d - a) - 1;
            System.arraycopy(this.c, a + 1, this.c, a, i2);
            System.arraycopy(this.b, a + 1, this.b, a, i2);
            this.d--;
            while (a < this.d) {
                iArr = this.c;
                iArr[a] = iArr[a] + 1;
                a++;
            }
            for (a = b + 1; a < this.g; a++) {
                iArr = this.f;
                iArr[a] = iArr[a] + 1;
            }
        }
    }

    boolean d(int i) {
        return Arrays.binarySearch(this.f, 0, this.g, i) >= 0;
    }

    void a() {
        if (this.g != 0) {
            a(0, this.f[this.g - 1] + 1);
        }
    }

    int[] b() {
        Object obj = new Object[this.g];
        System.arraycopy(this.f, 0, obj, 0, this.g);
        return obj;
    }

    int a(int i, int i2) {
        int i3;
        int i4 = 0;
        int[] iArr = new int[this.g];
        int[] iArr2 = new int[this.g];
        int i5 = 0;
        for (i3 = 0; i3 < this.g; i3++) {
            int i6 = this.e[i3];
            int i7 = this.f[i3];
            if (i <= i7 && i7 < i2) {
                iArr[i5] = i6;
                iArr2[i5] = i7 - i5;
                this.h[i3].c();
                this.h[i3] = null;
                i5++;
            } else if (i5 > 0) {
                int i8 = i3 - i5;
                this.e[i8] = i6;
                this.f[i8] = i7 - i5;
                this.h[i8] = this.h[i3];
            }
        }
        if (i5 == 0) {
            return 0;
        }
        i6 = a(this.c, this.d, iArr2[0]);
        for (i3 = this.d - 1; i3 >= i6; i3--) {
            this.b[i3 + i5] = this.b[i3];
            this.c[i3 + i5] = this.c[i3] - i5;
        }
        while (i4 < i5) {
            this.b[i6 + i4] = iArr[i4];
            this.c[i6 + i4] = iArr2[i4];
            i4++;
        }
        this.d += i5;
        this.g -= i5;
        return i5;
    }

    void e(int i) {
        int a;
        for (a = a(this.b, this.d, i); a < this.d; a++) {
            int[] iArr = this.b;
            iArr[a] = iArr[a] + 1;
            iArr = this.c;
            iArr[a] = iArr[a] + 1;
        }
        for (a = a(this.e, this.g, i); a < this.g; a++) {
            iArr = this.e;
            iArr[a] = iArr[a] + 1;
            iArr = this.f;
            iArr[a] = iArr[a] + 1;
        }
    }

    void f(int i) {
        int b;
        for (b = b(this.b, this.d, i); b < this.d; b++) {
            int[] iArr = this.b;
            iArr[b] = iArr[b] - 1;
            iArr = this.c;
            iArr[b] = iArr[b] - 1;
        }
        for (b = b(this.e, this.g, i); b < this.g; b++) {
            iArr = this.e;
            iArr[b] = iArr[b] - 1;
            iArr = this.f;
            iArr[b] = iArr[b] - 1;
        }
    }

    static ae c() {
        return new ae(new int[0]);
    }

    private static int a(int[] iArr, int i, int i2) {
        int binarySearch = Arrays.binarySearch(iArr, 0, i, i2);
        if (binarySearch < 0) {
            return binarySearch ^ -1;
        }
        int i3 = iArr[binarySearch];
        while (binarySearch >= 0 && iArr[binarySearch] == i3) {
            binarySearch--;
        }
        return binarySearch + 1;
    }

    int g(int i) {
        int binarySearch = Arrays.binarySearch(this.f, 0, this.g, i);
        return binarySearch < 0 ? i - (binarySearch ^ -1) : -1;
    }

    int h(int i) {
        return b(this.e, this.g, i) + i;
    }

    int i(int i) {
        if (i == 0) {
            return 0;
        }
        int g = g(i - 1);
        return g != -1 ? g + 1 : -1;
    }

    int j(int i) {
        return i == 0 ? 0 : h(i - 1) + 1;
    }

    private static int b(int[] iArr, int i, int i2) {
        int binarySearch = Arrays.binarySearch(iArr, 0, i, i2);
        if (binarySearch < 0) {
            return binarySearch ^ -1;
        }
        int i3 = iArr[binarySearch];
        while (binarySearch < i && iArr[binarySearch] == i3) {
            binarySearch++;
        }
        return binarySearch;
    }
}
