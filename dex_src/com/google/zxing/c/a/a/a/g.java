package com.google.zxing.c.a.a.a;

import com.google.zxing.common.a;
import com.google.zxing.e;
import com.google.zxing.j;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: AI01AndOtherAIs.java
public final class g extends h {
    public g(a aVar) {
        super(aVar);
    }

    public final String a() throws j, e {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(01)");
        int length = stringBuilder.length();
        stringBuilder.append(this.b.a((int) XZBDevice.DOWNLOAD_LIST_ALL, (int) XZBDevice.DOWNLOAD_LIST_ALL));
        a(stringBuilder, XZBDevice.Wait, length);
        return this.b.a(stringBuilder, (int) R.styleable.AppCompatTheme_homeAsUpIndicator);
    }
}
