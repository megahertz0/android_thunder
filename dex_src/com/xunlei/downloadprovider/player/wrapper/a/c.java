package com.xunlei.downloadprovider.player.wrapper.a;

import com.xunlei.downloadprovider.player.wrapper.d;

// compiled from: PlayerMessage.java
public abstract class c implements Runnable {
    d a;

    public abstract void a();

    public c(d dVar) {
        this.a = dVar;
    }

    public void run() {
        a();
    }
}
