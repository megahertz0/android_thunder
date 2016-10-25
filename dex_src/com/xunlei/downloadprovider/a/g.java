package com.xunlei.downloadprovider.a;

import android.annotation.SuppressLint;
import android.content.Context;

// compiled from: DipPixelUtil.java
public final class g {
    @SuppressLint({"DefaultLocale"})
    public static int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }
}
