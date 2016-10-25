package com.google.zxing.c.a.a;

import java.util.ArrayList;
import java.util.List;

// compiled from: ExpandedRow.java
final class b {
    final List<a> a;
    final int b;
    private final boolean c;

    b(List<a> list, int i) {
        this.a = new ArrayList(list);
        this.b = i;
        this.c = false;
    }

    final boolean a(List<a> list) {
        return this.a.equals(list);
    }

    public final String toString() {
        return new StringBuilder("{ ").append(this.a).append(" }").toString();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.a.equals(bVar.a) && this.c == bVar.c;
    }

    public final int hashCode() {
        return this.a.hashCode() ^ Boolean.valueOf(this.c).hashCode();
    }
}
