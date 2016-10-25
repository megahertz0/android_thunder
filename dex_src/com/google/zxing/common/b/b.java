package com.google.zxing.common.b;

// compiled from: GenericGFPoly.java
final class b {
    final int[] a;
    private final a b;

    b(a aVar, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.b = aVar;
        int length = iArr.length;
        if (length <= 1 || iArr[0] != 0) {
            this.a = iArr;
            return;
        }
        int i = 1;
        while (i < length && iArr[i] == 0) {
            i++;
        }
        if (i == length) {
            this.a = new int[]{0};
            return;
        }
        this.a = new int[(length - i)];
        System.arraycopy(iArr, i, this.a, 0, this.a.length);
    }

    final boolean a() {
        return this.a[0] == 0;
    }

    final int a(int i) {
        return this.a[(this.a.length - 1) - i];
    }

    final int b(int i) {
        int i2 = 0;
        if (i == 0) {
            return a(0);
        }
        int length = this.a.length;
        int i3;
        if (i == 1) {
            int[] iArr = this.a;
            length = iArr.length;
            i3 = 0;
            while (i2 < length) {
                i3 = a.b(i3, iArr[i2]);
                i2++;
            }
            return i3;
        }
        i3 = this.a[0];
        i2 = 1;
        while (i2 < length) {
            int b = a.b(this.b.c(i, i3), this.a[i2]);
            i2++;
            i3 = b;
        }
        return i3;
    }

    final b a(b bVar) {
        if (!this.b.equals(bVar.b)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (a()) {
            return bVar;
        } else {
            if (bVar.a()) {
                return this;
            }
            Object obj = this.a;
            Object obj2 = bVar.a;
            if (obj.length <= obj2.length) {
                Object obj3 = obj;
                obj = obj2;
                obj2 = obj3;
            }
            Object obj4 = new Object[obj.length];
            int length = obj.length - r0.length;
            System.arraycopy(obj, 0, obj4, 0, length);
            for (int i = length; i < obj.length; i++) {
                obj4[i] = a.b(r0[i - length], obj[i]);
            }
            return new b(this.b, obj4);
        }
    }

    final b b(b bVar) {
        if (!this.b.equals(bVar.b)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (a() || bVar.a()) {
            return this.b.j;
        } else {
            int[] iArr = this.a;
            int length = iArr.length;
            int[] iArr2 = bVar.a;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    iArr3[i + i3] = a.b(iArr3[i + i3], this.b.c(i2, iArr2[i3]));
                }
            }
            return new b(this.b, iArr3);
        }
    }

    final b c(int i) {
        if (i == 0) {
            return this.b.j;
        }
        if (i == 1) {
            return this;
        }
        int length = this.a.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.b.c(this.a[i2], i);
        }
        return new b(this.b, iArr);
    }

    final b a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.b.j;
        } else {
            int length = this.a.length;
            int[] iArr = new int[(length + i)];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.b.c(this.a[i3], i2);
            }
            return new b(this.b, iArr);
        }
    }

    final b[] c(b bVar) {
        if (!this.b.equals(bVar.b)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (bVar.a()) {
            throw new IllegalArgumentException("Divide by 0");
        } else {
            b bVar2 = this.b.j;
            int b = this.b.b(bVar.a(bVar.a.length - 1));
            b bVar3 = bVar2;
            bVar2 = this;
            while (bVar2.a.length - 1 >= bVar.a.length - 1 && !bVar2.a()) {
                int length = (bVar2.a.length - 1) - (bVar.a.length - 1);
                int c = this.b.c(bVar2.a(bVar2.a.length - 1), b);
                b a = bVar.a(length, c);
                bVar3 = bVar3.a(this.b.a(length, c));
                bVar2 = bVar2.a(a);
            }
            return new b[]{bVar3, bVar2};
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder((this.a.length - 1) * 8);
        for (int length = this.a.length - 1; length >= 0; length--) {
            int a = a(length);
            if (a != 0) {
                if (a < 0) {
                    stringBuilder.append(" - ");
                    a = -a;
                } else if (stringBuilder.length() > 0) {
                    stringBuilder.append(" + ");
                }
                if (length == 0 || a != 1) {
                    a = this.b.a(a);
                    if (a == 0) {
                        stringBuilder.append('1');
                    } else if (a == 1) {
                        stringBuilder.append('a');
                    } else {
                        stringBuilder.append("a^");
                        stringBuilder.append(a);
                    }
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
