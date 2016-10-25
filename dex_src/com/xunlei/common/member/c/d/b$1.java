package com.xunlei.common.member.c.d;

import android.text.TextUtils;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import java.io.UnsupportedEncodingException;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserSetUserInfoTask.java
final class b$1 implements BaseHttpClientListener {
    private /* synthetic */ b a;

    b$1(b bVar) {
        this.a = bVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        String str;
        int a;
        UnsupportedEncodingException e;
        JSONException e2;
        XLLog.v(b.a(this.a), new StringBuilder("xl user set info http status = ").append(i).toString());
        if (i == 200) {
            String str2 = "unpack error";
            try {
                str = new String(bArr, CharsetConvert.UTF_8);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    a = b.a(this.a, jSONObject.getInt("result"));
                    try {
                        Object optString = jSONObject.optString("verifytype");
                        if (!TextUtils.isEmpty(optString)) {
                            this.a.g().c(optString);
                        }
                    } catch (UnsupportedEncodingException e3) {
                        e = e3;
                        e.printStackTrace();
                        XLLog.v(b.a(this.a), new StringBuilder("xl user set info response = ").append(str).toString());
                        b.b(this.a, a);
                    } catch (JSONException e4) {
                        e2 = e4;
                        e2.printStackTrace();
                        XLLog.v(b.a(this.a), new StringBuilder("xl user set info response = ").append(str).toString());
                        b.b(this.a, a);
                    }
                } catch (UnsupportedEncodingException e5) {
                    UnsupportedEncodingException unsupportedEncodingException = e5;
                    a = 16777214;
                    e = unsupportedEncodingException;
                    e.printStackTrace();
                    XLLog.v(b.a(this.a), new StringBuilder("xl user set info response = ").append(str).toString());
                    b.b(this.a, a);
                } catch (JSONException e6) {
                    JSONException jSONException = e6;
                    a = 16777214;
                    e2 = jSONException;
                    e2.printStackTrace();
                    XLLog.v(b.a(this.a), new StringBuilder("xl user set info response = ").append(str).toString());
                    b.b(this.a, a);
                }
            } catch (UnsupportedEncodingException e52) {
                str = str2;
                e = e52;
                a = 16777214;
                e.printStackTrace();
                XLLog.v(b.a(this.a), new StringBuilder("xl user set info response = ").append(str).toString());
                b.b(this.a, a);
            } catch (JSONException e62) {
                str = str2;
                e2 = e62;
                a = 16777214;
                e2.printStackTrace();
                XLLog.v(b.a(this.a), new StringBuilder("xl user set info response = ").append(str).toString());
                b.b(this.a, a);
            }
            XLLog.v(b.a(this.a), new StringBuilder("xl user set info response = ").append(str).toString());
            b.b(this.a, a);
        }
        b.b(this.a, 16781295);
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        XLLog.v(b.a(this.a), new StringBuilder("xl user set info error = ").append(th.getMessage()).toString());
        b.b(this.a, 16781295);
    }
}
