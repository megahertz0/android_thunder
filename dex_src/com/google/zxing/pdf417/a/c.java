package com.google.zxing.pdf417.a;

import com.google.zxing.common.b;
import com.google.zxing.j;
import com.google.zxing.o;

// compiled from: BoundingBox.java
final class c {
    b a;
    o b;
    o c;
    o d;
    o e;
    int f;
    int g;
    int h;
    int i;

    c(b bVar, o oVar, o oVar2, o oVar3, o oVar4) throws j {
        if (!(oVar == null && oVar3 == null)) {
            if (!(oVar2 == null && oVar4 == null)) {
                if ((oVar == null || oVar2 != null) && (oVar3 == null || oVar4 != null)) {
                    a(bVar, oVar, oVar2, oVar3, oVar4);
                    return;
                }
            }
        }
        throw j.a();
    }

    c(c cVar) {
        a(cVar.a, cVar.b, cVar.c, cVar.d, cVar.e);
    }

    private void a(b bVar, o oVar, o oVar2, o oVar3, o oVar4) {
        this.a = bVar;
        this.b = oVar;
        this.c = oVar2;
        this.d = oVar3;
        this.e = oVar4;
        a();
    }

    final void a() {
        if (this.b == null) {
            this.b = new o(0.0f, this.d.b);
            this.c = new o(0.0f, this.e.b);
        } else if (this.d == null) {
            this.d = new o((float) (this.a.a - 1), this.b.b);
            this.e = new o((float) (this.a.a - 1), this.c.b);
        }
        this.f = (int) Math.min(this.b.a, this.c.a);
        this.g = (int) Math.max(this.d.a, this.e.a);
        this.h = (int) Math.min(this.b.b, this.d.b);
        this.i = (int) Math.max(this.c.b, this.e.b);
    }
}
