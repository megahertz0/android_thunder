package com.google.zxing.b.a;

import com.google.zxing.common.b.a;
import com.google.zxing.common.b.e;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: Decoder.java
public final class c {
    private final com.google.zxing.common.b.c a;

    public c() {
        this.a = new com.google.zxing.common.b.c(a.h);
    }

    public final void a(byte[] bArr, int i, int i2, int i3, int i4) throws com.google.zxing.c {
        int i5 = 0;
        int i6 = i2 + i3;
        int i7 = i4 == 0 ? 1 : XZBDevice.DOWNLOAD_LIST_RECYCLE;
        int[] iArr = new int[(i6 / i7)];
        int i8 = 0;
        while (i8 < i6) {
            if (i4 == 0 || i8 % 2 == i4 - 1) {
                iArr[i8 / i7] = bArr[i8 + i] & 255;
            }
            i8++;
        }
        try {
            this.a.a(iArr, i3 / i7);
            while (i5 < i2) {
                if (i4 == 0 || i5 % 2 == i4 - 1) {
                    bArr[i5 + i] = (byte) iArr[i5 / i7];
                }
                i5++;
            }
        } catch (e e) {
            throw com.google.zxing.c.a();
        }
    }
}
