package com.google.zxing.c.a.a.a;

import com.google.zxing.common.a;

// compiled from: AI01weightDecoder.java
abstract class i extends h {
    protected abstract int a(int i);

    protected abstract void a(StringBuilder stringBuilder, int i);

    i(a aVar) {
        super(aVar);
    }

    protected final void b(StringBuilder stringBuilder, int i, int i2) {
        int a = this.b.a(i, i2);
        a(stringBuilder, a);
        int a2 = a(a);
        int i3 = 100000;
        for (a = 0; a < 5; a++) {
            if (a2 / i3 == 0) {
                stringBuilder.append('0');
            }
            i3 /= 10;
        }
        stringBuilder.append(a2);
    }
}
