package com.xunlei.downloadprovider.download.tasklist.list.xzb;

import com.xunlei.downloadprovider.download.tasklist.list.xzb.e.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiCallback;
import com.xunlei.xllib.b.d;
import java.util.Iterator;

// compiled from: XiaZaiBaoUtil.java
public final class i extends XZBShouleiCallback {
    final /* synthetic */ e a;

    public i(e eVar) {
        this.a = eVar;
    }

    public final void cb_UpdateDeviceList(int i, int i2, XZBDevice[] xZBDeviceArr, String str, Object obj) {
        if (i == 0) {
            if (!d.a(this.a.a)) {
                Iterator it = this.a.a.iterator();
                while (it.hasNext()) {
                    ((a) it.next()).a();
                }
            }
            super.cb_UpdateDeviceList(i, i2, xZBDeviceArr, str, obj);
        }
    }
}
