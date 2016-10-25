package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.b;
import com.google.zxing.pdf417.encoder.Compaction;
import com.google.zxing.pdf417.encoder.c;
import com.google.zxing.q;
import com.google.zxing.r;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Map;

// compiled from: PDF417Writer.java
public final class d implements q {
    public final b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws r {
        if (barcodeFormat != BarcodeFormat.PDF_417) {
            throw new IllegalArgumentException(new StringBuilder("Can only encode PDF_417, but got ").append(barcodeFormat).toString());
        }
        int i3;
        int i4;
        com.google.zxing.pdf417.encoder.d dVar = new com.google.zxing.pdf417.encoder.d();
        int i5 = R.styleable.AppCompatTheme_actionModeSplitBackground;
        int i6 = XZBDevice.DOWNLOAD_LIST_RECYCLE;
        if (map != null) {
            if (map.containsKey(EncodeHintType.PDF417_COMPACT)) {
                dVar.b = ((Boolean) map.get(EncodeHintType.PDF417_COMPACT)).booleanValue();
            }
            if (map.containsKey(EncodeHintType.PDF417_COMPACTION)) {
                dVar.c = (Compaction) map.get(EncodeHintType.PDF417_COMPACTION);
            }
            if (map.containsKey(EncodeHintType.PDF417_DIMENSIONS)) {
                c cVar = (c) map.get(EncodeHintType.PDF417_DIMENSIONS);
                i3 = cVar.b;
                int i7 = cVar.a;
                int i8 = cVar.d;
                i4 = cVar.c;
                dVar.f = i3;
                dVar.e = i7;
                dVar.g = i8;
                dVar.h = i4;
            }
            if (map.containsKey(EncodeHintType.MARGIN)) {
                i5 = ((Number) map.get(EncodeHintType.MARGIN)).intValue();
            }
            if (map.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                i6 = ((Number) map.get(EncodeHintType.ERROR_CORRECTION)).intValue();
            }
            if (map.containsKey(EncodeHintType.CHARACTER_SET)) {
                dVar.d = Charset.forName((String) map.get(EncodeHintType.CHARACTER_SET));
            }
        }
        dVar.a(str, i6);
        byte[][] a = dVar.a.a(1, XZBDevice.DOWNLOAD_LIST_ALL);
        i4 = i2 > i ? 1 : 0;
        if (a[0].length < a.length) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        if ((i4 ^ i3) != 0) {
            a = a(a);
            i4 = 1;
        } else {
            i4 = 0;
        }
        i3 = i / a[0].length;
        int length = i2 / a.length;
        if (i3 < length) {
            length = i3;
        }
        if (length <= 1) {
            return a(a, i5);
        }
        byte[][] a2;
        a = dVar.a.a(length, length * 4);
        if (i4 != 0) {
            a2 = a(a);
        } else {
            a2 = a;
        }
        return a(a2, i5);
    }

    private static b a(byte[][] bArr, int i) {
        b bVar = new b(bArr[0].length + (i * 2), bArr.length + (i * 2));
        bVar.a();
        int i2 = (bVar.b - i) - 1;
        int i3 = 0;
        while (i3 < bArr.length) {
            for (int i4 = 0; i4 < bArr[0].length; i4++) {
                if (bArr[i3][i4] == 1) {
                    bVar.b(i4 + i, i2);
                }
            }
            i3++;
            i2--;
        }
        return bVar;
    }

    private static byte[][] a(byte[][] bArr) {
        byte[][] bArr2 = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{bArr[0].length, bArr.length});
        for (int i = 0; i < bArr.length; i++) {
            int length = (bArr.length - i) - 1;
            for (int i2 = 0; i2 < bArr[0].length; i2++) {
                bArr2[i2][length] = bArr[i][i2];
            }
        }
        return bArr2;
    }
}
