package com.xunlei.downloadprovider.ad.common.d;

import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.download.tasklist.list.e.b.b;
import java.util.Locale;

@Deprecated
// compiled from: SSPUtil.java
public class d {
    private static final String a;

    static {
        a = d.class.getSimpleName();
    }

    public static String a(b bVar) {
        return a(bVar.c_(), bVar.o());
    }

    public static String a(int i, int i2) {
        return new StringBuilder(SocializeConstants.OP_DIVIDER_MINUS).append(String.valueOf(i)).append(String.format(Locale.CHINA, "%04d", new Object[]{Integer.valueOf(i2)})).toString();
    }
}
