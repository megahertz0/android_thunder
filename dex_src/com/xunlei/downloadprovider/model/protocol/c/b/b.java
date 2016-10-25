package com.xunlei.downloadprovider.model.protocol.c.b;

import com.xunlei.downloadprovider.b.c.a.a;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: QueryGroupResExtendBox.java
public final class b implements a {
    final /* synthetic */ a a;

    public b(a aVar) {
        this.a = aVar;
    }

    public final void a(int i, Object obj, Map<String, List<String>> map) {
        if (i == 0) {
            this.a.setStatus(MqttConnectOptions.MQTT_VERSION_3_1);
        } else {
            this.a.setStatus(MqttConnectOptions.MQTT_VERSION_3_1_1);
        }
        this.a.mListener.obtainMessage(3109, i, -1, obj).sendToTarget();
    }
}
