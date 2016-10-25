package com.google.zxing.pdf417.a;

import com.google.zxing.o;
import java.util.Formatter;

// compiled from: DetectionResult.java
final class g {
    final a a;
    final h[] b;
    c c;
    final int d;

    g(a aVar, c cVar) {
        this.a = aVar;
        this.d = aVar.a;
        this.c = cVar;
        this.b = new h[(this.d + 2)];
    }

    final void a(h hVar) {
        if (hVar != null) {
            o oVar;
            o oVar2;
            i iVar = (i) hVar;
            a aVar = this.a;
            d[] dVarArr = iVar.b;
            for (d dVar : iVar.b) {
                if (dVar != null) {
                    dVar.b();
                }
            }
            iVar.a(dVarArr, aVar);
            c cVar = iVar.a;
            if (iVar.c) {
                oVar = cVar.b;
            } else {
                oVar = cVar.d;
            }
            if (iVar.c) {
                oVar2 = cVar.c;
            } else {
                oVar2 = cVar.e;
            }
            int b = iVar.b((int) oVar.b);
            int b2 = iVar.b((int) oVar2.b);
            int i = -1;
            int i2 = 1;
            int i3 = 0;
            int i4 = b;
            while (i4 < b2) {
                if (dVarArr[i4] != null) {
                    d dVar2 = dVarArr[i4];
                    b = dVar2.e - i;
                    int i5;
                    if (b == 0) {
                        i5 = i2;
                        i2 = i;
                        i = i3 + 1;
                        b = i5;
                    } else if (b == 1) {
                        b = Math.max(i2, i3);
                        r2 = 1;
                        i2 = dVar2.e;
                    } else if (b < 0 || dVar2.e >= aVar.e || b > i4) {
                        dVarArr[i4] = null;
                        b = i2;
                        i2 = i;
                        i = i3;
                    } else {
                        int i6;
                        if (i2 > 2) {
                            i6 = b * (i2 - 2);
                        } else {
                            i6 = b;
                        }
                        Object obj = i6 >= i4 ? 1 : null;
                        for (int i7 = 1; i7 <= i6 && obj == null; i7++) {
                            obj = dVarArr[i4 - i7] != null ? 1 : null;
                        }
                        if (obj != null) {
                            dVarArr[i4] = null;
                            b = i2;
                            i2 = i;
                            i = i3;
                        } else {
                            r2 = 1;
                            i5 = i2;
                            i2 = dVar2.e;
                            b = i5;
                        }
                    }
                } else {
                    b = i2;
                    i2 = i;
                    i = i3;
                }
                i4++;
                i3 = i;
                i = i2;
                i2 = b;
            }
        }
    }

    static int a(int i, int i2, d dVar) {
        if (dVar == null || dVar.a()) {
            return i2;
        }
        if (!dVar.a(i)) {
            return i2 + 1;
        }
        dVar.e = i;
        return 0;
    }

    public final String toString() {
        h hVar = this.b[0];
        if (hVar == null) {
            hVar = this.b[this.d + 1];
        }
        Formatter formatter = new Formatter();
        for (int i = 0; i < hVar.b.length; i++) {
            formatter.format("CW %3d:", new Object[]{Integer.valueOf(i)});
            for (int i2 = 0; i2 < this.d + 2; i2++) {
                if (this.b[i2] == null) {
                    formatter.format("    |   ", new Object[0]);
                } else {
                    if (this.b[i2].b[i] == null) {
                        formatter.format("    |   ", new Object[0]);
                    } else {
                        formatter.format(" %3d|%3d", new Object[]{Integer.valueOf(this.b[i2].b[i].e), Integer.valueOf(this.b[i2].b[i].d)});
                    }
                }
            }
            formatter.format("%n", new Object[0]);
        }
        String toString = formatter.toString();
        formatter.close();
        return toString;
    }
}
