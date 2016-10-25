package com.google.zxing.datamatrix.encoder;

import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: X12Encoder.java
final class m extends c {
    m() {
    }

    public final int a() {
        return XZBDevice.DOWNLOAD_LIST_FAILED;
    }

    public final void a(h hVar) {
        StringBuilder stringBuilder = new StringBuilder();
        while (hVar.b()) {
            char a = hVar.a();
            hVar.f++;
            a(a, stringBuilder);
            if (stringBuilder.length() % 3 == 0) {
                a(hVar, stringBuilder);
                int a2 = j.a(hVar.a, hVar.f, XZBDevice.DOWNLOAD_LIST_FAILED);
                if (a2 != 3) {
                    hVar.g = a2;
                    break;
                }
            }
        }
        b(hVar, stringBuilder);
    }

    final int a(char c, StringBuilder stringBuilder) {
        if (c == '\r') {
            stringBuilder.append('\u0000');
        } else if (c == '*') {
            stringBuilder.append('\u0001');
        } else if (c == '>') {
            stringBuilder.append('\u0002');
        } else if (c == ' ') {
            stringBuilder.append('\u0003');
        } else if (c >= '0' && c <= '9') {
            stringBuilder.append((char) ((c - 48) + 4));
        } else if (c < 'A' || c > 'Z') {
            j.c(c);
        } else {
            stringBuilder.append((char) ((c - 65) + 14));
        }
        return 1;
    }

    final void b(h hVar, StringBuilder stringBuilder) {
        hVar.d();
        int length = hVar.h.b - hVar.e.length();
        hVar.f -= stringBuilder.length();
        if (hVar.c() > 1 || length > 1 || hVar.c() != length) {
            hVar.a('\u00fe');
        }
        if (hVar.g < 0) {
            hVar.g = 0;
        }
    }
}
