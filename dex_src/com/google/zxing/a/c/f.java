package com.google.zxing.a.c;

import com.google.zxing.common.a;

// compiled from: SimpleToken.java
final class f extends h {
    private final short c;
    private final short d;

    f(h hVar, int i, int i2) {
        super(hVar);
        this.c = (short) i;
        this.d = (short) i2;
    }

    final void a(a aVar, byte[] bArr) {
        aVar.b(this.c, this.d);
    }

    public final String toString() {
        return new StringBuilder("<").append(Integer.toBinaryString(((this.c & ((1 << this.d) - 1)) | (1 << this.d)) | (1 << this.d)).substring(1)).append('>').toString();
    }
}
