package com.xunlei.downloadprovider.cloudlist;

import android.text.TextUtils;
import com.xunlei.downloadprovider.util.c.a;
import java.io.UnsupportedEncodingException;

// compiled from: CloudUtil.java
public final class k {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return a.d(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return str;
        }
    }
}
