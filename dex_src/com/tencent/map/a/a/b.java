package com.tencent.map.a.a;

import com.tencent.map.b.h;

public class b {
    private int a;
    private int b;
    private int c;
    private int d;

    public b(int i, int i2, int i3, int i4) {
        this.a = 1;
        this.b = 0;
        this.c = 12;
        this.d = 1;
        h.a(new StringBuilder("argument: ").append(this.a).append(" ").append(this.d).append(" ").append(this.b).toString());
        if (i >= 0 && i <= 1) {
            this.a = i;
        }
        if (i2 >= 0 && i2 <= 1) {
            this.d = i2;
        }
        if (i3 == 0 || i3 == 3 || i3 == 4 || i3 == 7) {
            this.b = i3;
        }
        if (this.d == 0) {
            this.b = 0;
        }
        this.c = i4;
    }

    public int a() {
        return this.a;
    }

    public void a(int i) {
    }

    public void a(d dVar) {
    }

    public void a(byte[] bArr, int i) {
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.d;
    }
}
