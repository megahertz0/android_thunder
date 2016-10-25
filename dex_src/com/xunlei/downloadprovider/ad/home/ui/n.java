package com.xunlei.downloadprovider.ad.home.ui;

import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.t;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: ADPlayVodItem.java
final class n implements t {
    final /* synthetic */ i a;

    n(i iVar) {
        this.a = iVar;
    }

    public final void a(MediaPlayerState mediaPlayerState, MediaPlayerState mediaPlayerState2) {
        switch (AnonymousClass_1.a[mediaPlayerState2.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
            case SimpleLog.LOG_LEVEL_DEBUG:
                this.a.h.h.a();
                this.a.h.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            case MqttConnectOptions.MQTT_VERSION_3_1:
                if (this.a.h.h.a) {
                    i.e(this.a);
                    this.a.h.h.b();
                }
            default:
                break;
        }
    }
}
