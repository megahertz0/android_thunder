package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public final class a {
    public static synchronized Map<String, String> a(Context context, Map<String, String> map) {
        Map<String, String> hashMap;
        synchronized (a.class) {
            String a = com.alipay.b.a.a.a.a.a(map, "appchannel", com.umeng.a.d);
            hashMap = new HashMap();
            hashMap.put("AA1", context.getPackageName());
            com.alipay.b.a.a.b.a.a();
            hashMap.put("AA2", com.alipay.b.a.a.b.a.a(context));
            hashMap.put("AA3", "security-sdk-token");
            hashMap.put("AA4", "3.2.0-20160621");
            hashMap.put("AA6", a);
        }
        return hashMap;
    }
}
