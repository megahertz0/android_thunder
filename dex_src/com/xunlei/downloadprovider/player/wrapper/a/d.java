package com.xunlei.downloadprovider.player.wrapper.a;

import com.xunlei.downloadprovider.player.MediaPlayerState;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: ReleaseMessage.java
public final class d extends c {
    public d(com.xunlei.downloadprovider.player.wrapper.d dVar) {
        super(dVar);
    }

    public final void a() {
        com.xunlei.downloadprovider.player.wrapper.d dVar = this.a;
        new StringBuilder("release--state=").append(dVar.a.a());
        com.xunlei.downloadprovider.player.wrapper.d.c();
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
            case SpdyProtocol.PUBKEY_SEQ_OPEN:
                dVar.b.release();
                dVar.a(MediaPlayerState.RELEASE);
            default:
                break;
        }
    }
}
