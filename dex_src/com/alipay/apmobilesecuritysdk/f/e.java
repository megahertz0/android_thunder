package com.alipay.apmobilesecuritysdk.f;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.g.a;
import com.taobao.accs.common.Constants;
import org.json.JSONObject;

public final class e {
    public static f a(Context context) {
        if (context == null) {
            return null;
        }
        String a = a.a(context, "device_feature_prefs_name", "device_feature_prefs_key");
        if (com.alipay.b.a.a.a.a.a(a)) {
            a = a.a("device_feature_file_name", "device_feature_file_key");
        }
        if (com.alipay.b.a.a.a.a.a(a)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(a);
            f fVar = new f();
            fVar.a(jSONObject.getString(Constants.KEY_IMEI));
            fVar.b(jSONObject.getString(Constants.KEY_IMSI));
            fVar.c(jSONObject.getString("mac"));
            fVar.d(jSONObject.getString("bluetoothmac"));
            fVar.e(jSONObject.getString("gsi"));
            return fVar;
        } catch (Throwable e) {
            com.alipay.apmobilesecuritysdk.c.a.a(e);
            return null;
        }
    }
}
