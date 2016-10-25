package com.xunlei.common.member.c;

import android.os.Bundle;
import com.umeng.message.util.HttpRequest;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.xiazaibao.BuildConfig;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: UserVerifyCodeTask.java
final class r$1 implements BaseHttpClientListener {
    private /* synthetic */ String a;
    private /* synthetic */ r b;

    r$1(r rVar, String str) {
        this.b = rVar;
        this.a = str;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        String str = BuildConfig.VERSION_NAME;
        Bundle bundle = new Bundle();
        int i2 = 0;
        int i3 = 1;
        while (i2 < headerArr.length) {
            int i4;
            Header header = headerArr[i2];
            if (header.getName().compareToIgnoreCase("Set-Cookie") == 0) {
                HeaderElement[] elements = header.getElements();
                for (i4 = 0; i4 < elements.length; i4++) {
                    HeaderElement headerElement = elements[i4];
                    if (headerElement.getName().compareToIgnoreCase("VERIFY_KEY") == 0) {
                        str = headerElement.getValue();
                        XLLog.v("UserVerifyCodeTask", new StringBuilder("VERIFY_KEY = ").append(str).toString());
                    }
                }
            }
            if (header.getName().compareToIgnoreCase(HttpRequest.l) != 0) {
                i4 = i3;
            } else if (header.getValue().compareToIgnoreCase("image/jpeg") == 0) {
                i4 = 1;
            } else {
                Object obj = header.getValue().compareToIgnoreCase("image/png") == 0 ? SimpleLog.LOG_LEVEL_DEBUG : header.getValue().compareToIgnoreCase("image/bmp") == 0 ? MqttConnectOptions.MQTT_VERSION_3_1 : MqttConnectOptions.MQTT_VERSION_3_1_1;
            }
            i2++;
            i3 = i4;
        }
        r.a(this.b, this.a);
        bundle.putString("verifyKey", str);
        bundle.putInt("imageType", i3);
        bundle.putByteArray("imageContent", bArr);
        bundle.putInt("errorCode", 0);
        bundle.putString("action", "UserVerifyCodeTask");
        this.b.g().a(this.b, bundle);
        XLLog.v("UserVerifyCodeTask", "get verify code.");
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        if (r.d() < r.o().length) {
            this.b.b();
            return;
        }
        XLLog.e("UserVerifyCodeTask", new StringBuilder("error = ").append(th.getMessage()).toString());
        r.a(this.b, this.a);
        Bundle bundle = new Bundle();
        bundle.putString("verifyKey", BuildConfig.VERSION_NAME);
        bundle.putInt("imageType", -1);
        bundle.putString("imageContent", BuildConfig.VERSION_NAME);
        bundle.putInt("errorCode", 16781312);
        bundle.putString("action", "UserVerifyCodeTask");
        this.b.g().a(this.b, bundle);
    }
}
