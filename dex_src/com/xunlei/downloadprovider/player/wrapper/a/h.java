package com.xunlei.downloadprovider.player.wrapper.a;

import android.view.SurfaceHolder;
import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.wrapper.d;

// compiled from: SetDisplayMessage.java
public final class h extends c {
    private SurfaceHolder b;

    public h(d dVar, SurfaceHolder surfaceHolder) {
        super(dVar);
        this.b = surfaceHolder;
    }

    public final void a() {
        d dVar = this.a;
        SurfaceHolder surfaceHolder = this.b;
        new StringBuilder("setDisplay--state=").append(dVar.a.a()).append("|holder=").append(surfaceHolder);
        if (dVar.a.a() != MediaPlayerState.RELEASE) {
            try {
                dVar.b.setDisplay(surfaceHolder);
                dVar.d = surfaceHolder;
            } catch (IllegalArgumentException e) {
            }
        }
    }
}
