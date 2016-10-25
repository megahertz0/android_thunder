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
import org.android.spdy.SpdyProtocol;
import org.android.spdy.TnetStatusCode;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: XLPhoneRegAndLoginTask.java
public final class h extends b {
    private String b;
    private String c;
    private String d;

    public final void a(String str, String str2, String str3) {
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public h(c cVar) {
        super(cVar);
        this.b = BuildConfig.VERSION_NAME;
        this.c = BuildConfig.VERSION_NAME;
        this.d = BuildConfig.VERSION_NAME;
    }

    public final void e() {
        if (this.a != a.c) {
            this.a = a.b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("op=mobileRegPwd");
            stringBuilder.append("&");
            stringBuilder.append("from=");
            stringBuilder.append(b().g());
            stringBuilder.append("&");
            stringBuilder.append(new StringBuilder("mobile=").append(this.b).toString());
            stringBuilder.append("&");
            stringBuilder.append(new StringBuilder("code=").append(this.c).toString());
            stringBuilder.append("&");
            stringBuilder.append(new StringBuilder("pwd=").append(this.d).toString());
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
                        XLLog.v("XLPhoneRegAndLoginTask", str);
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            int optInt = jSONObject.optInt("result");
                            int optInt2 = jSONObject.optInt(com.xunlei.download.proguard.c.f);
                            String optString = jSONObject.optString("sessionid");
                            h.this.a(SpdyProtocol.PUBKEY_SEQ_ADASH, new Object[]{Integer.valueOf(optInt), XLRegErrorCode.getErrorDesc(optInt), Integer.valueOf(h.this.a()), Integer.valueOf(optInt2), optString});
                        } catch (JSONException e) {
                            e.printStackTrace();
                            h.this.a(SpdyProtocol.PUBKEY_SEQ_ADASH, new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), XLRegErrorCode.getErrorDesc(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), Integer.valueOf(h.this.a()), Integer.valueOf(0), BuildConfig.VERSION_NAME});
                        }
                    }
                }

                public final void onFailure(Throwable th, byte[] bArr) {
                    XLLog.e("XLPhoneRegAndLoginTask", new StringBuilder("error = ").append(th.getMessage()).toString());
                    h.this.a(SpdyProtocol.PUBKEY_SEQ_ADASH, new Object[]{Integer.valueOf(-1), XLRegErrorCode.getErrorDesc(-1), Integer.valueOf(h.this.a()), Integer.valueOf(0), BuildConfig.VERSION_NAME});
                }
            });
            this.a = a.d;
        }
    }
}
