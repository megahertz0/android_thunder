package com.google.zxing.datamatrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.d;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.datamatrix.encoder.e;
import com.google.zxing.datamatrix.encoder.i;
import com.google.zxing.datamatrix.encoder.j;
import com.google.zxing.datamatrix.encoder.k;
import com.google.zxing.q;
import java.util.Map;

// compiled from: DataMatrixWriter.java
public final class b implements q {
    public final com.google.zxing.common.b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        d dVar = null;
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (barcodeFormat != BarcodeFormat.DATA_MATRIX) {
            throw new IllegalArgumentException(new StringBuilder("Can only encode DATA_MATRIX, but got ").append(barcodeFormat).toString());
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException(new StringBuilder("Requested dimensions are too small: ").append(i).append('x').append(i2).toString());
        } else {
            d dVar2;
            SymbolShapeHint symbolShapeHint = SymbolShapeHint.FORCE_NONE;
            if (map != null) {
                SymbolShapeHint symbolShapeHint2 = (SymbolShapeHint) map.get(EncodeHintType.DATA_MATRIX_SHAPE);
                if (symbolShapeHint2 != null) {
                    symbolShapeHint = symbolShapeHint2;
                }
                d dVar3 = (d) map.get(EncodeHintType.MIN_SIZE);
                if (dVar3 != null) {
                    dVar2 = dVar3;
                } else {
                    dVar2 = null;
                }
                dVar3 = (d) map.get(EncodeHintType.MAX_SIZE);
                if (dVar3 != null) {
                    dVar = dVar3;
                }
            } else {
                dVar2 = null;
            }
            String a = j.a(str, symbolShapeHint, dVar2, dVar);
            k a2 = k.a(a.length(), symbolShapeHint, dVar2, dVar);
            e eVar = new e(i.a(a, a2), a2.b(), a2.c());
            eVar.a();
            return a(eVar, a2);
        }
    }

    private static com.google.zxing.common.b a(e eVar, k kVar) {
        int i;
        int i2;
        int i3;
        int b = kVar.b();
        int c = kVar.c();
        com.google.zxing.qrcode.b.b bVar = new com.google.zxing.qrcode.b.b(kVar.d(), kVar.e());
        int i4 = 0;
        int i5 = 0;
        while (i4 < c) {
            boolean z;
            if (i4 % kVar.e == 0) {
                i = 0;
                for (i2 = 0; i2 < kVar.d(); i2++) {
                    if (i2 % 2 == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    bVar.a(i, i5, z);
                    i++;
                }
                i2 = i5 + 1;
            } else {
                i2 = i5;
            }
            i = 0;
            for (i5 = 0; i5 < b; i5++) {
                if (i5 % kVar.d == 0) {
                    bVar.a(i, i2, true);
                    i++;
                }
                if (eVar.b[(eVar.a * i4) + i5] == (byte) 1) {
                    z = true;
                } else {
                    z = false;
                }
                bVar.a(i, i2, z);
                i3 = i + 1;
                if (i5 % kVar.d == kVar.d - 1) {
                    bVar.a(i3, i2, i4 % 2 == 0);
                    i = i3 + 1;
                } else {
                    i = i3;
                }
            }
            i3 = i2 + 1;
            if (i4 % kVar.e == kVar.e - 1) {
                i = 0;
                for (i2 = 0; i2 < kVar.d(); i2++) {
                    bVar.a(i, i3, true);
                    i++;
                }
                i2 = i3 + 1;
            } else {
                i2 = i3;
            }
            i4++;
            i5 = i2;
        }
        i3 = bVar.b;
        i5 = bVar.c;
        com.google.zxing.common.b bVar2 = new com.google.zxing.common.b(i3, i5);
        bVar2.a();
        for (i = 0; i < i3; i++) {
            for (i2 = 0; i2 < i5; i2++) {
                if (bVar.a(i, i2) == (byte) 1) {
                    bVar2.b(i, i2);
                }
            }
        }
        return bVar2;
    }
}
