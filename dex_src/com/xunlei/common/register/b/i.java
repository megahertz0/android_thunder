package com.xunlei.common.register.b;

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

// compiled from: XLPhoneRegisterTask.java
public final class i extends b {
    private static int b = 0;
    private static int c = 1;
    private String d;
    private String e;
    private String f;
    private int g;

    public i(c cVar) {
        super(cVar);
        this.d = BuildConfig.VERSION_NAME;
        this.e = BuildConfig.VERSION_NAME;
        this.f = BuildConfig.VERSION_NAME;
        this.g = 0;
    }

    public final void a(int i) {
        this.g = 0;
    }

    public final void d(String str) {
        if (str != null) {
            this.d = str;
        }
    }

    public final void e(String str) {
        if (str != null) {
            this.e = str;
        }
    }

    private void f(String str) {
        if (str != null) {
            this.f = str;
        }
    }

    public final void e() {
        if (this.a != a.c) {
            this.a = a.b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("op=mobileReg");
            stringBuilder.append("&");
            stringBuilder.append("from=");
            stringBuilder.append(b().g());
            stringBuilder.append("&");
            stringBuilder.append(new StringBuilder("mobile=").append(this.d).toString());
            stringBuilder.append("&");
            stringBuilder.append(new StringBuilder("code=").append(this.e).toString());
            if (this.g == 1) {
                stringBuilder.append("&");
                stringBuilder.append(new StringBuilder("pwd=").append(this.f).toString());
            }
            stringBuilder.append("&");
            stringBuilder.append("checkPwdStrength=1&");
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
                        XLLog.v("XLPhoneRegisterTask", str);
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            int optInt = jSONObject.optInt("result");
                            int optInt2 = jSONObject.optInt(com.xunlei.download.proguard.c.f);
                            String optString = jSONObject.optString("sessionid");
                            i.this.a(0, new Object[]{Integer.valueOf(optInt), XLRegErrorCode.getErrorDesc(optInt), Integer.valueOf(i.this.a()), Integer.valueOf(optInt2), optString});
                        } catch (JSONException e) {
                            e.printStackTrace();
                            i.this.a(0, new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), XLRegErrorCode.getErrorDesc(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), Integer.valueOf(i.this.a()), Integer.valueOf(0), BuildConfig.VERSION_NAME});
                        }
                    }
                }

                public final void onFailure(Throwable th, byte[] bArr) {
                    XLLog.e("XLPhoneRegisterTask", new StringBuilder("error = ").append(th.getMessage()).toString());
                    i.this.a(0, new Object[]{Integer.valueOf(-1), XLRegErrorCode.getErrorDesc(-1), Integer.valueOf(i.this.a()), Integer.valueOf(0), BuildConfig.VERSION_NAME});
                }
            });
            this.a = a.d;
        }
    }
}
