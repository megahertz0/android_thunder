package com.xunlei.downloadprovider.player.wrapper;

import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.t;
import java.util.Iterator;

// compiled from: PlayerStateManager.java
final class g implements Runnable {
    final /* synthetic */ MediaPlayerState a;
    final /* synthetic */ MediaPlayerState b;
    final /* synthetic */ f c;

    g(f fVar, MediaPlayerState mediaPlayerState, MediaPlayerState mediaPlayerState2) {
        this.c = fVar;
        this.a = mediaPlayerState;
        this.b = mediaPlayerState2;
    }

    public final void run() {
        new StringBuilder("onStateChange--oldState=").append(this.a).append("|newState=").append(this.b);
        Iterator it = this.c.b.iterator();
        while (it.hasNext()) {
            ((t) it.next()).a(this.a, this.b);
        }
    }
}
