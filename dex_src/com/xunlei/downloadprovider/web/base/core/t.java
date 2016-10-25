package com.xunlei.downloadprovider.web.base.core;

import android.text.TextUtils;

// compiled from: WebUtils.java
public final class t {
    public static boolean a(String str) {
        return TextUtils.isEmpty(str) || "null".equalsIgnoreCase(str) || "undefined".equalsIgnoreCase(str);
    }
}
