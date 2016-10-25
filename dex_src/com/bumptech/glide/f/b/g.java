package com.bumptech.glide.f.b;

import com.bumptech.glide.h.h;

// compiled from: SimpleTarget.java
public abstract class g<Z> extends a<Z> {
    private final int a;
    private final int b;

    public g() {
        this((byte) 0);
    }

    private g(byte b) {
        this.a = Integer.MIN_VALUE;
        this.b = Integer.MIN_VALUE;
    }

    public final void a(h hVar) {
        if (h.a(this.a, this.b)) {
            hVar.a(this.a, this.b);
            return;
        }
        throw new IllegalArgumentException(new StringBuilder("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: ").append(this.a).append(" and height: ").append(this.b).append(", either provide dimensions in the constructor or call override()").toString());
    }
}
