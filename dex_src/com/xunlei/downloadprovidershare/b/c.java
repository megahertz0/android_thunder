package com.xunlei.downloadprovidershare.b;

import com.android.volley.r.b;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.xiazaibao.BuildConfig;
import org.json.JSONObject;

// compiled from: ShortUrlHelp.java
final class c implements b<JSONObject> {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        jSONObject.optString("result", XiaomiOAuthConstants.EXTRA_ERROR_CODE_2);
        String optString = jSONObject.optString("dlj", BuildConfig.VERSION_NAME);
        if (this.a.b != null) {
            this.a.b.a(optString);
            this.a.b = null;
        }
    }
}
