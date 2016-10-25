package com.xunlei.downloadprovider.player.wrapper.a;

import com.xunlei.downloadprovider.player.wrapper.d;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: SetVolumeMessage.java
public final class i extends c {
    private float b;
    private float c;

    public i(d dVar, float f, float f2) {
        super(dVar);
        this.b = f;
        this.c = f2;
    }

    public final void a() {
        d dVar = this.a;
        float f = this.b;
        float f2 = this.c;
        d.c();
        switch (AnonymousClass_1.a[dVar.a.a().ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
            case SimpleLog.LOG_LEVEL_DEBUG:
            case MqttConnectOptions.MQTT_VERSION_3_1:
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
            case SimpleLog.LOG_LEVEL_ERROR:
            case SimpleLog.LOG_LEVEL_FATAL:
            case SimpleLog.LOG_LEVEL_OFF:
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
            case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                new StringBuilder("setVolume--state=").append(dVar.a.a());
                dVar.b.setVolume(f, f2);
            default:
                new StringBuilder("setVolume--state=").append(dVar.a.a());
        }
    }
}
