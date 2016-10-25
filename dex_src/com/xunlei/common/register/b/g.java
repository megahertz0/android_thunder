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
import org.android.spdy.SpdyProtocol;
import org.android.spdy.TnetStatusCode;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: XLOldUserNameRegisterTask.java
public final class g extends b {
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public final void a(String str, String str2, String str3, String str4, String str5) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
    }

    public final void d(String str) {
    }

    private static String f() {
        return BuildConfig.VERSION_NAME;
    }

    private static String g() {
        return BuildConfig.VERSION_NAME;
    }

    public g(c cVar) {
        super(cVar);
        this.b = BuildConfig.VERSION_NAME;
        this.c = BuildConfig.VERSION_NAME;
        this.d = BuildConfig.VERSION_NAME;
        this.e = BuildConfig.VERSION_NAME;
        this.f = BuildConfig.VERSION_NAME;
    }

    public final void e() {
        if (this.a != a.c) {
            this.a = a.b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("op=usernameReg");
            stringBuilder.append("&");
            stringBuilder.append("from=");
            stringBuilder.append(b().g());
            stringBuilder.append("&");
            stringBuilder.append(new StringBuilder("username=").append(this.b).toString());
            stringBuilder.append("&");
            stringBuilder.append(new StringBuilder("pwd=").append(this.c).toString());
            stringBuilder.append("&");
            stringBuilder.append("checkPwdStrength=1&");
            if (!(c(this.e) || c(this.f))) {
                stringBuilder.append(new StringBuilder("verify_key=").append(this.e).toString());
                stringBuilder.append("&");
                stringBuilder.append(new StringBuilder("verify_type=").append(this.f).toString());
                stringBuilder.append("&");
                if (!TextUtils.isEmpty(this.d)) {
                    stringBuilder.append(new StringBuilder("code=").append(this.d).toString());
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
                        XLLog.v("XLOldUserNameRegisterTask", str);
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            int optInt = jSONObject.optInt("result");
                            int optInt2 = jSONObject.optInt(com.xunlei.download.proguard.c.f);
                            String optString = jSONObject.optString("sessionid");
                            g.this.a(SpdyProtocol.PUBKEY_PSEQ_ADASH, new Object[]{Integer.valueOf(optInt), XLRegErrorCode.getErrorDesc(optInt), Integer.valueOf(g.this.a()), Integer.valueOf(optInt2), optString, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME});
                        } catch (JSONException e) {
                            e.printStackTrace();
                            g.this.a(SpdyProtocol.PUBKEY_PSEQ_ADASH, new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), XLRegErrorCode.getErrorDesc(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), Integer.valueOf(g.this.a()), Integer.valueOf(0), BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME});
                        }
                    }
                }

                public final void onFailure(Throwable th, byte[] bArr) {
                    XLLog.e("XLOldUserNameRegisterTask", new StringBuilder("error = ").append(th.getMessage()).toString());
                    g.this.a(SpdyProtocol.PUBKEY_PSEQ_ADASH, new Object[]{Integer.valueOf(-1), XLRegErrorCode.getErrorDesc(-1), Integer.valueOf(g.this.a()), Integer.valueOf(0), BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME});
                }
            });
            this.a = a.d;
        }
    }
}
