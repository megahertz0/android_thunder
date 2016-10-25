package com.xunlei.downloadprovider.i.a;

import com.xunlei.downloadprovider.b.c.g.c;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: Update.java
final class l implements c {
    final /* synthetic */ c a;

    l(c cVar) {
        this.a = cVar;
    }

    public final void a() {
        if (!(c.l(this.a) || this.a.b == null || c.e(this.a).isFinishing())) {
            this.a.b.dismiss();
        }
        try {
            c.y(this.a).close();
        } catch (IOException e) {
        }
        c.v(this.a).obtainMessage(20008, MqttConnectOptions.MQTT_VERSION_3_1, 0).sendToTarget();
    }
}
