package com.google.zxing.client.a;

import com.google.zxing.BarcodeFormat;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.Vector;
import java.util.regex.Pattern;

// compiled from: DecodeFormatManager.java
final class a {
    static final Vector<BarcodeFormat> a;
    static final Vector<BarcodeFormat> b;
    static final Vector<BarcodeFormat> c;
    static final Vector<BarcodeFormat> d;
    private static final Pattern e;

    static {
        e = Pattern.compile(MiPushClient.ACCEPT_TIME_SEPARATOR);
        Vector vector = new Vector(5);
        a = vector;
        vector.add(BarcodeFormat.UPC_A);
        a.add(BarcodeFormat.UPC_E);
        a.add(BarcodeFormat.EAN_13);
        a.add(BarcodeFormat.EAN_8);
        vector = new Vector(a.size() + 4);
        b = vector;
        vector.addAll(a);
        b.add(BarcodeFormat.CODE_39);
        b.add(BarcodeFormat.CODE_93);
        b.add(BarcodeFormat.CODE_128);
        b.add(BarcodeFormat.ITF);
        vector = new Vector(1);
        c = vector;
        vector.add(BarcodeFormat.QR_CODE);
        vector = new Vector(1);
        d = vector;
        vector.add(BarcodeFormat.DATA_MATRIX);
    }
}
