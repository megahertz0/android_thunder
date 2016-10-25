package com.xunlei.common.member.c.b;

import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserSelectRecommendAvatarTask.java
final class b$1 implements BaseHttpClientListener {
    private /* synthetic */ b a;

    b$1(b bVar) {
        this.a = bVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        if (i == 200) {
            String str = new String(bArr);
            XLLog.v(b.a(this.a), new StringBuilder("select recommend avatar body = ").append(str).toString());
            try {
                b.b(this.a, b.a(this.a, new JSONObject(str).getInt("result")));
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                b.b(this.a, 16777214);
            }
        }
        b.b(this.a, 16781295);
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        XLLog.v(b.b(this.a), "select recommend avatar onFailure");
        b.b(this.a, 16781295);
    }
}
