package com.google.zxing.datamatrix.a;

import com.google.zxing.e;

// compiled from: Version.java
public final class f {
    private static final f[] h;
    final int a;
    final int b;
    final int c;
    final int d;
    final int e;
    final b f;
    final int g;

    // compiled from: Version.java
    static final class a {
        final int a;
        final int b;

        private a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    // compiled from: Version.java
    static final class b {
        final int a;
        final a[] b;

        private b(int i, a aVar) {
            this.a = i;
            this.b = new a[]{aVar};
        }

        private b(a aVar, a aVar2) {
            this.a = 62;
            this.b = new a[]{aVar, aVar2};
        }
    }

    private f(int i, int i2, int i3, int i4, int i5, b bVar) {
        int i6 = 0;
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = bVar;
        int i7 = bVar.a;
        a[] aVarArr = bVar.b;
        int length = aVarArr.length;
        int i8 = 0;
        while (i6 < length) {
            a aVar = aVarArr[i6];
            i8 += (aVar.b + i7) * aVar.a;
            i6++;
        }
        this.g = i8;
    }

    public static f a(int i, int i2) throws e {
        if ((i & 1) == 0 && (i2 & 1) == 0) {
            for (f fVar : h) {
                if (fVar.b == i && fVar.c == i2) {
                    return fVar;
                }
            }
            throw e.a();
        }
        throw e.a();
    }

    public final String toString() {
        return String.valueOf(this.a);
    }

    static {
        h = new f[]{new f(1, 10, 10, 8, 8, new b(new a(3, (byte) 0), (byte) 0)), new f(2, 12, 12, 10, 10, new b(new a(5, (byte) 0), (byte) 0)), new f(3, 14, 14, 12, 12, new b(new a(8, (byte) 0), (byte) 0)), new f(4, 16, 16, 14, 14, new b(new a(12, (byte) 0), (byte) 0)), new f(5, 18, 18, 16, 16, new b(new a(18, (byte) 0), (byte) 0)), new f(6, 20, 20, 18, 18, new b(new a(22, (byte) 0), (byte) 0)), new f(7, 22, 22, 20, 20, new b(new a(30, (byte) 0), (byte) 0)), new f(8, 24, 24, 22, 22, new b(new a(36, (byte) 0), (byte) 0)), new f(9, 26, 26, 24, 24, new b(new a(44, (byte) 0), (byte) 0)), new f(10, 32, 32, 14, 14, new b(new a(62, (byte) 0), (byte) 0)), new f(11, 36, 36, 16, 16, new b(new a(86, (byte) 0), (byte) 0)), new f(12, 40, 40, 18, 18, new b(new a(114, (byte) 0), (byte) 0)), new f(13, 44, 44, 20, 20, new b(new a(144, (byte) 0), (byte) 0)), new f(14, 48, 48, 22, 22, new b(new a(174, (byte) 0), (byte) 0)), new f(15, 52, 52, 24, 24, new b(new a(102, (byte) 0), (byte) 0)), new f(16, 64, 64, 14, 14, new b(new a(140, (byte) 0), (byte) 0)), new f(17, 72, 72, 16, 16, new b(new a(92, (byte) 0), (byte) 0)), new f(18, 80, 80, 18, 18, new b(new a(114, (byte) 0), (byte) 0)), new f(19, 88, 88, 20, 20, new b(new a(144, (byte) 0), (byte) 0)), new f(20, 96, 96, 22, 22, new b(new a(174, (byte) 0), (byte) 0)), new f(21, 104, 104, 24, 24, new b(new a(136, (byte) 0), (byte) 0)), new f(22, 120, 120, 18, 18, new b(new a(175, (byte) 0), (byte) 0)), new f(23, 132, 132, 20, 20, new b(new a(163, (byte) 0), (byte) 0)), new f(24, 144, 144, 22, 22, new b(new a(155, (byte) 0), (byte) 0)), new f(25, 8, 18, 6, 16, new b(new a(5, (byte) 0), (byte) 0)), new f(26, 8, 32, 6, 14, new b(new a(10, (byte) 0), (byte) 0)), new f(27, 12, 26, 10, 24, new b(new a(16, (byte) 0), (byte) 0)), new f(28, 12, 36, 10, 16, new b(new a(22, (byte) 0), (byte) 0)), new f(29, 16, 36, 14, 16, new b(new a(32, (byte) 0), (byte) 0)), new f(30, 16, 48, 14, 22, new b(new a(49, (byte) 0), (byte) 0))};
    }
}
