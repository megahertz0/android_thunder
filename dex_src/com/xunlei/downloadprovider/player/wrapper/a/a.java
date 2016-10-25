package com.xunlei.downloadprovider.player.wrapper.a;

import com.xunlei.downloadprovider.player.wrapper.d;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: AttachAuxEffectMessage.java
public final class a extends c {
    private int b;

    public a(d dVar) {
        super(dVar);
        this.b = 0;
    }

    public final void a() {
        d dVar = this.a;
        int i = this.b;
        switch (AnonymousClass_1.a[dVar.a.a().ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
            case SimpleLog.LOG_LEVEL_DEBUG:
            case MqttConnectOptions.MQTT_VERSION_3_1:
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
            case SimpleLog.LOG_LEVEL_ERROR:
            case SimpleLog.LOG_LEVEL_FATAL:
            case SimpleLog.LOG_LEVEL_OFF:
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
                new StringBuilder("attachAuxEffect--state=").append(dVar.a.a());
                dVar.b.attachAuxEffect(i);
            default:
                new StringBuilder("attachAuxEffect--state=").append(dVar.a.a());
        }
    }
}
