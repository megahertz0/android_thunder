package com.google.zxing.c.a.a.a;

import com.google.zxing.common.a;
import com.google.zxing.j;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: AI013x0xDecoder.java
abstract class f extends i {
    f(a aVar) {
        super(aVar);
    }

    public final String a() throws j {
        if (this.a.b != 60) {
            throw j.a();
        }
        StringBuilder stringBuilder = new StringBuilder();
        b(stringBuilder, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
        b(stringBuilder, R.styleable.AppCompatTheme_actionDropDownStyle, XZBDevice.Delete);
        return stringBuilder.toString();
    }
}
