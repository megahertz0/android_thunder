package com.xunlei.downloadprovider.vod;

import android.os.Message;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.service.g;
import com.xunlei.mediaserver.MediaServer;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: VodManager.java
final class i extends Thread {
    final /* synthetic */ g a;
    final /* synthetic */ h b;

    i(h hVar, g gVar) {
        this.b = hVar;
        this.a = gVar;
    }

    public final void run() {
        Object obj = null;
        if (MediaServer.getInstance(BrothersApplication.a().getApplicationContext(), XZBDevice.Success, b.d()) != null && MediaServer.getHttpListenPort() > 0) {
            obj = 1;
        }
        if (obj == null) {
            Message obtainMessage = this.a.a.obtainMessage();
            obtainMessage.what = 702;
            obtainMessage.sendToTarget();
            return;
        }
        this.a.b = MediaServer.getHttpListenPort();
        obtainMessage = this.a.a.obtainMessage();
        obtainMessage.what = 701;
        obtainMessage.obj = this.a;
        obtainMessage.sendToTarget();
    }
}
