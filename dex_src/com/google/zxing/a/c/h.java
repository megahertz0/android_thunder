package com.google.zxing.a.c;

import com.google.zxing.common.a;

// compiled from: Token.java
abstract class h {
    static final h a;
    final h b;

    abstract void a(a aVar, byte[] bArr);

    static {
        a = new f(null, 0, 0);
    }

    h(h hVar) {
        this.b = hVar;
    }

    final h a(int i, int i2) {
        return new f(this, i, i2);
    }
}
