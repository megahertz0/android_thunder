package com.xunlei.common.member.c.c;

import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserUnBindOtherAccountTask.java
final class d$1 implements BaseHttpClientListener {
    private /* synthetic */ d a;

    d$1(d dVar) {
        this.a = dVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        String str = new String(bArr);
        XLLog.v(d.a(this.a), new StringBuilder("UserUnBindOtherAccountTask http body = ").append(str).toString());
        try {
            d.b(this.a, d.a(this.a, new JSONObject(str).getInt(XiaomiOAuthConstants.EXTRA_CODE_2)));
        } catch (JSONException e) {
            e.printStackTrace();
            d.b(this.a, 16777214);
        }
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        XLLog.v(d.b(this.a), new StringBuilder("UserUnBindOtherAccountTask http error = ").append(th.getMessage()).toString());
        d.b(this.a, 16781295);
    }
}
