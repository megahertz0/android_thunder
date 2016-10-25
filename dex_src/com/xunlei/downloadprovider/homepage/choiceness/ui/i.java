package com.xunlei.downloadprovider.homepage.choiceness.ui;

import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.t;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: ChoicenessVideoPlayItemView.java
final class i implements t {
    final /* synthetic */ ChoicenessVideoPlayItemView a;

    i(ChoicenessVideoPlayItemView choicenessVideoPlayItemView) {
        this.a = choicenessVideoPlayItemView;
    }

    public final void a(MediaPlayerState mediaPlayerState, MediaPlayerState mediaPlayerState2) {
        switch (AnonymousClass_1.a[mediaPlayerState2.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
            case SimpleLog.LOG_LEVEL_DEBUG:
                this.a.a.l.a();
                this.a.a.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            case MqttConnectOptions.MQTT_VERSION_3_1:
                if (this.a.a.l.a) {
                    ChoicenessVideoPlayItemView.a(this.a);
                    this.a.a.l.b();
                }
            default:
                break;
        }
    }
}
