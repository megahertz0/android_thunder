package com.xunlei.common.member.c;

import com.umeng.common.a;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserGetAuthQRCodeTask.java
final class b$1 implements BaseHttpClientListener {
    final /* synthetic */ b a;

    b$1(b bVar) {
        this.a = bVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        if (i == 200) {
            try {
                JSONObject jSONObject = new JSONObject(new String(bArr));
                if (jSONObject.optInt("result") == 200) {
                    b.a(this.a, jSONObject.optString(a.d));
                    b.a(this.a, 1025);
                    this.a.g().n().post(new Runnable() {
                        public final void run() {
                            b$1.this.b();
                        }
                    });
                    return;
                }
            } catch (JSONException e) {
            }
        }
        b.b(this.a, 50331649);
        b.a(this.a, 1026);
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        b.b(this.a, 50331649);
        b.a(this.a, 1026);
    }
}
