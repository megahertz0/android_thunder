package com.xunlei.tdlive.user;

import com.xunlei.common.member.XLUserInfo;
import com.xunlei.tdlive.util.XLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: UserHelper.java
class i extends DefaultXLOnUserListener {
    final /* synthetic */ f a;

    i(f fVar) {
        this.a = fVar;
    }

    public boolean onUserLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        XLog.d("UserHelper", new StringBuilder("auto login finished. err = ").append(i).toString());
        f.a(this.a, i == 0 ? MqttConnectOptions.MQTT_VERSION_3_1 : MqttConnectOptions.MQTT_VERSION_3_1_1);
        return false;
    }
}
