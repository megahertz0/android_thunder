package com.google.zxing;

import java.util.EnumMap;
import java.util.Map;

// compiled from: Result.java
public final class n {
    public final String a;
    public final byte[] b;
    public o[] c;
    public final BarcodeFormat d;
    public Map<ResultMetadataType, Object> e;
    private final long f;

    public n(String str, byte[] bArr, o[] oVarArr, BarcodeFormat barcodeFormat) {
        this(str, bArr, oVarArr, barcodeFormat, System.currentTimeMillis());
    }

    private n(String str, byte[] bArr, o[] oVarArr, BarcodeFormat barcodeFormat, long j) {
        this.a = str;
        this.b = bArr;
        this.c = oVarArr;
        this.d = barcodeFormat;
        this.e = null;
        this.f = j;
    }

    public final void a(ResultMetadataType resultMetadataType, Object obj) {
        if (this.e == null) {
            this.e = new EnumMap(ResultMetadataType.class);
        }
        this.e.put(resultMetadataType, obj);
    }

    public final void a(Map<ResultMetadataType, Object> map) {
        if (map == null) {
            return;
        }
        if (this.e == null) {
            this.e = map;
        } else {
            this.e.putAll(map);
        }
    }

    public final String toString() {
        return this.a;
    }
}
