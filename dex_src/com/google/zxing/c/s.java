package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.b;
import com.google.zxing.c;
import com.google.zxing.common.a;
import com.google.zxing.e;
import com.google.zxing.j;
import com.google.zxing.n;
import java.util.Map;

// compiled from: UPCAReader.java
public final class s extends x {
    private final x a;

    public s() {
        this.a = new h();
    }

    public final n a(int i, a aVar, int[] iArr, Map<DecodeHintType, ?> map) throws j, e, c {
        return a(this.a.a(i, aVar, iArr, (Map) map));
    }

    public final n a(int i, a aVar, Map<DecodeHintType, ?> map) throws j, e, c {
        return a(this.a.a(i, aVar, (Map) map));
    }

    public final n a(b bVar, Map<DecodeHintType, ?> map) throws j, e {
        return a(this.a.a(bVar, map));
    }

    final BarcodeFormat b() {
        return BarcodeFormat.UPC_A;
    }

    protected final int a(a aVar, int[] iArr, StringBuilder stringBuilder) throws j {
        return this.a.a(aVar, iArr, stringBuilder);
    }

    private static n a(n nVar) throws e {
        String str = nVar.a;
        if (str.charAt(0) == '0') {
            return new n(str.substring(1), null, nVar.c, BarcodeFormat.UPC_A);
        }
        throw e.a();
    }
}
