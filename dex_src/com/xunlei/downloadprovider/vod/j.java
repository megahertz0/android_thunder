package com.xunlei.downloadprovider.vod;

import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.mediaserver.MediaServer;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: VodManager.java
public final class j extends Thread {
    final /* synthetic */ h a;

    public j(h hVar) {
        this.a = hVar;
    }

    public final void run() {
        MediaServer.getInstance(BrothersApplication.a().getApplicationContext(), XZBDevice.Success, b.d()).UninitMediaServer();
    }
}
