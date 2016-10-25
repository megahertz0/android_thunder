package com.xunlei.downloadprovider.player.wrapper.a;

import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.wrapper.d;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: PauseMessage.java
public final class b extends c {
    public b(d dVar) {
        super(dVar);
    }

    public final void a() {
        d dVar = this.a;
        d.c();
        switch (AnonymousClass_1.a[dVar.a.a().ordinal()]) {
            case MqttConnectOptions.MQTT_VERSION_3_1:
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
            case SimpleLog.LOG_LEVEL_ERROR:
            case SimpleLog.LOG_LEVEL_FATAL:
                new StringBuilder("pause--state=").append(dVar.a.a());
                dVar.b.pause();
                dVar.a(MediaPlayerState.PAUSED);
            default:
                new StringBuilder("pause--state=").append(dVar.a.a());
        }
    }
}
