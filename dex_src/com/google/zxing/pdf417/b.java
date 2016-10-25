package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.c;
import com.google.zxing.common.d;
import com.google.zxing.e;
import com.google.zxing.j;
import com.google.zxing.l;
import com.google.zxing.n;
import com.google.zxing.o;
import com.google.zxing.pdf417.a.k;
import com.google.zxing.pdf417.b.a;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// compiled from: PDF417Reader.java
public final class b implements l {
    private static int a(o oVar, o oVar2) {
        return (oVar == null || oVar2 == null) ? 0 : (int) Math.abs(oVar.a - oVar2.a);
    }

    private static int b(o oVar, o oVar2) {
        return (oVar == null || oVar2 == null) ? InMobiClientPositioning.NO_REPEAT : (int) Math.abs(oVar.a - oVar2.a);
    }

    public final void a() {
    }

    public final n a(com.google.zxing.b bVar, Map<DecodeHintType, ?> map) throws j, e, c {
        List arrayList = new ArrayList();
        com.google.zxing.pdf417.b.b a = a.a(bVar);
        for (o[] oVarArr : a.b) {
            d a2 = k.a(a.a, oVarArr[4], oVarArr[5], oVarArr[6], oVarArr[7], Math.min(Math.min(b(oVarArr[0], oVarArr[4]), (b(oVarArr[6], oVarArr[2]) * 17) / 18), Math.min(b(oVarArr[1], oVarArr[5]), (b(oVarArr[7], oVarArr[3]) * 17) / 18)), Math.max(Math.max(a(oVarArr[0], oVarArr[4]), (a(oVarArr[6], oVarArr[2]) * 17) / 18), Math.max(a(oVarArr[1], oVarArr[5]), (a(oVarArr[7], oVarArr[3]) * 17) / 18)));
            n nVar = new n(a2.b, a2.a, oVarArr, BarcodeFormat.PDF_417);
            nVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, a2.d);
            c cVar = (c) a2.g;
            if (cVar != null) {
                nVar.a(ResultMetadataType.PDF417_EXTRA_METADATA, cVar);
            }
            arrayList.add(nVar);
        }
        n[] nVarArr = (n[]) arrayList.toArray(new n[arrayList.size()]);
        if (nVarArr != null && nVarArr.length != 0 && nVarArr[0] != null) {
            return nVarArr[0];
        }
        throw j.a();
    }
}
