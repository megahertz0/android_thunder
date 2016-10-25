package com.xunlei.downloadprovider.homepage.recommend.feed;

import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.t;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: ChannelFeedVideoItemView.java
final class f implements t {
    final /* synthetic */ a a;

    f(a aVar) {
        this.a = aVar;
    }

    public final void a(MediaPlayerState mediaPlayerState, MediaPlayerState mediaPlayerState2) {
        switch (a$1.a[mediaPlayerState2.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
            case SimpleLog.LOG_LEVEL_DEBUG:
                a.a(this.a).a();
                a.q(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            case MqttConnectOptions.MQTT_VERSION_3_1:
                if (a.a(this.a).a) {
                    a.s(this.a);
                    a.a(this.a).b();
                }
            default:
                break;
        }
    }
}
