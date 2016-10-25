package com.xunlei.common.member.c.a;

import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserAqSendMessageTask.java
final class b$1 implements BaseHttpClientListener {
    private /* synthetic */ b a;

    b$1(b bVar) {
        this.a = bVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        String str = new String(bArr);
        XLLog.v(b.a(this.a), new StringBuilder("UserAqSendMessageTask send message result = ").append(str).toString());
        try {
            int i2 = new JSONObject(str).getInt("result");
            if (i2 == 0) {
                b.a(this.a, 0);
            } else if (i2 == 1102) {
                b.a(this.a, 16781277);
            } else if (i2 == 1104) {
                b.a(this.a, 16781276);
            } else if (i2 == 6002) {
                b.a(this.a, 16781279);
            } else if (i2 == 6004) {
                b.a(this.a, 16781278);
            } else {
                b.a(this.a, 16781281);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            b.a(this.a, 16777214);
        }
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        b.a(this.a, 16781295);
    }
}
