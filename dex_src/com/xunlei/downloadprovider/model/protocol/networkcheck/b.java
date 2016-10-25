package com.xunlei.downloadprovider.model.protocol.networkcheck;

import com.xunlei.downloadprovider.b.c;
import com.xunlei.downloadprovider.b.c.a.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import java.util.Map;

// compiled from: GetNetworkSpeedCheckTestUrlBox.java
public final class b implements a {
    final /* synthetic */ a a;

    public b(a aVar) {
        this.a = aVar;
    }

    public final void a(int i, Object obj, Map<String, List<String>> map) {
        this.a.setStatus(i == 0 ? XZBDevice.DOWNLOAD_LIST_FAILED : XZBDevice.DOWNLOAD_LIST_ALL);
        c cVar = new c();
        cVar.b = obj;
        cVar.a = this.a.getRunnerId();
        cVar.c = this.a.mUserData;
        if (this.a.mListener != null) {
            this.a.mListener.obtainMessage(10012, i, -1, cVar).sendToTarget();
        }
    }
}
