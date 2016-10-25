package com.xunlei.downloadprovider.qrcode.b;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;

// compiled from: BeepPlayer.java
public final class a implements OnCompletionListener {
    public boolean a;
    public MediaPlayer b;
    public Activity c;
    private final float d;
    private final long e;
    private String f;

    public a(Activity activity) {
        this.d = 1.0f;
        this.e = 200;
        this.a = true;
        this.f = getClass().getSimpleName();
        this.c = activity;
        if (this.a && this.b == null) {
            this.c.setVolumeControlStream(XZBDevice.DOWNLOAD_LIST_FAILED);
            this.b = new MediaPlayer();
            this.b.setAudioStreamType(XZBDevice.DOWNLOAD_LIST_FAILED);
            this.b.setOnCompletionListener(this);
            this.b.setOnErrorListener(new b(this));
            AssetFileDescriptor openRawResourceFd = this.c.getResources().openRawResourceFd(2131165184);
            try {
                this.b.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
                openRawResourceFd.close();
                this.b.setVolume(1.0f, 1.0f);
                this.b.prepare();
            } catch (IOException e) {
                this.b = null;
            }
        }
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.seekTo(0);
    }
}
