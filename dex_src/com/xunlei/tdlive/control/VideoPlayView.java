package com.xunlei.tdlive.control;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnInfoListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class VideoPlayView extends SurfaceView implements OnCompletionListener, OnInfoListener, Callback {
    private MediaPlayer a;
    private String b;
    private boolean c;
    private a d;

    public static interface a {
        void b();
    }

    public VideoPlayView(Context context) {
        super(context);
        this.b = "/sdcard/test.3gp";
        this.c = true;
        this.d = null;
        init();
    }

    public VideoPlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = "/sdcard/test.3gp";
        this.c = true;
        this.d = null;
        init();
    }

    public VideoPlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = "/sdcard/test.3gp";
        this.c = true;
        this.d = null;
        init();
    }

    public void init() {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        holder.setType(MqttConnectOptions.MQTT_VERSION_3_1);
    }

    public void play() {
        if (this.a != null) {
            try {
                this.a.start();
                this.a.setOnCompletionListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        if (this.a != null && this.a.isPlaying()) {
            this.a.pause();
            if (this.d != null) {
                this.d.b();
            }
        }
    }

    public boolean isPlaying() {
        return this.a != null && this.a.isPlaying();
    }

    public void setPath(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.b = str;
        }
    }

    public String getPath() {
        return this.b;
    }

    public void setOnVideoPlayListener(a aVar) {
        this.d = aVar;
    }

    private void a() {
        if (this.a == null) {
            this.a = new MediaPlayer();
            this.a.setOnInfoListener(this);
            this.a.setOnCompletionListener(this);
            this.a.setAudioStreamType(MqttConnectOptions.MQTT_VERSION_3_1);
            this.a.setDisplay(getHolder());
            try {
                this.a.setDataSource(this.b);
                this.a.prepare();
                this.a.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        new StringBuilder("surfaceChanged width=").append(i2).append(", videowidth=").append(this.a.getVideoWidth());
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        a();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        stop();
        this.a.release();
        this.a = null;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.seekTo(0);
        if (this.d != null) {
            this.d.b();
        }
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        this.a.pause();
        new StringBuilder("onInfo what=").append(i).append(",extra=").append(i2);
        return false;
    }
}
