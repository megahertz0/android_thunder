package com.xunlei.common.member.c.d;

import com.xunlei.common.base.XLLog;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import java.io.UnsupportedEncodingException;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserGetCityInfoTask.java
final class a$1 implements BaseHttpClientListener {
    private /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        UnsupportedEncodingException unsupportedEncodingException;
        JSONException jSONException;
        XLLog.v(a.a(this.a), new StringBuilder("xl user get city info http status = ").append(i).toString());
        if (i == 200) {
            String str;
            int a;
            String str2 = "unpack error";
            try {
                JSONObject jSONObject;
                str = new String(bArr, CharsetConvert.UTF_8);
                try {
                    jSONObject = new JSONObject(str);
                    a = a.a(this.a, jSONObject.getInt("result"));
                } catch (UnsupportedEncodingException e) {
                    UnsupportedEncodingException unsupportedEncodingException2 = e;
                    a = 16777214;
                    unsupportedEncodingException = unsupportedEncodingException2;
                    unsupportedEncodingException.printStackTrace();
                    XLLog.v(a.a(this.a), new StringBuilder("xl user get city info response = ").append(str).toString());
                    a.b(this.a, a);
                } catch (JSONException e2) {
                    JSONException jSONException2 = e2;
                    a = 16777214;
                    jSONException = jSONException2;
                    jSONException.printStackTrace();
                    XLLog.v(a.a(this.a), new StringBuilder("xl user get city info response = ").append(str).toString());
                    a.b(this.a, a);
                }
                if (a == 0) {
                    try {
                        a.a(this.a, jSONObject.getJSONObject("cities"));
                    } catch (UnsupportedEncodingException e3) {
                        unsupportedEncodingException = e3;
                        unsupportedEncodingException.printStackTrace();
                        XLLog.v(a.a(this.a), new StringBuilder("xl user get city info response = ").append(str).toString());
                        a.b(this.a, a);
                    } catch (JSONException e4) {
                        jSONException = e4;
                        jSONException.printStackTrace();
                        XLLog.v(a.a(this.a), new StringBuilder("xl user get city info response = ").append(str).toString());
                        a.b(this.a, a);
                    }
                }
            } catch (UnsupportedEncodingException e5) {
                str = str2;
                unsupportedEncodingException = e5;
                a = 16777214;
                unsupportedEncodingException.printStackTrace();
                XLLog.v(a.a(this.a), new StringBuilder("xl user get city info response = ").append(str).toString());
                a.b(this.a, a);
            } catch (JSONException e22) {
                str = str2;
                jSONException = e22;
                a = 16777214;
                jSONException.printStackTrace();
                XLLog.v(a.a(this.a), new StringBuilder("xl user get city info response = ").append(str).toString());
                a.b(this.a, a);
            }
            XLLog.v(a.a(this.a), new StringBuilder("xl user get city info response = ").append(str).toString());
            a.b(this.a, a);
        }
        a.b(this.a, 16781295);
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        XLLog.v(a.a(this.a), new StringBuilder("xl user set info error = ").append(th.getMessage()).toString());
        a.b(this.a, 16781295);
    }
}
