package com.xunlei.downloadprovider.player.wrapper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: AsyncMediaPlayer.java
final class b extends Handler {
    final /* synthetic */ a a;

    b(a aVar, Looper looper) {
        this.a = aVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case SimpleLog.LOG_LEVEL_DEBUG:
                int currentPosition;
                a aVar = this.a;
                d dVar = aVar.b;
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
                        currentPosition = dVar.b.getCurrentPosition();
                        break;
                    default:
                        currentPosition = 0;
                        break;
                }
                if (currentPosition >= 0) {
                    aVar.d.set(currentPosition);
                    aVar.a(currentPosition);
                }
                aVar.c.sendEmptyMessageDelayed(SimpleLog.LOG_LEVEL_DEBUG, 500);
            default:
                break;
        }
    }
}
