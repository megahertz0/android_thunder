package com.xunlei.common.member.c.c;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.member.XLBindedOtherAccountItem;
import java.util.Iterator;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserCheckBindOtherAccountTask.java
final class b$1 implements BaseHttpClientListener {
    private /* synthetic */ b a;

    b$1(b bVar) {
        this.a = bVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        String str = new String(bArr);
        XLLog.v(b.a(this.a), new StringBuilder("UserCheckBindOtherAccountTask http body = ").append(str).toString());
        try {
            JSONObject jSONObject = new JSONObject(str);
            int a = b.a(this.a, jSONObject.getInt(XiaomiOAuthConstants.EXTRA_CODE_2));
            if (a == 0) {
                JSONObject optJSONObject = jSONObject.optJSONObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA);
                if (optJSONObject != null) {
                    b.a(this.a, new XLBindedOtherAccountItem[optJSONObject.length()]);
                    Iterator keys = optJSONObject.keys();
                    int i2 = 0;
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        int i3 = i2 + 1;
                        b.b(this.a)[i2] = new XLBindedOtherAccountItem(b.a(this.a, str), optJSONObject.getInt(str));
                        i2 = i3;
                    }
                }
            }
            XLLog.v(b.c(this.a), new StringBuilder("UserCheckBindOtherAccountTask http error code = ").append(a).toString());
            b.b(this.a, a);
        } catch (JSONException e) {
            e.printStackTrace();
            b.b(this.a, 16777214);
        }
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        XLLog.v(b.d(this.a), new StringBuilder("UserCheckBindOtherAccountTask http error = ").append(th.getMessage()).toString());
        b.b(this.a, 16781295);
    }
}
