package com.google.zxing.datamatrix.encoder;

import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: EdifactEncoder.java
final class f implements g {
    f() {
    }

    public final void a(h hVar) {
        Object obj = 1;
        CharSequence stringBuilder = new StringBuilder();
        while (hVar.b()) {
            char a = hVar.a();
            if (a >= ' ' && a <= '?') {
                stringBuilder.append(a);
            } else if (a < '@' || a > '^') {
                j.c(a);
            } else {
                stringBuilder.append((char) (a - 64));
            }
            hVar.f++;
            if (stringBuilder.length() >= 4) {
                hVar.a(a(stringBuilder));
                stringBuilder.delete(0, XZBDevice.DOWNLOAD_LIST_ALL);
                if (j.a(hVar.a, hVar.f, XZBDevice.DOWNLOAD_LIST_ALL) != 4) {
                    hVar.g = 0;
                    break;
                }
            }
        }
        stringBuilder.append('\u001f');
        int length = stringBuilder.length();
        if (length == 0) {
            hVar.g = 0;
            return;
        }
        if (length == 1) {
            hVar.d();
            int length2 = hVar.h.b - hVar.e.length();
            if (hVar.c() == 0 && length2 <= 2) {
                hVar.g = 0;
                return;
            }
        }
        if (length > 4) {
            throw new IllegalStateException("Count must not exceed 4");
        } else {
            int i;
            length--;
            String a2 = a(stringBuilder);
            if ((!hVar.b() ? 1 : 0) == 0 || length > 2) {
                i = 0;
            }
            if (length <= 2) {
                hVar.a(hVar.e.length() + length);
                if (hVar.h.b - hVar.e.length() >= 3) {
                    hVar.a(hVar.e.length() + a2.length());
                    i = 0;
                }
            }
            if (i != 0) {
                hVar.h = null;
                hVar.f -= length;
            } else {
                hVar.a(a2);
            }
            hVar.g = 0;
        }
    }

    private static String a(CharSequence charSequence) {
        int i = 0;
        int length = charSequence.length() + 0;
        if (length == 0) {
            throw new IllegalStateException("StringBuilder must not be empty");
        }
        char charAt;
        int i2;
        int charAt2;
        char charAt3 = charSequence.charAt(0);
        if (length >= 2) {
            charAt = charSequence.charAt(1);
        } else {
            i2 = 0;
        }
        if (length >= 3) {
            charAt2 = charSequence.charAt(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        } else {
            charAt2 = 0;
        }
        if (length >= 4) {
            i = charSequence.charAt(XZBDevice.DOWNLOAD_LIST_FAILED);
        }
        charAt2 <<= 6;
        i += charAt2 + ((i2 << 12) + (charAt3 << 18));
        char c = (char) ((i >> 16) & 255);
        charAt = (char) ((i >> 8) & 255);
        char c2 = (char) (i & 255);
        StringBuilder stringBuilder = new StringBuilder(3);
        stringBuilder.append(c);
        if (length >= 2) {
            stringBuilder.append(charAt);
        }
        if (length >= 3) {
            stringBuilder.append(c2);
        }
        return stringBuilder.toString();
    }
}
