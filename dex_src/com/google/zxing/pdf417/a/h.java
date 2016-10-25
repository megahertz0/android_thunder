package com.google.zxing.pdf417.a;

import java.util.Formatter;

// compiled from: DetectionResultColumn.java
class h {
    final c a;
    final d[] b;

    h(c cVar) {
        this.a = new c(cVar);
        this.b = new d[((cVar.i - cVar.h) + 1)];
    }

    final d a(int i) {
        d c = c(i);
        if (c != null) {
            return c;
        }
        for (int i2 = 1; i2 < 5; i2++) {
            int b = b(i) - i2;
            if (b >= 0) {
                c = this.b[b];
                if (c != null) {
                    return c;
                }
            }
            b = b(i) + i2;
            if (b < this.b.length) {
                c = this.b[b];
                if (c != null) {
                    return c;
                }
            }
        }
        return null;
    }

    final int b(int i) {
        return i - this.a.h;
    }

    final void a(int i, d dVar) {
        this.b[b(i)] = dVar;
    }

    final d c(int i) {
        return this.b[b(i)];
    }

    public String toString() {
        Formatter formatter = new Formatter();
        d[] dVarArr = this.b;
        int length = dVarArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3;
            d dVar = dVarArr[i];
            if (dVar == null) {
                Object[] objArr = new Object[1];
                i3 = i2 + 1;
                objArr[0] = Integer.valueOf(i2);
                formatter.format("%3d:    |   %n", objArr);
            } else {
                r9 = new Object[3];
                i3 = i2 + 1;
                r9[0] = Integer.valueOf(i2);
                r9[1] = Integer.valueOf(dVar.e);
                r9[2] = Integer.valueOf(dVar.d);
                formatter.format("%3d: %3d|%3d%n", r9);
            }
            i++;
            i2 = i3;
        }
        String toString = formatter.toString();
        formatter.close();
        return toString;
    }
}
