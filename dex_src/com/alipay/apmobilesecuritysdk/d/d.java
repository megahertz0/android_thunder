package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import com.alipay.b.a.a.b.b;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.message.MessageService;

public final class d {
    public static synchronized Map<String, String> a(Context context) {
        Map<String, String> hashMap;
        synchronized (d.class) {
            com.alipay.b.a.a.b.d.a();
            b.a();
            hashMap = new HashMap();
            hashMap.put("AE1", com.alipay.b.a.a.b.d.b());
            hashMap.put("AE2", (com.alipay.b.a.a.b.d.c() ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT));
            hashMap.put("AE3", (com.alipay.b.a.a.b.d.a(context) ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT));
            hashMap.put("AE4", com.alipay.b.a.a.b.d.d());
            hashMap.put("AE5", com.alipay.b.a.a.b.d.e());
            hashMap.put("AE6", com.alipay.b.a.a.b.d.f());
            hashMap.put("AE7", com.alipay.b.a.a.b.d.g());
            hashMap.put("AE8", com.alipay.b.a.a.b.d.h());
            hashMap.put("AE9", com.alipay.b.a.a.b.d.i());
            hashMap.put("AE10", com.alipay.b.a.a.b.d.j());
            hashMap.put("AE11", com.alipay.b.a.a.b.d.k());
            hashMap.put("AE12", com.alipay.b.a.a.b.d.l());
            hashMap.put("AE13", com.alipay.b.a.a.b.d.m());
            hashMap.put("AE14", com.alipay.b.a.a.b.d.n());
            hashMap.put("AE15", com.alipay.b.a.a.b.d.o());
            hashMap.put("AE21", b.g());
        }
        return hashMap;
    }
}
