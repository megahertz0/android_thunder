package com.google.zxing.datamatrix.encoder;

import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: C40Encoder.java
class c implements g {
    c() {
    }

    public int a() {
        return 1;
    }

    public void a(h hVar) {
        StringBuilder stringBuilder = new StringBuilder();
        while (hVar.b()) {
            char a = hVar.a();
            hVar.f++;
            int a2 = a(a, stringBuilder);
            int length = ((stringBuilder.length() / 3) * 2) + hVar.e.length();
            hVar.a(length);
            length = hVar.h.b - length;
            if (!hVar.b()) {
                StringBuilder stringBuilder2 = new StringBuilder();
                if (stringBuilder.length() % 3 == 2) {
                    if (length < 2 || length > 2) {
                        a2 = a(hVar, stringBuilder, stringBuilder2, a2);
                    }
                }
                while (stringBuilder.length() % 3 == 1) {
                    if ((a2 > 3 || length == 1) && a2 <= 3) {
                        break;
                    }
                    a2 = a(hVar, stringBuilder, stringBuilder2, a2);
                }
            } else if (stringBuilder.length() % 3 == 0) {
                a2 = j.a(hVar.a, hVar.f, a());
                if (a2 != a()) {
                    hVar.g = a2;
                    break;
                }
            }
        }
        b(hVar, stringBuilder);
    }

    private int a(h hVar, StringBuilder stringBuilder, StringBuilder stringBuilder2, int i) {
        int length = stringBuilder.length();
        stringBuilder.delete(length - i, length);
        hVar.f--;
        length = a(hVar.a(), stringBuilder2);
        hVar.h = null;
        return length;
    }

    static void a(h hVar, StringBuilder stringBuilder) {
        char charAt = (char) (((((stringBuilder.charAt(0) * 1600) + (stringBuilder.charAt(1) * 40)) + stringBuilder.charAt(XZBDevice.DOWNLOAD_LIST_RECYCLE)) + 1) % 256);
        hVar.a(new String(new char[]{(char) (r0 / 256), charAt}));
        stringBuilder.delete(0, XZBDevice.DOWNLOAD_LIST_FAILED);
    }

    void b(h hVar, StringBuilder stringBuilder) {
        int length = stringBuilder.length() % 3;
        int length2 = ((stringBuilder.length() / 3) * 2) + hVar.e.length();
        hVar.a(length2);
        length2 = hVar.h.b - length2;
        if (length == 2) {
            stringBuilder.append('\u0000');
            while (stringBuilder.length() >= 3) {
                a(hVar, stringBuilder);
            }
            if (hVar.b()) {
                hVar.a('\u00fe');
            }
        } else if (length2 == 1 && length == 1) {
            while (stringBuilder.length() >= 3) {
                a(hVar, stringBuilder);
            }
            if (hVar.b()) {
                hVar.a('\u00fe');
            }
            hVar.f--;
        } else if (length == 0) {
            while (stringBuilder.length() >= 3) {
                a(hVar, stringBuilder);
            }
            if (length2 > 0 || hVar.b()) {
                hVar.a('\u00fe');
            }
        } else {
            throw new IllegalStateException("Unexpected case. Please report!");
        }
        hVar.g = 0;
    }

    int a(char c, StringBuilder stringBuilder) {
        if (c == ' ') {
            stringBuilder.append('\u0003');
            return 1;
        } else if (c >= '0' && c <= '9') {
            stringBuilder.append((char) ((c - 48) + 4));
            return 1;
        } else if (c >= 'A' && c <= 'Z') {
            stringBuilder.append((char) ((c - 65) + 14));
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
        } else if (c >= '`' && c <= '\u007f') {
            stringBuilder.append('\u0002');
            stringBuilder.append((char) (c - 96));
            return 2;
        } else if (c >= '\u0080') {
            stringBuilder.append("\u0001\u001e");
            return a((char) (c - 128), stringBuilder) + 2;
        } else {
            throw new IllegalArgumentException(new StringBuilder("Illegal character: ").append(c).toString());
        }
    }
}
