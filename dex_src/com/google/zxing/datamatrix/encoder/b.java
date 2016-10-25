package com.google.zxing.datamatrix.encoder;

import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: Base256Encoder.java
final class b implements g {
    b() {
    }

    public final void a(h hVar) {
        int a;
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('\u0000');
        while (hVar.b()) {
            stringBuilder.append(hVar.a());
            hVar.f++;
            a = j.a(hVar.a, hVar.f, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            if (a != 5) {
                hVar.g = a;
                break;
            }
        }
        int length = stringBuilder.length() - 1;
        a = (hVar.e.length() + length) + 1;
        hVar.a(a);
        a = hVar.h.b - a > 0 ? 1 : 0;
        if (hVar.b() || a != 0) {
            if (length <= 249) {
                stringBuilder.setCharAt(0, (char) length);
            } else if (length <= 249 || length > 1555) {
                throw new IllegalStateException(new StringBuilder("Message length not in valid ranges: ").append(length).toString());
            } else {
                stringBuilder.setCharAt(0, (char) ((length / 250) + 249));
                stringBuilder.insert(1, (char) (length % 250));
            }
        }
        int length2 = stringBuilder.length();
        while (i < length2) {
            char c;
            a = stringBuilder.charAt(i) + ((((hVar.e.length() + 1) * 149) % 255) + 1);
            if (a <= 255) {
                c = (char) a;
            } else {
                c = (char) (a - 256);
            }
            hVar.a(c);
            i++;
        }
    }
}
