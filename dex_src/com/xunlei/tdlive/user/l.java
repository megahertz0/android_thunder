package com.xunlei.tdlive.user;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.user.k.c;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.f$a;
import com.xunlei.tdlive.util.f.e;
import com.xunlei.tdlive.util.f.h;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: WxHelper.java
class l extends e<String> {
    final /* synthetic */ k a;

    l(k kVar) {
        this.a = kVar;
    }

    public void onSuccess(h<String> hVar) {
        if (hVar.a != null) {
            try {
                JSONObject jSONObject = new JSONObject((String) hVar.a);
                k.a(this.a, new c());
                k.a(this.a).b = jSONObject.getString("nickname");
                k.a(this.a).a = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_OPENID);
                k.a(this.a).d = jSONObject.getString("unionid");
                k.a(this.a).c = jSONObject.getString("headimgurl");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (k.b(this.a) != null) {
                k.b(this.a).a(k.a(this.a));
            }
            XLog.d("WxHelper", new StringBuilder("on getUserInfo: ").append(k.a(this.a)).append(", result string: ").append((String) hVar.a).toString());
        }
    }

    public void onFailure(f$a com_xunlei_tdlive_util_f_a, String str) {
        XLog.e("WxHelper", "get wx userinfo failed.");
    }
}
