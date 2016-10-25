package com.alipay.sdk.data;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.packet.d;
import com.alipay.sdk.util.i;
import com.alipay.sdk.util.j;
import com.umeng.a;
import org.json.JSONObject;

final class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ a b;

    b(a aVar, Context context) {
        this.b = aVar;
        this.a = context;
    }

    public final void run() {
        try {
            d bVar = new com.alipay.sdk.packet.impl.b();
            Context context = this.a;
            com.alipay.sdk.packet.b a = bVar.a(context, a.d, j.a(context), true);
            if (a != null) {
                a aVar = this.b;
                Object obj = a.b;
                if (!TextUtils.isEmpty(obj)) {
                    try {
                        JSONObject optJSONObject = new JSONObject(obj).optJSONObject(a.g);
                        aVar.i = optJSONObject.optInt(a.f, a.a);
                        aVar.j = optJSONObject.optString(a.h, a.b).trim();
                    } catch (Throwable th) {
                    }
                }
                a aVar2 = this.b;
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(a.f, aVar2.a());
                    jSONObject.put(a.h, aVar2.j);
                    i.a(com.alipay.sdk.sys.b.a().a, a.e, jSONObject.toString());
                } catch (Exception e) {
                }
            }
        } catch (Throwable th2) {
        }
    }
}
