package com.xunlei.downloadprovider.util;

import java.util.TimerTask;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: NetworkSpeedCheck.java
final class m extends TimerTask {
    final /* synthetic */ l a;

    m(l lVar) {
        this.a = lVar;
    }

    public final void run() {
        k kVar = this.a.a;
        kVar.n++;
        this.a.a.k.obtainMessage(MqttConnectOptions.MQTT_VERSION_3_1).sendToTarget();
    }
}
