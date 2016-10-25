package com.xunlei.downloadprovider.player.wrapper;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Handler;
import android.os.Looper;
import android.view.SurfaceHolder;
import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.t;
import com.xunlei.downloadprovider.player.u;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: MediaPlayerWrapper.java
public final class d implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, OnSeekCompleteListener, OnVideoSizeChangedListener {
    public f a;
    public MediaPlayer b;
    public e c;
    public SurfaceHolder d;
    private Handler e;

    // compiled from: MediaPlayerWrapper.java
    static /* synthetic */ class AnonymousClass_1 {
        public static final /* synthetic */ int[] a;

        static {
            a = new int[MediaPlayerState.values().length];
            try {
                a[MediaPlayerState.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[MediaPlayerState.PREPARED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[MediaPlayerState.LOADING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[MediaPlayerState.STARTED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[MediaPlayerState.PAUSED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[MediaPlayerState.PLAYBACK_COMPLETED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[MediaPlayerState.STOPPED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[MediaPlayerState.PREPARING.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[MediaPlayerState.IDLE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[MediaPlayerState.ERROR.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[MediaPlayerState.RELEASE.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    public d() {
        this.e = new Handler(Looper.getMainLooper());
        this.a = new f(this.e);
        this.b = new MediaPlayer();
        MediaPlayer mediaPlayer = this.b;
        mediaPlayer.setAudioStreamType(MqttConnectOptions.MQTT_VERSION_3_1);
        mediaPlayer.setScreenOnWhilePlaying(true);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnVideoSizeChangedListener(this);
        mediaPlayer.setOnInfoListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
    }

    public final void a() {
        c();
        switch (AnonymousClass_1.a[this.a.a().ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
            case SimpleLog.LOG_LEVEL_DEBUG:
            case MqttConnectOptions.MQTT_VERSION_3_1:
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
            case SimpleLog.LOG_LEVEL_ERROR:
            case SimpleLog.LOG_LEVEL_FATAL:
            case SimpleLog.LOG_LEVEL_OFF:
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
            case SpdyProtocol.PUBKEY_SEQ_OPEN:
                new StringBuilder("reset--state=").append(this.a.a());
                if (this.d != null) {
                    this.b.setDisplay(null);
                }
                this.b.reset();
                a(MediaPlayerState.IDLE);
            case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                new StringBuilder("reset--state=").append(this.a.a());
            default:
                new StringBuilder("reset--state=").append(this.a.a());
        }
    }

    public final int b() {
        new StringBuilder("getDuration--state=").append(this.a.a());
        switch (AnonymousClass_1.a[this.a.a().ordinal()]) {
            case SimpleLog.LOG_LEVEL_DEBUG:
            case MqttConnectOptions.MQTT_VERSION_3_1:
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
            case SimpleLog.LOG_LEVEL_ERROR:
            case SimpleLog.LOG_LEVEL_FATAL:
            case SimpleLog.LOG_LEVEL_OFF:
                return this.b.getDuration();
            default:
                return 0;
        }
    }

    public final void a(t tVar) {
        f fVar = this.a;
        if (tVar != null && !fVar.b.contains(tVar)) {
            fVar.b.add(tVar);
        }
    }

    public final void a(MediaPlayerState mediaPlayerState) {
        this.a.a(mediaPlayerState);
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        if (this.c != null) {
            this.c.a(this, i);
        }
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        a(MediaPlayerState.PLAYBACK_COMPLETED);
        if (this.c != null) {
            this.c.a(this);
        }
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        new StringBuilder("onError--what=").append(i).append("|extra=").append(i2);
        a(MediaPlayerState.ERROR);
        return this.c != null ? this.c.a(this, i, i2) : true;
    }

    public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        new StringBuilder("onInfo--what=").append(i).append("|extra=").append(i2).append("|state=").append(this.a.a());
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_3_1:
                if (this.a.a && this.a.a() != MediaPlayerState.PAUSED) {
                    a(MediaPlayerState.STARTED);
                }
                break;
            case XLRegErrorCode.REG_SIMPLE_PSW:
                if (this.a.a && this.a.a() != MediaPlayerState.PAUSED) {
                    a(MediaPlayerState.LOADING);
                }
                break;
            case XLRegErrorCode.REG_MAIL_EIXST:
                if (this.a.a && this.a.a() != MediaPlayerState.PAUSED) {
                    a(MediaPlayerState.STARTED);
                }
                break;
        }
        return this.c != null ? this.c.b(this, i, i2) : true;
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        if (this.c != null) {
            this.c.c(this, i, i2);
        }
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        new StringBuilder("onPrepared--state=").append(this.a.a());
        a(MediaPlayerState.PREPARED);
        if (this.c != null) {
            this.c.b(this);
        }
    }

    public final void onSeekComplete(MediaPlayer mediaPlayer) {
    }

    public static void c() {
        if (u.a()) {
            throw new IllegalStateException("do not call in UI thread");
        }
    }
}
