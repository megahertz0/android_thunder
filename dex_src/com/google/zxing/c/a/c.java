package com.google.zxing.c.a;

import com.google.zxing.o;

// compiled from: FinderPattern.java
public final class c {
    public final int a;
    public final int[] b;
    public final o[] c;

    public c(int i, int[] iArr, int i2, int i3, int i4) {
        this.a = i;
        this.b = iArr;
        this.c = new o[]{new o((float) i2, (float) i4), new o((float) i3, (float) i4)};
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        return this.a == ((c) obj).a;
    }

    public final int hashCode() {
        return this.a;
    }
}
