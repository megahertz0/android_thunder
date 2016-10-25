package com.google.zxing.c.a.a;

import com.google.zxing.c.a.b;
import com.google.zxing.c.a.c;

// compiled from: ExpandedPair.java
final class a {
    final b a;
    final b b;
    final c c;
    private final boolean d;

    a(b bVar, b bVar2, c cVar) {
        this.a = bVar;
        this.b = bVar2;
        this.c = cVar;
        this.d = true;
    }

    public final String toString() {
        Object obj;
        StringBuilder append = new StringBuilder("[ ").append(this.a).append(" , ").append(this.b).append(" : ");
        if (this.c == null) {
            obj = "null";
        } else {
            obj = Integer.valueOf(this.c.a);
        }
        return append.append(obj).append(" ]").toString();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return a(this.a, aVar.a) && a(this.b, aVar.b) && a(this.c, aVar.c);
    }

    private static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else {
            return obj.equals(obj2);
        }
    }

    public final int hashCode() {
        return (a(this.a) ^ a(this.b)) ^ a(this.c);
    }

    private static int a(Object obj) {
        return obj == null ? 0 : obj.hashCode();
    }
}
