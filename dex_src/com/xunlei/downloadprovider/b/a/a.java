package com.xunlei.downloadprovider.b.a;

import com.xunlei.downloadprovider.b.c.e;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: BpRunner.java
public class a implements d {
    e a;
    private com.xunlei.downloadprovider.b.a.d.a b;
    private e c;

    public a() {
        this.a = new e();
        this.a.a = 0;
    }

    public void start() {
        if (this.c != null) {
            new Thread(this.c).start();
            setStatus(1);
        }
    }

    public void stop() {
        if (this.c != null) {
            this.c.cancel();
            setStatus(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
    }

    public void pause() {
    }

    public void resume() {
    }

    public int getStatus() {
        return this.a.a;
    }

    public e getRunnerInfo() {
        return this.a;
    }

    public int getRunnerId() {
        return this.a.b;
    }

    public void setStatus(int i) {
        if (this.a.a != i) {
            this.a.a = i;
            if (this.b != null) {
                this.b.a(this.a.a, this);
            }
        }
    }

    public void setOnStatusChangedListener(com.xunlei.downloadprovider.b.a.d.a aVar) {
        this.b = aVar;
    }

    public void setBpFuture(e eVar) {
        this.c = eVar;
    }
}
