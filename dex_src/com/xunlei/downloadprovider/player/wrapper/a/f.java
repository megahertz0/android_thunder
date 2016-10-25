package com.xunlei.downloadprovider.player.wrapper.a;

import com.xunlei.downloadprovider.player.wrapper.d;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: SeekToPositionMessage.java
public final class f extends c {
    private int b;

    public f(d dVar, int i) {
        super(dVar);
        this.b = i;
    }

    public final void a() {
        d dVar = this.a;
        int i = this.b;
        switch (AnonymousClass_1.a[dVar.a.a().ordinal()]) {
            case SimpleLog.LOG_LEVEL_DEBUG:
            case MqttConnectOptions.MQTT_VERSION_3_1:
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
            case SimpleLog.LOG_LEVEL_ERROR:
            case SimpleLog.LOG_LEVEL_FATAL:
                new StringBuilder("seekTo--state=").append(dVar.a.a()).append("|position=").append(i);
                int b = dVar.b();
                if (b <= 0 || i != b || b <= 1000) {
                    b = i;
                } else {
                    b -= 1000;
                }
                dVar.b.seekTo(b);
                if (dVar.c != null) {
                    dVar.c.a(i);
                }
            default:
                new StringBuilder("seekTo--state=").append(dVar.a.a()).append("|position=").append(i);
        }
    }
}
