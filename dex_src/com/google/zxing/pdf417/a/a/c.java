package com.google.zxing.pdf417.a.a;

// compiled from: ModulusPoly.java
public final class c {
    public final b a;
    public final int[] b;

    public c(b bVar, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.a = bVar;
        int length = iArr.length;
        if (length <= 1 || iArr[0] != 0) {
            this.b = iArr;
            return;
        }
        int i = 1;
        while (i < length && iArr[i] == 0) {
            i++;
        }
        if (i == length) {
            this.b = new int[]{0};
            return;
        }
        this.b = new int[(length - i)];
        System.arraycopy(iArr, i, this.b, 0, this.b.length);
    }

    public final boolean a() {
        return this.b[0] == 0;
    }

    public final int a(int i) {
        return this.b[(this.b.length - 1) - i];
    }

    public final int b(int i) {
        int i2 = 0;
        if (i == 0) {
            return a(0);
        }
        int length = this.b.length;
        int i3;
        if (i == 1) {
            int[] iArr = this.b;
            length = iArr.length;
            i3 = 0;
            while (i2 < length) {
                i3 = this.a.b(i3, iArr[i2]);
                i2++;
            }
            return i3;
        }
        i3 = this.b[0];
        i2 = 1;
        while (i2 < length) {
            int b = this.a.b(this.a.d(i, i3), this.b[i2]);
            i2++;
            i3 = b;
        }
        return i3;
    }

    public final c a(c cVar) {
        if (!this.a.equals(cVar.a)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (a()) {
            return cVar;
        } else {
            if (cVar.a()) {
                return this;
            }
            Object obj = this.b;
            Object obj2 = cVar.b;
            if (obj.length <= obj2.length) {
                Object obj3 = obj;
                obj = obj2;
                obj2 = obj3;
            }
            Object obj4 = new Object[obj.length];
            int length = obj.length - r0.length;
            System.arraycopy(obj, 0, obj4, 0, length);
            for (int i = length; i < obj.length; i++) {
                obj4[i] = this.a.b(r0[i - length], obj[i]);
            }
            return new c(this.a, obj4);
        }
    }

    public final c b(c cVar) {
        if (this.a.equals(cVar.a)) {
            return cVar.a() ? this : a(cVar.b());
        } else {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        }
    }

    public final c c(c cVar) {
        if (!this.a.equals(cVar.a)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (a() || cVar.a()) {
            return this.a.d;
        } else {
            int[] iArr = this.b;
            int length = iArr.length;
            int[] iArr2 = cVar.b;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    iArr3[i + i3] = this.a.b(iArr3[i + i3], this.a.d(i2, iArr2[i3]));
                }
            }
            return new c(this.a, iArr3);
        }
    }

    public final c b() {
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = this.a.c(0, this.b[i]);
        }
        return new c(this.a, iArr);
    }

    public final c c(int i) {
        if (i == 0) {
            return this.a.d;
        }
        if (i == 1) {
            return this;
        }
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.a.d(this.b[i2], i);
        }
        return new c(this.a, iArr);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder((this.b.length - 1) * 8);
        for (int length = this.b.length - 1; length >= 0; length--) {
            int a = a(length);
            if (a != 0) {
                if (a < 0) {
                    stringBuilder.append(" - ");
                    a = -a;
                } else if (stringBuilder.length() > 0) {
                    stringBuilder.append(" + ");
                }
                if (length == 0 || a != 1) {
                    stringBuilder.append(a);
                }
                if (length != 0) {
                    if (length == 1) {
                        stringBuilder.append('x');
                    } else {
                        stringBuilder.append("x^");
                        stringBuilder.append(length);
                    }
                }
            }
        }
        return stringBuilder.toString();
    }
}
