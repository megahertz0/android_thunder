package com.google.zxing.qrcode.decoder;

import com.google.zxing.common.b;
import com.google.zxing.e;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: BitMatrixParser.java
final class a {
    final b a;
    h b;
    f c;
    boolean d;

    a(b bVar) throws e {
        int i = bVar.b;
        if (i < 21 || (i & 3) != 1) {
            throw e.a();
        }
        this.a = bVar;
    }

    final f a() throws e {
        int i = 0;
        if (this.c != null) {
            return this.c;
        }
        int i2;
        int i3 = 0;
        for (i2 = 0; i2 < 6; i2++) {
            i3 = a(i2, XZBDevice.Wait, i3);
        }
        i3 = a(XZBDevice.Wait, R.styleable.Toolbar_contentInsetLeft, a(XZBDevice.Wait, XZBDevice.Wait, a(R.styleable.Toolbar_contentInsetLeft, XZBDevice.Wait, i3)));
        for (i2 = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED; i2 >= 0; i2--) {
            i3 = a(XZBDevice.Wait, i2, i3);
        }
        int i4 = this.a.b;
        int i5 = i4 - 7;
        for (i2 = i4 - 1; i2 >= i5; i2--) {
            i = a(XZBDevice.Wait, i2, i);
        }
        for (i2 = i4 - 8; i2 < i4; i2++) {
            i = a(i2, XZBDevice.Wait, i);
        }
        this.c = f.b(i3, i);
        if (this.c != null) {
            return this.c;
        }
        throw e.a();
    }

    final h b() throws e {
        if (this.b != null) {
            return this.b;
        }
        int i = this.a.b;
        int i2 = (i - 17) / 4;
        if (i2 <= 6) {
            return h.b(i2);
        }
        int i3 = i - 11;
        int i4 = 0;
        for (int i5 = 5; i5 >= 0; i5--) {
            for (i2 = i - 9; i2 >= i3; i2--) {
                i4 = a(i2, i5, i4);
            }
        }
        h c = h.c(i4);
        if (c == null || c.a() != i) {
            int i6 = 0;
            for (i2 = 5; i2 >= 0; i2--) {
                int i7 = i - 9;
                while (i7 >= i3) {
                    i4 = a(i2, i7, i6);
                    i7--;
                    i6 = i4;
                }
            }
            c = h.c(i6);
            if (c == null || c.a() != i) {
                throw e.a();
            }
            this.b = c;
            return c;
        }
        this.b = c;
        return c;
    }

    private int a(int i, int i2, int i3) {
        return this.d ? this.a.a(i2, i) : this.a.a(i, i2) ? (i3 << 1) | 1 : i3 << 1;
    }
}
