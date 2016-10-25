package com.google.zxing.datamatrix.a;

import com.google.zxing.common.b;
import com.google.zxing.common.b.a;
import com.google.zxing.common.b.c;
import com.google.zxing.common.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: Decoder.java
public final class e {
    private final c a;

    public e() {
        this.a = new c(a.f);
    }

    public final d a(b bVar) throws com.google.zxing.e, com.google.zxing.c {
        a aVar = new a(bVar);
        f fVar = aVar.c;
        byte[] bArr = new byte[aVar.c.g];
        int i = XZBDevice.DOWNLOAD_LIST_ALL;
        int i2 = aVar.a.b;
        int i3 = aVar.a.a;
        int i4 = 0;
        int i5 = 0;
        Object obj = null;
        Object obj2 = null;
        Object obj3 = null;
        Object obj4 = null;
        while (true) {
            int i6;
            int i7;
            int i8;
            Object obj5;
            Object obj6;
            if (i == i2 && i4 == 0 && obj == null) {
                i6 = i5 + 1;
                i7 = 0;
                if (aVar.a(i2 - 1, 0, i2, i3)) {
                    i7 = 1;
                }
                i7 <<= 1;
                if (aVar.a(i2 - 1, 1, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(i2 - 1, XZBDevice.DOWNLOAD_LIST_RECYCLE, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(0, i3 - 2, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(0, i3 - 1, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(1, i3 - 1, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(XZBDevice.DOWNLOAD_LIST_RECYCLE, i3 - 1, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(XZBDevice.DOWNLOAD_LIST_FAILED, i3 - 1, i2, i3)) {
                    i7 |= 1;
                }
                bArr[i5] = (byte) i7;
                i -= 2;
                i8 = i4 + 2;
                obj6 = obj4;
                obj4 = obj3;
                obj3 = obj2;
                int i9 = 1;
                obj5 = obj6;
            } else if (i == i2 - 2 && i4 == 0 && (i3 & 3) != 0 && obj2 == null) {
                i6 = i5 + 1;
                i7 = 0;
                if (aVar.a(i2 - 3, 0, i2, i3)) {
                    i7 = 1;
                }
                i7 <<= 1;
                if (aVar.a(i2 - 2, 0, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(i2 - 1, 0, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(0, i3 - 4, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(0, i3 - 3, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(0, i3 - 2, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(0, i3 - 1, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(1, i3 - 1, i2, i3)) {
                    i7 |= 1;
                }
                bArr[i5] = (byte) i7;
                i -= 2;
                obj6 = obj4;
                obj4 = obj3;
                int i10 = 1;
                obj5 = obj6;
                r17 = obj;
                i8 = i4 + 2;
                obj2 = r17;
            } else if (i == i2 + 4 && i4 == 2 && (i3 & 7) == 0 && obj3 == null) {
                i6 = i5 + 1;
                i7 = 0;
                if (aVar.a(i2 - 1, 0, i2, i3)) {
                    i7 = 1;
                }
                i7 <<= 1;
                if (aVar.a(i2 - 1, i3 - 1, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(0, i3 - 3, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(0, i3 - 2, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(0, i3 - 1, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(1, i3 - 3, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(1, i3 - 2, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(1, i3 - 1, i2, i3)) {
                    i7 |= 1;
                }
                bArr[i5] = (byte) i7;
                i -= 2;
                obj6 = obj4;
                int i11 = 1;
                obj5 = obj6;
                r17 = obj2;
                obj2 = obj;
                i8 = i4 + 2;
                obj3 = r17;
            } else if (i == i2 - 2 && i4 == 0 && (i3 & 7) == 4 && obj4 == null) {
                i6 = i5 + 1;
                i7 = 0;
                if (aVar.a(i2 - 3, 0, i2, i3)) {
                    i7 = 1;
                }
                i7 <<= 1;
                if (aVar.a(i2 - 2, 0, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(i2 - 1, 0, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(0, i3 - 2, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(0, i3 - 1, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(1, i3 - 1, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(XZBDevice.DOWNLOAD_LIST_RECYCLE, i3 - 1, i2, i3)) {
                    i7 |= 1;
                }
                i7 <<= 1;
                if (aVar.a(XZBDevice.DOWNLOAD_LIST_FAILED, i3 - 1, i2, i3)) {
                    i7 |= 1;
                }
                bArr[i5] = (byte) i7;
                i -= 2;
                obj5 = 1;
                obj6 = obj3;
                obj3 = obj2;
                obj2 = obj;
                i8 = i4 + 2;
                obj4 = obj6;
            } else {
                i7 = i5;
                int i12 = i;
                i = i4;
                i4 = i12;
                while (true) {
                    if (i4 >= i2 || i < 0 || aVar.b.a(i, i4)) {
                        i6 = i7;
                    } else {
                        i6 = i7 + 1;
                        bArr[i7] = (byte) aVar.b(i4, i, i2, i3);
                    }
                    i4 -= 2;
                    i7 = i + 2;
                    if (i4 < 0 || i7 >= i3) {
                        break;
                    }
                    i = i7;
                    i7 = i6;
                }
                i4++;
                i = i7 + 3;
                i7 = i6;
                while (true) {
                    if (i4 < 0 || i >= i3 || aVar.b.a(i, i4)) {
                        i6 = i7;
                    } else {
                        i6 = i7 + 1;
                        bArr[i7] = (byte) aVar.b(i4, i, i2, i3);
                    }
                    i4 += 2;
                    i7 = i - 2;
                    if (i4 >= i2 || i7 < 0) {
                        break;
                    }
                    i = i7;
                    i7 = i6;
                }
                i = i4 + 3;
                obj6 = obj4;
                obj4 = obj3;
                obj3 = obj2;
                obj2 = obj;
                i8 = i7 + 1;
                obj5 = obj6;
            }
            if (i >= i2 && i8 >= i3) {
                break;
            }
            i4 = i8;
            i5 = i6;
            obj = obj2;
            obj2 = obj3;
            obj3 = obj4;
            obj4 = obj5;
        }
        if (i6 != aVar.c.g) {
            throw com.google.zxing.e.a();
        }
        b bVar2 = fVar.f;
        i11 = 0;
        a[] aVarArr = bVar2.b;
        for (a aVar2 : aVarArr) {
            i11 += aVar2.a;
        }
        b[] bVarArr = new b[i11];
        i11 = 0;
        i6 = aVarArr.length;
        for (i9 = 0; i9 < i6; i9++) {
            a aVar3 = aVarArr[i9];
            i7 = 0;
            while (i7 < aVar3.a) {
                int i13 = aVar3.b;
                i10 = i11 + 1;
                bVarArr[i11] = new b(i13, new byte[(bVar2.a + i13)]);
                i7++;
                i11 = i10;
            }
        }
        i10 = bVarArr[0].b.length - bVar2.a;
        i6 = i10 - 1;
        i9 = 0;
        for (i = 0; i < i6; i++) {
            i8 = 0;
            while (i8 < i11) {
                i7 = i9 + 1;
                bVarArr[i8].b[i] = bArr[i9];
                i8++;
                i9 = i7;
            }
        }
        if (fVar.a == 24) {
            i6 = 1;
        } else {
            Object obj7 = null;
        }
        if (obj7 != null) {
            obj5 = XZBDevice.Wait;
        } else {
            i7 = i11;
        }
        i = 0;
        while (i < i7) {
            i8 = i9 + 1;
            bVarArr[i].b[i10 - 1] = bArr[i9];
            i++;
            i9 = i8;
        }
        i5 = bVarArr[0].b.length;
        i7 = i9;
        while (i10 < i5) {
            i = 0;
            i9 = i7;
            while (i < i11) {
                if (obj7 != null) {
                    i8 = (i + 8) % i11;
                } else {
                    i8 = i;
                }
                if (obj7 == null || i8 <= 7) {
                    i7 = i10;
                } else {
                    i7 = i10 - 1;
                }
                byte[] bArr2 = bVarArr[i8].b;
                i8 = i9 + 1;
                bArr2[i7] = bArr[i9];
                i++;
                i9 = i8;
            }
            i10++;
            i7 = i9;
        }
        if (i7 != bArr.length) {
            throw new IllegalArgumentException();
        }
        i10 = bVarArr.length;
        i11 = 0;
        for (b bVar3 : bVarArr) {
            i11 += bVar3.a;
        }
        byte[] bArr3 = new byte[i11];
        for (i11 = 0; i11 < i10; i11++) {
            b bVar4 = bVarArr[i11];
            byte[] bArr4 = bVar4.b;
            i = bVar4.a;
            a(bArr4, i);
            for (i7 = 0; i7 < i; i7++) {
                bArr3[(i7 * i10) + i11] = bArr4[i7];
            }
        }
        return c.a(bArr3);
    }

    private void a(byte[] bArr, int i) throws com.google.zxing.c {
        int i2 = 0;
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = bArr[i3] & 255;
        }
        try {
            this.a.a(iArr, bArr.length - i);
            while (i2 < i) {
                bArr[i2] = (byte) iArr[i2];
                i2++;
            }
        } catch (com.google.zxing.common.b.e e) {
            throw com.google.zxing.c.a();
        }
    }
}
