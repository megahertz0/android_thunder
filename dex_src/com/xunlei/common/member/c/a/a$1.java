package com.xunlei.common.member.c.a;

import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserAqBindMobileTask.java
final class a$1 implements BaseHttpClientListener {
    private /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        String str = new String(bArr);
        XLLog.v(a.a(this.a), new StringBuilder("UserAqBindMobileTask bind mobile result = ").append(str).toString());
        try {
            int i2 = new JSONObject(str).getInt("result");
            if (i2 == 0) {
                a.a(this.a, 0);
            } else if (i2 == 1102) {
                a.a(this.a, 16781277);
            } else if (i2 == 1104) {
                a.a(this.a, 16781276);
            } else if (i2 == 6002) {
                a.a(this.a, 16781279);
            } else if (i2 == 6004) {
                a.a(this.a, 16781278);
            } else {
                a.a(this.a, 16781280);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            a.a(this.a, 16777214);
        }
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        a.a(this.a, 16781295);
    }
}
