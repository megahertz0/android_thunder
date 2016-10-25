package com.xunlei.downloadprovider.player.wrapper.a;

import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.wrapper.d;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: StopMessage.java
public final class k extends c {
    public k(d dVar) {
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
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
                new StringBuilder("stop--state=").append(dVar.a.a());
                dVar.b.stop();
                dVar.a(MediaPlayerState.STOPPED);
            default:
                new StringBuilder("stop--state=").append(dVar.a.a());
        }
    }
}
