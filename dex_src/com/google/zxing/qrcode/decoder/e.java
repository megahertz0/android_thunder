package com.google.zxing.qrcode.decoder;

import com.google.zxing.DecodeHintType;
import com.google.zxing.common.b;
import com.google.zxing.common.b.a;
import com.google.zxing.common.b.c;
import com.google.zxing.common.d;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Map;

// compiled from: Decoder.java
public final class e {
    private final c a;

    public e() {
        this.a = new c(a.e);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.zxing.common.d a(com.google.zxing.common.b r8, java.util.Map<com.google.zxing.DecodeHintType, ?> r9) throws com.google.zxing.e, com.google.zxing.c {
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.e.a(com.google.zxing.common.b, java.util.Map):com.google.zxing.common.d");
        /*
        this = this;
        r0 = 0;
        r4 = new com.google.zxing.qrcode.decoder.a;
        r4.<init>(r8);
        r0 = r7.a(r4, r9);	 Catch:{ e -> 0x000b, c -> 0x005d }
    L_0x000a:
        return r0;
    L_0x000b:
        r1 = move-exception;
        r2 = r0;
        r3 = r1;
    L_0x000e:
        r0 = r4.c;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        if (r0 == 0) goto L_0x0023;
    L_0x0012:
        r0 = r4.c;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r0 = r0.b;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r0 = com.google.zxing.qrcode.decoder.c.a(r0);	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r1 = r4.a;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r1 = r1.b;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r5 = r4.a;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r0.a(r5, r1);	 Catch:{ e -> 0x0071, c -> 0x0079 }
    L_0x0023:
        r0 = 0;
        r4.b = r0;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r0 = 0;
        r4.c = r0;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r0 = 1;
        r4.d = r0;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r4.b();	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r4.a();	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r0 = 0;
        r1 = r0;
    L_0x0034:
        r0 = r4.a;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r0 = r0.a;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        if (r1 >= r0) goto L_0x0065;
    L_0x003a:
        r0 = r1 + 1;
    L_0x003c:
        r5 = r4.a;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r5 = r5.b;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        if (r0 >= r5) goto L_0x0061;
    L_0x0042:
        r5 = r4.a;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r5 = r5.a(r1, r0);	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r6 = r4.a;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r6 = r6.a(r0, r1);	 Catch:{ e -> 0x0071, c -> 0x0079 }
        if (r5 == r6) goto L_0x005a;
    L_0x0050:
        r5 = r4.a;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r5.c(r0, r1);	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r5 = r4.a;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r5.c(r1, r0);	 Catch:{ e -> 0x0071, c -> 0x0079 }
    L_0x005a:
        r0 = r0 + 1;
        goto L_0x003c;
    L_0x005d:
        r1 = move-exception;
        r2 = r1;
        r3 = r0;
        goto L_0x000e;
    L_0x0061:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0034;
    L_0x0065:
        r0 = r7.a(r4, r9);	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r1 = new com.google.zxing.qrcode.decoder.g;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r1.<init>();	 Catch:{ e -> 0x0071, c -> 0x0079 }
        r0.g = r1;	 Catch:{ e -> 0x0071, c -> 0x0079 }
        goto L_0x000a;
    L_0x0071:
        r0 = move-exception;
    L_0x0072:
        if (r3 == 0) goto L_0x0075;
    L_0x0074:
        throw r3;
    L_0x0075:
        if (r2 == 0) goto L_0x0078;
    L_0x0077:
        throw r2;
    L_0x0078:
        throw r0;
    L_0x0079:
        r0 = move-exception;
        goto L_0x0072;
        */
    }

    private d a(a aVar, Map<DecodeHintType, ?> map) throws com.google.zxing.e, com.google.zxing.c {
        int i;
        int i2;
        h b = aVar.b();
        ErrorCorrectionLevel errorCorrectionLevel = aVar.a().a;
        f a = aVar.a();
        h b2 = aVar.b();
        c a2 = c.a(a.b);
        int i3 = aVar.a.b;
        a2.a(aVar.a, i3);
        int a3 = b2.a();
        b bVar = new b(a3);
        bVar.a(0, 0, XZBDevice.Pause, XZBDevice.Pause);
        bVar.a(a3 - 8, 0, XZBDevice.Wait, XZBDevice.Pause);
        bVar.a(0, a3 - 8, XZBDevice.Pause, XZBDevice.Wait);
        int length = b2.b.length;
        for (i = 0; i < length; i++) {
            int i4 = b2.b[i] - 2;
            i2 = 0;
            while (i2 < length) {
                if (i != 0 || (i2 != 0 && i2 != length - 1)) {
                    if (i != length - 1 || i2 != 0) {
                        bVar.a(b2.b[i2] - 2, i4, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                    }
                }
                i2++;
            }
        }
        bVar.a(R.styleable.Toolbar_contentInsetEnd, XZBDevice.Pause, 1, a3 - 17);
        bVar.a(XZBDevice.Pause, R.styleable.Toolbar_contentInsetEnd, a3 - 17, 1);
        if (b2.a > 6) {
            bVar.a(a3 - 11, 0, XZBDevice.DOWNLOAD_LIST_FAILED, R.styleable.Toolbar_contentInsetEnd);
            bVar.a(0, a3 - 11, R.styleable.Toolbar_contentInsetEnd, XZBDevice.DOWNLOAD_LIST_FAILED);
        }
        byte[] bArr = new byte[b2.c];
        int i5 = 0;
        Object obj = null;
        Object obj2 = null;
        i2 = i3 - 1;
        int i6 = 1;
        while (i2 > 0) {
            if (i2 == 6) {
                i2--;
            }
            a3 = 0;
            while (a3 < i3) {
                int i7;
                if (i6 != 0) {
                    i = (i3 - 1) - a3;
                } else {
                    i = a3;
                }
                int i8 = 0;
                length = i4;
                i4 = i7;
                while (i8 < 2) {
                    if (!bVar.a(i2 - i8, i)) {
                        length++;
                        i4 <<= 1;
                        if (aVar.a.a(i2 - i8, i)) {
                            i4 |= 1;
                        }
                        if (length == 8) {
                            i7 = i5 + 1;
                            bArr[i5] = (byte) i4;
                            length = 0;
                            i4 = 0;
                            i8++;
                            i5 = i7;
                        }
                    }
                    i7 = i5;
                    i8++;
                    i5 = i7;
                }
                a3++;
                i7 = i4;
                i4 = length;
            }
            i2 -= 2;
            i6 ^= 1;
        }
        if (i5 != b2.c) {
            throw com.google.zxing.e.a();
        } else if (bArr.length != b.c) {
            throw new IllegalArgumentException();
        } else {
            h.b a4 = b.a(errorCorrectionLevel);
            i = 0;
            h.a[] aVarArr = a4.b;
            for (h.a aVar2 : aVarArr) {
                i += aVar2.a;
            }
            b[] bVarArr = new b[i];
            i = 0;
            i5 = aVarArr.length;
            for (length = 0; length < i5; length++) {
                h.a aVar3 = aVarArr[length];
                i2 = 0;
                while (i2 < aVar3.a) {
                    int i9 = aVar3.b;
                    a3 = i + 1;
                    bVarArr[i] = new b(i9, new byte[(a4.a + i9)]);
                    i2++;
                    i = a3;
                }
            }
            a3 = bVarArr[0].b.length;
            i2 = bVarArr.length - 1;
            while (i2 >= 0 && bVarArr[i2].b.length != a3) {
                i2--;
            }
            i5 = i2 + 1;
            a3 -= a4.a;
            i7 = 0;
            i2 = 0;
            while (i7 < a3) {
                length = i2;
                i2 = 0;
                while (i2 < i) {
                    i4 = length + 1;
                    bVarArr[i2].b[i7] = bArr[length];
                    i2++;
                    length = i4;
                }
                i7++;
                i2 = length;
            }
            length = i5;
            while (length < i) {
                i4 = i2 + 1;
                bVarArr[length].b[a3] = bArr[i2];
                length++;
                i2 = i4;
            }
            i6 = bVarArr[0].b.length;
            while (a3 < i6) {
                i4 = 0;
                length = i2;
                while (i4 < i) {
                    i7 = length + 1;
                    bVarArr[i4].b[i4 < i5 ? a3 : a3 + 1] = bArr[length];
                    i4++;
                    length = i7;
                }
                a3++;
                i2 = length;
            }
            i = 0;
            for (b bVar2 : bVarArr) {
                i += bVar2.a;
            }
            byte[] bArr2 = new byte[i];
            i = 0;
            i7 = bVarArr.length;
            for (length = 0; length < i7; length++) {
                b bVar3 = bVarArr[length];
                byte[] bArr3 = bVar3.b;
                i6 = bVar3.a;
                a(bArr3, i6);
                i2 = 0;
                while (i2 < i6) {
                    a3 = i + 1;
                    bArr2[i] = bArr3[i2];
                    i2++;
                    i = a3;
                }
            }
            return d.a(bArr2, b, errorCorrectionLevel, (Map) map);
        }
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
