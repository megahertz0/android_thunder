package com.google.zxing.common;

import java.util.Arrays;

// compiled from: BitArray.java
public final class a implements Cloneable {
    public int[] a;
    public int b;

    public a() {
        this.b = 0;
        this.a = new int[1];
    }

    public a(int i) {
        this.b = i;
        this.a = f(i);
    }

    private a(int[] iArr, int i) {
        this.a = iArr;
        this.b = i;
    }

    public final int a() {
        return (this.b + 7) / 8;
    }

    private void e(int i) {
        if (i > this.a.length * 32) {
            Object f = f(i);
            System.arraycopy(this.a, 0, f, 0, this.a.length);
            this.a = f;
        }
    }

    public final boolean a(int i) {
        return (this.a[i / 32] & (1 << (i & 31))) != 0;
    }

    public final void b(int i) {
        int[] iArr = this.a;
        int i2 = i / 32;
        iArr[i2] = iArr[i2] | (1 << (i & 31));
    }

    public final int c(int i) {
        if (i >= this.b) {
            return this.b;
        }
        int i2 = i / 32;
        int i3 = this.a[i2] & (((1 << (i & 31)) - 1) ^ -1);
        while (i3 == 0) {
            i2++;
            if (i2 == this.a.length) {
                return this.b;
            }
            i3 = this.a[i2];
        }
        i2 = (i2 * 32) + Integer.numberOfTrailingZeros(i3);
        return i2 > this.b ? this.b : i2;
    }

    public final int d(int i) {
        if (i >= this.b) {
            return this.b;
        }
        int i2 = i / 32;
        int i3 = (this.a[i2] ^ -1) & (((1 << (i & 31)) - 1) ^ -1);
        while (i3 == 0) {
            i2++;
            if (i2 == this.a.length) {
                return this.b;
            }
            i3 = this.a[i2] ^ -1;
        }
        i2 = (i2 * 32) + Integer.numberOfTrailingZeros(i3);
        return i2 > this.b ? this.b : i2;
    }

    public final void b() {
        int length = this.a.length;
        for (int i = 0; i < length; i++) {
            this.a[i] = 0;
        }
    }

    public final boolean a(int i, int i2) {
        if (i2 < i) {
            throw new IllegalArgumentException();
        } else if (i2 == i) {
            return true;
        } else {
            int i3 = i2 - 1;
            int i4 = i / 32;
            int i5 = i3 / 32;
            int i6 = i4;
            while (i6 <= i5) {
                int i7 = i6 > i4 ? 0 : i & 31;
                int i8 = i6 < i5 ? 31 : i3 & 31;
                if (i7 == 0 && i8 == 31) {
                    i7 = -1;
                } else {
                    i7 = 0;
                    for (int i9 = i7; i9 <= i8; i9++) {
                        i7 |= 1 << i9;
                    }
                }
                if ((i7 & this.a[i6]) != 0) {
                    return false;
                }
                i6++;
            }
            return true;
        }
    }

    public final void a(boolean z) {
        e(this.b + 1);
        if (z) {
            int[] iArr = this.a;
            int i = this.b / 32;
            iArr[i] = iArr[i] | (1 << (this.b & 31));
        }
        this.b++;
    }

    public final void b(int i, int i2) {
        if (i2 < 0 || i2 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        e(this.b + i2);
        while (i2 > 0) {
            a(((i >> (i2 + -1)) & 1) == 1);
            i2--;
        }
    }

    public final void a(a aVar) {
        int i = aVar.b;
        e(this.b + i);
        for (int i2 = 0; i2 < i; i2++) {
            a(aVar.a(i2));
        }
    }

    public final void a(int i, byte[] bArr, int i2) {
        int i3 = 0;
        int i4 = i;
        while (i3 < i2) {
            int i5 = i4;
            i4 = 0;
            for (int i6 = 0; i6 < 8; i6++) {
                if (a(i5)) {
                    i4 |= 1 << (7 - i6);
                }
                i5++;
            }
            bArr[i3 + 0] = (byte) i4;
            i3++;
            i4 = i5;
        }
    }

    public final void c() {
        int i;
        int i2 = 1;
        int[] iArr = new int[this.a.length];
        int i3 = (this.b - 1) / 32;
        int i4 = i3 + 1;
        for (i = 0; i < i4; i++) {
            long j = (long) this.a[i];
            j = ((j & 1431655765) << 1) | ((j >> 1) & 1431655765);
            j = ((j & 858993459) << 2) | ((j >> 2) & 858993459);
            j = ((j & 252645135) << 4) | ((j >> 4) & 252645135);
            j = ((j & 16711935) << 8) | ((j >> 8) & 16711935);
            iArr[i3 - i] = (int) (((j & 65535) << 16) | ((j >> 16) & 65535));
        }
        if (this.b != i4 * 32) {
            int i5 = (i4 * 32) - this.b;
            i3 = 1;
            for (i = 0; i < 31 - i5; i++) {
                i3 = (i3 << 1) | 1;
            }
            i = (iArr[0] >> i5) & i3;
            while (i2 < i4) {
                int i6 = iArr[i2];
                iArr[i2 - 1] = i | (i6 << (32 - i5));
                i = (i6 >> i5) & i3;
                i2++;
            }
            iArr[i4 - 1] = i;
        }
        this.a = iArr;
    }

    private static int[] f(int i) {
        return new int[((i + 31) / 32)];
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.b == aVar.b && Arrays.equals(this.a, aVar.a);
    }

    public final int hashCode() {
        return (this.b * 31) + Arrays.hashCode(this.a);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder(this.b);
        for (int i = 0; i < this.b; i++) {
            if ((i & 7) == 0) {
                stringBuilder.append(' ');
            }
            stringBuilder.append(a(i) ? 'X' : '.');
        }
        return stringBuilder.toString();
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new a((int[]) this.a.clone(), this.b);
    }
}
