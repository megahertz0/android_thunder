package com.xunlei.common.member.c;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.download.proguard.c;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserSinaLoginTask.java
final class o$3 implements BaseHttpClientListener {
    private /* synthetic */ o a;

    o$3(o oVar) {
        this.a = oVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        String str = new String(bArr);
        XLLog.v(this.a.getClass().getSimpleName(), new StringBuilder("sina bind xl account = ").append(str).toString());
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i2 = jSONObject.getInt(XiaomiOAuthConstants.EXTRA_CODE_2);
            o.a(this.a, jSONObject.getString(c.f));
            o.b(this.a, jSONObject.getString("sessionid"));
            o.a(this.a, jSONObject.optInt("first_login"));
            if (i2 == 200) {
                i2 = 0;
            }
            if (i2 == 0) {
                o.b(this.a, i2);
                return;
            }
            o.c(this.a, jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_MSG));
            o.c(this.a, i2);
            o.d(this.a, 16781308);
        } catch (JSONException e) {
            e.printStackTrace();
            XLLog.e(this.a.getClass().getSimpleName(), new StringBuilder("sina bind xl account error = ").append(e.getMessage()).toString());
            o.d(this.a, 16777214);
        }
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        XLLog.e(this.a.getClass().getSimpleName(), new StringBuilder("sina bind xl account error = ").append(th.getMessage()).toString());
        o.c(this.a, th.getMessage());
        o.c(this.a, 16781295);
        o.d(this.a, 16781295);
    }
}
