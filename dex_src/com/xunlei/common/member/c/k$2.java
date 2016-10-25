package com.xunlei.common.member.c;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.download.proguard.c;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserQQLoginTask.java
final class k$2 implements BaseHttpClientListener {
    private /* synthetic */ k a;

    k$2(k kVar) {
        this.a = kVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        String str = new String(bArr);
        XLLog.v("UserQQLoginTask", new StringBuilder("qq bind xl account = ").append(str).toString());
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i2 = jSONObject.getInt(XiaomiOAuthConstants.EXTRA_CODE_2);
            if (i2 == 0 || i2 == 200) {
                k.a(this.a, jSONObject.getString(c.f));
                k.b(this.a, jSONObject.getString("sessionid"));
                k.a(this.a, jSONObject.optInt("first_login"));
                k.b(this.a, 257);
                return;
            }
            k.c(this.a, jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_MSG));
            k.c(this.a, i2);
            k.d(this.a, 16781296);
        } catch (JSONException e) {
            e.printStackTrace();
            XLLog.e("UserQQLoginTask", new StringBuilder("qq bind xl account error = ").append(e.getMessage()).toString());
            k.d(this.a, 16777214);
        }
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        XLLog.e("UserQQLoginTask", new StringBuilder("qq bind xl account error = ").append(th.getMessage()).toString());
        k.c(this.a, th.getMessage());
        k.c(this.a, 16781295);
        k.d(this.a, 16781295);
    }
}
