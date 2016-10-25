package com.xunlei.downloadprovider.model.protocol.f;

import com.xunlei.downloadprovider.b.c;
import com.xunlei.downloadprovider.b.c.a.a;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: UpdateBox.java
final class b implements a {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void a(int i, Object obj, Map<String, List<String>> map) {
        this.a.setStatus(i == 0 ? MqttConnectOptions.MQTT_VERSION_3_1 : MqttConnectOptions.MQTT_VERSION_3_1_1);
        c cVar = new c();
        cVar.b = obj;
        cVar.a = this.a.getRunnerId();
        cVar.c = this.a.mUserData;
        if (this.a.mListener != null) {
            this.a.mListener.obtainMessage(10004, i, -1, cVar).sendToTarget();
        }
    }
}
