package com.umeng.message.tag;

import com.umeng.common.UmLog;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: TagLengthFilter.java
public class a implements TagFilter {
    private static final String a;
    private static int b;

    static {
        a = a.class.getName();
        b = 256;
    }

    public boolean filter(String str) {
        if (str == null || BuildConfig.VERSION_NAME.equals(str.trim())) {
            return false;
        }
        if (str == null || str.length() <= b) {
            return true;
        }
        UmLog.e(a, String.format("The length of %s exceeds allowed max length %i", new Object[]{str, Integer.valueOf(b)}));
        return false;
    }
}
