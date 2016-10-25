package com.xunlei.xllib.b;

import com.xunlei.xllib.b.e.a.a;
import java.math.BigDecimal;

final class f implements a {
    final /* synthetic */ boolean a;
    final /* synthetic */ long b;

    f(boolean z, long j) {
        this.a = z;
        this.b = j;
    }

    public final boolean a(double d, int i, int i2, String[] strArr, String[] strArr2) {
        if (i2 > 0 && i > 0 && d > 999.9d && d < 1024.0d && i < strArr2.length - 1) {
            d = new BigDecimal(d).divide(new BigDecimal(1024), i2, 1).doubleValue();
            i++;
        }
        String toString = Double.toString(d);
        if (this.a || this.b == 0) {
            int indexOf = toString.indexOf(".");
            if (i2 == 0 && indexOf != -1) {
                toString = toString.substring(0, indexOf);
            }
            if (indexOf != -1 && toString.substring(indexOf).equals(".0")) {
                toString = toString.substring(0, indexOf);
            }
        }
        strArr[0] = toString;
        strArr[1] = strArr2[i];
        return true;
    }
}
