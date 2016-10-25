package com.google.zxing.c.a.a.a;

import com.google.zxing.common.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: AI01decoder.java
abstract class h extends j {
    h(a aVar) {
        super(aVar);
    }

    protected final void b(StringBuilder stringBuilder, int i) {
        stringBuilder.append("(01)");
        int length = stringBuilder.length();
        stringBuilder.append('9');
        a(stringBuilder, i, length);
    }

    protected final void a(StringBuilder stringBuilder, int i, int i2) {
        int i3;
        for (i3 = 0; i3 < 4; i3++) {
            int a = this.b.a((i3 * 10) + i, (int) XZBDevice.Stop);
            if (a / 100 == 0) {
                stringBuilder.append('0');
            }
            if (a / 10 == 0) {
                stringBuilder.append('0');
            }
            stringBuilder.append(a);
        }
        int i4 = 0;
        for (a = 0; a < 13; a++) {
            i3 = stringBuilder.charAt(a + i2) - 48;
            if ((a & 1) == 0) {
                i3 *= 3;
            }
            i4 += i3;
        }
        i3 = 10 - (i4 % 10);
        if (i3 == 10) {
            i3 = 0;
        }
        stringBuilder.append(i3);
    }
}
