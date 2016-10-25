package com.xunlei.tdlive.d;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.umeng.message.MsgConstant;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.w;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: MDPlayer.java
public class c extends Handler implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnPreparedListener, OnSeekCompleteListener, OnVideoSizeChangedListener, Callback, a {
    private static a r;
    private final String a;
    private int b;
    private int c;
    private int d;
    private MediaPlayer e;
    private MediaPlayer f;
    private String g;
    private SurfaceView h;
    private com.xunlei.tdlive.d.a.a i;
    private FrameLayout j;
    private int k;
    private Thread l;
    private w m;
    private w n;
    private w o;
    private w p;
    private w q;

    // compiled from: MDPlayer.java
    class a implements Runnable {
        MediaPlayer a;

        a(MediaPlayer mediaPlayer) {
            this.a = mediaPlayer;
        }

        public void run() {
            try {
                this.a.release();
            } catch (Exception e) {
            }
            try {
                this.a = null;
            } catch (Exception e2) {
            }
            c.this.l = null;
        }
    }

    static {
        r = null;
    }

    public static a h() {
        if (r == null) {
            r = new c();
        }
        return r;
    }

    private c() {
        this.a = "MDPlayer";
        this.m = new w("start_time");
        this.n = new w("duration");
        this.o = new w("connecting");
        this.p = new w("loading", true);
        this.q = new w("buffering");
    }

    public void a(Context context, FrameLayout frameLayout, String str, com.xunlei.tdlive.d.a.a aVar) {
        XLog.d("MDPlayer", new StringBuilder("streamURL:").append(str).toString());
        if (str != null && str.length() > 0) {
            if (!(this.j == null || this.h == null)) {
                this.j.removeView(this.h);
            }
            this.g = str;
            this.i = aVar;
            this.j = frameLayout;
            this.k = 0;
            this.b = 0;
            this.h = new SurfaceView(context);
            this.h.getHolder().addCallback(this);
            this.j.addView(this.h, 1, 1);
        } else if (aVar != null) {
            aVar.a(-1);
        }
    }

    public void a() {
        i();
        try {
            if (this.i != null) {
                this.i.a(this.m.b(), (int) this.o.f(), (int) this.q.f(), (int) this.p.f(), (int) this.n.d(), "exit", JsInterface.MSG_JS_OPEN_TRANSCODE_DOWNLOADLIST);
            }
        } catch (Exception e) {
        }
        this.b = 0;
        this.p.a();
        this.k = 0;
        this.i = null;
        removeCallbacksAndMessages(null);
    }

    public void b() {
        XLog.d("MDPlayer", "pause");
        this.k = f();
        try {
            this.e.pause();
        } catch (Exception e) {
        }
    }

    public void c() {
        XLog.d("MDPlayer", "resume");
        try {
            this.e.start();
        } catch (Exception e) {
        }
    }

    public void a(Configuration configuration) {
        float f;
        Rect rect = new Rect();
        this.j.getDrawingRect(rect);
        int width = rect.width();
        int height = rect.height();
        if (this.c < width && this.d >= height) {
            f = (float) ((((double) width) * 1.0d) / ((double) this.c));
        } else if (this.d >= height || this.c < width) {
            f = (float) ((((double) width) * 1.0d) / ((double) this.c));
            float f2 = (float) ((((double) height) * 1.0d) / ((double) this.d));
            if (f <= f2) {
                f = f2;
            }
        } else {
            f = (float) ((((double) height) * 1.0d) / ((double) this.d));
        }
        width = (int) (((float) this.c) * f);
        height = (int) (f * ((float) this.d));
        LayoutParams layoutParams = (LayoutParams) this.h.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        this.h.setLayoutParams(layoutParams);
    }

    public void a(FrameLayout frameLayout) {
    }

    public void a(com.xunlei.tdlive.d.a.a aVar) {
    }

    public boolean d() {
        return false;
    }

    public boolean e() {
        try {
            return !this.e.isPlaying();
        } catch (Exception e) {
            return false;
        }
    }

