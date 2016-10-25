package com.google.zxing.pdf417.a.a;

// compiled from: ModulusGF.java
public final class b {
    public static final b a;
    public final int[] b;
    public final int[] c;
    public final c d;
    public final c e;
    final int f;

    static {
        a = new b();
    }

    private b() {
        int i;
        this.f = 929;
        this.b = new int[929];
        this.c = new int[929];
        int i2 = 1;
        for (i = 0; i < 929; i++) {
            this.b[i] = i2;
            i2 = (i2 * 3) % 929;
        }
        for (i = 0; i < 928; i++) {
            this.c[this.b[i]] = i;
        }
        this.d = new c(this, new int[]{0});
        this.e = new c(this, new int[]{1});
    }

    public final c a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.d;
        } else {
            int[] iArr = new int[(i + 1)];
            iArr[0] = i2;
            return new c(this, iArr);
        }
    }

    final int b(int i, int i2) {
        return (i + i2) % this.f;
    }

    public final int c(int i, int i2) {
        return ((this.f + i) - i2) % this.f;
    }

    public final int a(int i) {
        if (i != 0) {
            return this.b[(this.f - this.c[i]) - 1];
        }
        throw new ArithmeticException();
    }

    public final int d(int i, int i2) {
        return (i == 0 || i2 == 0) ? 0 : this.b[(this.c[i] + this.c[i2]) % (this.f - 1)];
    }
}
