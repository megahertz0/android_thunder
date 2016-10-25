package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.f.h;
import com.alipay.b.a.a.a.a;
import com.tencent.open.SocialConstants;
import com.umeng.message.MsgConstant;
import java.util.HashMap;
import java.util.Map;

public final class b {
    public static synchronized Map<String, String> a(Context context, Map<String, String> map) {
        Map<String, String> hashMap;
        synchronized (b.class) {
            hashMap = new HashMap();
            String a = a.a(map, com.alipay.sdk.cons.b.c, com.umeng.a.d);
            String a2 = a.a(map, MsgConstant.KEY_UTDID, com.umeng.a.d);
            String a3 = a.a(map, "userId", com.umeng.a.d);
            String a4 = a.a(map, SocialConstants.PARAM_APPNAME, com.umeng.a.d);
            String a5 = a.a(map, "appKeyClient", com.umeng.a.d);
            String a6 = a.a(map, "tmxSessionId", com.umeng.a.d);
            String c = h.c(context);
            hashMap.put("AC1", a);
            hashMap.put("AC2", a2);
            hashMap.put("AC3", com.umeng.a.d);
            hashMap.put("AC4", c);
            hashMap.put("AC5", a3);
            hashMap.put("AC6", a6);
            hashMap.put("AC7", com.umeng.a.d);
            hashMap.put("AC8", a4);
            hashMap.put("AC9", a5);
        }
        return hashMap;
    }
}
