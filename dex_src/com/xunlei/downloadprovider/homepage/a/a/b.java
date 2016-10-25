package com.xunlei.downloadprovider.homepage.a.a;

import com.xunlei.downloadprovider.b.c.a.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import java.util.Map;

// compiled from: VipContinueBox.java
public final class b implements a {
    final /* synthetic */ a a;

    public b(a aVar) {
        this.a = aVar;
    }

    public final void a(int i, Object obj, Map<String, List<String>> map) {
        new StringBuilder().append(getClass()).append("---showVipContinue---errCode//////////////////////---").append(i).append("---").append(Thread.currentThread().getId());
        if (i == 0) {
            this.a.setStatus(XZBDevice.DOWNLOAD_LIST_FAILED);
            if (this.a.mListener != null) {
                this.a.mListener.obtainMessage(110001, obj).sendToTarget();
                return;
            }
            return;
        }
        this.a.setStatus(XZBDevice.DOWNLOAD_LIST_ALL);
    }
}
