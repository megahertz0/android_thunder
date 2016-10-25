package com.google.zxing;

import com.google.zxing.a.b;
import com.google.zxing.c.o;
import com.google.zxing.qrcode.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// compiled from: MultiFormatReader.java
public final class g implements l {
    public l[] a;
    private Map<DecodeHintType, ?> b;

    public final n a(b bVar, Map<DecodeHintType, ?> map) throws j {
        a((Map) map);
        return a(bVar);
    }

    public final void a(Map<DecodeHintType, ?> map) {
        Collection collection;
        Object obj = null;
        this.b = map;
        if (map == null || !map.containsKey(DecodeHintType.TRY_HARDER)) {
            Object obj2 = null;
        } else {
            int i = 1;
        }
        if (map == null) {
            collection = null;
        } else {
            collection = (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        }
        Collection arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(BarcodeFormat.UPC_A) || collection.contains(BarcodeFormat.UPC_E) || collection.contains(BarcodeFormat.EAN_13) || collection.contains(BarcodeFormat.EAN_8) || collection.contains(BarcodeFormat.CODABAR) || collection.contains(BarcodeFormat.CODE_39) || collection.contains(BarcodeFormat.CODE_93) || collection.contains(BarcodeFormat.CODE_128) || collection.contains(BarcodeFormat.ITF) || collection.contains(BarcodeFormat.RSS_14) || collection.contains(BarcodeFormat.RSS_EXPANDED)) {
                int i2 = 1;
            }
            if (obj != null && obj2 == null) {
                arrayList.add(new o(map));
            }
            if (collection.contains(BarcodeFormat.QR_CODE)) {
                arrayList.add(new a());
            }
            if (collection.contains(BarcodeFormat.DATA_MATRIX)) {
                arrayList.add(new com.google.zxing.datamatrix.a());
            }
            if (collection.contains(BarcodeFormat.AZTEC)) {
                arrayList.add(new b());
            }
            if (collection.contains(BarcodeFormat.PDF_417)) {
                arrayList.add(new com.google.zxing.pdf417.b());
            }
            if (collection.contains(BarcodeFormat.MAXICODE)) {
                arrayList.add(new com.google.zxing.b.a());
            }
            if (!(obj == null || obj2 == null)) {
                arrayList.add(new o(map));
            }
        }
        if (arrayList.isEmpty()) {
            if (obj2 == null) {
                arrayList.add(new o(map));
            }
            arrayList.add(new a());
            arrayList.add(new com.google.zxing.datamatrix.a());
            arrayList.add(new b());
            arrayList.add(new com.google.zxing.pdf417.b());
            arrayList.add(new com.google.zxing.b.a());
            if (obj2 != null) {
                arrayList.add(new o(map));
            }
        }
        this.a = (l[]) arrayList.toArray(new l[arrayList.size()]);
    }

    public final void a() {
        if (this.a != null) {
            for (l lVar : this.a) {
                lVar.a();
            }
        }
    }

    public final n a(b bVar) throws j {
        if (this.a != null) {
            for (l lVar : this.a) {
                try {
                    return lVar.a(bVar, this.b);
                } catch (m e) {
                }
            }
        }
        throw j.a();
    }
}
