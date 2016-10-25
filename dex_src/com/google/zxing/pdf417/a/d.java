package com.google.zxing.pdf417.a;

// compiled from: Codeword.java
final class d {
    final int a;
    final int b;
    final int c;
    final int d;
    int e;

    d(int i, int i2, int i3, int i4) {
        this.e = -1;
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    final boolean a() {
        return a(this.e);
    }

    final boolean a(int i) {
        return i != -1 && this.c == (i % 3) * 3;
    }

    final void b() {
        this.e = ((this.d / 30) * 3) + (this.c / 3);
    }

    final int c() {
        return this.b - this.a;
    }

    public final String toString() {
        return this.e + "|" + this.d;
    }
}
