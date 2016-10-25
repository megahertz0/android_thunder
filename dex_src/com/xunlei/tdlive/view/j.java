package com.xunlei.tdlive.view;

import android.graphics.Typeface;
import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.downloadprovider.member.payment.external.PayBaseConstants;
import com.xunlei.tdlive.aniengine.aa;
import com.xunlei.tdlive.aniengine.aa.c;
import com.xunlei.tdlive.aniengine.y;
import com.xunlei.tdlive.aniengine.z;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: FireworkSprite.java
public class j extends a {
    public j(String str, String str2, String str3) {
        super(str, str2, str3);
        a(XLRegErrorCode.REG_INVALID_VERIFY, 660);
        a(y.b("gift/firework/1.png"), aa.a(null, c.a(0).c(PayBaseConstants.HALF_OF_FLOAT, PayBaseConstants.HALF_OF_FLOAT).a("50%p", "50%p")).a(0, SimpleLog.LOG_LEVEL_DEBUG, 1).a(c.a(0).a(0.0f).a(0.0f, 0.0f)).a(c.a(5).a(1.0f).a(0.66f, 0.66f).c(com.xunlei.tdlive.aniengine.c.c(-1.0f))).a(c.a(9).a(0.88f, 0.88f)).a(c.a(8).a(0.0f).a(1.0f, 1.0f).c(com.xunlei.tdlive.aniengine.c.c(1.0f))).a(c.a(1).a(0.0f, 0.0f).b(90.0f)).a(c.a(1).a(1.0f).a(0.44f, 0.44f).c(com.xunlei.tdlive.aniengine.c.c(-1.0f))).a(c.a(9).a(0.6f, 0.6f)).a(c.a(8).a(0.0f).a(0.77f, 0.77f).c(com.xunlei.tdlive.aniengine.c.c(1.0f))).a(c.a(5).b(0.0f)));
        a(z.a(str).b(18.0f).a(2.0f, MqttConnectOptions.MQTT_VERSION_3_1, MqttConnectOptions.MQTT_VERSION_3_1, -8355712).a(Typeface.DEFAULT_BOLD).b(-1).a(PayBaseConstants.HALF_OF_FLOAT, 0.0f, PayBaseConstants.HALF_OF_FLOAT, 0.0f));
        aa.a((y) this, c.a(0).c(PayBaseConstants.HALF_OF_FLOAT, PayBaseConstants.HALF_OF_FLOAT).a("50%p", "40%p")).a(81);
    }
}
