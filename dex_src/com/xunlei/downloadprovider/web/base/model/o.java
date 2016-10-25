package com.xunlei.downloadprovider.web.base.model;

import com.xunlei.downloadprovider.c.a.b;
import com.xunlei.downloadprovider.c.a.f;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: ShortMovieDetailDataLoader.java
final class o implements a<f> {
    final /* synthetic */ d a;

    o(d dVar) {
        this.a = dVar;
    }

    public final /* synthetic */ void a(Object obj) {
        f fVar = (f) obj;
        int size = (fVar == null || fVar.e == null) ? 0 : fVar.e.size();
        this.a.e.a(size, fVar);
    }

    public final void a(b bVar) {
        this.a.e.a(MqttConnectOptions.MQTT_VERSION_3_1, bVar.a);
    }
}
