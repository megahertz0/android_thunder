package com.xunlei.downloadprovider.model.protocol.g;

import com.alipay.sdk.sys.a;
import com.xunlei.tdlive.R;
import java.util.HashMap;

// compiled from: XunleiScanCodeUrl.java
public final class l {
    public String a;
    public String b;

    public static l a(String str) {
        if (str == null) {
            return null;
        }
        if (!str.startsWith("http://u.155.com/d?")) {
            return null;
        }
        try {
            String substring = str.substring(R.styleable.Toolbar_collapseContentDescription);
            HashMap hashMap = new HashMap();
            String[] split = substring.split(a.b);
            for (String str2 : split) {
                String[] split2 = str2.split("=");
                if (2 == split2.length) {
                    hashMap.put(split2[0], split2[1]);
                }
            }
            String str3 = (String) hashMap.get("c");
            substring = (String) hashMap.get("b");
            if (((String) hashMap.get("s")) != null) {
                l lVar = new l();
                lVar.b = str3;
                lVar.a = substring;
                return lVar;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
