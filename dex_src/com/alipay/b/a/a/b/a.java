package com.alipay.b.a.a.b;

import android.content.Context;
import com.xunlei.tdlive.R;

public final class a {
    private static a a;

    static {
        a = new a();
    }

    private a() {
    }

    public static a a() {
        return a;
    }

    public static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), R.styleable.Toolbar_titleMarginBottom).versionName;
        } catch (Exception e) {
            return "0.0.0";
        }
    }
}
