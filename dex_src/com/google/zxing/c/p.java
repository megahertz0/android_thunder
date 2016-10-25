package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.common.a;
import com.google.zxing.j;
import com.google.zxing.l;
import com.google.zxing.m;
import com.google.zxing.n;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// compiled from: MultiFormatUPCEANReader.java
public final class p extends q {
    private final x[] a;

    public p(Map<DecodeHintType, ?> map) {
        Collection collection;
        if (map == null) {
            collection = null;
        } else {
            collection = (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        }
        Collection arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(BarcodeFormat.EAN_13)) {
                arrayList.add(new h());
            } else if (collection.contains(BarcodeFormat.UPC_A)) {
                arrayList.add(new s());
            }
            if (collection.contains(BarcodeFormat.EAN_8)) {
                arrayList.add(new j());
            }
            if (collection.contains(BarcodeFormat.UPC_E)) {
                arrayList.add(new z());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new h());
            arrayList.add(new j());
            arrayList.add(new z());
        }
        this.a = (x[]) arrayList.toArray(new x[arrayList.size()]);
    }

    public final n a(int i, a aVar, Map<DecodeHintType, ?> map) throws j {
        int[] a = x.a(aVar);
        x[] xVarArr = this.a;
        int length = xVarArr.length;
        int i2 = 0;
        while (i2 < length) {
            try {
                Collection collection;
                n a2 = xVarArr[i2].a(i, aVar, a, (Map) map);
                i2 = a2.d;
                xVarArr = BarcodeFormat.EAN_13;
                int i3 = (i2 == xVarArr && a2.a.charAt(0) == '0') ? 1 : 0;
                if (map == null) {
                    collection = null;
                } else {
                    collection = (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
                }
                if (collection == null || collection.contains(BarcodeFormat.UPC_A)) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (i3 == 0 || r0 == 0) {
                    return a2;
                }
                n nVar = new n(a2.a.substring(1), a2.b, a2.c, BarcodeFormat.UPC_A);
                nVar.a(a2.e);
                return nVar;
            } catch (m e) {
                i2++;
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
