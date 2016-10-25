package com.xunlei.downloadprovidershare;

import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import java.util.HashMap;

// compiled from: ShareFileIconTypeUtil.java
public abstract class b {
    static final HashMap<String, Integer> a;

    static {
        a = new c();
    }

    public static int a(String str) {
        int i = R.drawable.share_ic_task_file_other;
        String toLowerCase = XLFileTypeUtil.a(str, false).toLowerCase();
        return a.containsKey(toLowerCase) ? ((Integer) a.get(toLowerCase)).intValue() : i;
    }
}
