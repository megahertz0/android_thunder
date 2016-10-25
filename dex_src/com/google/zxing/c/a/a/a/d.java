package com.google.zxing.c.a.a.a;

import com.google.zxing.common.a;
import com.google.zxing.e;
import com.google.zxing.j;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: AI01393xDecoder.java
public final class d extends h {
    public d(a aVar) {
        super(aVar);
    }

    public final String a() throws j, e {
        if (this.a.b < 48) {
            throw j.a();
        }
        StringBuilder stringBuilder = new StringBuilder();
        b(stringBuilder, XZBDevice.Wait);
        int a = this.b.a((int) R.styleable.AppCompatTheme_homeAsUpIndicator, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
        stringBuilder.append("(393");
        stringBuilder.append(a);
        stringBuilder.append(')');
        a = this.b.a((int) R.styleable.AppCompatTheme_buttonBarStyle, (int) XZBDevice.Stop);
        if (a / 100 == 0) {
            stringBuilder.append('0');
        }
        if (a / 10 == 0) {
            stringBuilder.append('0');
        }
        stringBuilder.append(a);
        stringBuilder.append(this.b.a((int) R.styleable.AppCompatTheme_popupMenuStyle, null).a);
        return stringBuilder.toString();
    }
}
