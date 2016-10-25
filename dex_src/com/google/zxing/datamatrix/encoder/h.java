package com.google.zxing.datamatrix.encoder;

import com.google.zxing.d;
import java.nio.charset.Charset;

// compiled from: EncoderContext.java
final class h {
    final String a;
    SymbolShapeHint b;
    d c;
    d d;
    final StringBuilder e;
    int f;
    int g;
    k h;
    int i;

    h(String str) {
        byte[] bytes = str.getBytes(Charset.forName("ISO-8859-1"));
        StringBuilder stringBuilder = new StringBuilder(bytes.length);
        int i = 0;
        int length = bytes.length;
        while (i < length) {
            char c = (char) (bytes[i] & 255);
            if (c == '?' && str.charAt(i) != '?') {
                throw new IllegalArgumentException("Message contains characters outside ISO-8859-1 encoding.");
            }
            stringBuilder.append(c);
            i++;
        }
        this.a = stringBuilder.toString();
        this.b = SymbolShapeHint.FORCE_NONE;
        this.e = new StringBuilder(str.length());
        this.g = -1;
    }

    public final char a() {
        return this.a.charAt(this.f);
    }

    public final void a(String str) {
        this.e.append(str);
    }

    public final void a(char c) {
        this.e.append(c);
    }

    public final boolean b() {
        return this.f < e();
    }

    private int e() {
        return this.a.length() - this.i;
    }

    public final int c() {
        return e() - this.f;
    }

    public final void a(int i) {
        if (this.h == null || i > this.h.b) {
            this.h = k.a(i, this.b, this.c, this.d);
        }
    }

    public final void d() {
        a(this.e.length());
    }
}
