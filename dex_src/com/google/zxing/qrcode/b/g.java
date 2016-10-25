package com.google.zxing.qrcode.b;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.h;

// compiled from: QRCode.java
public final class g {
    Mode a;
    ErrorCorrectionLevel b;
    h c;
    int d;
    public b e;

    public g() {
        this.d = -1;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder(200);
        stringBuilder.append("<<\n");
        stringBuilder.append(" mode: ");
        stringBuilder.append(this.a);
        stringBuilder.append("\n ecLevel: ");
        stringBuilder.append(this.b);
        stringBuilder.append("\n version: ");
        stringBuilder.append(this.c);
        stringBuilder.append("\n maskPattern: ");
        stringBuilder.append(this.d);
        if (this.e == null) {
            stringBuilder.append("\n matrix: null\n");
        } else {
            stringBuilder.append("\n matrix:\n");
            stringBuilder.append(this.e);
        }
        stringBuilder.append(">>\n");
        return stringBuilder.toString();
    }
}
