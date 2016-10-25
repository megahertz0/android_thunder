package com.xunlei.downloadprovider.model.protocol.g;

import com.xunlei.downloadprovider.b.b.a.a;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: XunleiScanCodeBox.java
final class h implements a {
    final /* synthetic */ e a;

    h(e eVar) {
        this.a = eVar;
    }

    public final void a(int i, Object obj) {
        this.a.setStatus(MqttConnectOptions.MQTT_VERSION_3_1);
    }
}
