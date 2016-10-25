package com.xunlei.common.member.c.b;

import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.xllib.R;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserSetAvatarTask.java
final class c$1 implements BaseHttpClientListener {
    private /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        XLLog.v(c.a(this.a), new StringBuilder("set avatar response status = ").append(i).toString());
        if (i == 200) {
            String str = new String(bArr);
            XLLog.v(c.b(this.a), new StringBuilder("set avatar response body = ").append(str).toString());
            try {
                JSONObject jSONObject = new JSONObject(str);
                int a = c.a(this.a, jSONObject.getInt("result"));
                if (a == 0 && jSONObject.optInt("autoaudit", R.styleable.AppCompatTheme_buttonStyle) == 0) {
                    a = 16781285;
                }
                c.b(this.a, a);
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                c.b(this.a, 16777214);
            }
        }
        c.b(this.a, 16781295);
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        XLLog.v(c.c(this.a), new StringBuilder("set avatar onFailure message = ").append(th.getMessage()).toString());
        c.b(this.a, 16781295);
    }
}
