package com.xunlei.common.member.c.c;

import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.member.XLThirdUserInfo;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserBindOtherAccountTask.java
final class a$1 implements BaseHttpClientListener {
    private /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        String str = new String(bArr);
        XLLog.v(a.a(this.a), new StringBuilder("UserBindOtherAccountTask http body = ").append(str).toString());
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i2 = jSONObject.getInt(XiaomiOAuthConstants.EXTRA_CODE_2);
            jSONObject = jSONObject.optJSONObject("thirdinfo");
            if (jSONObject != null) {
                a.a(this.a, new XLThirdUserInfo(jSONObject));
            }
            a.b(this.a, a.a(this.a, i2));
        } catch (JSONException e) {
            e.printStackTrace();
            a.b(this.a, 16777214);
        }
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        XLLog.v(a.b(this.a), new StringBuilder("UserBindOtherAccountTask http error = ").append(th.getMessage()).toString());
        a.b(this.a, 16781295);
    }
}
