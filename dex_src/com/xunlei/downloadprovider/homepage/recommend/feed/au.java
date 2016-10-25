package com.xunlei.downloadprovider.homepage.recommend.feed;

import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.t;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: FeedVideoItemView.java
final class au implements t {
    final /* synthetic */ ap a;

    au(ap apVar) {
        this.a = apVar;
    }

    public final void a(MediaPlayerState mediaPlayerState, MediaPlayerState mediaPlayerState2) {
        switch (AnonymousClass_1.a[mediaPlayerState2.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
            case SimpleLog.LOG_LEVEL_DEBUG:
                this.a.w.a();
                this.a.x.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            case MqttConnectOptions.MQTT_VERSION_3_1:
                if (this.a.w.a) {
                    ap.q(this.a);
                    this.a.w.b();
                }
            default:
                break;
        }
    }
}
