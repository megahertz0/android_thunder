package com.xunlei.downloadprovider.member.payment.a;

import com.android.volley.r.b;
import com.xunlei.downloadprovider.a.f;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.member.payment.external.d;
import org.json.JSONException;

// compiled from: PayNetworkHelper.java
final class g implements b<String> {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        String str = (String) obj;
        try {
            f.a(this.a, d.a(str));
            f.b(this.a, f.a(this.a));
            f.a(str);
            new j(BrothersApplication.a(), "pay_configuration_param").a("pay_save_time", f.a());
        } catch (JSONException e) {
            e.printStackTrace();
            f.b(this.a, null);
        }
    }
}
