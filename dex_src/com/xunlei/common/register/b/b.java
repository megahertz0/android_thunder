package com.xunlei.common.register.b;

import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.common.register.XLRegisterUtil;
import com.xunlei.common.register.a.b.a;
import com.xunlei.common.register.a.c;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.TnetStatusCode;
import org.apache.http.Header;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: XLCheckNeedVerifyCodeTask.java
public final class b extends com.xunlei.common.register.a.b {
    public b(c cVar) {
        super(cVar);
    }

    public final void e() {
        if (this.a != a.c) {
            this.a = a.b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("op=needValidate");
            stringBuilder.append("&");
            stringBuilder.append("from=");
            stringBuilder.append(b().g());
            stringBuilder.append("&");
            stringBuilder.append(new StringBuilder("ip=").append(c()).toString());
            stringBuilder.append("&");
            stringBuilder.append(new StringBuilder("v=").append(b().e()).toString());
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
            stringBuilder.append("callback=");
            com.xunlei.common.register.a.a.a().b(null, stringBuilder.toString(), new BaseHttpClientListener() {
                public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        String str = new String(bArr);
                        XLLog.v("XLCheckNeedVerifyCodeTask", str);
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            int optInt = jSONObject.optInt("result");
                            int i2 = jSONObject.getInt("need");
                            String optString = jSONObject.optString("verifyType");
                            b.this.a(MqttConnectOptions.MQTT_VERSION_3_1_1, new Object[]{Integer.valueOf(optInt), XLRegErrorCode.getErrorDesc(optInt), Integer.valueOf(b.this.a()), Integer.valueOf(i2), optString});
                        } catch (JSONException e) {
                            e.printStackTrace();
                            b.this.a(MqttConnectOptions.MQTT_VERSION_3_1_1, new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), XLRegErrorCode.getErrorDesc(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), Integer.valueOf(b.this.a()), Integer.valueOf(-1), BuildConfig.VERSION_NAME});
                        }
                    }
                }

                public final void onFailure(Throwable th, byte[] bArr) {
                    XLLog.e("XLCheckNeedVerifyCodeTask", new StringBuilder("error = ").append(th.getMessage()).toString());
                    b.this.a(MqttConnectOptions.MQTT_VERSION_3_1_1, new Object[]{Integer.valueOf(-1), XLRegErrorCode.getErrorDesc(-1), Integer.valueOf(b.this.a()), Integer.valueOf(-1), BuildConfig.VERSION_NAME});
                }
            });
            this.a = a.d;
        }
    }
}
