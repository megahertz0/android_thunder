package com.google.zxing.datamatrix.encoder;

import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: TextEncoder.java
final class l extends c {
    l() {
    }

    public final int a() {
        return XZBDevice.DOWNLOAD_LIST_RECYCLE;
    }

    final int a(char c, StringBuilder stringBuilder) {
        if (c == ' ') {
            stringBuilder.append('\u0003');
            return 1;
        } else if (c >= '0' && c <= '9') {
            stringBuilder.append((char) ((c - 48) + 4));
            return 1;
        } else if (c >= 'a' && c <= 'z') {
            stringBuilder.append((char) ((c - 97) + 14));
            return 1;
        } else if (c >= '\u0000' && c <= '\u001f') {
            stringBuilder.append('\u0000');
            stringBuilder.append(c);
            return 2;
        } else if (c >= '!' && c <= '/') {
            stringBuilder.append('\u0001');
            stringBuilder.append((char) (c - 33));
            return 2;
        } else if (c >= ':' && c <= '@') {
            stringBuilder.append('\u0001');
            stringBuilder.append((char) ((c - 58) + 15));
            return 2;
        } else if (c >= '[' && c <= '_') {
            stringBuilder.append('\u0001');
            stringBuilder.append((char) ((c - 91) + 22));
            return 2;
        } else if (c == '`') {
            stringBuilder.append('\u0002');
            stringBuilder.append((char) (c - 96));
            return 2;
        } else if (c >= 'A' && c <= 'Z') {
            stringBuilder.append('\u0002');
            stringBuilder.append((char) ((c - 65) + 1));
            return 2;
        } else if (c >= '{' && c <= '\u007f') {
            stringBuilder.append('\u0002');
            stringBuilder.append((char) ((c - 123) + 27));
            return 2;
        } else if (c >= '\u0080') {
            stringBuilder.append("\u0001\u001e");
            return a((char) (c - 128), stringBuilder) + 2;
        } else {
            j.c(c);
            return -1;
        }
    }
}
