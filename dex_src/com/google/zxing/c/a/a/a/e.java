package com.google.zxing.c.a.a.a;

import com.google.zxing.common.a;
import com.google.zxing.j;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: AI013x0x1xDecoder.java
public final class e extends i {
    private final String c;
    private final String d;

    public e(a aVar, String str, String str2) {
        super(aVar);
        this.c = str2;
        this.d = str;
    }

    public final String a() throws j {
        if (this.a.b != 84) {
            throw j.a();
        }
        StringBuilder stringBuilder = new StringBuilder();
        b(stringBuilder, XZBDevice.Wait);
        b(stringBuilder, R.styleable.AppCompatTheme_homeAsUpIndicator, R.styleable.Toolbar_navigationIcon);
        int a = this.b.a((int) R.styleable.AppCompatTheme_searchViewStyle, (int) R.styleable.Toolbar_titleMarginBottom);
        if (a != 38400) {
            stringBuilder.append('(');
            stringBuilder.append(this.c);
            stringBuilder.append(')');
            int i = a % 32;
            a /= 32;
            int i2 = (a % 12) + 1;
            a /= 12;
            if (a / 10 == 0) {
                stringBuilder.append('0');
            }
            stringBuilder.append(a);
            if (i2 / 10 == 0) {
                stringBuilder.append('0');
            }
            stringBuilder.append(i2);
            if (i / 10 == 0) {
                stringBuilder.append('0');
            }
            stringBuilder.append(i);
        }
        return stringBuilder.toString();
    }

    protected final void a(StringBuilder stringBuilder, int i) {
        int i2 = i / 100000;
        stringBuilder.append('(');
        stringBuilder.append(this.d);
        stringBuilder.append(i2);
        stringBuilder.append(')');
    }

    protected final int a(int i) {
        return i % 100000;
    }
}
