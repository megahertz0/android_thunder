package com.xunlei.common.member.c.c;

import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.member.XLThirdUserInfo;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserGetOtherAccountInfoTask.java
final class c$1 implements BaseHttpClientListener {
    private /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        String str = new String(bArr);
        XLLog.v(c.a(this.a), new StringBuilder("UserGetOtherAccountInfoTask http body = ").append(str).toString());
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i2 = jSONObject.getInt(XiaomiOAuthConstants.EXTRA_CODE_2);
            jSONObject = jSONObject.optJSONObject("thirdinfo");
            if (jSONObject != null) {
                c.a(this.a, new XLThirdUserInfo(jSONObject));
            }
            c.b(this.a, c.a(this.a, i2));
        } catch (JSONException e) {
            e.printStackTrace();
            c.b(this.a, 16777214);
        }
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        XLLog.v(c.b(this.a), new StringBuilder("UserGetOtherAccountInfoTask http error = ").append(th.getMessage()).toString());
        c.b(this.a, 16781295);
    }
}
