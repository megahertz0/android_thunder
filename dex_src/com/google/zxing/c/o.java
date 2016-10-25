package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.c.a.a.c;
import com.google.zxing.c.a.e;
import com.google.zxing.common.a;
import com.google.zxing.j;
import com.google.zxing.l;
import com.google.zxing.m;
import com.google.zxing.n;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// compiled from: MultiFormatOneDReader.java
public final class o extends q {
    private final q[] a;

    public o(Map<DecodeHintType, ?> map) {
        Collection collection;
        if (map == null) {
            collection = null;
        } else {
            collection = (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        }
        boolean z = (map == null || map.get(DecodeHintType.ASSUME_CODE_39_CHECK_DIGIT) == null) ? false : true;
        Collection arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(BarcodeFormat.EAN_13) || collection.contains(BarcodeFormat.UPC_A) || collection.contains(BarcodeFormat.EAN_8) || collection.contains(BarcodeFormat.UPC_E)) {
                arrayList.add(new p(map));
            }
            if (collection.contains(BarcodeFormat.CODE_39)) {
                arrayList.add(new e(z));
            }
            if (collection.contains(BarcodeFormat.CODE_93)) {
                arrayList.add(new g());
            }
            if (collection.contains(BarcodeFormat.CODE_128)) {
                arrayList.add(new c());
            }
            if (collection.contains(BarcodeFormat.ITF)) {
                arrayList.add(new m());
            }
            if (collection.contains(BarcodeFormat.CODABAR)) {
                arrayList.add(new a());
            }
            if (collection.contains(BarcodeFormat.RSS_14)) {
                arrayList.add(new e());
            }
            if (collection.contains(BarcodeFormat.RSS_EXPANDED)) {
                arrayList.add(new c());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new p(map));
            arrayList.add(new e());
            arrayList.add(new a());
            arrayList.add(new g());
            arrayList.add(new c());
            arrayList.add(new m());
            arrayList.add(new e());
            arrayList.add(new c());
        }
        this.a = (q[]) arrayList.toArray(new q[arrayList.size()]);
    }

    public final n a(int i, a aVar, Map<DecodeHintType, ?> map) throws j {
        for (q qVar : this.a) {
            try {
                return qVar.a(i, aVar, (Map) map);
            } catch (m e) {
            }
        }
        throw j.a();
    }

    public final void a() {
        for (l lVar : this.a) {
            lVar.a();
        }
    }
}
