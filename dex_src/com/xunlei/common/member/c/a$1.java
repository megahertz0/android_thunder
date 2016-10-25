package com.xunlei.common.member.c;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.download.proguard.c;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserAlipayLoginTask.java
final class a$1 implements BaseHttpClientListener {
    private /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        String str = new String(bArr);
        XLLog.v("UserAlipayLoginTask", new StringBuilder("ali bind xl account = ").append(str).toString());
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i2 = jSONObject.getInt(XiaomiOAuthConstants.EXTRA_CODE_2);
            if (i2 == 200) {
                a.a(this.a, jSONObject.getInt(c.f));
                a.a(this.a, jSONObject.getString("sessionid"));
                a.b(this.a, jSONObject.optInt("first_login"));
                a.c(this.a, 257);
                this.a.b();
                return;
            }
            a.b(this.a, jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_MSG));
            a.d(this.a, i2);
            a.e(this.a, 16781308);
        } catch (JSONException e) {
            e.printStackTrace();
            XLLog.e("UserAlipayLoginTask", new StringBuilder("ali bind xl account error = ").append(e.getMessage()).toString());
            a.e(this.a, 16777214);
        }
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        XLLog.e("UserAlipayLoginTask", new StringBuilder("ali bind xl account error = ").append(th.getMessage()).toString());
        a.b(this.a, th.getMessage());
        a.d(this.a, 16781295);
        a.e(this.a, 16781295);
    }
}
