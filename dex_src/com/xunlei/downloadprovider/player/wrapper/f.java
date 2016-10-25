package com.xunlei.downloadprovider.player.wrapper;

import android.os.Handler;
import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.t;
import com.xunlei.downloadprovider.player.u;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: PlayerStateManager.java
public final class f {
    public boolean a;
    LinkedList<t> b;
    private Handler c;
    private final AtomicReference<MediaPlayerState> d;

    // compiled from: PlayerStateManager.java
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[MediaPlayerState.values().length];
            try {
                a[MediaPlayerState.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[MediaPlayerState.STOPPED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[MediaPlayerState.PREPARED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public f(Handler handler) {
        this.d = new AtomicReference(MediaPlayerState.IDLE);
        this.b = new LinkedList();
        this.c = handler;
    }

    public final synchronized MediaPlayerState a() {
        return (MediaPlayerState) this.d.get();
    }

    public final synchronized void a(MediaPlayerState mediaPlayerState) {
        MediaPlayerState mediaPlayerState2 = (MediaPlayerState) this.d.get();
        if (mediaPlayerState2 != mediaPlayerState) {
            switch (AnonymousClass_1.a[mediaPlayerState.ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                case SimpleLog.LOG_LEVEL_DEBUG:
                    this.a = false;
                    break;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    this.a = true;
                    break;
            }
            this.d.set(mediaPlayerState);
            if (!this.b.isEmpty()) {
                if (u.a()) {
                    new StringBuilder("onStateChange--oldState=").append(mediaPlayerState2).append("|newState=").append(mediaPlayerState);
                    Iterator it = this.b.iterator();
                    while (it.hasNext()) {
                        ((t) it.next()).a(mediaPlayerState2, mediaPlayerState);
                    }
                } else {
                    this.c.post(new g(this, mediaPlayerState2, mediaPlayerState));
                }
            }
        }
    }
}
