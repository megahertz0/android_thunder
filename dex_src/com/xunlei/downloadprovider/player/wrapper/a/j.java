package com.xunlei.downloadprovider.player.wrapper.a;

import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.wrapper.d;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: StartMessage.java
public final class j extends c {
    public j(d dVar) {
        super(dVar);
    }

    public final void a() {
        d dVar = this.a;
        d.c();
        switch (AnonymousClass_1.a[dVar.a.a().ordinal()]) {
            case SimpleLog.LOG_LEVEL_DEBUG:
            case MqttConnectOptions.MQTT_VERSION_3_1:
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
            case SimpleLog.LOG_LEVEL_ERROR:
            case SimpleLog.LOG_LEVEL_FATAL:
            case SimpleLog.LOG_LEVEL_OFF:
                new StringBuilder("start--state=").append(dVar.a.a());
                if (dVar.d != null) {
                    dVar.b.setDisplay(dVar.d);
                }
                dVar.b.start();
                dVar.a(MediaPlayerState.STARTED);
            default:
                new StringBuilder("start--state=").append(dVar.a.a());
        }
    }
}
