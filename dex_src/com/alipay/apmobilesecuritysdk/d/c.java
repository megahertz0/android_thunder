package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.f.e;
import com.alipay.apmobilesecuritysdk.f.f;
import com.alipay.b.a.a.a.a;
import com.alipay.b.a.a.b.b;
import com.taobao.accs.common.Constants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class c {
    public static Map<String, String> a(Context context) {
        String str;
        b a = b.a();
        Map<String, String> hashMap = new HashMap();
        f a2 = e.a(context);
        String a3 = b.a(context);
        String b = b.b(context);
        String l = b.l(context);
        String o = b.o(context);
        String n = b.n(context);
        if (a2 != null) {
            if (a.a(a3)) {
                a3 = a2.a();
            }
            if (a.a(b)) {
                b = a2.b();
            }
            if (a.a(l)) {
                l = a2.c();
            }
            if (a.a(o)) {
                o = a2.d();
            }
            if (a.a(n)) {
                n = a2.e();
            }
            str = n;
            n = o;
            o = l;
            l = b;
            b = a3;
        } else {
            str = n;
            n = o;
            o = l;
            l = b;
            b = a3;
        }
        f fVar = new f(b, l, o, n, str);
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Constants.KEY_IMEI, fVar.a());
                jSONObject.put(Constants.KEY_IMSI, fVar.b());
                jSONObject.put("mac", fVar.c());
                jSONObject.put("bluetoothmac", fVar.d());
                jSONObject.put("gsi", fVar.e());
                a3 = jSONObject.toString();
                com.alipay.apmobilesecuritysdk.g.a.a("device_feature_file_name", "device_feature_file_key", a3);
                com.alipay.apmobilesecuritysdk.g.a.a(context, "device_feature_prefs_name", "device_feature_prefs_key", a3);
            } catch (Throwable e) {
                com.alipay.apmobilesecuritysdk.c.a.a(e);
            }
        }
        hashMap.put("AD1", b);
        hashMap.put("AD2", l);
        hashMap.put("AD3", b.g(context));
        hashMap.put("AD5", b.i(context));
        hashMap.put("AD6", b.j(context));
        hashMap.put("AD7", b.k(context));
        hashMap.put("AD8", o);
        hashMap.put("AD9", b.m(context));
        hashMap.put("AD10", str);
        hashMap.put("AD11", b.d());
        hashMap.put("AD12", a.e());
        hashMap.put("AD13", b.f());
        hashMap.put("AD14", b.h());
        hashMap.put("AD15", b.i());
        hashMap.put("AD16", b.j());
        hashMap.put("AD17", com.umeng.a.d);
        hashMap.put("AD18", n);
        hashMap.put("AD19", b.p(context));
        hashMap.put("AD20", b.k());
        hashMap.put("AD21", b.f(context));
        hashMap.put("AD22", com.umeng.a.d);
        hashMap.put("AD23", b.l());
        hashMap.put("AD24", a.f(b.h(context)));
        hashMap.put("AD26", b.e(context));
        hashMap.put("AD27", b.q());
        hashMap.put("AD28", b.s());
        hashMap.put("AD29", b.u());
        hashMap.put("AD30", b.r());
        hashMap.put("AD31", b.t());
        hashMap.put("AD32", b.o());
        hashMap.put("AD33", b.p());
        hashMap.put("AD34", b.s(context));
        hashMap.put("AD35", b.t(context));
        hashMap.put("AD36", b.r(context));
        hashMap.put("AD37", b.n());
        hashMap.put("AD38", b.m());
        hashMap.put("AD39", b.c(context));
        hashMap.put("AD40", b.d(context));
        hashMap.put("AD41", b.b());
        hashMap.put("AD42", b.c());
        hashMap.put("AL3", b.q(context));
        return hashMap;
    }
}
