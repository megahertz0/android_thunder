package com.xunlei.common.register.b;

import com.umeng.message.util.HttpRequest;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.encrypt.MD5;
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
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: XLSendMessageTask.java
public final class j extends b {
    private final String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g;
    private boolean h;

    public j(c cVar) {
        super(cVar);
        this.c = BuildConfig.VERSION_NAME;
        this.d = BuildConfig.VERSION_NAME;
        this.e = BuildConfig.VERSION_NAME;
        this.f = BuildConfig.VERSION_NAME;
        this.g = 1;
        this.h = true;
    }

    public final void d(String str) {
        if (str != null) {
            this.f = str;
        }
    }

    public final void a(String str, String str2, String str3) {
        this.c = str;
        this.d = str2;
        this.e = str3;
    }

    public final void a(int i) {
        this.g = i;
    }

    public final void a(boolean z) {
        this.h = z;
    }

    private String f() {
        return MD5.encrypt(b().g() + this.f + "17c5bc4b8fabd847b010c49d9d174aec");
    }

    public final void e() {
        if (this.a != a.c) {
            this.a = a.b;
            StringBuilder stringBuilder = new StringBuilder();
            if (this.h) {
                stringBuilder.append("op=sendSmsSignLimit");
            } else {
                stringBuilder.append("op=sendSmsSign");
            }
            stringBuilder.append("&");
            stringBuilder.append("sign=");
            stringBuilder.append(MD5.encrypt(b().g() + this.f + "17c5bc4b8fabd847b010c49d9d174aec"));
            stringBuilder.append("&");
            stringBuilder.append("from=");
            stringBuilder.append(b().g());
            stringBuilder.append("&");
            stringBuilder.append(new StringBuilder("mobile=").append(this.f).toString());
            stringBuilder.append("&");
            stringBuilder.append("type=");
            stringBuilder.append(this.g);
            stringBuilder.append("&");
            stringBuilder.append(new StringBuilder("ip=").append(c()).toString());
            stringBuilder.append("&");
            stringBuilder.append("verifyKey=");
            stringBuilder.append(this.c);
            stringBuilder.append("&");
            stringBuilder.append("verifyCode=");
            stringBuilder.append(this.d);
            stringBuilder.append("&");
            stringBuilder.append("verifyType=");
            stringBuilder.append(this.e);
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
                        XLLog.v("XLSendMessageTask", str);
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            int optInt = jSONObject.optInt("result");
                            str = BuildConfig.VERSION_NAME;
                            if (optInt == 409) {
                                str = jSONObject.getString("verifyType");
                            }
                            j.this.a(MqttConnectOptions.MQTT_VERSION_3_1, new Object[]{Integer.valueOf(optInt), XLRegErrorCode.getErrorDesc(optInt), Integer.valueOf(j.this.a()), Integer.valueOf(j.this.g), str});
                        } catch (JSONException e) {
                            e.printStackTrace();
                            j.this.a(MqttConnectOptions.MQTT_VERSION_3_1, new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), XLRegErrorCode.getErrorDesc(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), Integer.valueOf(j.this.a()), Integer.valueOf(j.this.g), BuildConfig.VERSION_NAME});
                        }
                    }
                }

                public final void onFailure(Throwable th, byte[] bArr) {
                    XLLog.e("XLSendMessageTask", new StringBuilder("error = ").append(th.getMessage()).toString());
                    j.this.a(MqttConnectOptions.MQTT_VERSION_3_1, new Object[]{Integer.valueOf(-1), XLRegErrorCode.getErrorDesc(-1), Integer.valueOf(j.this.a()), Integer.valueOf(j.this.g), BuildConfig.VERSION_NAME});
                }
            });
            this.a = a.d;
        }
    }
}
