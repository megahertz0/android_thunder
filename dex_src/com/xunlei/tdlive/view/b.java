package com.xunlei.tdlive.view;

import android.graphics.Typeface;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.downloadprovider.member.payment.external.PayBaseConstants;
import com.xunlei.tdlive.aniengine.aa;
import com.xunlei.tdlive.aniengine.aa.c;
import com.xunlei.tdlive.aniengine.y;
import com.xunlei.tdlive.aniengine.z;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: BallonSprite.java
public class b extends a {
    public b(String str, String str2, String str3) {
        super(str, str2, str3);
        a(XLRegErrorCode.REG_INVALID_VERIFY, 660);
        a(y.a((int) XLPayErrorCode.XLP_BD_PAYING, "gift/ballon/1.png", "gift/ballon/2.png"), 0.0f, 60.0f);
        a(z.a(str).b(18.0f).a(2.0f, MqttConnectOptions.MQTT_VERSION_3_1, MqttConnectOptions.MQTT_VERSION_3_1, -8355712).a(Typeface.DEFAULT_BOLD).b(-1).a(PayBaseConstants.HALF_OF_FLOAT, 0.0f, PayBaseConstants.HALF_OF_FLOAT, 0.0f));
        aa.a((y) this).a(c.a(0).c(PayBaseConstants.HALF_OF_FLOAT, PayBaseConstants.HALF_OF_FLOAT).a("40%p", "100%p")).a(c.a(12).a("60%p", "75%p").c(com.xunlei.tdlive.aniengine.c.b)).a(c.a(12).a("50%p", "50%p").c(com.xunlei.tdlive.aniengine.c.b)).a(40).a(c.a(12).a("40%p", "25%p").c(com.xunlei.tdlive.aniengine.c.b)).a(c.a(12).a("60%p", "0%p").c(com.xunlei.tdlive.aniengine.c.b)).a(c.a(30).a("50%p", "-100%p").c(com.xunlei.tdlive.aniengine.c.b));
    }
}
