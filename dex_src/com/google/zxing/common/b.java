package com.google.zxing.common;

import com.xunlei.tdlive.R;
import java.util.Arrays;

// compiled from: BitMatrix.java
public final class b implements Cloneable {
    public final int a;
    public final int b;
    public final int c;
    public final int[] d;

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return d();
    }

    public b(int i) {
        this(i, i);
    }

    public b(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.a = i;
        this.b = i2;
        this.c = (i + 31) / 32;
        this.d = new int[(this.c * i2)];
    }

    private b(int i, int i2, int i3, int[] iArr) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = iArr;
    }

    public final boolean a(int i, int i2) {
        return ((this.d[(this.c * i2) + (i / 32)] >>> (i & 31)) & 1) != 0;
    }

    public final void b(int i, int i2) {
        int i3 = (this.c * i2) + (i / 32);
        int[] iArr = this.d;
        iArr[i3] = iArr[i3] | (1 << (i & 31));
    }

    public final void c(int i, int i2) {
        int i3 = (this.c * i2) + (i / 32);
        int[] iArr = this.d;
        iArr[i3] = iArr[i3] ^ (1 << (i & 31));
    }

    public final void a() {
        int length = this.d.length;
        for (int i = 0; i < length; i++) {
            this.d[i] = 0;
        }
    }

    public final void a(int i, int i2, int i3, int i4) {
        if (i2 < 0 || i < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        } else if (i4 <= 0 || i3 <= 0) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        } else {
            int i5 = i + i3;
            int i6 = i2 + i4;
            if (i6 > this.b || i5 > this.a) {
                throw new IllegalArgumentException("The region must fit inside the matrix");
            }
            while (i2 < i6) {
                int i7 = i2 * this.c;
                for (int i8 = i; i8 < i5; i8++) {
                    int[] iArr = this.d;
                    int i9 = (i8 / 32) + i7;
                    iArr[i9] = iArr[i9] | (1 << (i8 & 31));
                }
                i2++;
            }
        }
    }

    public final a a(int i, a aVar) {
        if (aVar == null || aVar.b < this.a) {
            aVar = new a(this.a);
        } else {
            aVar.b();
        }
        int i2 = i * this.c;
        for (int i3 = 0; i3 < this.c; i3++) {
            int i4 = i3 * 32;
            aVar.a[i4 / 32] = this.d[i2 + i3];
        }
        return aVar;
    }

    public final int[] b() {
        int i = 0;
        while (i < this.d.length && this.d[i] == 0) {
            i++;
        }
        if (i == this.d.length) {
            return null;
        }
        int i2 = i / this.c;
        int i3 = (i % this.c) * 32;
        int i4 = this.d[i];
        i = 0;
        while ((i4 << (31 - i)) == 0) {
            i++;
        }
        i3 += i;
        return new int[]{i3, i2};
    }

    public final int[] c() {
        int length = this.d.length - 1;
        while (length >= 0 && this.d[length] == 0) {
            length--;
        }
        if (length < 0) {
            return null;
        }
        int i = length / this.c;
        int i2 = (length % this.c) * 32;
        int i3 = this.d[length];
        length = R.styleable.AppCompatTheme_actionModeCloseDrawable;
        while ((i3 >>> length) == 0) {
            length--;
        }
        i2 += length;
        return new int[]{i2, i};
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.a == bVar.a && this.b == bVar.b && this.c == bVar.c && Arrays.equals(this.d, bVar.d);
    }

    public final int hashCode() {
        return (((((((this.a * 31) + this.a) * 31) + this.b) * 31) + this.c) * 31) + Arrays.hashCode(this.d);
    }

    public final String toString() {
        String str = "X ";
        String str2 = "  ";
        String str3 = "\n";
        StringBuilder stringBuilder = new StringBuilder(this.b * (this.a + 1));
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.a; i2++) {
                String str4;
                if (a(i2, i)) {
                    str4 = str;
                } else {
                    str4 = str2;
                }
                stringBuilder.append(str4);
            }
            stringBuilder.append(str3);
        }
        return stringBuilder.toString();
    }

    public final b d() {
        return new b(this.a, this.b, this.c, (int[]) this.d.clone());
    }

    public final void b(int i, a aVar) {
        System.arraycopy(aVar.a, 0, this.d, this.c * i, this.c);
    }
}
