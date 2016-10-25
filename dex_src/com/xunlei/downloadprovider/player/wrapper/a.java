package com.xunlei.downloadprovider.player.wrapper;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.SurfaceHolder;
import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.t;
import com.xunlei.downloadprovider.player.u;
import com.xunlei.downloadprovider.player.wrapper.a.e;
import com.xunlei.downloadprovider.player.wrapper.a.h;
import com.xunlei.downloadprovider.player.wrapper.a.i;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.android.spdy.SpdyAgent;

// compiled from: AsyncMediaPlayer.java
public final class a implements t, e {
    public final AtomicReference<PlayerMessageState> a;
    public d b;
    public Handler c;
    public AtomicInteger d;
    public AtomicInteger e;
    public e f;
    private HandlerThread g;
    private Handler h;
    private AtomicInteger i;

    public a() {
        this.a = new AtomicReference(PlayerMessageState.IDLE);
        this.h = new Handler(Looper.getMainLooper());
        this.d = new AtomicInteger(0);
        this.e = new AtomicInteger(0);
        this.i = new AtomicInteger(0);
        this.b = new d();
        this.b.c = this;
        this.b.a(this);
        this.g = new HandlerThread("media_player");
        this.g.start();
        this.c = new b(this, this.g.getLooper());
    }

    private void d() {
        if (this.c.hasMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            this.c.removeMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
    }

    public final void a() {
        b();
        this.a.set(PlayerMessageState.RESETTING);
        this.c.post(new e(this.b));
    }

    public final void a(SurfaceHolder surfaceHolder) {
        this.c.post(new h(this.b, surfaceHolder));
    }

    public final void b() {
        this.c.removeCallbacksAndMessages(null);
        this.a.set(PlayerMessageState.IDLE);
    }

    public final void a(t tVar) {
        this.b.a(tVar);
    }

    public final void b(t tVar) {
        f fVar = this.b.a;
        if (tVar != null) {
            fVar.b.remove(tVar);
        }
    }

    public final void a(float f, float f2) {
        this.c.post(new i(this.b, f, f2));
    }

    public final int c() {
        if (this.i.get() <= 0) {
            this.i.set(this.b.b());
        }
        return this.i.get();
    }

    public final void a(d dVar, int i) {
        this.e.set((c() * i) / 100);
        if (this.f != null) {
            this.f.a(dVar, i);
        }
    }

    public final void a(d dVar) {
        a(c());
        if (this.f != null) {
            this.f.a(dVar);
        }
    }

    public final boolean a(d dVar, int i, int i2) {
        return this.f != null ? this.f.a(dVar, i, i2) : true;
    }

    public final boolean b(d dVar, int i, int i2) {
        return this.f != null ? this.f.b(dVar, i, i2) : true;
    }

    public final void c(d dVar, int i, int i2) {
        if (this.f != null) {
            this.f.c(dVar, i, i2);
        }
    }

    public final void b(d dVar) {
        this.i.set(this.b.b());
        if (this.f != null) {
            this.f.b(dVar);
        }
    }

    public final void a(int i) {
        if (this.f != null) {
            c cVar = new c(this, i);
            if (u.a()) {
                cVar.run();
            } else {
                this.h.post(cVar);
            }
        }
    }

    public final void a(MediaPlayerState mediaPlayerState, MediaPlayerState mediaPlayerState2) {
        d();
        switch (AnonymousClass_1.a[mediaPlayerState2.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.i.set(0);
                this.e.set(0);
                this.d.set(0);
                this.a.set(PlayerMessageState.IDLE);
            case XZBDevice.DOWNLOAD_LIST_ALL:
                d();
                this.c.sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            default:
                break;
        }
    }
}
