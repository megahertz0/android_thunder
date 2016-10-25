package com.google.zxing.c.a.a.a;

import com.google.zxing.e;

// compiled from: DecodedNumeric.java
final class p extends q {
    final int a;
    final int b;

    p(int i, int i2, int i3) throws e {
        super(i);
        if (i2 < 0 || i2 > 10 || i3 < 0 || i3 > 10) {
            throw e.a();
        }
        this.a = i2;
        this.b = i3;
    }

    final boolean a() {
        return this.b == 10;
    }
}
