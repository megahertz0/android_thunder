package com.alipay.mobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.alipay.b.a.a.a.a;
import com.alipay.sdk.cons.b;
import com.umeng.message.MsgConstant;
import java.util.HashMap;
import java.util.Map;

public class SecurityClientMobile {
    public static synchronized String GetApdid(Context context, Map<String, String> map) {
        String a;
        synchronized (SecurityClientMobile.class) {
            Map hashMap = new HashMap();
            hashMap.put(MsgConstant.KEY_UTDID, a.a(map, MsgConstant.KEY_UTDID, com.umeng.a.d));
            hashMap.put(b.c, a.a(map, b.c, com.umeng.a.d));
            hashMap.put("userId", a.a(map, "userId", com.umeng.a.d));
            APSecuritySdk.getInstance(context).initToken(0, hashMap, null);
            a = com.alipay.apmobilesecuritysdk.a.a.a(context);
        }
        return a;
    }
}
