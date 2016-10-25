package com.google.zxing.datamatrix.encoder;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: ASCIIEncoder.java
final class a implements g {
    a() {
    }

    public final void a(h hVar) {
        char charAt;
        CharSequence charSequence = hVar.a;
        int i = hVar.f;
        int length = charSequence.length();
        if (i < length) {
            charAt = charSequence.charAt(i);
            int i2 = i;
            i = 0;
            while (j.a(charAt) && i2 < length) {
                i++;
                i2++;
                if (i2 < length) {
                    charAt = charSequence.charAt(i2);
                }
            }
        } else {
            i = 0;
        }
        if (i >= 2) {
            charAt = hVar.a.charAt(hVar.f);
            char charAt2 = hVar.a.charAt(hVar.f + 1);
            if (j.a(charAt) && j.a(charAt2)) {
                hVar.a((char) ((((charAt - 48) * 10) + (charAt2 - 48)) + 130));
                hVar.f += 2;
                return;
            }
            throw new IllegalArgumentException(new StringBuilder("not digits: ").append(charAt).append(charAt2).toString());
        }
        charAt = hVar.a();
        i = j.a(hVar.a, hVar.f, 0);
        if (i != 0) {
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    hVar.a('\u00e6');
                    hVar.g = 1;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    hVar.a('\u00ef');
                    hVar.g = 2;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    hVar.a('\u00ee');
                    hVar.g = 3;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    hVar.a('\u00f0');
                    hVar.g = 4;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    hVar.a('\u00e7');
                    hVar.g = 5;
                default:
                    throw new IllegalStateException(new StringBuilder("Illegal mode: ").append(i).toString());
            }
        } else if (j.b(charAt)) {
            hVar.a('\u00eb');
            hVar.a((char) ((charAt - 128) + 1));
            hVar.f++;
        } else {
            hVar.a((char) (charAt + 1));
            hVar.f++;
        }
    }
}
