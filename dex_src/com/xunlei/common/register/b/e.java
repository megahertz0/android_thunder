package com.xunlei.common.register.b;

import android.text.TextUtils;
import com.umeng.message.util.HttpRequest;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.common.register.XLRegisterUtil;
import com.xunlei.common.register.a.b;
import com.xunlei.common.register.a.b.a;
import com.xunlei.common.register.a.c;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.TnetStatusCode;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: XLEmailRegisterTask.java
public final class e extends b {
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public e(c cVar) {
        super(cVar);
        this.b = BuildConfig.VERSION_NAME;
        this.c = BuildConfig.VERSION_NAME;
        this.d = BuildConfig.VERSION_NAME;
        this.e = BuildConfig.VERSION_NAME;
        this.f = BuildConfig.VERSION_NAME;
    }

    public final void d(String str) {
        if (str != null) {
            this.b = str;
        }
    }

    public final void e(String str) {
        if (str != null) {
            this.f = str;
        }
    }

    public final void f(String str) {
    }

    public final void g(String str) {
        if (str != null) {
            this.c = str;
        }
    }

    public final void h(String str) {
        if (str != null) {
            this.d = str;
        }
    }

    public final void i(String str) {
        if (str != null) {
            this.e = str;
        }
    }

    private static String f() {
        return BuildConfig.VERSION_NAME;
    }

    private static String g() {
        return BuildConfig.VERSION_NAME;
    }

    public final void e() {
        if (this.a != a.c) {
            this.a = a.b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("op=emailReg");
            stringBuilder.append("&");
            stringBuilder.append("from=");
            stringBuilder.append(b().g());
            stringBuilder.append("&");
            stringBuilder.append(new StringBuilder("email=").append(this.b).toString());
            stringBuilder.append("&");
            stringBuilder.append(new StringBuilder("pwd=").append(this.f).toString());
            stringBuilder.append("&");
            stringBuilder.append("checkPwdStrength=1&");
            if (!(c(this.d) || c(this.e))) {
                stringBuilder.append(new StringBuilder("verify_key=").append(this.d).toString());
                stringBuilder.append("&");
                stringBuilder.append(new StringBuilder("verify_type=").append(this.e).toString());
                stringBuilder.append("&");
                if (!TextUtils.isEmpty(this.c)) {
                    stringBuilder.append(new StringBuilder("code=").append(this.c).toString());
                    stringBuilder.append("&");
                }
            }
            stringBuilder.append(new StringBuilder("ip=").append(c()).toString());
            stringBuilder.append("&");
            stringBuilder.append("businessType=");
            stringBuilder.append(XLRegisterUtil.getInstance().getBusinessType());
            stringBuilder.append("&");
            stringBuilder.append("appName=");
            stringBuilder.append(d());
            stringBuilder.append("&");
            stringBuilder.append("sdkVersion=");
            stringBuilder.append(b().e());
            stringBuilder.append("&");
            stringBuilder.append("clientVersion=");
            stringBuilder.append(b().f());
            stringBuilder.append("&");
            stringBuilder.append("deviceID=");
            stringBuilder.append(d.a());
            stringBuilder.append("&");
            stringBuilder.append(new StringBuilder("v=").append(b().e()).toString());
            com.xunlei.common.register.a.a.a().a(new Header[]{new BasicHeader(HttpRequest.l, HttpRequest.b)}, stringBuilder.toString(), new BaseHttpClientListener() {
                public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        String str = new String(bArr);
                        XLLog.v("XLEmailRegisterTask", str);
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            int optInt = jSONObject.optInt("result");
                            int optInt2 = jSONObject.optInt(com.xunlei.download.proguard.c.f);
                            String optString = jSONObject.optString("sessionid");
                            e.this.a(1, new Object[]{Integer.valueOf(optInt), XLRegErrorCode.getErrorDesc(optInt), Integer.valueOf(e.this.a()), Integer.valueOf(optInt2), optString, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME});
                        } catch (JSONException e) {
                            e.printStackTrace();
                            e.this.a(1, new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), XLRegErrorCode.getErrorDesc(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), Integer.valueOf(e.this.a()), Integer.valueOf(0), BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME});
                        }
                    }
                }

                public final void onFailure(Throwable th, byte[] bArr) {
                    XLLog.e("XLEmailRegisterTask", new StringBuilder("error = ").append(th.getMessage()).toString());
                    e.this.a(1, new Object[]{Integer.valueOf(-1), XLRegErrorCode.getErrorDesc(-1), Integer.valueOf(e.this.a()), Integer.valueOf(0), BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME});
                }
            });
            this.a = a.d;
        }
    }
}
