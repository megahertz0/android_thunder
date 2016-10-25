package com.bumptech.glide.a;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

// compiled from: StrictLineReader.java
final class d extends ByteArrayOutputStream {
    final /* synthetic */ c a;

    d(c cVar, int i) {
        this.a = cVar;
        super(i);
    }

    public final String toString() {
        int i = (this.count <= 0 || this.buf[this.count - 1] != 13) ? this.count : this.count - 1;
        try {
            return new String(this.buf, 0, i, this.a.a.name());
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
