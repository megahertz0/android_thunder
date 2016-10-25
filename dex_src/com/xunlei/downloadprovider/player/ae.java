package com.xunlei.downloadprovider.player;

import android.os.Build.VERSION;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback2;
import com.xunlei.downloadprovider.player.wrapper.PlayerMessageState;
import com.xunlei.downloadprovider.player.wrapper.a;

// compiled from: ThunderMediaPlayer.java
final class ae implements Callback2 {
    final /* synthetic */ ab a;

    ae(ab abVar) {
        this.a = abVar;
    }

    public final void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        new StringBuilder("onSurfaceTextureAvailable--state=").append(this.a.f());
        if (this.a.e != null) {
            Object obj;
            this.a.e.a(surfaceHolder);
            a aVar = this.a.e;
            if (aVar.b.a.a() == MediaPlayerState.PAUSED || ((PlayerMessageState) aVar.a.get()) == PlayerMessageState.PAUSING) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj == null) {
                return;
            }
            if (!this.a.s) {
                this.a.c();
            } else if (VERSION.SDK_INT <= 19 || VERSION.SDK_INT > 22) {
                this.a.a(this.a.h());
            }
        }
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        new StringBuilder("onSurfaceTextureDestroyed--state=").append(this.a.f());
        if (this.a.e != null) {
            this.a.e.a(null);
        }
    }
}