    public int f() {
        try {
            return this.e.getCurrentPosition();
        } catch (Exception e) {
            return 0;
        }
    }

    public int g() {
        try {
            return this.e.getDuration();
        } catch (Exception e) {
            return -1;
        }
    }

    public int a(int i) {
        try {
            this.e.seekTo(i);
            return i;
        } catch (Exception e) {
            return f();
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        XLog.d("MDPlayer", new StringBuilder("onError, what:").append(i).append(", extra:").append(i2).toString());
        if (this.i != null) {
            if (!(i == -38 && i2 == 0)) {
                if (!(i == 1 && i2 == -38)) {
                    if (!(i == 1 && i2 == -19) && ac.a()) {
                        this.i.a(i);
                        this.i.a(this.m.b(), (int) this.o.f(), (int) this.q.f(), (int) this.p.f(), (int) this.n.d(), MsgConstant.KEY_FAIL, i2);
                        return true;
                    }
                }
            }
        }
        if (!ac.a()) {
            XLog.d("MDPlayer", "NoNetworkAvailable try after 1s");
            if (!hasMessages(IHost.HOST_NOFITY_REFRESH_LIST)) {
                sendEmptyMessageDelayed(IHost.HOST_NOFITY_REFRESH_LIST, 1000);
            }
        }
        return true;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        XLog.d("MDPlayer", "onCompletion");
        if (this.i != null) {
            this.i.a(0);
        }
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        XLog.d("MDPlayer", new StringBuilder("onBufferingUpdate, percent:").append(i).toString());
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        XLog.d("MDPlayer", "onPrepared");
        try {
            this.m.c();
            this.n.c();
            this.o.d();
            mediaPlayer.start();
            mediaPlayer.seekTo(this.k);
            this.e = mediaPlayer;
            this.f = null;
        } catch (Exception e) {
        }
        this.b = 1;
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        this.e = mediaPlayer;
        this.f = null;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            this.p.c();
            this.q.c();
            this.o.c();
            try {
                if (this.e != null) {
                    this.e.setDisplay(null);
                    this.k = f();
                }
            } catch (Exception e) {
            }
            this.f = null;
            this.f = new MediaPlayer();
            this.f.setAudioStreamType(XZBDevice.DOWNLOAD_LIST_FAILED);
            this.f.setOnBufferingUpdateListener(this);
            this.f.setOnPreparedListener(this);
            this.f.setOnVideoSizeChangedListener(this);
            this.f.setOnErrorListener(this);
            this.f.setScreenOnWhilePlaying(true);
            this.f.setOnCompletionListener(this);
            this.f.setOnSeekCompleteListener(this);
            this.f.reset();
            this.f.setDataSource(this.g);
            this.f.prepareAsync();
            this.f.setDisplay(surfaceHolder);
        } catch (Exception e2) {
            if (this.i != null) {
                this.i.a(-1);
            }
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.k = f();
        i();
        try {
            if (this.i != null) {
                this.i.a(this.m.b(), (int) this.o.f(), (int) this.q.f(), (int) this.p.f(), (int) this.n.d(), "pause", JsInterface.MSG_JS_GET_USER_INFO_CHANGE);
            }
        } catch (Exception e) {
        }
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        XLog.d("MDPlayer", new StringBuilder("onVideoSizeChanged, w:").append(i).append(", h:").append(i2).toString());
        this.c = i;
        this.d = i2;
        if (!(this.c == 0 || this.d == 0)) {
            this.h.postDelayed(new d(this), 1000);
        }
        this.p.d();
        this.q.d();
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what != 1000) {
            return;
        }
        if (!ac.a()) {
            XLog.d("MDPlayer", "NoNetworkAvailable try after 1s");
            sendEmptyMessageDelayed(IHost.HOST_NOFITY_REFRESH_LIST, 1000);
        } else if (this.h != null) {
            surfaceCreated(this.h.getHolder());
        }
    }

    private void i() {
        if (this.e != null) {
            if (this.l == null) {
                this.l = new Thread(new a(this.e));
                this.l.start();
            }
            this.e = null;
        }
    }
}
