package com.xunlei.downloadprovider.util;

import com.xunlei.downloadprovider.b.c.g;
import com.xunlei.downloadprovider.b.c.g.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import java.util.Map;
import java.util.Timer;

// compiled from: NetworkSpeedCheck.java
final class l implements a {
    final /* synthetic */ k a;

    l(k kVar) {
        this.a = kVar;
    }

    public final void a(int i, Map<String, List<String>> map, g gVar) {
        if (200 != i) {
            if (this.a.k != null) {
                this.a.k.obtainMessage(1).sendToTarget();
            }
            gVar.cancel();
            return;
        }
        if (map != null) {
            List list;
            for (String str : map.keySet()) {
                if (str != null) {
                    new StringBuilder("[").append(str).append("]:").append(map.get(str));
                    if (str.equalsIgnoreCase("content-length")) {
                        list = (List) map.get(str);
                        break;
                    }
                }
            }
            list = null;
            if (list == null) {
                if (this.a.k != null) {
                    this.a.k.obtainMessage(1).sendToTarget();
                }
                gVar.cancel();
                return;
            }
            this.a.i = (long) Integer.parseInt((String) list.get(0));
            if (!this.a.l) {
                this.a.l = true;
                this.a.k.obtainMessage(XZBDevice.DOWNLOAD_LIST_RECYCLE).sendToTarget();
                this.a.e = System.currentTimeMillis();
                this.a.m = new Timer();
                this.a.m.schedule(new m(this), 0, 1000);
            }
        }
        new StringBuilder("file length is = ").append(this.a.i);
    }
}
