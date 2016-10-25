package com.baidu.mobads.production.a;

import com.baidu.mobads.j.m;
import com.umeng.a;

class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void run() {
        m.a().m().getBaiduMapsInfo(this.a.f);
        m.a().n().getCUID(this.a.f);
        m.a().n().getCell(this.a.f);
        m.a().n().getAppSDC();
        m.a().n().getWIFI(this.a.f);
        m.a().n().getGPS(this.a.f);
        m.a().n().getNetType(this.a.f);
        m.a().n().getAppSDC();
        m.a().n().getMem();
        m.a().n().getAndroidId(this.a.f);
        m.a().m().createRequestId(this.a.f, a.d);
    }
}
