package com.google.zxing.common.b;

// compiled from: GenericGF.java
public final class a {
    public static final a a;
    public static final a b;
    public static final a c;
    public static final a d;
    public static final a e;
    public static final a f;
    public static final a g;
    public static final a h;
    final int[] i;
    final b j;
    final b k;
    final int l;
    final int m;
    private final int[] n;
    private final int o;

    static {
        a = new a(4201, 4096, 1);
        b = new a(1033, 1024, 1);
        c = new a(67, 64, 1);
        d = new a(19, 16, 1);
        e = new a(285, 256, 0);
        a aVar = new a(301, 256, 1);
        f = aVar;
        g = aVar;
        h = c;
    }

    private a(int i, int i2, int i3) {
        this.o = i;
        this.l = i2;
        this.m = i3;
        this.i = new int[i2];
        this.n = new int[i2];
        int i4 = 1;
        for (int i5 = 0; i5 < i2; i5++) {
            this.i[i5] = i4;
            i4 *= 2;
            if (i4 >= i2) {
                i4 = (i4 ^ i) & (i2 - 1);
            }
        }
        for (i4 = 0; i4 < i2 - 1; i4++) {
            this.n[this.i[i4]] = i4;
        }
        this.j = new b(this, new int[]{0});
        this.k = new b(this, new int[]{1});
    }

    final b a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.j;
        } else {
            int[] iArr = new int[(i + 1)];
            iArr[0] = i2;
            return new b(this, iArr);
        }
    }

    static int b(int i, int i2) {
        return i ^ i2;
    }

    final int a(int i) {
        if (i != 0) {
            return this.n[i];
        }
        throw new IllegalArgumentException();
    }

    final int b(int i) {
        if (i != 0) {
            return this.i[(this.l - this.n[i]) - 1];
        }
        throw new ArithmeticException();
    }

    final int c(int i, int i2) {
        return (i == 0 || i2 == 0) ? 0 : this.i[(this.n[i] + this.n[i2]) % (this.l - 1)];
    }

    public final String toString() {
        return new StringBuilder("GF(0x").append(Integer.toHexString(this.o)).append(',').append(this.l).append(')').toString();
    }
}
