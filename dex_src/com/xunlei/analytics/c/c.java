package com.xunlei.analytics.c;

import android.util.Base64;
import org.apache.commons.logging.impl.SimpleLog;

public class c {
    public static String a(byte[] bArr) {
        return Base64.encodeToString(bArr, SimpleLog.LOG_LEVEL_DEBUG);
    }

    public static byte[] a(String str) {
        return Base64.decode(str.getBytes(), SimpleLog.LOG_LEVEL_DEBUG);
    }
}
